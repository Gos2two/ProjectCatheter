package GridPlotUI;

import MainUI.MainPanel;

import javax.swing.*;

public class GridPlotWindow extends JFrame {
    public GridPlotWindow(GridPanel gridPanel){
        setSize(1000,1000);

        // Add panel containing charts.
        getContentPane().add(gridPanel);

        //This should be the last two statements so everything is visible and you can close the window.
        setVisible(true);
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
    }
}
