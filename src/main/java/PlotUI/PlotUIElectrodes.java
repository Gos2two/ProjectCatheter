package PlotUI;

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

    public PlotUIElectrodes() {
        mainPanel=new JPanel();
        dataset = createDataset();
        chart = new ArrayList<JFreeChart>();

        //Set Layouts
        mainPanel.setLayout(new GridLayout(4,4));
        for(int i=0; i<16;i++)
        {
            chart.add(createChart(dataset,"Electrode "+ (i+1))); //Add charts for each electrode
            ChartPanel chartPanel = new ChartPanel(chart.get(i));
            chartPanel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
            chartPanel.setBackground(Color.white);
            //Rearrange panels
            mainPanel.add(chartPanel);
        }


    }
    public JPanel getMainPanel() {return mainPanel;}
    private XYDataset createDataset(){
        XYSeriesCollection dataset=new XYSeriesCollection();
        XYSeries series1=new XYSeries("Object 1");

        ArrayList<Double> series=new ArrayList<Double>();
        series.add(1.0);
        series.add(2.0);
        series.add(3.0);
        series.add(4.0);
        series.add(5.0);
        series.add(8.0);
        series.add(10.0);
        series.add(14.0);
        series.add(18.0);
        series.add(25.0);
        series.add(50.0);

        for(double i=0; i<series.size(); i++)
        {
            series1.add(i,series.get((int)i));
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
