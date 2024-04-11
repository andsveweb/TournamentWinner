import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

public class Tournament {
    private final List<Participant> participants = new ArrayList<>();
    private final ErrorManager errorManager = new ErrorManager();
    public void loadResultsFromFile(String filePath) {
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                if (!errorManager.validateLineFormat(line, 5)) {
                    continue;
                }
                try {
                    String[] parts = line.split(",");
                    String name = parts[0].trim();
                    String id = parts[1].trim();
                    String startTime = parts[2].trim();
                    String endTime = parts[3].trim();
                    String raceType = parts[4].trim();

                    RaceResult result = new RaceResult(startTime, endTime, raceType);
                    Participant participant = findOrCreateParticipantBy(name, id);
                    participant.addRaceResult(result);

                } catch (Exception e) {
                    errorManager.addError("Error processing line: " + line + ". Error: " + e.getMessage());

                }
            }
        } catch (Exception e) {
            errorManager.addError("Error reading file " + e.getMessage());
        }
    }
    private Participant findOrCreateParticipantBy(String name, String id) {
        for (Participant participant : participants) {
            if (participant.getId().equals(id)) {
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
