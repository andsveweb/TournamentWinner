import java.util.ArrayList;
import java.util.List;

public class Participant {
    private final String name;
    private final String id;
    private List<RaceResult> results = new ArrayList<>();


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







}
