import java.util.*;

public class Participant {
    private final String name;
    private final String id;
    private final List<RaceResult> results = new ArrayList<>();


    public Participant(String name, String id) {
        this.name = name;
        this.id = id;
    }

    public void addRaceResult(RaceResult result) {
        results.add(result);
    }

    public List<RaceResult> getResults() {
        return results;
    }
    public String getName() {
        return name;
    }
    public String getId() {
        return id;
    }
    public boolean hasCompletedAllRaces() {
        Set<String> requiredRaces = new HashSet<>(Arrays.asList("1000m", "eggRace", "sackRace"));
        Set<String> completedRaces = new HashSet<>();
        for (RaceResult result : results) {
            completedRaces.add(result.getRaceType());
        }
        return completedRaces.containsAll(requiredRaces);
    }

}
