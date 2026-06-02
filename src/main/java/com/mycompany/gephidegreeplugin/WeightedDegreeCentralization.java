package com.mycompany.gephidegreeplugin;

import org.gephi.graph.api.Column;
import org.gephi.graph.api.Edge;
import org.gephi.graph.api.Graph;
import org.gephi.graph.api.GraphModel;
import org.gephi.graph.api.Node;
import org.gephi.graph.api.Table;
import org.gephi.statistics.spi.Statistics;

public class WeightedDegreeCentralization implements Statistics {

    private double graphCentralization;

    @Override
    public void execute(GraphModel graphModel) {
        Graph graph = graphModel.getGraph();
        int n = graph.getNodeCount();

        Table nodeTable = graphModel.getNodeTable();
        Column col = nodeTable.getColumn("weighted_degree_centrality");

        if (col == null) {
            col = nodeTable.addColumn("weighted_degree_centrality", Double.class);
        }

        double maxWeightedDegree = 0;

        for (Node node : graph.getNodes()) {
            double weightedDegree = 0;

            for (Edge edge : graph.getEdges(node)) {
                double weight = edge.getWeight();

                if (weight <= 0) {
                    weight = 1.0;
                }

                weightedDegree += weight;
            }

            node.setAttribute(col, weightedDegree);

            if (weightedDegree > maxWeightedDegree) {
                maxWeightedDegree = weightedDegree;
            }
        }

        double numerator = 0;

        for (Node node : graph.getNodes()) {
            Double weightedDegree = (Double) node.getAttribute(col);

            if (weightedDegree == null) {
                weightedDegree = 0.0;
            }

            numerator += maxWeightedDegree - weightedDegree;
        }

        double denominator = maxWeightedDegree * (n - 1);

        graphCentralization = denominator > 0 ? numerator / denominator : 0;
    }

    @Override
    public String getReport() {
        return "<html><body>"
                + "<h1>Weighted Degree Centralization</h1>"
                + "<p>Graph Weighted Degree Centralization: "
                + graphCentralization
                + "</p>"
                + "<p>This measure uses edge weights when available. If an edge has no usable weight, it counts as 1.</p>"
                + "</body></html>";
    }
}