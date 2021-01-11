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
        JPanel dataPanel= new JPanel();
        JPanel graphPanel= new JPanel();
        //setLayouts
        setLayout(new GridLayout(2,1));
        dataPanel.setLayout(new FlowLayout());
        graphPanel.setLayout(new FlowLayout(1,60,0));
        //Define
        JButton gridPlotB = new JButton("GRID PLOT");
        JButton singlePlotB = new JButton("SINGLE PLOT");
        JButton inputDataB = new JButton("INPUT DATA");
        userDialogues = new UserDialogues();

        //Add
        dataPanel.add(inputDataB);
        graphPanel.add(gridPlotB);
        graphPanel.add(singlePlotB);
        add(dataPanel);
        add(graphPanel);

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
