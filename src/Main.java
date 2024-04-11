public class Main {
    public static void main(String[] args) {

        // Start of the application. New instance of Tournament class. Calling method loadResultsFromFile.
        Tournament tournament = new Tournament();
        tournament.loadResultsFromFile("src/resultsfiles/race-results.txt");
        tournament.printParticipants();
        tournament.checkAndReportErrors();
        Participant participant = new Participant("Andreas", "123");
        participant.addRaceResult(new RaceResult("10:00:00", "10:30:00", "1000m"));
        participant.addRaceResult(new RaceResult("11:00:00", "11:30:00", "eggRace"));
        participant.addRaceResult(new RaceResult("12:00:00", "12:30:00", "sackRace"));

        System.out.println("Has completed all races: " + participant.hasCompletedAllRaces());

    }
}