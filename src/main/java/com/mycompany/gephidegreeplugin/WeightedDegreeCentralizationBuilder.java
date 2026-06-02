package com.mycompany.gephidegreeplugin;

import org.gephi.statistics.spi.Statistics;
import org.gephi.statistics.spi.StatisticsBuilder;
import org.openide.util.lookup.ServiceProvider;

@ServiceProvider(service = StatisticsBuilder.class)
public class WeightedDegreeCentralizationBuilder implements StatisticsBuilder {

    @Override
    public String getName() {
        return "Weighted Degree Centralization";
    }

    @Override
    public Statistics getStatistics() {
        return new WeightedDegreeCentralization();
    }

    @Override
    public Class<? extends Statistics> getStatisticsClass() {
        return WeightedDegreeCentralization.class;
    }
}