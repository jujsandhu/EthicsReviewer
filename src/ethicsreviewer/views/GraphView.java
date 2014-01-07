package ethicsreviewer.views;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import ethicsreviewer.graphs.PieChart;

public class GraphView {

	
	public void Open(){
		// Create window, give it a title
        JFrame frame = new JFrame("Graph Screen");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        // Make a new border layout for the content in the window
        JPanel contentPane = new JPanel();
        contentPane.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        contentPane.setBackground(new Color(255,255,255));
        contentPane.setLayout(new BorderLayout());
        contentPane.setPreferredSize(new Dimension(1000,765));
        
        JPanel responsePanel = new JPanel();
        responsePanel.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        responsePanel.setBackground(new Color(255,255,255));
        responsePanel.setPreferredSize(new Dimension(500,400));
        contentPane.add(responsePanel, BorderLayout.WEST);
       
      //response text area
        JTextArea responses = new JTextArea(37,50);
        responses.setText(readResponses());
        responses.setLineWrap(true);
        responses.setEditable(false);
        responses.setBorder(BorderFactory.createEmptyBorder(5,5,5,5));
        JScrollPane responsesScrollPane = new JScrollPane(responses);
        responsesScrollPane.setBorder(null);
        responsePanel.add(responsesScrollPane, BorderLayout.NORTH);
        
        
      //title panel
        JPanel titleContainer = new JPanel(null);
        titleContainer.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        titleContainer.setLayout(new BorderLayout());
        titleContainer.setBackground(new Color(255,255,255));
        titleContainer.setPreferredSize(new Dimension(600,50));
        
        JLabel responseTitle = new JLabel("Response");
        responseTitle.setFont(new Font("Calibri", Font.PLAIN, 40));
        responseTitle.setForeground(Color.black);
        responseTitle.setBorder(BorderFactory.createEmptyBorder(0, 165, 0, 0));
        titleContainer.add(responseTitle, BorderLayout.WEST);
        
        
        JLabel graphTitle = new JLabel("Select Graph");
        graphTitle.setFont(new Font("Calibri", Font.PLAIN, 40));
        graphTitle.setForeground(Color.black);
        graphTitle.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 130));
        titleContainer.add(graphTitle, BorderLayout.EAST);
        contentPane.add(titleContainer, BorderLayout.NORTH);
        
        setUpGraphPanel(contentPane);
        
        frame.setContentPane(contentPane);
        frame.pack();
        frame.setLocationByPlatform(true);
        frame.setVisible(true);
	}
	
	public void setUpGraphPanel(JPanel contentPane){
		JPanel graphPanel = new JPanel();
		graphPanel.setLayout(new BorderLayout());
        graphPanel.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        graphPanel.setBackground(new Color(255,255,255));
        graphPanel.setPreferredSize(new Dimension(480,700));
        contentPane.add(graphPanel, BorderLayout.EAST);
		
        //overall piechart Container
		JPanel pieContainer = new JPanel(null);
		pieContainer.setBorder(BorderFactory.createEmptyBorder(20, 5, 5, 5));
		pieContainer.setLayout(new BorderLayout());
		pieContainer.setBackground(new Color(255,255,255));
		JLabel pieText = new JLabel("Pie Chart");
		pieText.setFont(new Font("Calibri", Font.ITALIC, 25));
		pieText.setBorder(BorderFactory.createEmptyBorder(0, 50, 0, 0));
		JLabel pieLabel = new JLabel(new ImageIcon(uploadPicture("pie")));
		pieLabel.addMouseListener(new PieChartListener());
		pieLabel.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 50));
		pieContainer.add(pieText, BorderLayout.WEST);
		pieContainer.add(pieLabel);
		graphPanel.add(pieContainer, BorderLayout.NORTH);
		
		//overal barchart container
		JPanel barContainer = new JPanel(null);
		barContainer.setBackground(new Color(255,255,255));
		barContainer.setLayout(new BorderLayout());
		JLabel barText = new JLabel("Bar Chart");
		barText.setFont(new Font("Calibri", Font.ITALIC, 25));
		barText.setBorder(BorderFactory.createEmptyBorder(0, 50, 0, 0));
		JLabel barLabel = new JLabel(new ImageIcon(uploadPicture("bar")));
		barContainer.add(barText, BorderLayout.WEST);
		barContainer.add(barLabel);
		graphPanel.add(barContainer, BorderLayout.SOUTH);
		
		//overall group container
		JPanel groupContainer = new JPanel(null);
		groupContainer.setBackground(new Color(255,255,255));
		groupContainer.setLayout(new BorderLayout());
		JLabel groupText = new JLabel("Grouping");
		groupText.setFont(new Font("Calibri", Font.ITALIC, 25));
		groupText.setBorder(BorderFactory.createEmptyBorder(0, 50, 0, 0));
		JLabel groupLabel = new JLabel(new ImageIcon(uploadPicture("group")));
		groupContainer.add(groupText, BorderLayout.WEST);
		groupContainer.add(groupLabel);
		graphPanel.add(groupContainer, BorderLayout.CENTER);
		
		
		
	}
	
	public BufferedImage uploadPicture(String name){
		BufferedImage myPicture = null;
		try {
			myPicture = ImageIO.read(new File("images/images_chart/"+name+".jpg"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return myPicture;
	}
	
	public String readResponses(){
		String text = "";
		String line = "";
		
		try{
			BufferedReader reader = new BufferedReader(new FileReader("settings/transcript/response_temporary.txt"));
			
			while((line = reader.readLine()) != null)
				text = text +"\n"+line;
			
			reader.close();
			
		} catch(Exception e){
			e.printStackTrace();
		}
		
		return text;
   }
    
	class PieChartListener implements MouseListener{

		@Override
		public void mouseClicked(MouseEvent arg0) {
		          PieChart demo = new PieChart("Comparison", "Was the judge's verdict correct?");
		          demo.pack();
		          demo.setVisible(true);
			
		}

		@Override
		public void mouseEntered(MouseEvent arg0) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mouseExited(MouseEvent arg0) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mousePressed(MouseEvent arg0) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mouseReleased(MouseEvent arg0) {
			// TODO Auto-generated method stub
			
		}
		
	}
}
