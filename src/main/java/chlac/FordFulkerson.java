package chlac;

import java.util.ArrayList;
import java.util.Stack;

public class FordFulkerson {

    public FlowNetwork findMaximumFlow(FlowNetwork fn) {

        FlowNetwork max = fn.copy();
        FlowNetwork rn = max.getResidualNetwork();

        System.out.println("Residual network\n" + rn);

        int flow = (int) Double.POSITIVE_INFINITY;
        Stack<String> augmentingPath;
        while((augmentingPath = rn.depthFirstSearch(rn.getSource(), rn.getTarget())) != null) {

            System.out.println("Augmenting path : " + augmentingPath);

            String end = augmentingPath.pop();

            String start;
            Edge edge;
            ArrayList<Edge> residualCapacityPath = new ArrayList<>();
            ArrayList<Edge> residualFlowPath = new ArrayList<>();

            while(!augmentingPath.empty()) {

                start = augmentingPath.pop();
                edge = max.findEdge(start, end);

                if(edge != null) {
                    residualCapacityPath.add(edge);
                    flow = Math.min(flow, edge.getResidualCapacity());
                } else {
                    edge = max.findEdge(end, start);
                    residualFlowPath.add(edge);
                    flow = Math.min(flow, edge.getFlow());
                }

                end = start;

            }


            for(Edge e : residualCapacityPath)
                e.setFlow(e.getFlow() + flow);

            for(Edge e : residualFlowPath)
                e.setFlow(e.getFlow() - flow);


            rn = max.getResidualNetwork();
        }

        return max;

    }



}
