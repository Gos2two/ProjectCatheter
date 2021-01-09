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
        JFreeChart[] charts = new JFreeChart[numRows * numCol];//Create charts + Add charts to panel

        //SET PANEL LAYOUT
        setLayout(new GridLayout(1, 1));
        controlPanel.setLayout(new GridLayout(numRows * numCol, 1));

        //Scroll bars will only appear when necessary
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);

        //FILL CHART WITH DATA AND FUNCTIONALITY + ADD THEM TO SINGLE PANEL
        for (int i = 0; i < (numRows * numCol); i++) {

            XYDataset dataset = createDataset(electrodes[i].getData()); //Create dataset of single electrode
            charts[i] = createChart(dataset, electrodes[i].getName(), electrodes, numRows, numCol); //Create chart element
            ChartPanel chartPanel = new ChartPanel(charts[i]); //Create new chart panel

            //Set layout of chart panel
            chartPanel.setBorder(BorderFactory.createEmptyBorder(0, 5, 0, 5));
            chartPanel.setPreferredSize(new Dimension(200, 130));
            chartPanel.setMouseWheelEnabled(true);//Enables to zoom with mousewheel
            chartPanel.setBackground(Color.white);
            chartPanel.add(CreateZoom(chartPanel, electrodes, numRows, numCol, charts[i]));//Add buttons to restore axis
            chartPanel.addChartMouseListener(CreateMouseListener(chartPanel,electrodes,numRows,numCol,charts));

            //Add chart panels to control panel
            controlPanel.add(chartPanel);
        }
        add(scrollPane);// Adds control panel (with scrollbar) to single panel
    }
}

