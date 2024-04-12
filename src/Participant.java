/*
Andreas Svensson
svefastbygg@gmail.com
*/

import java.util.*;

public class Participant {
    private final String name;
    private final String id;
    private final List<RaceResult> results = new ArrayList<>();

    // This class represents a participant in the turnament
    public Participant(String name, String id) {
        this.name = name;
        this.id = id;
    }
    // Store result of races
    public void addRaceResult(RaceResult result) {
        results.add(result);
    }

    // gets all results for a participant
    public List<RaceResult> getResults() {
        return results;
    }

    public String getName() {
        return name;
    }
    public String getId() {
        return id;
    }

    // Checks if a participant have completed all required race
    public boolean hasCompletedAllRaces() {
        Set<String> requiredRaces = new HashSet<>(Arrays.asList("1000m", "eggRace", "sackRace"));
        Set<String> completedRaces = new HashSet<>();
        for (RaceResult result : results) {
            completedRaces.add(result.getRaceType());
        }
        return completedRaces.containsAll(requiredRaces);
    }

    // Calculate total time for all races
    public long getTotalTimeInSeconds() {
        if (!hasCompletedAllRaces()) {
            return Long.MAX_VALUE;
        }
        long totalTime = 0;
        for (RaceResult result : results) {
            totalTime += result.getDurationInSeconds();
        }
        return totalTime;
    }

}
