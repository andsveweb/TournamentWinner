/*
Andreas Svensson
svefastbygg@gmail.com
*/

import java.time.Duration;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;

// Represent a single race for a participant
public class RaceResult {
    private final LocalTime startTime;
    private final LocalTime endTime;
    private final String raceType;

    public RaceResult(String startTime, String endTime, String raceType) {
        this.startTime = LocalTime.parse(startTime);
        this.endTime = LocalTime.parse(endTime);
        this.raceType = raceType;
    }
    // get duration of a race between start and end time in HH:MM:SS
    public String getDuration() {
        long seconds = ChronoUnit.SECONDS.between(startTime, endTime);
        Duration duration = Duration.ofSeconds(seconds);

        long hours = duration.toHours();
        int minutes = duration.toMinutesPart();
        int sec = duration.toSecondsPart();

        return String.format("%02d:%02d:%02d", hours, minutes, sec);
    }

    // Get duration in seconds
    public long getDurationInSeconds() {
        return ChronoUnit.SECONDS.between(startTime, endTime);
    }

    public String getRaceType() {
        return raceType;
    }
}
