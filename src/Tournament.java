import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Tournament {
    private final List<Participant> participants = new ArrayList<>();
    private final ErrorManager errorManager = new ErrorManager();
    private final Map<String, String> participantRegistry = new HashMap<>();

    // Reads the text file and validate number of fields.
    public void loadResultsFromFile(String filePath) {
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            int lineNumber = 0;
            while ((line = reader.readLine()) != null) {
                lineNumber++;
                if (!errorManager.validateLineFormat(line, 5, lineNumber)) {
                    continue;
                }

                String[] parts = line.split(",");
                String name = parts[0].trim().toLowerCase();
                String id = parts[1].trim();
                String startTime = parts[2].trim();
                String endTime = parts[3].trim();
                String raceType = parts[4].trim();

                // Error handling from ErrorManager class.
                if(!errorManager.validateName(name, line, lineNumber) ||
                        !errorManager.validateId(id, line, lineNumber) ||
                        errorManager.validateTime(startTime, line, lineNumber) ||
                        errorManager.validateTime(endTime, line, lineNumber) ||
                        !errorManager.validateRaceType(raceType, line, lineNumber) ||
                        !errorManager.validateTimeInterval(startTime, endTime, line, lineNumber) ||
                        !errorManager.checkForIdConsistency(id, name, participantRegistry, lineNumber)) {
                        continue;
                }
                participantRegistry.put(id, name);


                try {
                    RaceResult result = new RaceResult(parts[2].trim(), parts[3].trim(), parts[4].trim());
                    Participant participant = findOrCreateParticipantBy(name, id);
                    participant.addRaceResult(result);

                } catch (Exception e) {
                    errorManager.addError("Error processing line: " + line + ". Error: " + e.getMessage(), lineNumber);

                }
            }
        } catch (Exception e) {
            errorManager.addError("Error reading file " + e.getMessage(), -1);
        }
    }
    private Participant findOrCreateParticipantBy(String name, String id) {
        for (Participant participant : participants) {
            if (participant.getId().equals(id) && participant.getName().equals(name)) {
                return participant;
            }
        }
        Participant newParticipant = new Participant(name, id);
            participants.add(newParticipant);
            return newParticipant;

    }
    public void printParticipants() {
        System.out.println("\u001B[33mAll the participants and there races they competed in:\n\u001B[0m");
        for (Participant participant : participants) {
            System.out.println("Participant: " + participant.getName() + ", ID: " + participant.getId());
            for (RaceResult result : participant.getResults()) {
                System.out.println("    Race Type: " + result.getRaceType() + ", Duration: " + result.getDuration());

            }
        }
        System.out.println();
    }
    public void determineWinner() {
        List<Participant> winners = new ArrayList<>();
        long bestAvarageTime = Long.MAX_VALUE;

        for (Participant participant : participants) {
            if (participant.hasCompletedAllRaces()) {
                long totalTime = participant.getTotalTimeInSeconds();
                long averageTime = totalTime / participant.getResults().size();

                if (averageTime < bestAvarageTime) {
                    bestAvarageTime = averageTime;
                    winners.clear();
                    winners.add(participant);
                } else if (averageTime == bestAvarageTime) {
                    winners.add(participant);
                }
            }
        }
        if (!winners.isEmpty() && bestAvarageTime != Long.MAX_VALUE) {
            System.out.println("\u001B[32mWinner(s) with the lowest avarage time of " + formatSeconds(bestAvarageTime));
            for (Participant winner : winners) {
                System.out.println(winner.getName() + " ID " + winner.getId() + " ");
            }
            System.out.println("\u001B[0m");
        } else {
            System.out.println("No winner could be determed");
        }
    }
    private String formatSeconds(long seconds) {
        long hours = seconds / 3600;
        long minutes = (seconds % 3600) / 60;
        long secs = seconds % 60;
        return String.format("%02d:%02d:%02d", hours, minutes, secs);
    }

    public void checkAndReportErrors() {
        errorManager.printErrors();
    }
}
