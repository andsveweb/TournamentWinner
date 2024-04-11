public class Main {
    public static void main(String[] args) {

        Tournament tournament = new Tournament();
        tournament.loadResultsFromFile("src/resultsfiles/race-results.txt");
        tournament.printParticipants();
        tournament.checkAndReportErrors();

    }
}