package chlac;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class FlowNetworkTest {

    FlowNetwork fn;

    @Before
    public void setUp() throws Exception {
        fn = new FlowNetwork("1 5\n" + "" +
                "1 2 1\n" +
                "3 2 2\n" +
                "4 2 3\n" +
                "2 5 5");
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void testConstructor() {
        assertTrue(fn != null);
        Edge e = new Edge("6", "3", 10, 0);
        fn = new FlowNetwork.FlowNetworkBuilder()
                .source("s")
                .target("t")
                .addEdge(e)
                .addEdge(new Edge("6", "4", 8, 0))
                .build();
        assertEquals(e, fn.findEdge(e.getStart(), e.getEnd()));

    }

    @Test
    public void copy() {
        FlowNetwork c = fn.copy();
        assertEquals(fn.toString(), c.toString());

        fn = new FlowNetwork("1 5\n" + "" +
                "1 2 1\n" +
                "3 2 2\n" +
                "4 2 3\n" +
                "2 5 5");
        c = fn.copy();
        assertEquals(fn.toString(), c.toString());
    }

    @Test
    public void getResidualNetwork() {
        assertEquals(fn.getResidualNetwork().toString(), "Source = 1; Target = 5; Edges : {1=[1 - 1(1) - 2], 2=[2 - 5(5) - 5], 3=[3 - 2(2) - 2], 4=[4 - 3(3) - 2]}");
    }

    @Test
    public void addEdge() {
        Edge e = new Edge("6", "3", 10, 0);
        fn.addEdge(e);
        assertEquals(e, fn.findEdge(e.getStart(), e.getEnd()));
    }

    @Test
    public void DFS() {
    }

    @Test
    public void getSource() {
    }

    @Test
    public void getTarget() {
    }

    @Test
    public void findEdge() {
        assertEquals(fn.findEdge("6", "2"), null);
        assertEquals(fn.findEdge("1", "6"), null);
        assertEquals(fn.findEdge("1", "2").toString(), "1 - 0(1) - 2");
    }

    @Test
    public void getAdjacency() {
        assertNotEquals(fn.getAdjacency(), null);
    }

    @Test
    public void toStringTest() {
        assertEquals("Source = 1; Target = 5; Edges : {1=[1 - 0(1) - 2], 2=[2 - 0(5) - 5], 3=[3 - 0(2) - 2], 4=[4 - 0(3) - 2]}", fn.toString());
        fn = new FlowNetwork.FlowNetworkBuilder().build();
        assertEquals("Source = null; Target = null; Edges : {}", fn.toString());
    }


}