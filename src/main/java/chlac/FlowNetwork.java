package chlac;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

public class FlowNetwork {

    private String source;
    private String target;
    private HashMap<String, ArrayList<Edge>> adjacency = new HashMap<>();

    public FlowNetwork(final FlowNetworkBuilder b) {
        this.source = b.source;
        this.target = b.target;
        this.adjacency = b.adjacency;
    }

    public FlowNetwork(String s) {

        String[] lines = s.split("[\\r\\n]+");

        String[] init = lines[0].split(" ");
        source = init[0]; target = init[1];

        for (int i = 1; i < lines.length; i++) {

            String[] edge = lines[i].split(" ");
            addEdge(new Edge(edge[0], edge[1], Integer.parseInt(edge[2]), 0));

        }

    }

    public FlowNetwork copy() {

        FlowNetwork c = new FlowNetworkBuilder().source(source).target(target).build();

            for (Map.Entry<String, ArrayList<Edge>> entry : adjacency.entrySet()) {
                for (Edge e : entry.getValue()) {
                    c.addEdge(new Edge(e.getStart(), e.getEnd(), e.getCapacity(), 0));
                }
            }



        return c;
    }


    public FlowNetwork getResidualNetwork() {

        FlowNetwork rn = new FlowNetworkBuilder().source(source).target(target).build();

        int flow;
        for (String v : adjacency.keySet()) {
            for (Edge e : adjacency.get(v)) {
                flow = e.getCapacity() - e.getFlow();
                if (flow > 0) {
                    rn.addEdge(new Edge(e.getStart(), e.getEnd(), e.getCapacity(), flow));
                }
                if (e.getFlow() > 0) {
                    rn.addEdge(new Edge(e.getEnd(), e.getStart(), e.getCapacity(), e.getFlow()));
                }
            }
        }


        return rn;
    }

    public void addEdge(final Edge e) {
        if (adjacency.get(e.getStart()) == null) {
            adjacency.put(e.getStart(), new ArrayList<Edge>());
        }
        adjacency.get(e.getStart()).add(e);
        System.out.println("Added edge : " + e);
    }

    public Stack<String> depthFirstSearch(final String s, final String t) {

        ArrayList<String> marked = new ArrayList<>();
        Stack<String> stack = new Stack<>();
        stack.push(s);

        boolean deadEnd;

        while (stack.size() != 0) {

            String v = stack.peek();
            System.out.println("Visiting : " + v);

            if (v.equals(t)) {
                return stack;
            }

            deadEnd = true;
            if (adjacency.get(v) != null) {
                for (Edge e : adjacency.get(v)) {
                    if (!marked.contains(e.getEnd())) {
                        stack.push(e.getEnd());
                        marked.add(e.getEnd());
                        deadEnd = false;
                    }
                }
            }



            if (deadEnd) {
                stack.pop();
            }

        }

        return null;


    }

    public final String getSource() {
        return source;
    }

    public final String getTarget() {
        return target;
    }

    public final HashMap<String, ArrayList<Edge>> getAdjacency() {
        return adjacency;
    }

    public Edge findEdge(final String s, final String n) {
        if (adjacency.get(s) != null) {
            for (Edge e : adjacency.get(s)) {
                if (e.getEnd().equals(n)) {
                    return e;
                }
            }
        }
            return null;
    }

    @Override
    public String toString() {
        return "Source = " + source + "; Target = " + target + "; Edges : " + adjacency.toString();
    }

    public static class FlowNetworkBuilder {

        private String source;
        private String target;
        private HashMap<String, ArrayList<Edge>> adjacency = new HashMap<>();

        public FlowNetworkBuilder() { }

        public final FlowNetworkBuilder source(final String s) {
            this.source = s;
            return this;
        }

        public final FlowNetworkBuilder target(final String t) {
            this.target = t;
            return this;
        }

        public final FlowNetworkBuilder addEdge(final Edge e) {
            if (this.adjacency.get(e.getStart()) == null) {
                this.adjacency.put(e.getStart(), new ArrayList<Edge>());
            }
            this.adjacency.get(e.getStart()).add(e);
            return this;
        }

        public final FlowNetwork build() {
            return new FlowNetwork(this);
        }



    }
}
