package com.mycompany.gephidegreeplugin;

import org.gephi.statistics.spi.Statistics;
import org.gephi.statistics.spi.StatisticsBuilder;
import org.openide.util.lookup.ServiceProvider;

@ServiceProvider(service = StatisticsBuilder.class)
public class EigenvectorCentralizationBuilder implements StatisticsBuilder {

    @Override
    public String getName() {
        return "Eigenvector Centralization";
    }

    @Override
    public Statistics getStatistics() {
        return new EigenvectorCentralization();
    }

    @Override
    public Class<? extends Statistics> getStatisticsClass() {
        return EigenvectorCentralization.class;
    }
}