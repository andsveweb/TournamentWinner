/*
Andreas Svensson
svefastbygg@gmail.com
*/

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

// Manages error and validation in the application
public class ErrorManager {
    // Stores the error in a list
    private final List<String> errors = new ArrayList<>();

    // Adds error message to the list of errors where the error is.
    public void addError(String error, int lineNumber) {
        errors.add("Line " + lineNumber + ": " + error);
    }

    public boolean hasErrors() {
        return !errors.isEmpty();
    }

    // Print the errors
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

    // Validates the format of a line by checking if the number of fields matches the expected number.
    public boolean validateLineFormat(String line, int expectedFields, int lineNumber) {
        String[] parts = line.split(",");
        if (parts.length != expectedFields) {
            addError("Error in number of fields. Expected " + expectedFields + ", having " + parts.length + ": " + line, lineNumber);
            return false;
        }
        return true;
    }

    // Validate that names only contains letter and spaces.
    public boolean validateName(String name, String line, int lineNumber) {
        if(!name.matches("[a-zA-Z\\s]+")) {
            addError("Invalid name detected - " + line , lineNumber);
            return false;
        }
        return true;
    }

    // Validate id that it contains only numbers
    public boolean validateId(String id, String line, int lineNumber) {
        if(!id.matches("\\d+")) {
            addError("Invalide ID detected in line: " + line +" ", lineNumber);
            return false;
        }
        return true;
    }

    // Validate time string correct format HH:MM:SS
    public boolean validateTime(String time, String line, int lineNumber) {
        if (!time.matches("\\d{2}:\\d{2}:\\d{2}")) {
            addError("Invalid time format detected in line: " + line + " Time should be in HH:MM:SS", lineNumber);
            return true;
        }
        return false;
    }

    // Validate that start time is not after end time
    public boolean validateTimeInterval(String startTime, String endTime, String line, int lineNumber) {
        LocalTime start = LocalTime.parse(startTime);
        LocalTime end = LocalTime.parse(endTime);
        if (start.isAfter(end)) {
            addError("Negative time interval. Start time is later than end time" + " - " + line, lineNumber);
            return false;
        }
        return true;
    }

    // Validate so that the race type is one of the three
    public boolean validateRaceType(String raceType, String line, int lineNumber) {
        List<String> validateType = Arrays.asList("eggRace", "1000m", "sackRace");
        if (!validateType.contains(raceType)) {
            addError("Invalid race type detected in line: " + line + " Race type must be one of eggRace, 1000m, sackRace", lineNumber);
            return false;
        }
        return true;
    }

    // Check that the participant not have the same id.
    public boolean checkForIdConsistency(String id, String name, Map<String, String> participantRegistry, int lineNumber) {
        if (participantRegistry.containsKey(id) && !participantRegistry.get(id).equals(name)) {
            addError("Data error. Different names with same id " + participantRegistry.get(id) + " and " + name + " have the same id " + id, lineNumber);
            return false;
        }
        return true;
    }
}
