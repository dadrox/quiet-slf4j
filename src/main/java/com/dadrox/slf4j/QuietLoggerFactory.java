package com.dadrox.slf4j;

import org.slf4j.ILoggerFactory;
import org.slf4j.Logger;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

public class QuietLoggerFactory implements ILoggerFactory {

    ConcurrentMap<String, Logger> loggers = new ConcurrentHashMap<String, Logger>();

    @Override
    public Logger getLogger(String name) {
        Logger logger = loggers.get(name);
        if (logger != null) {
            return logger;
        }
        Logger newLogger = new QuietLogger(name);
        Logger oldLogger = loggers.putIfAbsent(name, newLogger);
        return oldLogger == null ? newLogger : oldLogger;
    }

    void reset() {
        loggers.clear();
    }
}