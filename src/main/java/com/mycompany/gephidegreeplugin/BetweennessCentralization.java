package com.mycompany.gephidegreeplugin;

import org.gephi.graph.api.Column;
import org.gephi.graph.api.Graph;
import org.gephi.graph.api.GraphModel;
import org.gephi.graph.api.Node;
import org.gephi.graph.api.Table;
import org.gephi.statistics.plugin.GraphDistance;
import org.gephi.statistics.spi.Statistics;


public class BetweennessCentralization implements Statistics {

    private double graphCentralization;

    @Override
    public void execute(GraphModel graphModel) {

        Graph graph = graphModel.getGraph();

        GraphDistance distance = new GraphDistance();
        distance.setDirected(graphModel.isDirected());
        distance.execute(graphModel);

        int n = graph.getNodeCount();

        Table nodeTable = graphModel.getNodeTable();

        Column betweenCol = nodeTable.getColumn(GraphDistance.BETWEENNESS);

        Column myCol = nodeTable.getColumn("betweenness_centrality");

        if (myCol == null) {
            myCol = nodeTable.addColumn("betweenness_centrality", Double.class);
        }

        double maxBetweenness = 0;

        for (Node node : graph.getNodes()) {

            Double value = (Double) node.getAttribute(betweenCol);

            if (value == null) {
                value = 0.0;
            }

            node.setAttribute(myCol, value);

            if (value > maxBetweenness) {
                maxBetweenness = value;
            }
        }

        double numerator = 0;

        for (Node node : graph.getNodes()) {

            Double value = (Double) node.getAttribute(betweenCol);

            if (value == null) {
                value = 0.0;
            }

            numerator += (maxBetweenness - value);
        }

        double denominator;

        if (n > 2) {
            denominator = (n - 1.0) * (n - 1.0) * (n - 2.0);
        } else {
            denominator = 1;
        }

        graphCentralization = numerator / denominator;
    }

    @Override
    public String getReport() {
        return "<html><body>"
                + "<h1>Betweenness Centralization</h1>"
                + "<p>Graph Betweenness Centralization: "
                + graphCentralization
                + "</p>"
                + "</body></html>";
    }
}

