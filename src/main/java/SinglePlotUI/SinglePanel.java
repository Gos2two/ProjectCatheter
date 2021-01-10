package SinglePlotUI;

import DataHandling.ElectrodeDB;
import DataHandling.Unipolar;
import PlotUI.PlotPanel;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.data.xy.XYDataset;

import javax.swing.*;
import java.awt.*;

public class SinglePanel extends PlotPanel {

    protected JScrollPane scrollPane;
    protected JPanel controlPanel;

    public SinglePanel(ElectrodeDB electrodeDB) {


        super();//Initialize super class: gets catheter dimensions

        //DEFINITIONS
        controlPanel = new JPanel();
        scrollPane = new JScrollPane(controlPanel);//Create scrollbar that will act on controlPanel
        Unipolar[] electrodes = electrodeDB.getElectrodeArray(); //Instantiate ElectrodeArray: data from user:Data Handling
        JFreeChart[] charts = new JFreeChart[numRows * numCol];//Create charts
        ChartPanel[] chartPanels = new ChartPanel[numRows * numCol];//Create chart panels
        JToolBar toolBar = new JToolBar("Still draggable");

        //SET PANEL LAYOUT
        setLayout(new BorderLayout());
        controlPanel.setLayout(new GridLayout(numRows * numCol, 1));

        //Scroll bars will only appear when necessary
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);

        //FILL CHART WITH DATA AND FUNCTIONALITY + ADD THEM TO SINGLE PANEL
        for (int i = 0; i < (numRows * numCol); i++) {

            XYDataset dataset = createDataset(electrodes[i].getData()); //Create dataset of single electrode
            charts[i] = createChart(dataset, electrodes[i].getName(), electrodes, numRows, numCol); //Create chart element
            chartPanels[i] = new ChartPanel(charts[i]); //Create new chart panel

            //Set layout of chart panel
          
            chartPanels[i].addChartMouseListener(CreateMouseListener(chartPanels[i],electrodes,numRows,numCol,charts));
            chartPanels[i].setBorder(BorderFactory.createEmptyBorder(0, 5, 0, 5));
            chartPanels[i].setPreferredSize(new Dimension(200, 130));
            chartPanels[i].setMouseWheelEnabled(true);//Enables to zoom with mousewheel
            chartPanels[i].setBackground(Color.white);

            //Add chart panels to control panel
            controlPanel.add(chartPanels[i]);
        }
      
        toolBar.add(restoreZoomB(chartPanels,electrodes,numRows,numCol,charts));//Add button to restore axis
        toolBar.add(zoomAllB(chartPanels,numRows,numCol,charts));//Add button to zoom all charts
        add(toolBar, BorderLayout.PAGE_START);//Add tool bar
        add(scrollPane);// Adds control panel (with scrollbar) to single panel
    }
}

