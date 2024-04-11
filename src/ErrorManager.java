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

    public boolean validateName(String name, String line) {
        if(!name.matches("[a-zA-Z\\s]+")) {
            addError("Invalid name detected in line: " + line + " Name should only contain letters and spaces.");
            return false;
        }
        return true;
    }

    public boolean validateId(String id, String line) {
        if(!id.matches("\\d+")) {
            addError("Invalide ID detected in line: " + line + " ID should only contain numbers");
            return false;
        }
        return true;
    }
}
