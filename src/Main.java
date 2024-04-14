/*
Andreas Svensson
svefastbygg@gmail.com
*/

public class Main {
    public static void main(String[] args) {

        // Start of the application. New instance of Tournament class.
        Tournament tournament = new Tournament();
        // Calling method loadResultsFromFile.
        tournament.loadResultsFromFile("src/resultsfiles/race-results.txt");
        // Prints the participant and there races where no error occurred.
        tournament.printParticipants();
        // Prints the errors in the textile.
        tournament.checkAndReportErrors();
        // Print the winner(s) with the lowes total time and print the average time in the three races.
        tournament.determineWinner();

    }
}