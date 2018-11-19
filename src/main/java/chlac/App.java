package chlac;

/**
 * Hello world!
 *
 */
public final class App {

    /**
     * Private, not to be instantiated.
     */
    private App() {
        // Throw an exception if this ever *is* called
        throw new AssertionError("Instantiating utility class.");
    }

    /**
     * Main method.
     */
    public static void main(final String[] args) {
        FlowNetwork fn = new FlowNetwork("1 5\n"
                + ""
                + "1 2 1\n"
                + "3 2 2\n"
                + "4 2 3\n"
                + "2 5 5");
        FordFulkerson ff = new FordFulkerson();
        System.out.println(ff.findMaximumFlow(fn));
    }
}
