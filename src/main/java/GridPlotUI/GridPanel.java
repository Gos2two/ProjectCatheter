package GridPlotUI;

import DataHandling.ElectrodeDB;
import DataHandling.Unipolar;
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

public class GridPanel extends JPanel {
    private int numRows;
    private int numCol;

    public GridPanel(ElectrodeDB electrodeDB){
        //Set layout according to size of the grid: input from user dialogue.
        setGridDimensions();
        setLayout(new GridLayout(numRows,numCol));
        //Instantiate ElectrodeArray: data from user : Data Handling
        Unipolar[] electrodes = electrodeDB.getElectrodeArray();
        //Create charts + Add charts to panel
        JFreeChart[] charts = new JFreeChart[numRows * numCol];

        for(int i=0; i<(numRows*numCol); i++){

            XYDataset dataset = createDataset(electrodes[i].getData());
            charts[i] = createChart(dataset, electrodes[i].getName());
            ChartPanel chartPanel = new ChartPanel(charts[i]);
            chartPanel.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
            chartPanel.setBackground(Color.white);
            //Rearrange panels
            add(chartPanel);
        }

    }

    private void setGridDimensions(){
        JTextField rowField = new JTextField(5);
        JTextField colField = new JTextField(5);

        //Setting up panel with text fields for the user to input values
        JPanel myPanel = new JPanel();
        myPanel.add(new JLabel("Number of rows:"));
        myPanel.add(rowField);
        myPanel.add(Box.createHorizontalStrut(15)); // a spacer
        myPanel.add(new JLabel("Number of columns:"));
        myPanel.add(colField);

        int result = JOptionPane.showConfirmDialog(null, myPanel,
                "Please Enter Catheter Dimensions", JOptionPane.OK_CANCEL_OPTION);
        if (result == JOptionPane.OK_OPTION) {
            System.out.println("Number of rows: " + rowField.getText());
            try
            {
                // the String to int conversion happens here
                numRows = Integer.parseInt((rowField.getText()).trim());
            }
            catch (NumberFormatException nfe)
            {
                System.out.println("NumberFormatException: " + nfe.getMessage());
            }
            System.out.println("Number of columns: " + colField.getText());
            try
            {
                // the String to int conversion happens here
                numCol = Integer.parseInt((colField.getText()).trim());
            }
            catch (NumberFormatException nfe)
            {
                System.out.println("NumberFormatException: " + nfe.getMessage());
            }

        }
    }
    private XYDataset createDataset(Double[] electrode){
        XYSeriesCollection dataset=new XYSeriesCollection();
        XYSeries series=new XYSeries("Object 1");

        for(double i=0; i < electrode.length; i++) {
            series.add(i,electrode[(int) i]);
        }
        dataset.addSeries(series);

        return dataset;
    }
    private JFreeChart createChart(XYDataset dataset, String title) {
        JFreeChart chart = ChartFactory.createXYLineChart(
                title,
                null,
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

        chart.setTitle(new TextTitle(title,
                        new Font("Calibri", java.awt.Font.BOLD, 18)
                )
        );

        return chart;
    }
}
