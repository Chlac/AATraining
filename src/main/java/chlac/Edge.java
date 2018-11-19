package chlac;

public class Edge {

    /**
     * The start vertex of the edge
     * (order important in case of oriented graph).
     */
    private String start;
    /**
     * The end vertex of the edge
     * (order important in case of oriented graph).
     */
    private String end;
    /**
     * The capacity of this edge (max possible flow).
     */
    private int capacity;
    /**
     * The current flow on this edge.
     */
    private int flow;

    /**
     * Constructs a new Edge.
     * @param start The {@link #start} vertex of the edge
     *              (order important in case of oriented graph)
     * @param end The {@link #end} vertex of the edge
     *            (order important in case of oriented graph)
     * @param capacity The {@link #capacity} of this edge (max possible flow)
     * @param flow The current {@link #flow} on this edge
     */
    public Edge(final String start, final String end, final int capacity, final int flow) {
        this.start = start;
        this.end = end;
        this.capacity = capacity;
        this.flow = flow;

    }

    /**
     * @return the starting vertex of this edge
     */
    public final String getStart() {
        return start;
    }

    /**
     * @return the ending vertex of this edge
     */
    public final String getEnd() {
        return end;
    }

    public final int getCapacity() {
        return capacity;
    }

    public final int getFlow() {
        return flow;
    }


    public void setFlow(final int flow) {
        this.flow = flow;
    }

    @Override
    public String toString() {
        return start + " - " + flow + "(" + capacity + ") - " + end;
    }

    public int getResidualCapacity() {
        return capacity - flow;
    }
}
