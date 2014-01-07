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
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

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
import javax.swing.TransferHandler;

import ethicsreviewer.graphs.PieChart;

public class GraphView {

	private JFrame frame;
	private JPanel contentPane, responsePanel, titleContainer, graphPanel, pieContainer, barContainer, groupContainer, drawButtonContainer, responses;
	private JPanel groupingPanel, groupAContainer, groupADropContainer, groupBContainer, groupBDropContainer, groupCContainer, groupCDropContainer;
	private JPanel responsesContainer;
	private JLabel responseTitle, graphTitle, groupText, groupLabel, barText, barLabel, pieText, pieLabel;
	private JLabel groupAText, groupBText, groupCText, instructionText;
	private JLabel response1, response2, response3, response4, response5, response6, response7, response8, response9;
	private JButton groupADrop, groupBDrop, groupCDrop, draw;
	
	public void Open(){
		
		
		// Create window, give it a title
        frame = new JFrame("Graph Screen");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        // Make a new border layout for the content in the window
        contentPane = new JPanel();
        contentPane.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        contentPane.setBackground(new Color(255,255,255));
        contentPane.setLayout(new BorderLayout());
        contentPane.setPreferredSize(new Dimension(1000,765));
        
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
 
        response1 = addResponse("<html>I think that he should have taken some<br>more time to" +
        		" put questions to the inquiry council.</html>");
        responses.add(response1);
        
        response2 = addResponse("<html>I think that he should have taken some<br>more time to" +
        		" put questions to the inquiry council.</html>");
        responses.add(response2);
        
        response3 = addResponse("<html>I think that he should have taken some<br>more time to" +
        		" put questions to the inquiry council.</html>");
        responses.add(response3);
        
        response4 = addResponse("<html>I think that he should have taken some<br>more time to" +
        		" put questions to the inquiry council.</html>");
        responses.add(response4);
        
        response5 = addResponse("<html>I think that he should have taken some<br>more time to" +
        		" put questions to the inquiry council.</html>");
        responses.add(response5);
        
        response6 = addResponse("<html>I think that he should have taken some<br>more time to" +
        		" put questions to the inquiry council.</html>");
        responses.add(response6);
        
        response7 = addResponse("<html>I think that he should have taken some<br>more time to" +
        		" put questions to the inquiry council.</html>");
        responses.add(response7);
        
        response8 = addResponse("<html>I think that he should have taken some<br>more time to" +
        		" put questions to the inquiry council.</html>");
        responses.add(response8);
        
        response9 = addResponse("<html>I think that he should have taken some<br>more time to" +
        		" put questions to the inquiry council.</html>");
        responses.add(response9);
        
        
        MouseListener listener = new DragMouseAdapter();
        response1.addMouseListener(listener);
        response2.addMouseListener(listener);
        response3.addMouseListener(listener);
        response4.addMouseListener(listener);
        response5.addMouseListener(listener);
        response6.addMouseListener(listener);
        response7.addMouseListener(listener);
        response8.addMouseListener(listener);
        response9.addMouseListener(listener);
        
        /*
        response1.setTransferHandler(new TransferHandler("icon"));
        response2.setTransferHandler(new TransferHandler("icon"));
        response3.setTransferHandler(new TransferHandler("icon"));
        response4.setTransferHandler(new TransferHandler("icon"));
        response5.setTransferHandler(new TransferHandler("icon"));
        response6.setTransferHandler(new TransferHandler("icon"));
        response7.setTransferHandler(new TransferHandler("icon"));
        response8.setTransferHandler(new TransferHandler("icon"));
        response9.setTransferHandler(new TransferHandler("icon"));
        */
        /*
        groupADrop.setTransferHandler(new TransferHandler("text"));
        groupBDrop.setTransferHandler(new TransferHandler("text"));
        groupCDrop.setTransferHandler(new TransferHandler("text"));
        */
        
      //title panel
        titleContainer = new JPanel(null);
        titleContainer.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        titleContainer.setLayout(new BorderLayout());
        titleContainer.setBackground(new Color(255,255,255));
        titleContainer.setPreferredSize(new Dimension(600,50));
        
        responseTitle = new JLabel("Responses");
        responseTitle.setFont(new Font("Calibri", Font.PLAIN, 40));
        responseTitle.setForeground(Color.black);
        responseTitle.setBorder(BorderFactory.createEmptyBorder(0, 165, 0, 0));
        titleContainer.add(responseTitle, BorderLayout.WEST);
        
        
        graphTitle = new JLabel("Pie Chart groupings");
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
	
	public void setUpGraphPanel(JPanel contentPane){
		
		graphPanel = new JPanel();
		graphPanel.setLayout(new BorderLayout());
        graphPanel.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        graphPanel.setBackground(new Color(255,255,255));
        graphPanel.setPreferredSize(new Dimension(480,700));
        contentPane.add(graphPanel, BorderLayout.EAST);
		
        //overall piechart Container
		pieContainer = new JPanel(null);
		pieContainer.setBorder(BorderFactory.createEmptyBorder(20, 5, 5, 5));
		pieContainer.setLayout(new BorderLayout());
		pieContainer.setBackground(new Color(255,255,255));
		pieText = new JLabel("Pie Chart");
		pieText.setFont(new Font("Calibri", Font.ITALIC, 25));
		pieText.setBorder(BorderFactory.createEmptyBorder(0, 50, 0, 0));
		pieLabel = new JLabel(new ImageIcon(uploadPicture("pie")));
		pieLabel.addMouseListener(new PieChartListener());
		pieLabel.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 50));
		pieContainer.add(pieText, BorderLayout.WEST);
		pieContainer.add(pieLabel);
		graphPanel.add(pieContainer, BorderLayout.NORTH);
		
		//overal barchart container
		barContainer = new JPanel(null);
		barContainer.setBackground(new Color(255,255,255));
		barContainer.setLayout(new BorderLayout());
		barText = new JLabel("Bar Chart");
		barText.setFont(new Font("Calibri", Font.ITALIC, 25));
		barText.setBorder(BorderFactory.createEmptyBorder(0, 50, 0, 0));
		barLabel = new JLabel(new ImageIcon(uploadPicture("bar")));
		barContainer.add(barText, BorderLayout.WEST);
		barContainer.add(barLabel);
		graphPanel.add(barContainer, BorderLayout.SOUTH);
		
		//overall group container
		groupContainer = new JPanel(null);
		groupContainer.setBackground(new Color(255,255,255));
		groupContainer.setLayout(new BorderLayout());
		groupText = new JLabel("Grouping");
		groupText.setFont(new Font("Calibri", Font.ITALIC, 25));
		groupText.setBorder(BorderFactory.createEmptyBorder(0, 50, 0, 0));
		groupLabel = new JLabel(new ImageIcon(uploadPicture("group")));
		groupContainer.add(groupText, BorderLayout.WEST);
		groupContainer.add(groupLabel);
		graphPanel.add(groupContainer, BorderLayout.CENTER);
		
		
	}
	
	public void setUpGroupingPanel(JPanel contentPane) {
		
		groupingPanel = new JPanel();
		groupingPanel.setLayout(new BoxLayout(groupingPanel, BoxLayout.Y_AXIS));
		groupingPanel.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
		groupingPanel.setBackground(new Color(255,255,255));
		groupingPanel.setPreferredSize(new Dimension(480,700));
        contentPane.add(groupingPanel, BorderLayout.EAST);
		
        instructionText = new JLabel("Drag the responses into the groups below");
        instructionText.setFont(new Font("Calibri", Font.ITALIC, 25));
        instructionText.setAlignmentX(Component.CENTER_ALIGNMENT);
        instructionText.setBorder(BorderFactory.createEmptyBorder(40, 10, 40, 0));
        
        groupingPanel.add(instructionText);
        
		groupAContainer = new JPanel(null);
		groupAContainer.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
		groupAContainer.setAlignmentX(Component.CENTER_ALIGNMENT);
		groupAContainer.setLayout(new BoxLayout(groupAContainer, BoxLayout.Y_AXIS));
		groupAContainer.setBackground(new Color(255,255,255));
		
		groupingPanel.add(groupAContainer);
		
		groupAText = new JLabel("Group A");
		groupAText.setFont(new Font("Calibri", Font.ITALIC, 25));
		groupAText.setBorder(BorderFactory.createEmptyBorder(0, 135, 0, 0));
		
		groupAContainer.add(groupAText, BorderLayout.NORTH);
		
        groupADropContainer = new JPanel();
        groupADropContainer.setBorder(BorderFactory.createEmptyBorder(5,5,5,5));
        groupADropContainer.setBackground(new Color(255,255,255));
        groupADropContainer.setPreferredSize(new Dimension(200,100));
        
        groupAContainer.add(groupADropContainer, BorderLayout.SOUTH);
        
        groupADrop = new JButton("A");
        groupADrop.setPreferredSize(new Dimension(200,100));
        groupADrop.setFocusable(false);
        
        groupADropContainer.add(groupADrop);
        
        
        
        groupBContainer = new JPanel(null);
        groupBContainer.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        groupBContainer.setAlignmentX(Component.CENTER_ALIGNMENT);
        groupBContainer.setLayout(new BoxLayout(groupBContainer, BoxLayout.Y_AXIS));
        groupBContainer.setBackground(new Color(255,255,255));
		
		groupingPanel.add(groupBContainer);
		
		groupBText = new JLabel("Group B");
		groupBText.setFont(new Font("Calibri", Font.ITALIC, 25));
		groupBText.setBorder(BorderFactory.createEmptyBorder(0, 135, 0, 0));
		
		groupBContainer.add(groupBText, BorderLayout.NORTH);
		
        groupBDropContainer = new JPanel();
        groupBDropContainer.setBorder(BorderFactory.createEmptyBorder(5,5,5,5));
        groupBDropContainer.setBackground(new Color(255,255,255));
        groupBDropContainer.setPreferredSize(new Dimension(200,100));
        
        groupBContainer.add(groupBDropContainer, BorderLayout.SOUTH);
        
        groupBDrop = new JButton("B");
        groupBDrop.setPreferredSize(new Dimension(200,100));
        groupBDrop.setFocusable(false);
        
        groupBDropContainer.add(groupBDrop);
        
        
        
        
        groupCContainer = new JPanel(null);
		groupCContainer.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
		groupCContainer.setAlignmentX(Component.CENTER_ALIGNMENT);
		groupCContainer.setLayout(new BoxLayout(groupCContainer, BoxLayout.Y_AXIS));
		groupCContainer.setBackground(new Color(255,255,255));
		
		groupingPanel.add(groupCContainer);
		
		groupCText = new JLabel("Group C");
		groupCText.setFont(new Font("Calibri", Font.ITALIC, 25));
		groupCText.setBorder(BorderFactory.createEmptyBorder(0, 135, 0, 0));
		
		groupCContainer.add(groupCText, BorderLayout.NORTH);
		
        groupCDropContainer = new JPanel();
        groupCDropContainer.setBorder(BorderFactory.createEmptyBorder(5,5,5,5));
        groupCDropContainer.setBackground(new Color(255,255,255));
        groupCDropContainer.setPreferredSize(new Dimension(200,100));
        
        groupCContainer.add(groupCDropContainer, BorderLayout.SOUTH);
        
        groupCDrop = new JButton("C");
        groupCDrop.setPreferredSize(new Dimension(200,100));
        groupCDrop.setFocusable(false);
        
        groupCDropContainer.add(groupCDrop);
        
        
        
        
        
        //buttonPanel
        drawButtonContainer = new JPanel();
        drawButtonContainer.setBorder(BorderFactory.createEmptyBorder(5,5,5,5));
        drawButtonContainer.setBackground(new Color(255,255,255));
        drawButtonContainer.setPreferredSize(new Dimension(200,55));
        
        groupingPanel.add(drawButtonContainer, BorderLayout.SOUTH);
        
        draw = new JButton("Draw");
        draw.setPreferredSize(new Dimension(80,40));
        
        draw.addActionListener(new NextButtonListener());
        drawButtonContainer.add(draw);
        
        
        
		
	}
	
	private JLabel addResponse(String responseText)
	{
		JLabel responseLabel = new JLabel(responseText);
		responseLabel.setFont(new Font("Calibri", Font.ITALIC, 25));
		responseLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
		responseLabel.setBorder(BorderFactory.createEmptyBorder(25, 25, 25, 25));
		responseLabel.setPreferredSize(new Dimension(520, 100));
		
		return responseLabel;
	}
	
	class DragMouseAdapter extends MouseAdapter {
        public void mousePressed(MouseEvent e) {
            JComponent c = (JComponent) e.getSource();
            TransferHandler handler = c.getTransferHandler();
            handler.exportAsDrag(c, e, TransferHandler.COPY);
        }
        
		public void mouseReleased(MouseEvent e) {
        	
        }
        
    }
	
	class NextButtonListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent arg0) {

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
			
			graphPanel.setVisible(false);
			setUpGroupingPanel(contentPane);
			
			response1.setForeground(Color.blue);
			
			responses.revalidate();
			responses.repaint();
			
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
