package SinglePlotUI;

import javax.swing.*;

public class SinglePlotWindow extends JFrame {
    public SinglePlotWindow(SinglePanel singlePanel){
        setSize(1000,1000);
        // Add panel containing charts.
        getContentPane().add(singlePanel);

        //This should be the last two statements so everything is visible and you can close the window.

        setVisible(true);
        //This should be the last two statements so everything is visible and you can close the window.
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
    }
}
