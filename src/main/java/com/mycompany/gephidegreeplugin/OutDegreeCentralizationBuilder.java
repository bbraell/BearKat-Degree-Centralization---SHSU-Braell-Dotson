package com.mycompany.gephidegreeplugin;

import org.gephi.statistics.spi.Statistics;
import org.gephi.statistics.spi.StatisticsBuilder;
import org.openide.util.lookup.ServiceProvider;

@ServiceProvider(service = StatisticsBuilder.class)
public class OutDegreeCentralizationBuilder implements StatisticsBuilder {

    @Override
    public String getName() {
        return "Out-Degree Centralization";
    }

    @Override
    public Statistics getStatistics() {
        return new OutDegreeCentralization();
    }

    @Override
    public Class<? extends Statistics> getStatisticsClass() {
        return OutDegreeCentralization.class;
    }
}