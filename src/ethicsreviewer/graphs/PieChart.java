package ethicsreviewer.graphs;

import javax.swing.JFrame;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PiePlot3D;
import org.jfree.data.general.DefaultPieDataset;
import org.jfree.data.general.PieDataset;
import org.jfree.util.Rotation;

import ethicsreviewer.views.GraphView;


public class PieChart extends JFrame {

  private static final long serialVersionUID = 1L;

  public PieChart(String applicationTitle, String chartTitle, int qnum) {
        super(applicationTitle);
        // This will create the dataset 
        PieDataset dataset = createDataset(qnum);
        // based on the dataset we create the chart
        JFreeChart chart = createChart(dataset, chartTitle);
        // we put the chart into a panel
        ChartPanel chartPanel = new ChartPanel(chart);
        // default size
        chartPanel.setPreferredSize(new java.awt.Dimension(500, 370));
        // add it to our application
        setContentPane(chartPanel);

    }
    
    
/**
     * Creates a sample dataset 
     */
  
  	public static Double[] getPercentages(int[] catacount){
  		int total = catacount[0] + catacount[1] + catacount[2];
  		System.out.println(total);
  		Double[] percArray = new Double[3];
  		double dubtotal = total* 1.0 ;
  		for (int i= 0; i<3; i++){
  			double val = catacount[i] *100.0; 
  			percArray[i] = val/dubtotal; 
  		}
  		
  		return percArray;
  		
  	
  	}

  	private int[] halfarray(int [] array){
		for (int i = 0; i<3; i++){
			array[i] = array[i]/2;
			}
		return array;
	}
  	
    private  PieDataset createDataset(int qnum) {
        DefaultPieDataset result = new DefaultPieDataset();
        int[] CatCount = halfarray(GraphView.getcatCount(qnum));
        Double[] percentages = getPercentages(CatCount);
        String[] cataNames = GraphView.getCategoryNames(qnum);
        for (int i =0; i<3; i++){
        result.setValue(cataNames[i],new Double(percentages[i]));
        System.out.println(" Percentage [" + percentages[0] + "," + percentages[1] + "," + percentages[2] + "]" );
        
        }
        return result;
       
    }
    
    
/**
     * Creates a chart
     */

    private JFreeChart createChart(PieDataset dataset, String title) {
        
        JFreeChart chart = ChartFactory.createPieChart3D(title,          // chart title
            dataset,                // data
            true,                   // include legend
            true,
            true);

        PiePlot3D plot = (PiePlot3D) chart.getPlot();
        plot.setStartAngle(290);
        plot.setDirection(Rotation.CLOCKWISE);
        plot.setForegroundAlpha(0.5f);
        return chart;
        
    }
} 
