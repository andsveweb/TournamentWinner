import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class ErrorManager {
    private final List<String> errors = new ArrayList<>();

    public void addError(String error, int lineNumber) {
        errors.add("Line " + lineNumber + ": " + error);
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

    public boolean validateLineFormat(String line, int expectedFields, int lineNumber) {
        String[] parts = line.split(",");
        if (parts.length != expectedFields) {
            addError("Error in number of fields. Expected " + expectedFields + ", having " + parts.length + ": " + line, lineNumber);
            return false;
        }
        return true;
    }

    public boolean validateName(String name, String line, int lineNumber) {
        if(!name.matches("[a-zA-Z\\s]+")) {
            addError("Invalid name detected - " + line , lineNumber);
            return false;
        }
        return true;
    }

    public boolean validateId(String id, String line, int lineNumber) {
        if(!id.matches("\\d+")) {
            addError("Invalide ID detected in line: " + line +" ", lineNumber);
            return false;
        }
        return true;
    }

    public boolean validateTime(String time, String line, int lineNumber) {
        if (!time.matches("\\d{2}:\\d{2}:\\d{2}")) {
            addError("Invalid time format detected in line: " + line + " Time should be in HH:MM:SS", lineNumber);
            return true;
        }
        return false;
    }

    public boolean validateTimeInterval(String startTime, String endTime, String line, int lineNumber) {
        LocalTime start = LocalTime.parse(startTime);
        LocalTime end = LocalTime.parse(endTime);
        if (start.isAfter(end)) {
            addError("Negative time interval. Start time is later than end time" + " - " + line, lineNumber);
            return false;
        }
        return true;
    }

    public boolean validateRaceType(String raceType, String line, int lineNumber) {
        List<String> validateType = Arrays.asList("eggRace", "1000m", "sackRace");
        if (!validateType.contains(raceType)) {
            addError("Invalid race type detected in line: " + line + " Race type must be one of eggRace, 1000m, sackRace", lineNumber);
            return false;
        }
        return true;
    }

    public boolean checkForIdConsistency(String id, String name, Map<String, String> participantRegistry, int lineNumber) {
        if (participantRegistry.containsKey(id) && !participantRegistry.get(id).equals(name)) {
            addError("Data error. Different names with same id " + participantRegistry.get(id) + " and " + name + " have the same id " + id, lineNumber);
            return false;
        }
        return true;
    }
}
