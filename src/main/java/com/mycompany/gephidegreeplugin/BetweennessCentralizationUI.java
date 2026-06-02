package com.mycompany.gephidegreeplugin;

import javax.swing.JPanel;
import org.gephi.statistics.spi.Statistics;
import org.gephi.statistics.spi.StatisticsUI;
import org.openide.util.lookup.ServiceProvider;

@ServiceProvider(service = StatisticsUI.class)
public class BetweennessCentralizationUI implements StatisticsUI {

    @Override
    public JPanel getSettingsPanel() {
        return null;
    }

    @Override
    public void setup(Statistics statistics) {
    }

    @Override
    public void unsetup() {
    }

    @Override
    public Class<? extends Statistics> getStatisticsClass() {
        return BetweennessCentralization.class;
    }

    @Override
    public String getValue() {
        return "Done";
    }

    @Override
    public String getDisplayName() {
        return "Betweenness Centralization";
    }

    @Override
    public String getShortDescription() {
        return "Measures graph centralization using betweenness scores.";
    }

    @Override
    public String getCategory() {
        return StatisticsUI.CATEGORY_NETWORK_OVERVIEW;
    }

    @Override
    public int getPosition() {
        return 1003;
    }
}