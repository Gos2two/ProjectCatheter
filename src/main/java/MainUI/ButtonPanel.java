package MainUI;

import DataHandling.ElectrodeDB;
import DataHandling.UserDialogues;
import GridPlotUI.GridPlotWindow;
import SinglePlotUI.SinglePlotWindow;
import javax.swing.*;

public class ButtonPanel extends JPanel {
    private UserDialogues userDialogues;
    private ElectrodeDB ElectrodeDB;


    public ButtonPanel() {
        //Define
        JButton gridPlotB = new JButton("GRID PLOT");
        JButton singlePlotB = new JButton("SINGLE PLOT");
        JButton inputDataB = new JButton("INPUT DATA");
        userDialogues = new UserDialogues();


        //Add
        add(inputDataB);
        add(gridPlotB);
        add(singlePlotB);

        //Define functions of buttons through action listeners.
        
        gridPlotB.addActionListener(e -> new GridPlotWindow(ElectrodeDB));

        singlePlotB.addActionListener(e -> new SinglePlotWindow(ElectrodeDB));

        inputDataB.addActionListener(e -> {
            userDialogues.getFileSource();
            ElectrodeDB =  new ElectrodeDB(userDialogues.getExcelFilePath());
            ElectrodeDB.printElectrodes();
        });
    }
}
