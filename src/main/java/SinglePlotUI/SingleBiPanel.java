package SinglePlotUI;

import DataHandling.Bipolar;
import DataHandling.ElectrodeDB;
import PlotUI.PlotPanel;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.data.xy.XYDataset;
import javax.swing.*;
import java.awt.*;

public class SingleBiPanel extends PlotPanel {

    protected JScrollPane scrollPane;
    protected JPanel controlPanel;

    public SingleBiPanel(ElectrodeDB electrodeDB) {


        super(electrodeDB);//Initialize super class: gets catheter dimensions

        //DEFINITIONS
        controlPanel = new JPanel();
        scrollPane = new JScrollPane(controlPanel);//Create scrollbar that will act on controlPanel
        Bipolar[] electrodes = electrodeDB.getBipolarArray(); //Instantiate ElectrodeArray: data from user:Data Handling
        JFreeChart[] charts = new JFreeChart[combsRows * combsCols];//Create charts
        ChartPanel[] chartPanels = new ChartPanel[combsRows * combsCols];//Create chart panels
        JToolBar toolBar = new JToolBar("Still draggable");

        //SET PANEL LAYOUT
        setLayout(new BorderLayout());
        controlPanel.setLayout(new GridLayout(combsRows * combsCols, 1));

        //Scroll bars will only appear when necessary
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);

        //FILL CHART WITH DATA AND FUNCTIONALITY + ADD THEM TO SINGLE PANEL
        for (int i = 0; i < (electrodes.length); i++) {

            XYDataset dataset = createDataset(electrodes[i].getData()); //Create dataset of single electrode
            charts[i] = createChart(dataset, electrodes[i].getName(), electrodes, electrodes.length, 1); //Create chart element
            chartPanels[i] = new ChartPanel(charts[i]); //Create new chart panel

            //Set layout of chart panel

            chartPanels[i].addChartMouseListener(CreateMouseListener(chartPanels[i],electrodes,electrodes.length,1,charts));
            chartPanels[i].setBorder(BorderFactory.createEmptyBorder(0, 5, 0, 5));
            chartPanels[i].setPreferredSize(new Dimension(200, 130));
            chartPanels[i].setMouseWheelEnabled(true);//Enables to zoom with mousewheel
            chartPanels[i].setBackground(Color.white);

            //Add chart panels to control panel
            controlPanel.add(chartPanels[i]);
        }

        toolBar.add(restoreZoomB(chartPanels,electrodes,electrodes.length,1,charts));//Add button to restore axis
        toolBar.add(clearMarkers(charts));//Add a button to clear markers
        toolBar.add(zoomAllB(electrodes.length,1,charts));//Add button to zoom all charts
        add(toolBar, BorderLayout.PAGE_START);//Add tool bar
        add(scrollPane);// Adds control panel (with scrollbar) to single panel
    }
}
