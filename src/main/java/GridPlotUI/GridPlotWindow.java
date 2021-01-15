package GridPlotUI;

import DataHandling.ElectrodeDB;
import javax.swing.*;

public class GridPlotWindow extends JFrame {
    public GridPlotWindow(ElectrodeDB electrodeDB){
        setSize(1000,1000);

        //Create tabbedPane with two tabs: one for unipolar and one for bipolar recordings.
        JTabbedPane tabbedPane = new JTabbedPane();
        tabbedPane.addTab("Unipolar", null, new GridPanel(electrodeDB), "Unipolar electrode recordings");
        tabbedPane.addTab("Bipolar",null, new GridBiPanel(electrodeDB), "Bipolar electrode recordings");
        // Add panel containing charts.
        getContentPane().add(tabbedPane);

        //This should be the last two statements so everything is visible and you can close the window.
        setVisible(true);
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
    }
}
