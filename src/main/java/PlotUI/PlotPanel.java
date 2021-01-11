package PlotUI;

import DataHandling.Electrode;
import DataHandling.ElectrodeDB;
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
    protected int numCols;
    protected int combsRows;
    protected int combsCols;

    public PlotPanel(ElectrodeDB electrodeDB){ setDimensions(electrodeDB); }

    //PROTECTED METHODS SHARED BY GRID AND SINGLE PANEL

    private void setDimensions(ElectrodeDB electrodeDB){
        //Unipolar
        numRows = electrodeDB.getNumRows();
        numCols = electrodeDB.getNumCols();
        //Bipolar
        int totalE = numRows*(numCols-1)+numCols*(numRows-1);

        if(totalE % 2 == 0){
            combsRows = totalE/2;
        }
        else{
            combsRows = (totalE/2)+1;
        }

        combsCols = 2;
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

    protected JFreeChart createChart(XYDataset dataset, String title, Electrode[] electrodes, int numRows, int numCol) {

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


        //Set all yAxis equal initially
        ValueAxis yAxis= plot.getRangeAxis();
        yAxis.setRange(-getMaxValue(electrodes,numRows,numCol),getMaxValue(electrodes,numRows,numCol));

        return chart;
    }

    protected JButton restoreZoomB(ChartPanel[] chartPanels, Electrode[] electrodes, int numRows, int numCol, JFreeChart[] charts){

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

    protected JButton clearMarkers( JFreeChart[] charts){
        //Create button to remove markers.

        return new JButton(new AbstractAction("Clear Markers") {
            @Override
            public void actionPerformed(ActionEvent e) {
                for(int i = 0; i < (numRows*numCols); i++){
                    XYPlot scopeXYPlot = charts[i].getXYPlot();
                    scopeXYPlot.clearDomainMarkers();
                    scopeXYPlot.setRangeCrosshairVisible(false);
                }
            }
        });
    }
  
    protected ChartMouseListener CreateMouseListener(ChartPanel chartPanel, Electrode[] electrodes, int numRows, int numCol, JFreeChart[] charts) {
      
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
                // Make sure the range cross-hair is on
                scopeXYPlot.setRangeCrosshairVisible(true);
                // and plot it
                scopeXYPlot.setRangeCrosshairValue(yy, true);

                ValueMarker markerX = new ValueMarker(xx);
                markerX.setLabelOffsetType(LengthAdjustmentType.EXPAND);
                markerX.setPaint(Color.black);
                markerX.setLabel(String.format("X: %-1.3f Y: %-1.3f", xx, yy));
                markerX.setLabelPaint(Color.black);
                markerX.setLabelAnchor(RectangleAnchor.TOP_RIGHT);
                markerX.setLabelTextAnchor(TextAnchor.TOP_LEFT);
                markerX.setLabelFont(new Font("Arial", Font.PLAIN, 10));
                markerX.setStroke(new BasicStroke(2.0f));
                scopeXYPlot.addDomainMarker(markerX);
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
  
    protected JToggleButton zoomAllB(int numRows, int numCol,JFreeChart[] charts){

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

    protected double getMaxValue(Electrode[] electrodes, int numRows, int numCol){

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

