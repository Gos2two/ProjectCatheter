package MainUI;

import javax.swing.*;
import java.awt.*;

public class MainPanel extends JPanel {

    public MainPanel(){
      
        //Set Layout
        setLayout(new GridLayout(3,1));
      
        //Define panels and icons
        ImageIcon icon= new ImageIcon(new ImageIcon("src/catheter.png").getImage().getScaledInstance(300,113,Image.SCALE_SMOOTH));
        JLabel title = new JLabel(icon);
        ButtonPanel buttonPanel = new ButtonPanel();
        ImagePanel images= new ImagePanel();

        //Add
        add(title);
        add(buttonPanel);
        add(images);
    }


}
