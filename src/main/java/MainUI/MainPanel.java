package MainUI;

import javax.swing.*;
import java.awt.*;

public class MainPanel extends JPanel {

    public MainPanel(){
        //Set Layout
        setLayout(new GridLayout(3,1));
        //Define
        JLabel title = new JLabel("CATHETER LAB", null, JLabel.CENTER);
        ButtonPanel buttonPanel = new ButtonPanel();
        //Add
        add(title);
        add(buttonPanel);

    }


}
