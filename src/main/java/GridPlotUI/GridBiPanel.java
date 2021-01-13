package GridPlotUI;

import DataHandling.Bipolar;
import DataHandling.ElectrodeDB;
import PlotUI.PlotPanel;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.data.xy.XYDataset;
import javax.swing.*;
import java.awt.*;

public class GridBiPanel extends PlotPanel {

    protected JScrollPane scrollPane;
    protected JPanel controlPanel;

    public GridBiPanel(ElectrodeDB electrodeDB){
        super(electrodeDB); //Initialize super class: gets catheter dimensions

        //DEFINITIONS
        controlPanel = new JPanel();
        scrollPane = new JScrollPane(controlPanel);//Create scrollbar that will act on controlPanel
        Bipolar[] electrodes = electrodeDB.getBipolarArray(); //Instantiate ElectrodeArray: data from user:Data Handling
        JFreeChart[] charts = new JFreeChart[combsRows * combsCols];//Create charts
        ChartPanel[] chartPanels = new ChartPanel[combsRows * combsCols];//Create chart panels
        JToolBar toolBar = new JToolBar("Still draggable");

        //SET PANEL LAYOUT
        setLayout(new BorderLayout());
        controlPanel.setLayout(new GridLayout(combsRows,combsCols));
        controlPanel.setPreferredSize(new Dimension(800,3000));

        //FILL CHARTS WITH DATA AND FUNCTIONALITY + ADD THEM TO GRID PANEL
        for(int i=0; i<(electrodes.length); i++){

            XYDataset dataset = createDataset(electrodes[i].getData()); //Create dataset of single electrode
            charts[i] = createChart(dataset, electrodes[i].getName(),electrodes,electrodes.length,1);//Create chart element
            chartPanels[i] = new ChartPanel(charts[i]);//Create new chart panel

            //Set layout of chart panel
            chartPanels[i].addChartMouseListener(CreateMouseListener(chartPanels[i],electrodes,electrodes.length,1,charts));
            chartPanels[i].setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
            chartPanels[i].setBackground(Color.white);
            chartPanels[i].setMouseWheelEnabled(true); //Enable to zoom with mousewheel

            //Add chart panels to control panel
            controlPanel.add(chartPanels[i]);
        }
        toolBar.add(restoreZoomB(chartPanels,electrodes,electrodes.length,1,charts));//Add button to restore axis
        toolBar.add(clearMarkers(charts));//Add a button to clear markers
        toolBar.add(zoomAllB(electrodes.length,1,charts));//Add button to zoom all charts
        add(toolBar, BorderLayout.PAGE_START);//Add tool bar
        add(scrollPane);
    }
}
