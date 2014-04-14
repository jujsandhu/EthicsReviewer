package ethicsreviewer.graphs;


	import java.awt.Color;
import java.awt.Dimension;
import java.awt.GradientPaint;

import javax.swing.JFrame;

	import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.CategoryAxis;
import org.jfree.chart.axis.CategoryLabelPositions;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.renderer.category.BarRenderer;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.ui.ApplicationFrame;
import org.jfree.ui.RefineryUtilities;

import ethicsreviewer.views.GraphView;

	/**
	 * A simple demonstration application showing how to create a bar chart.
	 *
	 */
	public class BarChartStudent extends JFrame {

	    /**
	     * Creates a new demo instance.
	     *
	     * @param title  the frame title.
	     */
	    public BarChartStudent(String applicationTitle, String chartTitle, int qnum, int[] catcount) {

	    	super(applicationTitle);
	        CategoryDataset dataset = createDataset(qnum, catcount);
	        JFreeChart chart = createChart(dataset, chartTitle);
	        ChartPanel chartPanel = new ChartPanel(chart);
	        chartPanel.setPreferredSize(new Dimension(500, 370));
	        setContentPane(chartPanel);

	    }

	    
	  	private int[] halfarray(int [] array){
			for (int i = 0; i<3; i++){
				array[i] = array[i]/2;
				}
			return array;
		}
	    /**
	     * Returns a sample dataset.
	     * 
	     * @return The dataset.
	     */
	    private CategoryDataset createDataset(int qnum, int[] catcount) {
	    	 DefaultCategoryDataset dataset = new DefaultCategoryDataset();
	    	
	    	 //int[] CatCount = halfarray(GraphView.getcatCount(qnum));
	         String[] cataNames = GraphView.getCategoryNames(qnum);
	      
	         
	        final String category1 = "Category 1";
	       

	        for (int i =0; i<3; i++){
	        	dataset.addValue(catcount[i],cataNames[i],""); 
	            
	            }
	        
	        return dataset;
	        
	    }
	    
	    /**
	     * Creates a sample chart.
	     * 
	     * @param dataset  the dataset.
	     * 
	     * @return The chart.
	     */
	    private JFreeChart createChart(final CategoryDataset dataset, String title) {
	        
	        // create the chart...
	        final JFreeChart chart = ChartFactory.createBarChart(
	            title,         // chart title
	            "",               // domain axis label
	            "Responses",                  // range axis label
	            dataset,                  // data
	            PlotOrientation.VERTICAL, // orientation
	            true,                     // include legend
	            true,                     // tooltips?
	            false                     // URLs?
	        );

	        // NOW DO SOME OPTIONAL CUSTOMISATION OF THE CHART...

	        // set the background color for the chart...
	        chart.setBackgroundPaint(Color.white);

	        // get a reference to the plot for further customisation...
	        final CategoryPlot plot = chart.getCategoryPlot();
	        plot.setBackgroundPaint(Color.lightGray);
	        plot.setDomainGridlinePaint(Color.white);
	        plot.setRangeGridlinePaint(Color.white);

	        // set the range axis to display integers only...
	        final NumberAxis rangeAxis = (NumberAxis) plot.getRangeAxis();
	        rangeAxis.setStandardTickUnits(NumberAxis.createIntegerTickUnits());

	        // disable bar outlines...
	        final BarRenderer renderer = (BarRenderer) plot.getRenderer();
	        renderer.setDrawBarOutline(true);
	        
	        // set up gradient paints for series...
	        final GradientPaint gp0 = new GradientPaint(
	            0.0f, 0.0f, Color.blue, 
	            0.0f, 0.0f, Color.blue
	        );
	        final GradientPaint gp1 = new GradientPaint(
	            0.0f, 0.0f, Color.green, 
	            0.0f, 0.0f, Color.green
	        );
	        final GradientPaint gp2 = new GradientPaint(
	            0.0f, 0.0f, Color.red, 
	            0.0f, 0.0f, Color.red
	        );
	        renderer.setSeriesPaint(0, gp0);
	        renderer.setSeriesPaint(1, gp1);
	        renderer.setSeriesPaint(2, gp2);

	        final CategoryAxis domainAxis = plot.getDomainAxis();
	        domainAxis.setCategoryLabelPositions(
	            CategoryLabelPositions.createUpRotationLabelPositions(Math.PI / 6.0)
	        );
	        // OPTIONAL CUSTOMISATION COMPLETED.
	        
	        return chart;
	        
	    }
	

}
