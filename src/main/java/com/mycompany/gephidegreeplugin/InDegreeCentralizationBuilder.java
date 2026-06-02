package com.mycompany.gephidegreeplugin;

import org.gephi.statistics.spi.Statistics;
import org.gephi.statistics.spi.StatisticsBuilder;
import org.openide.util.lookup.ServiceProvider;

@ServiceProvider(service = StatisticsBuilder.class)
public class InDegreeCentralizationBuilder implements StatisticsBuilder {

    @Override
    public String getName() {
        return "In-Degree Centralization";
    }

    @Override
    public Statistics getStatistics() {
        return new InDegreeCentralization();
    }

    @Override
    public Class<? extends Statistics> getStatisticsClass() {
        return InDegreeCentralization.class;
    }
}