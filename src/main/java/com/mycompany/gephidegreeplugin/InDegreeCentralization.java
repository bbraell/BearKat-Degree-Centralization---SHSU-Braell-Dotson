package com.mycompany.gephidegreeplugin;

import org.gephi.graph.api.Column;
import org.gephi.graph.api.DirectedGraph;
import org.gephi.graph.api.GraphModel;
import org.gephi.graph.api.Node;
import org.gephi.graph.api.Table;
import org.gephi.statistics.spi.Statistics;

public class InDegreeCentralization implements Statistics {

    private double graphCentralization;
    private boolean directed;

    @Override
    public void execute(GraphModel graphModel) {
        directed = graphModel.isDirected();

        if (!directed) {
            graphCentralization = 0;
            return;
        }

        DirectedGraph graph = graphModel.getDirectedGraph();
        int n = graph.getNodeCount();

        Table nodeTable = graphModel.getNodeTable();
        Column col = nodeTable.getColumn("in_degree_centrality");

        if (col == null) {
            col = nodeTable.addColumn("in_degree_centrality", Double.class);
        }

        int maxInDegree = 0;

        for (Node node : graph.getNodes()) {
            int inDegree = graph.getInDegree(node);
            maxInDegree = Math.max(maxInDegree, inDegree);
            node.setAttribute(col, (double) inDegree);
        }

        double numerator = 0;

        for (Node node : graph.getNodes()) {
            int inDegree = graph.getInDegree(node);
            numerator += maxInDegree - inDegree;
        }

        double denominator = (n - 1) * (n - 1);

        graphCentralization = denominator > 0 ? numerator / denominator : 0;
    }

    @Override
    public String getReport() {
        if (!directed) {
            return "<html><body>"
                    + "<h1>In-Degree Centralization</h1>"
                    + "<p>This statistic is only meaningful for directed graphs.</p>"
                    + "</body></html>";
        }

        return "<html><body>"
                + "<h1>In-Degree Centralization</h1>"
                + "<p>Graph In-Degree Centralization: " + graphCentralization + "</p>"
                + "</body></html>";
    }
}