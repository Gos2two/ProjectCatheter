package MainUI;

import DataHandling.ElectrodeDB;
import DataHandling.UserDialogues;
import GridPlotUI.GridPlotWindow;
import SinglePlotUI.SinglePlotWindow;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ButtonPanel extends JPanel {
    private JButton gridPlotB;
    private JButton singlePlotB;
    private JButton inputDataB;
    private UserDialogues userDialogues;
    private ElectrodeDB ElectrodeDB;


    public ButtonPanel() {
        JPanel dataPanel= new JPanel();
        JPanel graphPanel= new JPanel();
        //setLayouts
        setLayout(new GridLayout(2,1));
        dataPanel.setLayout(new FlowLayout());
        graphPanel.setLayout(new FlowLayout(1,60,0));
        //Define
        gridPlotB = new JButton("GRID PLOT");
        singlePlotB = new JButton("SINGLE PLOT");
        inputDataB = new JButton("INPUT DATA");
        userDialogues = new UserDialogues();

        //Add
        dataPanel.add(inputDataB);
        graphPanel.add(gridPlotB);
        graphPanel.add(singlePlotB);
        add(dataPanel);
        add(graphPanel);

        //Define functions of buttons through action listeners.
        gridPlotB.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                GridPlotWindow gridPlotWindow = new GridPlotWindow(ElectrodeDB);
            }
        });
        singlePlotB.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                SinglePlotWindow singlePlotWindow = new SinglePlotWindow(ElectrodeDB);
            }
        });
        //Here we should add a new action performed: ask user for catheter size
        inputDataB.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                userDialogues.getFileSource();
                ElectrodeDB =  new ElectrodeDB(userDialogues.getExcelFilePath());
                ElectrodeDB.printElectrodes();
            }
        });
    }
}
