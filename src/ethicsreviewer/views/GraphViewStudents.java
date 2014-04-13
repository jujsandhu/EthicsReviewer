package ethicsreviewer.views;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.SwingWorker;
import javax.swing.TransferHandler;

import ethicsreviewer.controller.Response;
import ethicsreviewer.graphs.PieChart;
import ethicsreviewer.views.GraphView.DragMouseAdapter;
import ethicsreviewer.views.GraphView.NextButtonListener;
import ethicsreviewer.views.GraphView.PieChartListener;
import ethicsreviewer.views.GraphView.ResponseThread;

public class GraphViewStudents {

	private JFrame frame;
	private JPanel contentPane, responsePanel, titleContainer, graphPanel, pieContainer, barContainer, groupContainer, drawButtonContainer, responses;
	private JPanel groupingPanel, groupAContainer, groupADropContainer, groupBContainer, groupBDropContainer, groupCContainer, groupCDropContainer;
	private JPanel responsesContainer;
	private JLabel responseTitle, graphTitle, groupText, groupLabel, barText, barLabel, pieText, pieLabel;
	private JLabel groupAText, groupBText, groupCText, instructionText;
	private JButton groupADrop, groupBDrop, groupCDrop, draw;
	private MouseListener listener;
	ArrayList<String> responseList;
	ArrayList<JLabel> responsePanels;
	private ResponseThread worker;
	private static JComponent component;
	
	private int questionNum;
	
	public void Open(){
		
		
		// Create window, give it a title
        frame = new JFrame("Graph Screen for Students");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        // Make a new border layout for the content in the window
        contentPane = new JPanel();
        contentPane.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        contentPane.setBackground(new Color(255,255,255));
        contentPane.setLayout(new BorderLayout());
        contentPane.setPreferredSize(new Dimension(1100,800));
        
        responsePanel = new JPanel();
        responsePanel.setLayout(new BorderLayout());
        responsePanel.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        responsePanel.setBackground(new Color(255,255,255));
        
        contentPane.add(responsePanel, BorderLayout.WEST);
        
        responsesContainer = new JPanel();
        responsesContainer.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        responsesContainer.setBackground(new Color(255,255,255));
        responsesContainer.setLayout(new BoxLayout(responsesContainer, BoxLayout.Y_AXIS));
        responsesContainer.setPreferredSize(new Dimension(500,1400));
        
        responses = new JPanel();
        responses.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        responses.setBackground(new Color(255,255,255));
        responses.setLayout(new BoxLayout(responses, BoxLayout.Y_AXIS));
        
        responsesContainer.add(responses);
        
        JScrollPane responsesScrollPanel = new JScrollPane(responsesContainer);
        responsesScrollPanel.setPreferredSize(new Dimension(530,650));
        responsesScrollPanel.setBackground(new Color(255,255,255));
        responsesScrollPanel.setBorder(BorderFactory.createEmptyBorder(25, 5, 5, 5));
        
        responsePanel.add(responsesScrollPanel, BorderLayout.CENTER);
        
        instructionText = new JLabel("<html>The interviewer states that he had<br>" +
        		"no idea what the editor was planning on<br>publishing, " +
        		"how true do you think this is?</html>");
        instructionText.setFont(new Font("Calibri", Font.BOLD, 25));
        instructionText.setAlignmentX(Component.CENTER_ALIGNMENT);
        instructionText.setBorder(BorderFactory.createEmptyBorder(5, 50, 5, 5));
        
        responsePanel.add(instructionText, BorderLayout.NORTH);
        
        /*******NEW THREAD TO CARRY OUT MESSENGING*********/
        
        //which question number to retrieve
        questionNum = 1;
        
        responseList = Response.getResponseByQuestionNum(questionNum);
		responsePanels = new ArrayList<JLabel>();
        for(int i = 0; i<responseList.size();i++ ){
        	responsePanels.add(addResponse("<html>" +responseList.get(i)+ "<html>"));
        	responses.add(responsePanels.get(responsePanels.size()-1));
        }
        worker = new ResponseThread();
        worker.execute();
        
      //title panel
        titleContainer = new JPanel(null);
        titleContainer.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        titleContainer.setLayout(new BorderLayout());
        titleContainer.setBackground(new Color(255,255,255));
        titleContainer.setPreferredSize(new Dimension(500,50));
        
        responseTitle = new JLabel("Responses");
        responseTitle.setFont(new Font("Calibri", Font.PLAIN, 40));
        responseTitle.setForeground(Color.black);
        responseTitle.setBorder(BorderFactory.createEmptyBorder(0, 165, 0, 0));
        titleContainer.add(responseTitle, BorderLayout.WEST);
        
        
        graphTitle = new JLabel("View Responses in Chart");
        graphTitle.setFont(new Font("Calibri", Font.PLAIN, 40));
        graphTitle.setForeground(Color.black);
        graphTitle.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 90));
        titleContainer.add(graphTitle, BorderLayout.EAST);
        contentPane.add(titleContainer, BorderLayout.NORTH);
        
        setUpGraphPanel(contentPane);
        
        frame.setContentPane(contentPane);
        frame.pack();
        frame.setLocationByPlatform(true);
        frame.setVisible(true);
        
	}
	
	private JLabel addResponse(String responseText)
	{
		JLabel responseLabel = new JLabel(responseText);
		responseLabel.setFont(new Font("Calibri", Font.ITALIC, 25));
		responseLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
		responseLabel.setBorder(BorderFactory.createEmptyBorder(25, 25, 25, 25));
		responseLabel.setPreferredSize(new Dimension(500, 100));
		
		return responseLabel;
	}
	
	public void setUpGraphPanel(JPanel contentPane){
		
		graphPanel = new JPanel();
		graphPanel.setLayout(new BorderLayout());
        graphPanel.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        graphPanel.setBackground(new Color(255,255,255));
        graphPanel.setPreferredSize(new Dimension(550,800));
        contentPane.add(graphPanel, BorderLayout.EAST);
		
        //overall piechart Container
		pieContainer = new JPanel(null);
		pieContainer.setBorder(BorderFactory.createEmptyBorder(20, 5, 5, 5));
		pieContainer.setLayout(new BorderLayout());
		pieContainer.setBackground(new Color(255,255,255));
		JButton Piebutton = new JButton("Pie Chart");
		Piebutton.setFont(new Font("Calibri", Font.ITALIC, 25));
		//Piebutton.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
		pieLabel = new JLabel(new ImageIcon(uploadPicture("pie")));
		Piebutton.addActionListener(new NextButtonListener());
		//pieLabel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
		pieContainer.add(Piebutton, BorderLayout.NORTH);
		pieContainer.add(pieLabel);
		graphPanel.add(pieContainer, BorderLayout.NORTH);
		
		//overal barchart container
		barContainer = new JPanel(null);
		barContainer.setBackground(new Color(255,255,255));
		barContainer.setLayout(new BorderLayout());
		JButton Barbutton = new JButton("Bar Chart");
		Barbutton.setFont(new Font("Calibri", Font.ITALIC, 25));
		//Barbutton.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
		barLabel = new JLabel(new ImageIcon(uploadPicture("bar")));
		Barbutton.addActionListener(new NextButtonListener());
		//barLabel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
		barContainer.add(Barbutton, BorderLayout.NORTH);
		barContainer.add(barLabel);
		graphPanel.add(barContainer, BorderLayout.CENTER);
		
		//overall group container
		groupContainer = new JPanel(null);
		groupContainer.setBackground(new Color(255,255,255));
		groupContainer.setLayout(new BorderLayout());
		JButton Groupingbutton = new JButton("Grouping Chart");
		Groupingbutton.setFont(new Font("Calibri", Font.ITALIC, 25));
		//Groupingbutton.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
		groupLabel = new JLabel(new ImageIcon(uploadPicture("group")));
		Groupingbutton.addActionListener(new NextButtonListener());
		//groupLabel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
		groupContainer.add(Groupingbutton, BorderLayout.NORTH);
		groupContainer.add(groupLabel);
		graphPanel.add(groupContainer, BorderLayout.SOUTH);
		
		
	}
	
	
	class NextButtonListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent arg0) {
			worker.cancel(true);
			PieChart demo = new PieChart("Comparison", "Was the judge's verdict correct?");
	        demo.pack();
	        demo.setVisible(true);
			
		}
		
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
			BufferedReader reader = new BufferedReader(new FileReader("settings/transcripts/response_temporary.txt"));
			
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

      class ResponseThread extends SwingWorker<Void,String>{
		@Override
		protected void process(List<String> chunks) {
            responsePanels.add(addResponse("<html>" +chunks.get(chunks.size()-1)+ "<html>"));
            JLabel latestResponse = responsePanels.get(responsePanels.size()-1); 
          	responses.add(latestResponse);
          	latestResponse.addMouseListener(listener);
          	latestResponse.setTransferHandler(new TransferHandler("text"));
          	responses.repaint();
          	responses.revalidate();
		}

		@Override
		protected Void doInBackground() throws Exception {
			while(!isCancelled())
			{
				int size = responseList.size();
				responseList = Response.getResponseByQuestionNum(questionNum);
				int newSize = responseList.size();				
				
					for(int i = size; i <= newSize-1 ; i++){
						publish(responseList.get(i));
					}	

				try {
					Thread.sleep(3000);
				} catch (InterruptedException e) {
				}	

			}
			return null;	
		}
	  };
  
}

