import java.io.BufferedReader;
import java.io.FileReader;

public class Tournament {
    public void loadResultsFromFile(String filePath) {
        try(BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length !=5) {
                    System.err.println("testing number of fields error" + line);
                    continue;
                }
                System.out.println(line);
            }
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}
