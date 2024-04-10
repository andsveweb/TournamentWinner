import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

public class Tournament {
    private final List<Participant> participants = new ArrayList<>();
    public void loadResultsFromFile(String filePath) {
        try(BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length !=5) {
                    System.err.println("testing number of fields error" + line);
                    continue;
                }
                //System.out.println(line);
                String name = parts[0].trim();
                String id = parts[1].trim();

                Participant participant = new Participant(name, id);
                participants.add(participant);
            }
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    public void printParticipants() {
        for (Participant participant: participants) {
            System.out.println("Participant " + participant.getName() + " id" + participant.getId());
        }
    }
}
