import PlotUI.PlotUIElectrodes;

import javax.swing.*;
import java.awt.*;

public class Main {
    static GraphicsConfiguration gc; // Class field containing config info

    public static void main(String[] args) {
        JFrame frame = new JFrame(gc); // Create a new JFrame
        frame.setSize(10000, 10000);

        PlotUIElectrodes plotUIElectrodes = new PlotUIElectrodes();
        frame.getContentPane().add(plotUIElectrodes.getMainPanel());
        frame.setTitle("Catheter readings");
        frame.setVisible(true);
        // This next line closes the program when the frame is closed
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }
}