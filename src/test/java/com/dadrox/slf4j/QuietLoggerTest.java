package com.dadrox.slf4j;

import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import static com.dadrox.slf4j.Level.*;
import static org.junit.Assert.*;

public class QuietLoggerTest {

    private Logger unit() {
        return new QuietLogger("name");
    }

    @Before
    public void resetSystemProperty() {
        System.setProperty(QuietLogger.SystemProperyName, "");
    }

    private void expect(Level level, String from) {
        assertEquals(level, fromString(from));
    }

    @Test
    public void default_log_level_is_off() {
        expect(Off, null);
        expect(Off, "");
        expect(Off, "wrong");
        expect(Off, "infoo");
    }

    @Test
    public void log_level_is_read_from_String_correctly() {
        expect(All, "a");
        expect(All, "A");
        expect(All, "all");
        expect(Trace, "t");
        expect(Trace, "tr");
        expect(Trace, "TrAc");
    }

    private void set(Level level) {
        System.setProperty(QuietLogger.SystemProperyName, level.name());
    }

    @Test
    public void all_enforced() throws Exception {
        set(All);
        assertTrue(unit().isTraceEnabled());
        assertTrue(unit().isDebugEnabled());
        assertTrue(unit().isInfoEnabled());
        assertTrue(unit().isWarnEnabled());
        assertTrue(unit().isErrorEnabled());
    }

    @Test
    public void trace_enforced() throws Exception {
        set(Trace);
        assertTrue(unit().isTraceEnabled());
        assertTrue(unit().isDebugEnabled());
        assertTrue(unit().isInfoEnabled());
        assertTrue(unit().isWarnEnabled());
        assertTrue(unit().isErrorEnabled());
    }

    @Test
    public void debug_enforced() throws Exception {
        set(Debug);
        assertFalse(unit().isTraceEnabled());
        assertTrue(unit().isDebugEnabled());
        assertTrue(unit().isInfoEnabled());
        assertTrue(unit().isWarnEnabled());
        assertTrue(unit().isErrorEnabled());
    }

    @Test
    public void info_enforced() throws Exception {
        set(Info);
        assertFalse(unit().isTraceEnabled());
        assertFalse(unit().isDebugEnabled());
        assertTrue(unit().isInfoEnabled());
        assertTrue(unit().isWarnEnabled());
        assertTrue(unit().isErrorEnabled());
    }

    @Test
    public void warn_enforced() throws Exception {
        set(Warn);
        assertFalse(unit().isTraceEnabled());
        assertFalse(unit().isDebugEnabled());
        assertFalse(unit().isInfoEnabled());
        assertTrue(unit().isWarnEnabled());
        assertTrue(unit().isErrorEnabled());
    }

    @Test
    public void error_enforced() throws Exception {
        set(Error);
        assertFalse(unit().isTraceEnabled());
        assertFalse(unit().isDebugEnabled());
        assertFalse(unit().isInfoEnabled());
        assertFalse(unit().isWarnEnabled());
        assertTrue(unit().isErrorEnabled());
    }

    @Test
    public void off_enforced() throws Exception {
        set(Off);
        assertFalse(unit().isTraceEnabled());
        assertFalse(unit().isDebugEnabled());
        assertFalse(unit().isInfoEnabled());
        assertFalse(unit().isWarnEnabled());
        assertFalse(unit().isErrorEnabled());
    }
}
