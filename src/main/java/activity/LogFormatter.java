package activity;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
public final class LogFormatter {
    private static final String hours = "{H}";
    private static final String minutes = "{M}";
    private static final String seconds = "{S}";
    private static final String day = "{D}";
    private static final String month = "{MO}";
    private static final String year = "{Y}";
    private static final String threadId = "{TH}";
    private static final String logParam = "{LP}";
    private static final String logType = "{LT}";
    private static final String reporterName = "{RN}";

    private String format;

    private String iterateFormat(String log, final String reporter, final LogType type) {
        final LocalDateTime dateTime = LocalDateTime.now();

        log = format.replace(logParam, log);
        log = log.replace(reporterName, reporter);
        log = log.replace(logType, type.name());
        log = log.replace(threadId, String.valueOf(Thread.currentThread().getName()));
        log = log.replace(hours, String.valueOf(dateTime.getHour()));
        log = log.replace(minutes, String.valueOf(dateTime.getMinute()));
        log = log.replace(seconds, String.valueOf(dateTime.getSecond()));
        log = log.replace(day, String.valueOf(dateTime.getDayOfMonth()));
        log = log.replace(month, String.valueOf(dateTime.getMonthValue()));
        log = log.replace(year, String.valueOf(dateTime.getYear()));

        return log;
    }

    public String formatLog(final String log, final String reporterName, final LogType type) {
        return iterateFormat(log, reporterName, type);
    }
}
