package ee.neotech.persistence.entities;

import java.sql.Timestamp;
import java.time.LocalDateTime;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MomentTest {

    private Moment moment;

    @BeforeEach
    void setUp() {
        moment = new Moment();
    }

    @Test
    void idTest() {
        moment.setId(123);
        final int id = moment.getId();
        assertEquals(123, id);
    }

    @Test
    void timeValueTest() {
        moment.setTimeValue(Timestamp.valueOf(LocalDateTime.of(2020, 1, 20, 11,33)));
        final Timestamp timeValue = moment.getTimeValue();
        assertEquals(Timestamp.valueOf(LocalDateTime.of(2020, 1, 20, 11,33)), timeValue);
    }

    @Test
    void equalsTest() {
        moment.setId(123);
        moment.setTimeValue(Timestamp.valueOf(LocalDateTime.of(2020, 1, 20, 11,33)));
        Moment momentActual = new Moment();
        momentActual.setId(123);
        momentActual.setTimeValue(Timestamp.valueOf(LocalDateTime.of(2020, 1, 20, 11,33)));
        assertEquals(moment, momentActual);
        assertEquals(moment.hashCode(), momentActual.hashCode());
    }

    @Test
    void notEqualsTest() {
        moment.setId(123);
        moment.setTimeValue(Timestamp.valueOf(LocalDateTime.of(2020, 1, 20, 11,33)));
        Moment momentActual = new Moment();
        momentActual.setId(124);
        momentActual.setTimeValue(Timestamp.valueOf(LocalDateTime.of(2021, 1, 20, 11,33)));
        assertNotEquals(moment, momentActual);
        assertNotEquals(moment.hashCode(), momentActual.hashCode());
    }

}