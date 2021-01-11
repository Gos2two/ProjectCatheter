package MainUI;

import DataHandling.ElectrodeDB;
import DataHandling.UserDialogues;
import GridPlotUI.GridPlotWindow;
import SinglePlotUI.SinglePlotWindow;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ButtonPanel extends JPanel implements UserDialogues  {
    private JButton gridPlotB;
    private JButton singlePlotB;
    private JButton inputDataB;
    private ElectrodeDB ElectrodeDB;


    public ButtonPanel() {
        //Define
        gridPlotB = new JButton("GRID PLOT");
        singlePlotB = new JButton("SINGLE PLOT");
        inputDataB = new JButton("INPUT DATA");


        //Add
        add(inputDataB);
        add(gridPlotB);
        add(singlePlotB);

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
                String source = UserDialogues.getFileSource();
                if(UserDialogues.extensionCheck(source)){
                    ElectrodeDB =  new ElectrodeDB(source);
                    ElectrodeDB.printElectrodes();
                }
            }
        });
    }
}
