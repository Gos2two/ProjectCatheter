package GridPlotUI;

import DataHandling.ElectrodeDB;
import MainUI.MainPanel;

import javax.swing.*;

public class GridPlotWindow extends JFrame {
    public GridPlotWindow(ElectrodeDB electrodeDB){
        setSize(1000,1000);

        // Add panel containing charts.
        getContentPane().add(new GridPanel(electrodeDB));

        //This should be the last two statements so everything is visible and you can close the window.
        setVisible(true);
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
    }
}
