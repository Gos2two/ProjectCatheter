package GridPlotUI;

import DataHandling.ElectrodeDB;
import DataHandling.Unipolar;
import PlotUI.PlotPanel;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.data.xy.XYDataset;
import javax.swing.*;
import java.awt.*;

public class GridPanel extends PlotPanel {

    public GridPanel(ElectrodeDB electrodeDB){

        super(electrodeDB); //Initialize super class: gets catheter dimensions

        //DEFINITIONS
        JPanel gridChartPanel = new JPanel();
        Unipolar[] electrodes = electrodeDB.getUnipolarArray(); //Instantiate ElectrodeArray: data from user:Data Handling
        JFreeChart[] charts = new JFreeChart[numRows * numCols];//Create charts
        ChartPanel[] chartPanels = new ChartPanel[numRows * numCols];//Create chart panels
        JToolBar toolBar = new JToolBar("Still draggable");

        //SET PANEL LAYOUT
        setLayout(new BorderLayout());
        gridChartPanel.setLayout(new GridLayout(numRows,numCols));

        //FILL CHARTS WITH DATA AND FUNCTIONALITY + ADD THEM TO GRID PANEL
        for(int i=0; i<(numRows*numCols); i++){

            XYDataset dataset = createDataset(electrodes[i].getData()); //Create dataset of single electrode
            charts[i] = createChart(dataset, electrodes[i].getName(),electrodes,numRows,numCols);//Create chart element
            chartPanels[i] = new ChartPanel(charts[i]);//Create new chart panel

            //Set layout of chart panel
            chartPanels[i].addChartMouseListener(CreateMouseListener(chartPanels[i],electrodes,numRows,numCols,charts));
            chartPanels[i].setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
            chartPanels[i].setBackground(Color.white);
            chartPanels[i].setMouseWheelEnabled(true); //Enable to zoom with mousewheel

            //Add chart panels to control panel
            gridChartPanel.add(chartPanels[i]);
        }
        toolBar.add(restoreZoomB(chartPanels,electrodes,numRows,numCols,charts));//Add button to restore axis
        toolBar.add(clearMarkers(charts));//Add a button to clear markers
        toolBar.add(zoomAllB(numRows,numCols,charts));//Add button to zoom all charts
        toolBar.add(hideNameB(numRows,numCols,charts,electrodes));//Add button to hide Title Name

        add(toolBar, BorderLayout.PAGE_START);//Add tool bar
        add(gridChartPanel);
    }
}