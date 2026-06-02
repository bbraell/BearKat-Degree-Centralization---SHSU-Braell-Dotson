package com.mycompany.gephidegreeplugin;

import org.gephi.statistics.spi.Statistics;
import org.gephi.statistics.spi.StatisticsBuilder;
import org.openide.util.lookup.ServiceProvider;

@ServiceProvider(service = StatisticsBuilder.class)
public class ClosenessCentralizationBuilder implements StatisticsBuilder {

    @Override
    public String getName() {
        return "Closeness Centralization";
    }

    @Override
    public Statistics getStatistics() {
        return new ClosenessCentralization();
    }

    @Override
    public Class<? extends Statistics> getStatisticsClass() {
        return ClosenessCentralization.class;
    }
}