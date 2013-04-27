package com.dadrox.slf4j;

import org.slf4j.helpers.MarkerIgnoringBase;
import org.slf4j.helpers.MessageFormatter;

import java.text.SimpleDateFormat;
import java.util.Date;

enum Level {
    All("ALL  "),
    Trace("TRACE"),
    Debug("DEBUG"),
    Info("INFO "),
    Warn("WARN "),
    Error("ERROR"),
    Off("OFF  "), ;

    private String formattedName;

    private Level(String formattedName) {
        this.formattedName = formattedName;
    }

    public String formatted() {
        return formattedName;
    }

    public static Level fromString(String value) {
        if (value == null) return Off;
        else if (value.trim().equals("")) return Off;
        for (Level level : Level.values()) {
            if (level.name().toLowerCase().startsWith(value.toLowerCase())) return level;
        }
        return Off;
    }
}

public class QuietLogger extends MarkerIgnoringBase {

    public static final String SystemProperyName = "log.level";

    private Level level = systemProperty();
    private final String name;

    public QuietLogger(String name) {
        this.name = name;
    }

    private Level systemProperty() {
        return Level.fromString(System.getProperty(SystemProperyName));
    }

    private boolean isEnabled(Level level) {
        return level.ordinal() >= this.level.ordinal();
    }

    private void log(Level level, String message, Throwable t) {
        if (!isEnabled(level)) return;

        SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss.SSS");
        String msg = dateFormat.format(new Date()) + " " + level.formatted() + " [" + shortLoggerName(name) + "] " + message;
        System.out.println(msg);
        if (t != null) t.printStackTrace();
    }

    private String shortLoggerName(String name) {
        String[] tokens = name.split("\\.");
        int length = tokens.length;
        StringBuilder sb = new StringBuilder();
        for (String token : tokens) {
            length--;
            if (length == 0) sb.append(token);
            else sb.append(token.charAt(0) + ".");
        }
        return sb.toString();
    }

    private void log(Level level, String message) {
        log(level, message, null);
    }

    @Override
    public void debug(String msg) {
        log(Level.Debug, msg);
    }

    @Override
    public void debug(String format, Object arg1) {
        log(Level.Debug, MessageFormatter.format(format, arg1).getMessage());
    }

    @Override
    public void debug(String format, Object... arg1) {
        log(Level.Debug, MessageFormatter.arrayFormat(format, arg1).getMessage());
    }

    @Override
    public void debug(String msg, Throwable t) {
        log(Level.Debug, msg, t);
    }

    @Override
    public void debug(String format, Object arg1, Object arg2) {
        log(Level.Debug, MessageFormatter.format(format, arg1, arg2).getMessage());
    }

    @Override
    public void error(String msg) {
        log(Level.Error, msg);
    }

    @Override
    public void error(String format, Object arg1) {
        log(Level.Error, MessageFormatter.format(format, arg1).getMessage());
    }

    @Override
    public void error(String format, Object... arg1) {
        log(Level.Error, MessageFormatter.arrayFormat(format, arg1).getMessage());
    }

    @Override
    public void error(String msg, Throwable t) {
        log(Level.Error, msg, t);
    }

    @Override
    public void error(String format, Object arg1, Object arg2) {
        log(Level.Error, MessageFormatter.format(format, arg1, arg2).getMessage());
    }

    @Override
    public void info(String msg) {
        log(Level.Info, msg);
    }

    @Override
    public void info(String format, Object arg1) {
        log(Level.Info, MessageFormatter.format(format, arg1).getMessage());
    }

    @Override
    public void info(String format, Object... arg1) {
        log(Level.Info, MessageFormatter.arrayFormat(format, arg1).getMessage());
    }

    @Override
    public void info(String msg, Throwable t) {
        log(Level.Info, msg, t);
    }

    @Override
    public void info(String format, Object arg1, Object arg2) {
        log(Level.Info, MessageFormatter.format(format, arg1, arg2).getMessage());
    }

    @Override
    public boolean isDebugEnabled() {
        return isEnabled(Level.Debug);
    }

    @Override
    public boolean isErrorEnabled() {
        return isEnabled(Level.Error);
    }

    @Override
    public boolean isInfoEnabled() {
        return isEnabled(Level.Info);
    }

    @Override
    public boolean isTraceEnabled() {
        return isEnabled(Level.Trace);
    }

    @Override
    public boolean isWarnEnabled() {
        return isEnabled(Level.Warn);
    }

    @Override
    public void trace(String msg) {
        log(Level.Trace, msg);
    }

    @Override
    public void trace(String format, Object arg1) {
        log(Level.Trace, MessageFormatter.format(format, arg1).getMessage());
    }

    @Override
    public void trace(String format, Object... arg1) {
        log(Level.Trace, MessageFormatter.arrayFormat(format, arg1).getMessage());
    }

    @Override
    public void trace(String msg, Throwable t) {
        log(Level.Trace, msg, t);
    }

    @Override
    public void trace(String format, Object arg1, Object arg2) {
        log(Level.Trace, MessageFormatter.format(format, arg1, arg2).getMessage());
    }

    @Override
    public void warn(String msg) {
        log(Level.Warn, msg);
    }

    @Override
    public void warn(String format, Object arg1) {
        log(Level.Warn, MessageFormatter.format(format, arg1).getMessage());
    }

    @Override
    public void warn(String format, Object... arg1) {
        log(Level.Warn, MessageFormatter.arrayFormat(format, arg1).getMessage());
    }

    @Override
    public void warn(String msg, Throwable t) {
        log(Level.Warn, msg, t);
    }

    @Override
    public void warn(String format, Object arg1, Object arg2) {
        log(Level.Warn, MessageFormatter.format(format, arg1, arg2).getMessage());
    }
}
