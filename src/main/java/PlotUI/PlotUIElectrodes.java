package PlotUI;

import org.apache.commons.collections4.ArrayStack;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.block.BlockBorder;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.SamplingXYLineRenderer;
import org.jfree.chart.title.TextTitle;
import org.jfree.data.xy.XYDataset;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;


import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class PlotUIElectrodes {
    protected JPanel mainPanel;
    protected XYDataset dataset;
    protected ArrayList<JFreeChart> chart;
    protected Double[][] electrodeData;

    public PlotUIElectrodes(ReadExcel readExcel) {
        mainPanel=new JPanel();
        //Initialize data from electrodes
        electrodeData=readExcel.getDataset();
        Double[] oneElectrode = new Double[electrodeData.length];
        chart=new ArrayList<JFreeChart>();

        //Set Layouts
        mainPanel.setLayout(new GridLayout(4,4));
        for(int i=0; i<16;i++) {
            for(int j=0; j<oneElectrode.length; j++){
                oneElectrode[j]=electrodeData[j][i];
            }
            dataset=createDataset(oneElectrode);
            chart.add(createChart(dataset,"Electrode "+ (i+1))); //Add charts for each electrode
            ChartPanel chartPanel = new ChartPanel(chart.get(i));
            chartPanel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
            chartPanel.setBackground(Color.white);
            //Rearrange panels
            mainPanel.add(chartPanel);
        }


    }
    public JPanel getMainPanel() {return mainPanel;}
    private XYDataset createDataset(Double[] electrode){
        XYSeriesCollection dataset=new XYSeriesCollection();
        XYSeries series1=new XYSeries("Object 1");

        for(double i=0; i<electrode.length; i++) {
            series1.add(i,electrode[(int) i]);
        }
        dataset.addSeries(series1);

        return dataset;
    }
    private JFreeChart createChart(XYDataset dataset, String title ) {

        JFreeChart chart = ChartFactory.createXYLineChart(
                title,
                "xAxisLAbel",
                "yAxisLAbel",
                dataset,
                PlotOrientation.VERTICAL,
                true,
                true,
                false
        );
        XYPlot plot = chart.getXYPlot();

        SamplingXYLineRenderer renderer = new SamplingXYLineRenderer();
        renderer.setSeriesPaint(0, Color.RED);
        renderer.setSeriesStroke(0, new BasicStroke(2.0f));

        plot.setRenderer(renderer);
        plot.setBackgroundPaint(Color.white);

        plot.setRangeGridlinesVisible(true);
        plot.setRangeGridlinePaint(Color.BLACK);

        plot.setDomainGridlinesVisible(true);
        plot.setDomainGridlinePaint(Color.BLACK);

        chart.getLegend().setFrame(BlockBorder.NONE);

        chart.setTitle(new TextTitle(title,
                new Font("Calibri", java.awt.Font.BOLD, 18)
                )
        );

        return chart;
    }



}
