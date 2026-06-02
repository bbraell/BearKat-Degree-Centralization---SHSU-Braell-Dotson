package com.mycompany.gephidegreeplugin;

import org.gephi.graph.api.Column;
import org.gephi.graph.api.DirectedGraph;
import org.gephi.graph.api.GraphModel;
import org.gephi.graph.api.Node;
import org.gephi.graph.api.Table;
import org.gephi.statistics.spi.Statistics;

public class OutDegreeCentralization implements Statistics {

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
        Column col = nodeTable.getColumn("out_degree_centrality");

        if (col == null) {
            col = nodeTable.addColumn("out_degree_centrality", Double.class);
        }

        int maxOutDegree = 0;

        for (Node node : graph.getNodes()) {
            int outDegree = graph.getOutDegree(node);
            maxOutDegree = Math.max(maxOutDegree, outDegree);
            node.setAttribute(col, (double) outDegree);
        }

        double numerator = 0;

        for (Node node : graph.getNodes()) {
            int outDegree = graph.getOutDegree(node);
            numerator += maxOutDegree - outDegree;
        }

        double denominator = (n - 1) * (n - 1);

        graphCentralization = denominator > 0 ? numerator / denominator : 0;
    }

    @Override
    public String getReport() {
        if (!directed) {
            return "<html><body>"
                    + "<h1>Out-Degree Centralization</h1>"
                    + "<p>This statistic is only meaningful for directed graphs.</p>"
                    + "</body></html>";
        }

        return "<html><body>"
                + "<h1>Out-Degree Centralization</h1>"
                + "<p>Graph Out-Degree Centralization: " + graphCentralization + "</p>"
                + "</body></html>";
    }
}