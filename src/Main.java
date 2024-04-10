public class Main {
    public static void main(String[] args) {

        RaceResult raceResult = new RaceResult("10:10:10", "15:15:15", "1000m");
        System.out.println("Total time " + raceResult.getDuration() + " seconds " + raceResult.getRaceType());
    }
}