import java.util.ArrayList;
import java.util.List;

public class ErrorManager {
    private final List<String> errors = new ArrayList<>();

    public void addError(String error) {
        errors.add(error);
    }

    public boolean hasErrors() {
        return !errors.isEmpty();
    }

    public void printErrors() {
        if (!hasErrors()) {
            System.out.println("No errors detected");
            return;
        }
        System.err.println("The following error was encountered");
        for (String error : errors) {
            System.err.println(error);
        }
    }

    public boolean validateLineFormat(String line, int expectedFields) {
        String[] parts = line.split(",");
        if (parts.length != expectedFields) {
            addError("Error in number of fields. Expected " + expectedFields + ", " + parts.length + ": " + line);
            return false;
        }
        return true;
    }
}
