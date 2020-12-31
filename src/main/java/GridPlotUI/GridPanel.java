package GridPlotUI;

import DataHandling.ElectrodeDB;
import DataHandling.Unipolar;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.ValueAxis;
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
import java.awt.event.ActionEvent;

import static java.lang.StrictMath.abs;

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

            XYDataset dataset = createDataset(electrodes[i].getData()); //creates dataset of single electrode
            charts[i] = createChart(dataset, electrodes[i].getName(),electrodes,numRows,numCol);//creates chart element
            ChartPanel chartPanel = new ChartPanel(charts[i]);//creates new chart panel
            //setting layout of chart panel
            chartPanel.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
            chartPanel.setBackground(Color.white);
            chartPanel.setMouseWheelEnabled(true); //enables to zoom with mousewheel
            chartPanel.add(CreateZoom(chartPanel,electrodes,numRows,numCol,charts[i]));//adding buttons to restore axis
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
        XYSeries series=new XYSeries("");

        for(double i=0; i < electrode.length; i++) {
            series.add(i,electrode[(int) i]);
        }
        dataset.addSeries(series);

        return dataset;
    }
    private JFreeChart createChart(XYDataset dataset, String title, Unipolar[] electrodes, int numRows, int numCol) {
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


        //setting equal all initial yAxis ranges for all electrodes
        ValueAxis yAxis= plot.getRangeAxis();
        yAxis.setRange(-getMaxValue(electrodes,numRows,numCol),getMaxValue(electrodes,numRows,numCol));

        return chart;
    }
    private JButton CreateZoom (ChartPanel chartPanels, Unipolar[] electrodes,int numRows, int numCol, JFreeChart chart){

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

    public double getMaxValue(Unipolar[] electrodes, int numRows, int numCol){
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
