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
        for (Participant participant : participants) {
            System.out.println("Participant: " + participant.getName() + ", ID: " + participant.getId());
            for (RaceResult result : participant.getResults()) {
                System.out.println("    Race Type: " + result.getRaceType() + ", Duration: " + result.getDuration() + " seconds");
            }
        }
    }

    public void checkAndReportErrors() {
        errorManager.printErrors();
    }
}
