public class Main {
    public static void main(String[] args) {

        Tournament tournament = new Tournament();
        tournament.loadResultsFromFile("src/race-results.txt");
    }
}