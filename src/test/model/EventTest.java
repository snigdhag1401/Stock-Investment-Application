package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Calendar;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

public class EventTest {
    private Event e;
    private Date d;

//NOTE: these tests might fail if time at which line (2) below is executed
//is different from time that line (1) is executed. Lines (1) and (2) must
//run in same millisecond for this test to make sense and pass.

    @BeforeEach
    public void runBefore() {
        e = new Event("Added stock Apple to list!"); // (1)
        d = Calendar.getInstance().getTime(); // (2)
    }

    @Test
    public void testEvent() {
        assertEquals("Added stock Apple to list!", e.getDescription());
        assertEquals(d, e.getDate());
    }

    @Test
    public void testToString() {
        assertEquals(d.toString() + "\n" + "Added stock Apple to list!", e.toString());
    }

    @Test
    public void testEventEquals() {
        Event testE = e;
        assertTrue(e.equals(testE));
        assertEquals(e.hashCode(), testE.hashCode());
    }

    @Test
    public void testEventNotEqualsWithNull() {
        Event testE = null;
        Date testD = Calendar.getInstance().getTime();
        assertFalse(e.equals(testE));
        assertNotEquals(e.hashCode(), testE.hashCode());
    }

    @Test
    public void testEventNotEqualsWithDiffClass() {
        StockList testStockList = new StockList("Added stock Apple to list!");
        Date testD = Calendar.getInstance().getTime();
        assertFalse(e.equals(testStockList));
    }
}



