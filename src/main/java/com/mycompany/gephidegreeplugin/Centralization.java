package com.mycompany.gephidegreeplugin;

import org.gephi.graph.api.Column;
import org.gephi.graph.api.Graph;
import org.gephi.graph.api.GraphModel;
import org.gephi.graph.api.Node;
import org.gephi.graph.api.Table;
import org.gephi.statistics.spi.Statistics;

public class Centralization implements Statistics {

    private double graphCentralization;

    public void execute(GraphModel graphModel) {
        Graph graph = graphModel.getUndirectedGraph();

        int n = graph.getNodeCount();

        Table nodeTable = graphModel.getNodeTable();
        Column col = nodeTable.getColumn("degree_centrality");

        if (col == null) {
            col = nodeTable.addColumn("degree_centrality", Double.class);
        }

        int maxDegree = 0;

        for (Node node : graph.getNodes()) {
            int degree = graph.getDegree(node);

            if (degree > maxDegree) {
                maxDegree = degree;
            }

            node.setAttribute(col, (double) degree);
        }

        double numerator = 0;

        for (Node node : graph.getNodes()) {
            int degree = graph.getDegree(node);
            numerator += maxDegree - degree;
        }

        double denominator = (n - 1) * (n - 2);

        if (denominator > 0) {
            graphCentralization = numerator / denominator;
        } else {
            graphCentralization = 0;
        }
    }

    @Override
    public String getReport() {
        return "<html><body>"
                + "<h1>Centralization Report</h1>"
                + "<p>Graph Centralization: " + graphCentralization + "</p>"
                + "</body></html>";
    }
}