package PlotUI;

import DataHandling.Unipolar;
import DataHandling.UserDialogues;
import org.jfree.chart.*;
import org.jfree.chart.plot.ValueMarker;
import org.jfree.chart.ui.LengthAdjustmentType;
import org.jfree.chart.ui.RectangleAnchor;
import org.jfree.chart.ui.RectangleEdge;
import org.jfree.chart.ui.TextAnchor;
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
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import static java.lang.StrictMath.abs;

public class PlotPanel extends JPanel {

    protected int numRows;
    protected int numCol;

    public PlotPanel(){ setDimensions(); }

    //PROTECTED METHODS SHARED BY GRID AND SINGLE PANEL

    private void setDimensions(){

        //Define temporal variables
        UserDialogues userDialogues = new UserDialogues();

        //Call method from user dialogues to get dimensions
        int[] gridDimensions = userDialogues.getGridDimensions();
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


        //Set all yAxis equal initially
        ValueAxis yAxis= plot.getRangeAxis();
        yAxis.setRange(-getMaxValue(electrodes,numRows,numCol),getMaxValue(electrodes,numRows,numCol));

        return chart;
    }

    protected JButton restoreZoomB(ChartPanel[] chartPanels, Unipolar[] electrodes, int numRows, int numCol, JFreeChart[] charts){

        double finalMaxElement= getMaxValue(electrodes,numRows,numCol);
        //Create button to restore axis after zoom.

        return new JButton(new AbstractAction("Restore Axis") {
            @Override
            public void actionPerformed(ActionEvent e) {
                for(int i = 0; i < (numRows*numCol); i++){
                    chartPanels[i].restoreAutoDomainBounds();
                    charts[i].getXYPlot().getRangeAxis().setRange(-finalMaxElement, finalMaxElement);
                }
            }
        });
    }

    protected JButton clearMarkers(ChartPanel[] chartPanels, Unipolar[] electrodes, JFreeChart[] charts){
        //Create button to remove markers.

        return new JButton(new AbstractAction("Clear Markers") {
            @Override
            public void actionPerformed(ActionEvent e) {
                for(int i = 0; i < (numRows*numCol); i++){
                    XYPlot scopeXYPlot = charts[i].getXYPlot();
                    scopeXYPlot.clearDomainMarkers();
                    scopeXYPlot.setRangeCrosshairVisible(false);
                }
            }
        });
    }
  
    protected ChartMouseListener CreateMouseListener(ChartPanel chartPanel, Unipolar[] electrodes, int numRows, int numCol, JFreeChart[] charts) {
        return new ChartMouseListener(){
        @Override
        public void chartMouseClicked (ChartMouseEvent cme){

            try {

            XYPlot plot = chartPanel.getChart().getXYPlot();

            double xx = plot.getDomainAxis().java2DToValue(cme.getTrigger().getX(), chartPanel.getScreenDataArea(), RectangleEdge.BOTTOM);

            for (int j = 0; j < (numRows * numCol); j++) {

                XYPlot scopeXYPlot = charts[j].getXYPlot();

                scopeXYPlot.clearRangeMarkers();
                scopeXYPlot.clearDomainMarkers();

                double yy = (double) createDataset(electrodes[j].getData()).getY(0, (int) xx);
                // make sure the range crosshair is on
                scopeXYPlot.setRangeCrosshairVisible(true);
                // and plot it
                scopeXYPlot.setRangeCrosshairValue(yy, true);

                ValueMarker markerx = new ValueMarker(xx);
                markerx.setLabelOffsetType(LengthAdjustmentType.EXPAND);
                markerx.setPaint(Color.black);
                markerx.setLabel(String.format("X: %-1.3f Y: %-1.3f", xx, yy));
                markerx.setLabelPaint(Color.black);
                markerx.setLabelAnchor(RectangleAnchor.TOP_RIGHT);
                markerx.setLabelTextAnchor(TextAnchor.TOP_LEFT);
                markerx.setLabelFont(new Font("Arial", Font.PLAIN, 10));
                markerx.setStroke(new BasicStroke(2.0f));
                scopeXYPlot.addDomainMarker(markerx);
            }


            }
            catch(Exception e) {
                for (int j = 0; j < (numRows * numCol); j++) {

                    XYPlot scopeXYPlot = charts[j].getXYPlot();
                    scopeXYPlot.clearDomainMarkers();
                    scopeXYPlot.setRangeCrosshairVisible(false);
                }
            }

        }
        @Override
        public void chartMouseMoved (ChartMouseEvent cme){

        }
        };
    }
  
    protected JToggleButton zoomAllB(ChartPanel[] chartPanels, int numRows, int numCol,JFreeChart[] charts){

        //Create button to zoom all axis.
        JToggleButton zoomAll= new JToggleButton("Zoom All");

        ValueAxis[] range = new ValueAxis[numRows * numCol];
        ValueAxis[] domain = new ValueAxis[numRows * numCol];


        for (int i = 0; i < (numRows * numCol); i++) {
            range[i] = charts[i].getXYPlot().getRangeAxis();
            domain[i] = charts[i].getXYPlot().getDomainAxis();
        }

        ItemListener itemListener = e -> {
            int state = e.getStateChange();

            if(state == ItemEvent.SELECTED) {
                for (int i = 0; i < (numRows * numCol); i++) {
                    range[i].setRange(range[numRows*numCol - i -1].getRange());
                    domain[i].setRange(domain[numRows*numCol - i -1].getRange());
                    charts[i].getXYPlot().setRangeAxis(range[0]);
                    charts[i].getXYPlot().setDomainAxis(domain[0]);
                }
                zoomAll.setText("Zoom All:On");
            }
            else {
                for (int i = 0; i < (numRows * numCol); i++) {
                    range[i].setRange(range[0].getRange());
                    domain[i].setRange(domain[0].getRange());
                    charts[i].getXYPlot().setRangeAxis(range[i]);
                    charts[i].getXYPlot().setDomainAxis(domain[i]);
                }
                zoomAll.setText("Zoom All:Off");
            }
        };
        zoomAll.addItemListener(itemListener);

        return zoomAll;
    }

    protected double getMaxValue(Unipolar[] electrodes, int numRows, int numCol){

        //Calculate max. abs. value amongst all data
        double maxElement=0;

        for(int i=0; i < (numCol*numRows); i++){
            Double[] electrodeData= electrodes[i].getData();
            for (Double electrodeDatum : electrodeData) {
                if (abs(electrodeDatum) > maxElement) {
                    maxElement = abs(electrodeDatum);
                }
            }
        }
        return maxElement;
    }
}

