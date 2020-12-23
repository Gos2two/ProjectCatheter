package SinglePlotUI;

import javax.swing.*;

public class SinglePlotWindow extends JFrame {
    public SinglePlotWindow(){
        setSize(500,500);
        setVisible(true);
        //This should be the last two statements so everything is visible and you can close the window.
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
    }
}
