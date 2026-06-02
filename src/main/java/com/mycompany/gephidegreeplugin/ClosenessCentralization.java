package com.mycompany.gephidegreeplugin;

import org.gephi.graph.api.Column;
import org.gephi.graph.api.Graph;
import org.gephi.graph.api.GraphModel;
import org.gephi.graph.api.Node;
import org.gephi.graph.api.Table;
import org.gephi.statistics.plugin.GraphDistance;
import org.gephi.statistics.spi.Statistics;

public class ClosenessCentralization implements Statistics {

    private double graphCentralization;

    @Override
    public void execute(GraphModel graphModel) {

        Graph graph = graphModel.getGraph();

        GraphDistance distance = new GraphDistance();
        distance.setDirected(graphModel.isDirected());
        distance.execute(graphModel);

        int n = graph.getNodeCount();

        Table nodeTable = graphModel.getNodeTable();

        Column closenessCol = nodeTable.getColumn(GraphDistance.CLOSENESS);

        Column myCol = nodeTable.getColumn("closeness_centrality");

        if (myCol == null) {
            myCol = nodeTable.addColumn("closeness_centrality", Double.class);
        }

        double maxCloseness = 0;

        for (Node node : graph.getNodes()) {
            Double value = (Double) node.getAttribute(closenessCol);

            if (value == null) {
                value = 0.0;
            }

            node.setAttribute(myCol, value);

            if (value > maxCloseness) {
                maxCloseness = value;
            }
        }

        double numerator = 0;

        for (Node node : graph.getNodes()) {
            Double value = (Double) node.getAttribute(closenessCol);

            if (value == null) {
                value = 0.0;
            }

            numerator += maxCloseness - value;
        }

        double denominator = n - 2.0;

        graphCentralization = denominator > 0 ? numerator / denominator : 0;
    }

    @Override
    public String getReport() {
        return "<html><body>"
                + "<h1>Closeness Centralization</h1>"
                + "<p>Graph Closeness Centralization: "
                + graphCentralization
                + "</p>"
                + "</body></html>";
    }
}