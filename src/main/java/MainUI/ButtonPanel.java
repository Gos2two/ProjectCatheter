package MainUI;

import DataHandeling.UserDialogues;
import GridPlotUI.GridPlotWindow;
import SinglePlotUI.SinglePlotWindow;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ButtonPanel extends JPanel {
    protected JButton gridPlotB;
    protected JButton singlePlotB;
    protected JButton inputDataB;
    protected UserDialogues userDialogues;

    public ButtonPanel(){
        //Define
        gridPlotB=new JButton("GRID PLOT");
        singlePlotB=new JButton("SINGLE PLOT");
        inputDataB=new JButton("INPUT DATA");
        userDialogues=new UserDialogues();

        //Add
        add(inputDataB);
        add(gridPlotB);
        add(singlePlotB);

        //Define functions of buttons through action listeners.
        gridPlotB.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                GridPlotWindow plotWindow1=new GridPlotWindow();
            }
        });
        singlePlotB.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                SinglePlotWindow plotWindow2=new SinglePlotWindow();
            }
        });
        inputDataB.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                userDialogues.getFileSource();
            }
        });
    }
}
