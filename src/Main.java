public class Main {
    public static void main(String[] args) {

        Tournament tournament = new Tournament();
        tournament.loadResultsFromFile("src/resultsfiles/race-results.txt");
        tournament.printParticipants();
        Participant participant = new Participant("TestPerson", "123");
        participant.addRaceResult(new RaceResult("00:10:00", "10:10:10", "1000m"));
        participant.addRaceResult(new RaceResult("00:20:00", "20:10:10", "eggRace"));

        System.out.println("Result : " + participant.getName());
        for (RaceResult result: participant.getResults()) {
            System.out.println(result.getRaceType() + " Duration " + result.getDuration() + "seconds" );
        }
    }
}