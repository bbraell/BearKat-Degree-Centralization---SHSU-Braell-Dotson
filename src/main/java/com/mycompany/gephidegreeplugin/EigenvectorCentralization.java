package com.mycompany.gephidegreeplugin;

import org.gephi.graph.api.Column;
import org.gephi.graph.api.Graph;
import org.gephi.graph.api.GraphModel;
import org.gephi.graph.api.Node;
import org.gephi.graph.api.Table;
import org.gephi.statistics.plugin.EigenvectorCentrality;
import org.gephi.statistics.spi.Statistics;

public class EigenvectorCentralization implements Statistics {

    private double graphCentralization;

    @Override
    public void execute(GraphModel graphModel) {
        Graph graph = graphModel.getGraph();

        EigenvectorCentrality eigenvector = new EigenvectorCentrality();
        eigenvector.execute(graphModel);

        int n = graph.getNodeCount();

        Table nodeTable = graphModel.getNodeTable();

        Column eigenCol = nodeTable.getColumn(EigenvectorCentrality.EIGENVECTOR);

        Column myCol = nodeTable.getColumn("eigenvector_centrality");

        if (myCol == null) {
            myCol = nodeTable.addColumn("eigenvector_centrality", Double.class);
        }

        double maxEigenvector = 0;

        for (Node node : graph.getNodes()) {
            Double value = (Double) node.getAttribute(eigenCol);

            if (value == null) {
                value = 0.0;
            }

            node.setAttribute(myCol, value);

            if (value > maxEigenvector) {
                maxEigenvector = value;
            }
        }

        double numerator = 0;

        for (Node node : graph.getNodes()) {
            Double value = (Double) node.getAttribute(eigenCol);

            if (value == null) {
                value = 0.0;
            }

            numerator += maxEigenvector - value;
        }

        double denominator = n - 1.0;

        graphCentralization = denominator > 0 ? numerator / denominator : 0;
    }

    @Override
    public String getReport() {
        return "<html><body>"
                + "<h1>Eigenvector Centralization</h1>"
                + "<p>Graph Eigenvector Centralization: "
                + graphCentralization
                + "</p>"
                + "</body></html>";
    }
}