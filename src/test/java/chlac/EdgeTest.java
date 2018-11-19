package chlac;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class EdgeTest {

    private Edge e;

    @Before
    public void setUp() throws Exception {
        e = new Edge("s", "e", 10, 5);
    }

    @Test
    public void getStart() {
        assertEquals("s", e.getStart());
    }

    @Test
    public void getEnd() {
        assertEquals("e", e.getEnd());
    }

    @Test
    public void getCapacity() {
        assertEquals(10, e.getCapacity());
    }

    @Test
    public void getFlow() {
        assertEquals(5, e.getFlow());
    }

    @Test
    public void setFlow() {
        e.setFlow(2);
        assertEquals(2, e.getFlow());
    }

    @Test
    public void toStringTest() {
        assertEquals("s - 5(10) - e", e.toString());
    }

    @Test
    public void getResidualCapacity() {
        assertEquals(5, e.getResidualCapacity());
    }
}