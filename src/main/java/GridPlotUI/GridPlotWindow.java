package GridPlotUI;

import javax.swing.*;

public class GridPlotWindow extends JFrame {
    public GridPlotWindow(){
        setSize(500,500);

        //This should be the last two statements so everything is visible and you can close the window.
        setVisible(true);
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
    }
}
