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

import org.jfree.ui.RefineryUtilities;

import ethicsreviewer.controller.CurrentSession;
import ethicsreviewer.controller.Response;
import ethicsreviewer.graphs.BarChart;
import ethicsreviewer.graphs.Grouping;
import ethicsreviewer.graphs.PieChart;
import ethicsreviewer.utils.ConnectDatabase;

public class GraphView {

	private JFrame frame;
	private JPanel contentPane, responsePanel, titleContainer, graphPanel, pieContainer, barContainer, groupContainer, drawButtonContainer, responses;
	private JPanel groupingPanel, groupAContainer, groupADropContainer, groupBContainer, groupBDropContainer, groupCContainer, groupCDropContainer;
	private JPanel responsesContainer;
	private JLabel responseTitle, graphTitle, groupText, groupLabel, barText, barLabel, pieText, pieLabel;
	private JLabel groupAText, groupBText, groupCText, instructionText;
	private JButton groupADrop, groupBDrop, groupCDrop, draw, nextq, drawAnother;
	private MouseListener listener;
	ArrayList<String> responseList;
	ArrayList<JLabel> responsePanels;
	private ResponseThread worker;
	private static JComponent component;
	private static int[] categoryCount = new int[9] ;
	private static String[] categoryNames = new String[] {"Untrue Statement", "Inadmissable in Count", "Deniable in Court", "Yes", "No", "Under certain conditions", "Privacy converns",
		"Public outrage", "Political Lobbying"} ;
	private static String question1 = "Question 1: What does Alastair Brett mean by off the record?";
	private static String question2 = "Question 2: Did  Alastair Brett have permission to leak the information to his colleagues?";
	private static String question3 = "Question 3: What could be the problem that Alastair Brett was so annoyed about?";
	public String chartType;
	private static int questionNum;
	
	public void Open( int questNum){
		
		questionNum = questNum;
		System.out.println(questionNum);	
		
		// Create window, give it a title
        frame = new JFrame("Graph Screen - Question: " + questionNum);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        // Make a new border layout for the content in the window
        contentPane = new JPanel();
        contentPane.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        contentPane.setBackground(new Color(255,255,255));
        contentPane.setLayout(new BorderLayout());
        contentPane.setPreferredSize(new Dimension(1200,800));
        
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
        
        if (questionNum == 1) {
        	instructionText = new JLabel("<html>Question 1<br>What does Alastair Brett mean by<br>off the record?</html>");
        }
        if (questionNum == 2) {
        	instructionText = new JLabel("<html>Question 2<br>Did Alastair Brett have permission to leak<br>the information to his colleagues?</html>");
        }
        if (questionNum == 3) {
        	instructionText = new JLabel("<html>Question 3<br>What could be the problem that <br> Alastair Brett was so annoyed about?</html>");
        }
        instructionText.setFont(new Font("Calibri", Font.BOLD, 25));
        instructionText.setAlignmentX(Component.CENTER_ALIGNMENT);
        instructionText.setBorder(BorderFactory.createEmptyBorder(5, 50, 5, 5));
        
        responsePanel.add(instructionText, BorderLayout.NORTH);
        
        /*******NEW THREAD TO CARRY OUT MESSENGING*********/
        
        //which question number to retrieve
        
        
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
        titleContainer.setPreferredSize(new Dimension(800,50));
        
        responseTitle = new JLabel("Responses");
        responseTitle.setFont(new Font("Calibri", Font.PLAIN, 40));
        responseTitle.setForeground(Color.black);
        responseTitle.setBorder(BorderFactory.createEmptyBorder(0, 165, 0, 0));
        titleContainer.add(responseTitle, BorderLayout.WEST);
        
        
        graphTitle = new JLabel("Types of Charts");
        graphTitle.setFont(new Font("Calibri", Font.PLAIN, 40));
        graphTitle.setForeground(Color.black);
        graphTitle.setBorder(BorderFactory.createEmptyBorder(0, 0, 0,180));
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
		responseLabel.setPreferredSize(new Dimension(520, 100));
		
		return responseLabel;
	}
	
	public void setUpGraphPanel(JPanel contentPane){
		
		graphPanel = new JPanel();
		graphPanel.setLayout(new BorderLayout());
        graphPanel.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        graphPanel.setBackground(new Color(255,255,255));
        graphPanel.setPreferredSize(new Dimension(600,700));
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
		barLabel.addMouseListener(new BarChartListener());
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
		groupLabel.addMouseListener(new GroupingListener());
		groupContainer.add(groupText, BorderLayout.WEST);
		groupContainer.add(groupLabel);
		graphPanel.add(groupContainer, BorderLayout.CENTER);
		
		
	}
	
	public void setUpGroupingPanel(JPanel contentPane) {
		
			
		
		groupingPanel = new JPanel();
		groupingPanel.setLayout(new BoxLayout(groupingPanel, BoxLayout.Y_AXIS));
		groupingPanel.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
		groupingPanel.setBackground(new Color(255,255,255));
		groupingPanel.setPreferredSize(new Dimension(650,700));
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
		
		groupAText = new JLabel(categoryNames[0 + 3*(questionNum -1)]);
		groupAText.setFont(new Font("Calibri", Font.ITALIC, 25));
		groupAText.setForeground(Color.blue);
		groupAText.setBorder(BorderFactory.createEmptyBorder(0, 170, 0, 0));
		
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
		
		groupBText = new JLabel(categoryNames[1 + 3*(questionNum -1)]);
		groupBText.setFont(new Font("Calibri", Font.ITALIC, 25));
		groupBText.setForeground(Color.red);
		groupBText.setBorder(BorderFactory.createEmptyBorder(0, 170, 0, 0));
		
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
		
		groupCText = new JLabel(categoryNames[2 + 3*(questionNum -1)]);
		groupCText.setFont(new Font("Calibri", Font.ITALIC, 25));
		groupCText.setForeground(Color.green);
		groupCText.setBorder(BorderFactory.createEmptyBorder(0, 170, 0, 0));
		
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
        
        resetCatCount(questionNum);        
        
        PropertyChangeListener a = new PropertyChangeListener() {
			@Override
			public void propertyChange(PropertyChangeEvent arg0) {
				// TODO Auto-generated method stub
					component.setForeground(Color.blue);
					responses.revalidate();
					responses.repaint();	
					incrementCatagory(0); 
			
				groupADrop.setText("A");
			}
        };
        
        groupADrop.addPropertyChangeListener("text", a);
        
        PropertyChangeListener b = new PropertyChangeListener() {
			@Override
			public void propertyChange(PropertyChangeEvent arg0) {
				// TODO Auto-generated method stub
				component.setForeground(Color.red);
				responses.revalidate();
				responses.repaint();
				incrementCatagory(1);
				
				groupBDrop.setText("B");
			}
        };
        
        groupBDrop.addPropertyChangeListener("text", b);
        
        PropertyChangeListener c = new PropertyChangeListener() {
			@Override
			public void propertyChange(PropertyChangeEvent arg0) {
				// TODO Auto-generated method stub
				component.setForeground(Color.green);
				responses.revalidate();
				responses.repaint();
				incrementCatagory(2);
				
				groupCDrop.setText("C");
			}
        };
        
              
        groupCDrop.addPropertyChangeListener("text", c);
      
        
        
        //buttonPanel
        drawButtonContainer = new JPanel();
        drawButtonContainer.setBorder(BorderFactory.createEmptyBorder(0,0,0,0));
        drawButtonContainer.setBackground(new Color(255,255,255));
        drawButtonContainer.setPreferredSize(new Dimension(500,150));
        
        
       
        
        draw = new JButton("Draw");
        draw.setPreferredSize(new Dimension(80,40));
        
        if (questionNum != 3) {nextq = new JButton("Next Question");}
        else {nextq = new JButton("Close");}
        
        nextq.setPreferredSize(new Dimension(150,40));
        nextq.addActionListener(new NextQuestionButtonListener());        
        
        JButton prevq;
		if (questionNum != 1) {prevq = new JButton(" Previous Question");}
        else {prevq = new JButton("Close");}
        
        prevq.setPreferredSize(new Dimension(150,40));
        prevq.addActionListener(new PrevQuestionButtonListener());
        
        
        
        if (chartType == "pie"){ draw.addActionListener(new PieButtonListener());}
        if (chartType == "bar"){ draw.addActionListener(new BarButtonListener());}
        if (chartType == "grouping"){ draw.addActionListener(new GroupingButtonListener());}
        
        drawAnother = new JButton("Draw Another Chart");     
		drawAnother.setPreferredSize(new Dimension(150,40));
		drawAnother.addActionListener(new drawAnotherListener());
		
		
		
		   JPanel subPanel1 = new JPanel();
		    subPanel1.add(prevq); 
		    subPanel1.add(draw);
	        subPanel1.add(drawAnother);
	        subPanel1.add(nextq);
	        subPanel1.setBackground(new Color(255,255,255));
	        drawButtonContainer.add(subPanel1, BorderLayout.SOUTH);
		
	        groupingPanel.add(drawButtonContainer, BorderLayout.SOUTH);
	        
	        
        listener = new DragMouseAdapter();
        for(int i = 0; i<responsePanels.size();i++){
        	responsePanels.get(i).addMouseListener(listener);
        }
        
        groupADrop.addMouseListener(listener);
        groupBDrop.addMouseListener(listener);
        groupCDrop.addMouseListener(listener);
        
        for(int i = 0; i<responsePanels.size();i++){
        	responsePanels.get(i).setTransferHandler(new TransferHandler("text"));
        }
        
        
        groupADrop.setTransferHandler(new TransferHandler("text"));
        groupBDrop.setTransferHandler(new TransferHandler("text"));
        groupCDrop.setTransferHandler(new TransferHandler("text"));
		
	}
	
	
	class DragMouseAdapter extends MouseAdapter {
		
        public void mousePressed(MouseEvent e) {
        	component = (JComponent) e.getSource();
            TransferHandler handler = component.getTransferHandler();
            handler.exportAsDrag(component, e, TransferHandler.COPY);
        }
        
    }
	
	class drawAnotherListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent arg0) {
			frame.dispose();
			new GraphView().Open(questionNum);
			
		}
		
		
	}
	
	class PieButtonListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent arg0) {
			
			int qnum = getQnum();
			Response.uploadGraphData(qnum, 0, categoryCount[0 + 3*(qnum-1)]/2);
			Response.uploadGraphData(qnum, 1, categoryCount[1 + 3*(qnum-1)]/2);
			Response.uploadGraphData(qnum, 2, categoryCount[2 + 3*(qnum-1)]/2);
			
			
			worker.cancel(true);
			PieChart demo = new PieChart("Pie Chart for Question " + questionNum, "Pie Chart for " + getQuestionString(questionNum), getQnum());
	        demo.pack();
	        demo.setVisible(true);
	        
			
		}
		
	}
	
	class BarButtonListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent arg0) {
			
			int qnum = getQnum();
			Response.uploadGraphData(qnum, 0, categoryCount[0 + 3*(qnum-1)]/2);
			Response.uploadGraphData(qnum, 1, categoryCount[1 + 3*(qnum-1)]/2);
			Response.uploadGraphData(qnum, 2, categoryCount[2 + 3*(qnum-1)]/2);
			
			
			worker.cancel(true);
			BarChart demo = new BarChart("Bar Chart for Question " + questionNum, "Bar Chart for " + getQuestionString(questionNum),qnum);
	        demo.pack();
	        RefineryUtilities.centerFrameOnScreen(demo);
	        demo.setVisible(true);
			
		}
		
	}
	
	class GroupingButtonListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent arg0) {
			
			int qnum = getQnum();
			Response.uploadGraphData(qnum, 0, categoryCount[0 + 3*(qnum-1)]/2);
			Response.uploadGraphData(qnum, 1, categoryCount[1 + 3*(qnum-1)]/2);
			Response.uploadGraphData(qnum, 2, categoryCount[2 + 3*(qnum-1)]/2);
			
			
			worker.cancel(true);
			Grouping demo = new Grouping("Grouping for Question " + questionNum, "Grouping for " + getQuestionString(questionNum), responsePanels, categoryNames, qnum);
	        demo.pack();
	        RefineryUtilities.centerFrameOnScreen(demo);
	        demo.setVisible(true);
			
		}
		
	}
	
	
	class NextQuestionButtonListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent arg0) {
			
			int qnum = getQnum();
			Response.uploadGraphData(qnum, 0, categoryCount[0 + 3*(qnum-1)]/2);
			Response.uploadGraphData(qnum, 1, categoryCount[1 + 3*(qnum-1)]/2);
			Response.uploadGraphData(qnum, 2, categoryCount[2 + 3*(qnum-1)]/2);
			
			if (questionNum != 3){ 
				frame.dispose();
				new GraphView().Open(questionNum + 1);
				}
			else{frame.dispose();}
		}
	
	}
	
	class PrevQuestionButtonListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent arg0) {
			if (questionNum != 1){ 
				frame.dispose();
				new GraphView().Open(questionNum - 1);
				}
			else{frame.dispose();}
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
	
		
	public static void resetCatCount(int qnum){
		if (qnum == 1){
			categoryCount[0] = 0;
			categoryCount[1] = 0;
			categoryCount[2] = 0;
			}
			
			if (qnum == 2){
				categoryCount[3] = 0;
				categoryCount[4] = 0;
				categoryCount[5] = 0;
			}	
			
			if (qnum == 3){
				categoryCount[6] = 0;
				categoryCount[7] = 0;
				categoryCount[8] = 0;
		   }	
				
			
		}
	
	
	public static int[] getcatCount(int qnum){
		int[] catcounter = new int[3];
		for (int i = 0; i<3; i++){
			catcounter[i] =categoryCount[i + 3*(qnum-1)];
		}
		return catcounter;
		
	}
	
	public void incrementCatagory(int catNo){
		int qNum = questionNum;
		categoryCount[catNo + 3*(qNum -1)] = categoryCount[catNo + 3*(qNum -1)] + 1;
				
	}
	
	public void setCategoryNames(int qnum, String[] catNames, String a, String b, String c){
		if (qnum == 1){
		catNames[0] = a;
		catNames[1] = b;
		catNames[2] = c;
		}
		
		if (qnum == 2){
		catNames[3] = a;
		catNames[4] = b;
		catNames[5] = c;
		}	
		
		if (qnum == 3){
			catNames[6] = a;
			catNames[7] = b;
			catNames[8] = c;
	   }	
			
		
	}
	
	
	public static int getQnum(){
		int some = questionNum;
		return some;
		
	}
	
	public static String getQuestionString(int qnum){
		if (qnum ==1) return question1;
		if (qnum ==2) return question2;
		if (qnum ==3) return question3;
		else return "bad";
	}
	
	public static String[] getCategoryNames(int qnum){
		String[] catNames = new String[3];
			for (int i = 0; i<3; i++){
				catNames[i] =categoryNames[i + 3*(qnum-1)];
			}
			return catNames;
		
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
			
			chartType = "pie";
			graphPanel.setVisible(false);
			setUpGroupingPanel(contentPane);
			
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
	
	
	class BarChartListener implements MouseListener{

		@Override
		public void mouseClicked(MouseEvent arg0) {
			
			chartType = "bar";
			graphPanel.setVisible(false);
			setUpGroupingPanel(contentPane);
			
		}

		@Override
		public void mouseEntered(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mouseExited(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mousePressed(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mouseReleased(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}
		
		
	}
	
	class GroupingListener implements MouseListener{

		@Override
		public void mouseClicked(MouseEvent arg0) {
			
			chartType = "grouping";
			graphPanel.setVisible(false);
			setUpGroupingPanel(contentPane);
			
		}

		@Override
		public void mouseEntered(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mouseExited(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mousePressed(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mouseReleased(MouseEvent e) {
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
