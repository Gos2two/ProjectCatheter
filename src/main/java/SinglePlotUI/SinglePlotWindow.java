package SinglePlotUI;

import DataHandling.ElectrodeDB;
import javax.swing.*;

public class SinglePlotWindow extends JFrame {
    public SinglePlotWindow(ElectrodeDB electrodeDB){
        setSize(1000,1000);
        //Create tabbedPane with two tabs: one for unipolar and one for bipolar recordings.
        JTabbedPane tabbedPane = new JTabbedPane();
        tabbedPane.addTab("Unipolar", null, new SinglePanel(electrodeDB), "Unipolar electrode recordings");
        tabbedPane.addTab("Bipolar",null, new SingleBiPanel(electrodeDB), "Bipolar electrode recordings");
        // Add panel containing charts.
        getContentPane().add(tabbedPane);
        //This should be the last two statements so everything is visible and you can close the window.

        setVisible(true);
        //This should be the last two statements so everything is visible and you can close the window.
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
    }
}
