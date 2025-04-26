package ge.tsu.gameencyclopedia.util;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

public class TimeFormatter {

    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM dd, yyyy HH:mm");

    public static String prettyFormat(LocalDateTime time) {
        if (time == null) {
            return "";
        }

        LocalDateTime now = LocalDateTime.now();
        long minutes = ChronoUnit.MINUTES.between(time, now);

        if (minutes < 1) {
            return "just now";
        } else if (minutes < 60) {
            return minutes + " minute" + (minutes > 1 ? "s" : "") + " ago";
        } else {
            long hours = ChronoUnit.HOURS.between(time, now);
            if (hours < 24) {
                return hours + " hour" + (hours > 1 ? "s" : "") + " ago";
            } else {
                long days = ChronoUnit.DAYS.between(time, now);
                if (days < 7) {
                    return days + " day" + (days > 1 ? "s" : "") + " ago";
                } else {
                    return time.format(formatter);
                }
            }
        }
    }
}