/*
Andreas Svensson
svefastbygg@gmail.com
*/

public class Main {
    public static void main(String[] args) {

        // Start of the application. New instance of Tournament class. Calling method loadResultsFromFile.
        Tournament tournament = new Tournament();
        tournament.loadResultsFromFile("src/resultsfiles/race-results.txt");
        tournament.printParticipants();
        tournament.checkAndReportErrors();
        tournament.determineWinner();

    }
}