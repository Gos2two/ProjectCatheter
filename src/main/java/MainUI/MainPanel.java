package MainUI;

import javax.swing.*;
import java.awt.*;

public class MainPanel extends JPanel {
    private JLabel title;
    private ButtonPanel buttonPanel;

    public MainPanel(){
        //Set Layout
        setLayout(new GridLayout(3,1));
        //Define
        title = new JLabel("CATHETER LAB",null,JLabel.CENTER);
        buttonPanel = new ButtonPanel();
        //Add
        add(title);
        add(buttonPanel);

    }


}
