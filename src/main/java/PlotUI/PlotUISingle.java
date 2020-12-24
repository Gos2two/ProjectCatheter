package PlotUI;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.axis.NumberTickUnit;
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

import static java.lang.StrictMath.abs;

public class PlotUISingle {
    protected JPanel mainPanel;
    protected XYDataset dataset;
    protected ArrayList<JFreeChart> chart;
    protected Double[][] electrodeData;

    public PlotUISingle(ReadExcel readExcel) {
        mainPanel= new JPanel();
        electrodeData= readExcel.getDataset();
        Double[] oneElectrode = new Double[electrodeData.length];

        dataset = createDataset(oneElectrode);




        mainPanel.setLayout(new GridLayout(1,1));

        JFreeChart chart = ChartFactory.createXYLineChart(
                "Single PLot",
                "time",
                "Intensity",
                dataset,
                PlotOrientation.VERTICAL,
                true,
                true,
                false
        );
        ChartPanel chartPanel = new ChartPanel( chart );
        chartPanel.setPreferredSize( new java.awt.Dimension( 560 , 367 ) );
        mainPanel.add(chartPanel);

        XYPlot plot = chart.getXYPlot();

        NumberAxis yAxis = (NumberAxis) plot.getRangeAxis();                          //remove labels and add a solid line for each zero
        yAxis.setTickMarksVisible(false);
        yAxis.setTickLabelsVisible(false);
        yAxis.setTickUnit(new NumberTickUnit(2 * getmaxelement(oneElectrode)));

        plot.setRangeGridlineStroke(new BasicStroke());

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

        chart.setTitle(new TextTitle("Demo",
                        new Font("Calibri", java.awt.Font.BOLD, 18)
                )
        );

        }


    public JPanel getMainPanel() {
        return mainPanel;
    }

    public double getmaxelement(Double[] electrode){               //gets largest single value, positive or negative. Needed for separating the graphs
        double maxelement = 0;

        for(int i=0; i<16;i++) {

            for (int j = 0; j < electrode.length; j++) {
                if(abs(electrodeData[j][i]) > maxelement){
                    maxelement = abs(electrodeData[j][i]);
                }
            }
        }
        return maxelement;
    }

    private XYDataset createDataset(Double[] electrode){
        XYSeriesCollection dataset=new XYSeriesCollection();


        for(int i=0; i<16;i++) {
            XYSeries series=new XYSeries("Electrode "+(i+1));

            for (int j = 0; j < electrode.length; j++) {
                electrode[j] = electrodeData[j][i] - i * 2 * getmaxelement(electrode);
            }
            for (double k = 0; k < electrode.length; k++) {
                series.add(k, electrode[(int) k]);
            }
            dataset.addSeries(series);
        }

        return dataset;
    }


}
