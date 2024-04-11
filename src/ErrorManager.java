import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

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

    public boolean validateTime(String time, String line) {
        if (!time.matches("\\d{2}:\\d{2}:\\d{2}")) {
            addError("Invalid time format detected in line: " + line + " Time should be in HH:MM:SS");
            return true;
        }
        return false;
    }

    public boolean validateRaceType(String raceType, String line) {
        List<String> validateType = Arrays.asList("eggRace", "1000m", "sackRace");
        if (!validateType.contains(raceType)) {
            addError("Invalid race type detected in line: " + line + " Race type must be one of eggRace, 1000m, sackRace");
            return false;
        }
        return true;
    }

    public boolean checkForIdConsistency(String id, String name, Map<String, String> participantRegistry) {
        if (participantRegistry.containsKey(id) && !participantRegistry.get(id).equals(name)) {
            addError("Data error. Different names with same id " + participantRegistry.get(id) + " and " + name + " have the same id " + id);
            return false;
        }
        return true;
    }
}
