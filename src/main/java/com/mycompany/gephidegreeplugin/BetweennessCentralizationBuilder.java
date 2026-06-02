package com.mycompany.gephidegreeplugin;

import org.gephi.statistics.spi.Statistics;
import org.gephi.statistics.spi.StatisticsBuilder;
import org.openide.util.lookup.ServiceProvider;

@ServiceProvider(service = StatisticsBuilder.class)
public class BetweennessCentralizationBuilder implements StatisticsBuilder {

    @Override
    public String getName() {
        return "Betweenness Centralization";
    }

    @Override
    public Statistics getStatistics() {
        return new BetweennessCentralization();
    }

    @Override
    public Class<? extends Statistics> getStatisticsClass() {
        return BetweennessCentralization.class;
    }
}