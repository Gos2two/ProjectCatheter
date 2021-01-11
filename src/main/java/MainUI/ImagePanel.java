package MainUI;

import javax.swing.*;
import java.awt.*;


public class ImagePanel extends JPanel{
    private JLabel label1;
    private JLabel label2;
    private ImageIcon image1;
    private ImageIcon image2;


    public ImagePanel() {
        //setLayout manager
        setLayout(new FlowLayout(1,50,0));
        //Define labels and ImageIcons
        label1= new JLabel();
        label2= new JLabel();
        image1= new ImageIcon(new ImageIcon("src/spreadsheet.png").getImage().getScaledInstance(120, 120, Image.SCALE_SMOOTH));
        image2= new ImageIcon(new ImageIcon("src/line-graph.png").getImage().getScaledInstance(120, 120, Image.SCALE_SMOOTH));
        //Add icons to labels
        label1.setIcon(image1);
        label2.setIcon(image2);
        //Add labels to ImagePanel
        add(label1);
        add(label2);

    }

}
