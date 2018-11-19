package chlac;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class FordFulkersonTest {

    FordFulkerson ff;
    FlowNetwork fn;

    @Before
    public void setUp() throws Exception {
        ff = new FordFulkerson();
        fn = new FlowNetwork("1 5\n" + "" +
                "1 2 1\n" +
                "3 2 2\n" +
                "4 2 3\n" +
                "2 5 5");
    }

    @Test
    public void findMaximumFlow() {
        assertEquals(ff.findMaximumFlow(fn).toString(), "Source = 1; Target = 5; Edges : {1=[1 - 1(1) - 2], 2=[2 - 1(5) - 5], 3=[3 - 0(2) - 2], 4=[4 - 0(3) - 2]}");
    }
}