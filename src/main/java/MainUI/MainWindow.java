package MainUI;

import javax.swing.*;

public class MainWindow extends JFrame {
    public MainWindow(){
        //Set layout
        setSize(500,500);

        // Add panel containing different buttons.
        getContentPane().add(new MainPanel());

        //This should be the last two statements so everything is visible and you can close the application.
        setVisible(true);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }
}
