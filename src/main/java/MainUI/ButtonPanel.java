package MainUI;

import DataHandling.ElectrodeDB;
import DataHandling.UserDialogues;
import GridPlotUI.GridPanel;
import GridPlotUI.GridPlotWindow;
import SinglePlotUI.SinglePlotWindow;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ButtonPanel extends JPanel {
    private JButton gridPlotB;
    private JButton singlePlotB;
    private JButton inputDataB;
    private UserDialogues userDialogues;
    private ElectrodeDB ElectrodeDB;


    public ButtonPanel() {
        //Define
        gridPlotB = new JButton("GRID PLOT");
        singlePlotB = new JButton("SINGLE PLOT");
        inputDataB = new JButton("INPUT DATA");
        userDialogues = new UserDialogues();


        //Add
        add(inputDataB);
        add(gridPlotB);
        add(singlePlotB);

        //Define functions of buttons through action listeners.
        gridPlotB.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                GridPanel gridPanel = new GridPanel(ElectrodeDB);
                GridPlotWindow gridPlotWindow = new GridPlotWindow(gridPanel);
            }
        });
        singlePlotB.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                SinglePlotWindow singlePlotWindow = new SinglePlotWindow();
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
