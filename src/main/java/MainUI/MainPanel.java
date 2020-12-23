package MainUI;

import javax.swing.*;

public class MainPanel extends JPanel {
    ButtonPanel buttonPanel;
    public MainPanel(){
        //Define
        buttonPanel=new ButtonPanel();

        //Add
        add(buttonPanel);
    }
}
