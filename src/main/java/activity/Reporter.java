package activity;

import lombok.Data;

import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

@Data
public final class Reporter {
    private static final String DEFAULT_FORMAT = "[{D}/{MO}/{Y}]-[{H}:{M}:{S}]-[{TH}]-[{LT}]-[{RN}] : {LP}";

    private final Map<String, Reporter> childReporters;
    private String reporterName;
    private Boolean showLogsInSTDOut;
    private Boolean storeLogs;
    private final List<String> logs;
    private final LogFormatter formatter;

    public Reporter(final String reporterName) {
        this.reporterName = reporterName;
        this.childReporters = new HashMap<>();
        this.logs = new LinkedList<>();
        this.showLogsInSTDOut = Boolean.TRUE;
        this.storeLogs = Boolean.FALSE;
        this.formatter = new LogFormatter(DEFAULT_FORMAT);
    }

    public Reporter(final String reporterName, final Boolean showLogsInSTDOut) {
        this.reporterName = reporterName;
        this.childReporters = new HashMap<>();
        this.logs = new LinkedList<>();
        this.showLogsInSTDOut = showLogsInSTDOut;
        this.storeLogs = Boolean.FALSE;
        this.formatter = new LogFormatter(DEFAULT_FORMAT);
    }

    public Reporter(final String reporterName, final Boolean showLogsInSTDOut, final Boolean storeLogs) {
        this.reporterName = reporterName;
        this.childReporters = new HashMap<>();
        this.logs = new LinkedList<>();
        this.showLogsInSTDOut = showLogsInSTDOut;
        this.storeLogs = storeLogs;
        this.formatter = new LogFormatter(DEFAULT_FORMAT);
    }

    public Reporter(final String reporterName, final Boolean showLogsInSTDOut,
                    final Boolean storeLogs, final String format) {
        this.reporterName = reporterName;
        this.childReporters = new HashMap<>();
        this.logs = new LinkedList<>();
        this.showLogsInSTDOut = showLogsInSTDOut;
        this.storeLogs = storeLogs;
        this.formatter = new LogFormatter(format);
    }

    public Reporter getChild(final String reporterName) {
        if (childReporters.containsKey(reporterName)) {
            return childReporters.get(reporterName);
        }

        final Reporter reporter = new Reporter(reporterName, showLogsInSTDOut, storeLogs, formatter.getFormat());

        childReporters.put(reporterName, reporter);

        return reporter;
    }

    public Collection<Reporter> getAllChildren() {
        return childReporters.values();
    }

    public void reportInfo(final String log, final Object... object) {
        reportInfo(String.format(log, object));
    }

    public void reportInfo(final String log) {
        final String formattedLog = formatter.formatLog(log, reporterName, LogType.INFO);

        if (showLogsInSTDOut) {
            System.out.println(formattedLog);
        }

        if (storeLogs) {
            logs.add(formattedLog);
        }
    }

    public void reportWarn(final String log, final Object... object) {
        reportWarn(String.format(log, object));
    }

    public void reportWarn(final String log) {
        final String formattedLog = formatter.formatLog(log, reporterName, LogType.WARN);

        if (showLogsInSTDOut) {
            System.out.println(formattedLog);
        }

        if (storeLogs) {
            logs.add(formattedLog);
        }
    }

    public void reportDebug(final String log, final Object... object) {
        reportDebug(String.format(log, object));
    }

    public void reportDebug(final String log) {
        final String formattedLog = formatter.formatLog(log, reporterName, LogType.DEBUG);

        if (showLogsInSTDOut) {
            System.out.println(formattedLog);
        }

        if (storeLogs) {
            logs.add(formattedLog);
        }
    }

    public void reportError(final String log, final Object... object) {
        reportError(String.format(log, object));
    }

    public void reportError(final String log) {
        final String formattedLog = formatter.formatLog(log, reporterName, LogType.ERROR);

        if (showLogsInSTDOut) {
            System.out.println(formattedLog);
        }

        if (storeLogs) {
            logs.add(formattedLog);
        }
    }

    public void setFormat(final String format) {
        if(format == null) {
            formatter.setFormat(format);
        }
    }
}
