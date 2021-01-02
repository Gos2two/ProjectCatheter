package PlotUI;

import DataHandling.ElectrodeDB;
import DataHandling.Unipolar;
import DataHandling.UserDialogues;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.ValueAxis;
import org.jfree.chart.block.BlockBorder;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.SamplingXYLineRenderer;
import org.jfree.data.xy.XYDataset;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

import static java.lang.StrictMath.abs;

public class PlotPanel extends JPanel {
    protected int numRows;
    protected int numCol;

    public PlotPanel(){ setDimensions(); }

    private void setDimensions(){
        //Define temporal variables
        UserDialogues userDialogues = new UserDialogues();
        int[] gridDimensions = new int[2];

        //Call method from user dialogues to get dimensions
        gridDimensions = userDialogues.getGridDimensions();
        numRows = gridDimensions[0];
        numCol = gridDimensions[1];
    }
    protected XYDataset createDataset(Double[] electrode){
        XYSeriesCollection dataset=new XYSeriesCollection();
        XYSeries series=new XYSeries("");

        for(double i=0; i < electrode.length; i++) {
            series.add(i,electrode[(int) i]);
        }
        dataset.addSeries(series);

        return dataset;
    }
    protected JFreeChart createChart(XYDataset dataset, String title, Unipolar[] electrodes, int numRows, int numCol) {
        JFreeChart chart = ChartFactory.createXYLineChart(
                null,
                title,
                null,
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


        //Setting equal all initial yAxis ranges for all electrodes
        ValueAxis yAxis= plot.getRangeAxis();
        yAxis.setRange(-getMaxValue(electrodes,numRows,numCol),getMaxValue(electrodes,numRows,numCol));

        return chart;
    }
    protected JButton CreateZoom(ChartPanel chartPanels, Unipolar[] electrodes, int numRows, int numCol, JFreeChart chart){

        double finalMaxElement= getMaxValue(electrodes,numRows,numCol);
        //creates button to restore axis after any zoom operation has been done
        JButton autoZoom= new JButton(new AbstractAction("Restore Axis") {
            @Override
            public void actionPerformed(ActionEvent e) {
                chartPanels.restoreAutoDomainBounds();
                chart.getXYPlot().getRangeAxis().setRange(-finalMaxElement, finalMaxElement);


            }
        });

        return autoZoom;
    }
    protected double getMaxValue(Unipolar[] electrodes, int numRows, int numCol){
        //calculating maximum absolute value among all the data
        double maxElement=0;

        for(int i=0; i<numCol*numRows;i++){
            Double[] electrodeData= electrodes[i].getData();
            for (int j = 0; j < electrodeData.length; j++) {
                if(abs(electrodeData[j])>maxElement){
                    maxElement = abs(electrodeData[j]);
                }
            }
        }
        double finalMaxElement = maxElement;
        return finalMaxElement;
    }
}

