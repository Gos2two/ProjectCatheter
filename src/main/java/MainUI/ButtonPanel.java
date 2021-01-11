package MainUI;

import DataHandling.ElectrodeDB;
import DataHandling.UserDialogues;
import GridPlotUI.GridPlotWindow;
import SinglePlotUI.SinglePlotWindow;
import javax.swing.*;
import java.awt.*;


public class ButtonPanel extends JPanel implements UserDialogues {
  
    private ElectrodeDB ElectrodeDB;
  
    public ButtonPanel() {
        JPanel dataPanel= new JPanel();
        JPanel graphPanel= new JPanel();
      
        //SetLayouts
        setLayout(new GridLayout(2,1));
        dataPanel.setLayout(new FlowLayout());
        graphPanel.setLayout(new FlowLayout(1,60,0));
      
        //Define
        JButton gridPlotB = new JButton("GRID PLOT");
        JButton singlePlotB = new JButton("SINGLE PLOT");
        JButton inputDataB = new JButton("INPUT DATA");

        //Add
        dataPanel.add(inputDataB);
        graphPanel.add(gridPlotB);
        graphPanel.add(singlePlotB);
        add(dataPanel);
        add(graphPanel);

        //Define functions of buttons through action listeners      
        gridPlotB.addActionListener(e -> new GridPlotWindow(ElectrodeDB));

        singlePlotB.addActionListener(e -> new SinglePlotWindow(ElectrodeDB));

        inputDataB.addActionListener(e -> {
            String source = UserDialogues.getFileSource();
                if(UserDialogues.extensionCheck(source)){
                    ElectrodeDB =  new ElectrodeDB(source);
                    ElectrodeDB.printElectrodes();
                }
        });
    }
}
