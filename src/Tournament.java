import java.io.BufferedReader;
import java.io.FileReader;
import java.security.PublicKey;
import java.util.ArrayList;
import java.util.List;

public class Tournament {
    private final List<Participant> participants = new ArrayList<>();
    private final ErrorManager errorManager = new ErrorManager();
    public void loadResultsFromFile(String filePath) {
        try(BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length !=5) {
                    errorManager.addError("Error in number of fields" + line);
                    continue;
                }

                String name = parts[0].trim();
                String id = parts[1].trim();
                String startTime = parts[2].trim();
                String endTime = parts[3].trim();
                String raceType = parts[4].trim();

                RaceResult result = new RaceResult(startTime, endTime, raceType);
                System.out.println("Creating result " + name + "race " + raceType);

                Participant participant = findParticipantById(id);
                if (participant == null) {
                    participant = new Participant(name, id);
                    participants.add(participant);
                }
                participant.addRaceResult(result);

            }
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());

        }
    }
    private Participant findParticipantById(String id) {
        for (Participant participant : participants) {
            if (participant.getId().equals(id)) {
                return participant;
            }
        }
        return null;
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
