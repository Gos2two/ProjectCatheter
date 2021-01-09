package GridPlotUI;

import DataHandling.ElectrodeDB;
import DataHandling.Unipolar;
import PlotUI.PlotPanel;
import org.jfree.chart.ChartMouseEvent;
import org.jfree.chart.ChartMouseListener;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.ValueAxis;
import org.jfree.chart.plot.Crosshair;
import org.jfree.chart.plot.Marker;
import org.jfree.chart.plot.ValueMarker;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.ui.LengthAdjustmentType;
import org.jfree.chart.ui.RectangleAnchor;
import org.jfree.chart.ui.RectangleEdge;
import org.jfree.chart.ui.TextAnchor;
import org.jfree.data.xy.XYDataset;

import javax.swing.*;
import java.awt.*;

public class GridPanel extends PlotPanel {

    public GridPanel(ElectrodeDB electrodeDB){

        super(); //Initialize super class: gets catheter dimensions

        //DEFINITIONS
        Unipolar[] electrodes = electrodeDB.getElectrodeArray(); //Instantiate ElectrodeArray: data from user:Data Handling
        JFreeChart[] charts = new JFreeChart[numRows * numCol];//Create charts + Add charts to panel

        //SET PANEL LAYOUT
        setLayout(new GridLayout(numRows,numCol));

        //FILL CHARTS WITH DATA AND FUNCTIONALITY + ADD THEM TO GRID PANEL
        for(int i=0; i<(numRows*numCol); i++){

            XYDataset dataset = createDataset(electrodes[i].getData()); //Create dataset of single electrode
            charts[i] = createChart(dataset, electrodes[i].getName(),electrodes,numRows,numCol);//Create chart element
            ChartPanel chartPanel = new ChartPanel(charts[i]);//Create new chart panel

            //Set layout of chart panel
            chartPanel.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
            chartPanel.setBackground(Color.white);
            chartPanel.setMouseWheelEnabled(true); //Enable to zoom with mousewheel
            chartPanel.add(CreateZoom(chartPanel,electrodes,numRows,numCol,charts[i]));//Add buttons to restore axis
            chartPanel.addChartMouseListener(CreateMouseListener(chartPanel,electrodes,numRows,numCol,charts));
            //Add chart panels to control panel
            add(chartPanel);
        }
    }
}