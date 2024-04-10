import java.time.LocalTime;
import java.time.temporal.ChronoUnit;

public class RaceResult {
    private final LocalTime startTime;
    private final LocalTime endTime;
    private final String raceType;

    public RaceResult(String startTime, String endTime, String raceType) {
        this.startTime = LocalTime.parse(startTime);
        this.endTime = LocalTime.parse(endTime);
        this.raceType = raceType;
    }

    public long getDuration() {
        return ChronoUnit.SECONDS.between(startTime, endTime);
    }

    public String getRaceType() {
        return raceType;
    }
}
