package ethicsreviewer.views;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashMap;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import ethicsreviewer.controller.Response;

public class ResponseView {
	
	HashMap<Integer, String> responseList;
    JTextArea responseArea;
    JTextArea responseArea2;
    JTextArea responseArea3;
	public ResponseView(){
		responseList = new HashMap<Integer,String>();
		
	}
	
	public void openScreen(){

		// Create window, give it a title
        JFrame frame = new JFrame("Response Screen");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        // Make a new border layout for the content in the window
        JPanel contentPane = new JPanel();
        contentPane.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        contentPane.setLayout(new BorderLayout());
        contentPane.setPreferredSize(new Dimension(1000,725));
        
        JPanel transcriptPanel = new JPanel();
        transcriptPanel.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        transcriptPanel.setBackground(new Color(255,255,255));
        transcriptPanel.setPreferredSize(new Dimension(550,400));
        contentPane.add(transcriptPanel, BorderLayout.WEST);
        
        JTextArea transcript = new JTextArea(15,38);
        transcript.setText(readTranscript("New evidence from Daily Telegraph.txt"));
        transcript.setLineWrap(true);
        transcript.setEditable(false);
        transcript.setBorder(BorderFactory.createEmptyBorder(5,5,5,5));
        JScrollPane transcriptScrollPane = new JScrollPane(transcript);
        transcriptPanel.add(transcriptScrollPane, BorderLayout.NORTH);
        
        JLabel rulesTitle = new JLabel("Rules");
        rulesTitle.setFont(new Font("Calibri", Font.PLAIN, 40));
        rulesTitle.setForeground(Color.black);
        rulesTitle.setBorder(BorderFactory.createEmptyBorder(5,150,5,150));
        transcriptPanel.add(rulesTitle, BorderLayout.CENTER);
        
        JTextArea rules = new JTextArea(15,38);
        rules.setText(readRules("New evidence from Daily Telegraph rules.txt"));
        rules.setLineWrap(true);
        rules.setEditable(false);
        rules.setBorder(BorderFactory.createEmptyBorder(5,5,5,5));
        JScrollPane rulesScrollPane = new JScrollPane(rules);
        transcriptPanel.add(rulesScrollPane, BorderLayout.SOUTH);
        
        //title panel
        JPanel titleContainer = new JPanel(null);
        titleContainer.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        titleContainer.setLayout(new BorderLayout());
        titleContainer.setBackground(new Color(255,255,255));
        titleContainer.setPreferredSize(new Dimension(600,50));
        
        JLabel transcriptTitle = new JLabel("Transcript");
        transcriptTitle.setFont(new Font("Calibri", Font.PLAIN, 40));
        transcriptTitle.setForeground(Color.black);
        transcriptTitle.setBorder(BorderFactory.createEmptyBorder(0, 190, 0, 0));
        titleContainer.add(transcriptTitle, BorderLayout.WEST);
        
        
        JLabel responseTitle = new JLabel("Response");
        responseTitle.setFont(new Font("Calibri", Font.PLAIN, 40));
        responseTitle.setForeground(Color.black);
        responseTitle.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 150));
        titleContainer.add(responseTitle, BorderLayout.EAST);
        contentPane.add(titleContainer, BorderLayout.NORTH);
        
        setUpTextPanel(contentPane);
        
        frame.setContentPane(contentPane);
        frame.pack();
        frame.setLocationByPlatform(true);
        frame.setVisible(true);
	}
	
	public void setUpTextPanel(JPanel contentPane){
		JPanel textPanel = new JPanel();
		textPanel.setLayout(new BorderLayout());
        textPanel.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        textPanel.setBackground(new Color(255,255,255));
        textPanel.setPreferredSize(new Dimension(440,700));
        contentPane.add(textPanel, BorderLayout.EAST);
        
        JPanel questionsPanel = new JPanel();
        questionsPanel.setLayout(new BorderLayout());
        questionsPanel.setBorder(BorderFactory.createEmptyBorder(5, 5, 0, 5));
        questionsPanel.setBackground(new Color(255,255,255));
        textPanel.add(questionsPanel, BorderLayout.NORTH);
        
        JPanel question1Panel = new JPanel();
        question1Panel.setLayout(new BorderLayout());
        question1Panel.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        question1Panel.setBackground(new Color(255,255,255));
        questionsPanel.add(question1Panel, BorderLayout.NORTH);

        JLabel question = new JLabel();
        question.setText("<html><p><b>Question:</b><br><br> The interviewee states he had no idea what the editor was planning on publishing, " 
        +"\n"+"how true do you think this is?</p></html>");
        question1Panel.add(question, BorderLayout.NORTH);
        
        JPanel answer1Panel = new JPanel();
        answer1Panel.setLayout(new BorderLayout());
        answer1Panel.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        answer1Panel.setBackground(new Color(255,255,255));
        question1Panel.add(answer1Panel, BorderLayout.SOUTH);
        
        JLabel responseLabel = new JLabel();
        responseLabel.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        responseLabel.setText("Please Enter Your Response Here:");
        answer1Panel.add(responseLabel, BorderLayout.NORTH);
        
        responseArea = new JTextArea(5,10);
        JScrollPane scrollpane = new JScrollPane(responseArea);
        answer1Panel.add(scrollpane, BorderLayout.SOUTH);
        
        
        
        JPanel question2Panel = new JPanel();
        question2Panel.setLayout(new BorderLayout());
        question2Panel.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        question2Panel.setBackground(new Color(255,255,255));
        questionsPanel.add(question2Panel, BorderLayout.CENTER);

        JLabel question2 = new JLabel();
        question2.setText("<html><p><b>Question:</b><br><br> The interviewee states he had no idea what the editor was planning on publishing, " 
        +"\n"+"how true do you think this is?</p></html>");
        question2Panel.add(question2, BorderLayout.NORTH);
        
        JPanel answer2Panel = new JPanel();
        answer2Panel.setLayout(new BorderLayout());
        answer2Panel.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        answer2Panel.setBackground(new Color(255,255,255));
        question2Panel.add(answer2Panel, BorderLayout.CENTER);
        
        JLabel responseLabel2 = new JLabel();
        responseLabel2.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        responseLabel2.setText("Please Enter Your Response Here:");
        answer2Panel.add(responseLabel2, BorderLayout.NORTH);
        
        responseArea2 = new JTextArea(5,10);
        JScrollPane scrollpane2 = new JScrollPane(responseArea2);
        answer2Panel.add(scrollpane2, BorderLayout.SOUTH);
           
        
        JPanel question3Panel = new JPanel();
        question3Panel.setLayout(new BorderLayout());
        question3Panel.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        question3Panel.setBackground(new Color(255,255,255));
        questionsPanel.add(question3Panel, BorderLayout.SOUTH);

        JLabel question3 = new JLabel();
        question3.setText("<html><p><b>Question:</b><br><br> The interviewee states he had no idea what the editor was planning on publishing, " 
        +"\n"+"how true do you think this is?</p></html>");
        question3Panel.add(question3, BorderLayout.NORTH);
        
        JPanel answer3Panel = new JPanel();
        answer3Panel.setLayout(new BorderLayout());
        answer3Panel.setBorder(BorderFactory.createEmptyBorder(5, 5, 0, 5));
        answer3Panel.setBackground(new Color(255,255,255));
        question3Panel.add(answer3Panel, BorderLayout.CENTER);
        
        JLabel responseLabel3 = new JLabel();
        responseLabel3.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        responseLabel3.setText("Please Enter Your Response Here:");
        answer3Panel.add(responseLabel3, BorderLayout.NORTH);
        
        responseArea3 = new JTextArea(5,10);
        JScrollPane scrollpane3 = new JScrollPane(responseArea3);
        answer3Panel.add(scrollpane3, BorderLayout.SOUTH);
        
        //buttonPanel
        JPanel buttonContainer = new JPanel();
        buttonContainer.setBorder(BorderFactory.createEmptyBorder(0,5,5,5));
        buttonContainer.setBackground(new Color(255,255,255));
        buttonContainer.setPreferredSize(new Dimension(200,55));
        
        textPanel.add(buttonContainer, BorderLayout.SOUTH);
        
        JButton next = new JButton("Next");
        next.setPreferredSize(new Dimension(80,40));
        
        next.addActionListener(new NextButtonListener());
        buttonContainer.add(next);
	}
	
	class NextButtonListener implements ActionListener{
        //iterate in the future if JTextArea is dynamic
		@Override
		public void actionPerformed(ActionEvent arg0) {
			responseList.put(1, responseArea.getText());
			responseList.put(2, responseArea2.getText());
			responseList.put(3, responseArea3.getText());
			
			Response.uploadResponses(responseList);
			new GraphView().Open();
		}
		
	}
	
	public String readTranscript(String transcriptFileName){
		String text = "";
		String line = "";
		
		try{
			BufferedReader reader = new BufferedReader(new FileReader("settings/transcripts/" + transcriptFileName));
			
			while((line = reader.readLine()) != null)
				text = text +"\n"+line;
			
			reader.close();
			
	} catch(Exception e){
		  e.printStackTrace();
	}
		
		return text;
   }
	
	public String readRules(String rulesFileName){
		String text = "";
		String line = "";
		
		try{
			BufferedReader reader = new BufferedReader(new FileReader("settings/rules/" + rulesFileName));
			
			while((line = reader.readLine()) != null)
				text = text +"\n"+line;
			
			reader.close();
			
	} catch(Exception e){
		  e.printStackTrace();
	}
		
		return text;
   }
	
	
}