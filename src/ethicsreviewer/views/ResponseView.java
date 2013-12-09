package ethicsreviewer.views;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.io.BufferedReader;
import java.io.FileReader;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class ResponseView {

	public ResponseView(){
		
	}
	
	public void openScreen(){

		// Create window, give it a title
        JFrame frame = new JFrame("Response Screen");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        // Make a new border layout for the content in the window
        JPanel contentPane = new JPanel();
        contentPane.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        contentPane.setLayout(new BorderLayout());
        contentPane.setPreferredSize(new Dimension(1000,700));
        
        JPanel transcriptPanel = new JPanel();
        transcriptPanel.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        transcriptPanel.setBackground(new Color(200,200,200));
        transcriptPanel.setPreferredSize(new Dimension(500,400));
        contentPane.add(transcriptPanel, BorderLayout.WEST);
        
        JTextArea transcript = new JTextArea(35,35);
        transcript.setText(readTranscript());
        transcript.setLineWrap(true);
        transcript.setEditable(false);
        transcript.setBorder(BorderFactory.createEmptyBorder(5,5,5,5));
        JScrollPane scrollPane = new JScrollPane(transcript);
        transcriptPanel.add(scrollPane);
        
        //title panel
        JPanel titleContainer = new JPanel(null);
        titleContainer.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        titleContainer.setLayout(new BorderLayout());
        titleContainer.setBackground(new Color(100,100,100));
        titleContainer.setPreferredSize(new Dimension(600,50));
        
        JLabel title = new JLabel("Transcript");
        title.setFont(new Font("Calibri", Font.PLAIN, 40));
        title.setForeground(Color.white);
        title.setLocation(0,400);
        titleContainer.add(title, BorderLayout.WEST);
        
        
        JLabel title1 = new JLabel("Response");
        title1.setFont(new Font("Calibri", Font.PLAIN, 40));
        title1.setForeground(Color.white);
        title1.setLocation(0,400);
        titleContainer.add(title1, BorderLayout.EAST);
        contentPane.add(titleContainer, BorderLayout.NORTH);
        
        setUpTextPanel(contentPane);
        
        frame.setContentPane(contentPane);
        frame.pack();
        frame.setLocationByPlatform(true);
        frame.setVisible(true);
	}
	
	public void setUpTextPanel(JPanel contentPane){
		JPanel textPanel = new JPanel();
        textPanel.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        textPanel.setBackground(new Color(240,240,240));
        textPanel.setPreferredSize(new Dimension(500,800));
        contentPane.add(textPanel, BorderLayout.EAST);

        JLabel question = new JLabel();
        question.setPreferredSize(new Dimension(400,100));
        question.setText("<html><p><b>Question:</b><br><br> The interviewee states he had no idea what the editor was planning on publishing, " 
        +"\n"+"how true do you think this is?</p></html>");
        textPanel.add(question);
        
        JLabel responseLabel = new JLabel();
        responseLabel.setText("Please Enter Your Response Here:");
        textPanel.add(responseLabel);
        
        JTextArea responseArea = new JTextArea(10,30);
        JScrollPane scrollpane = new JScrollPane(responseArea);
        textPanel.add(scrollpane);
        
        //buttonPanel
        JPanel buttonContainer = new JPanel();
        buttonContainer.setBorder(BorderFactory.createEmptyBorder(5,5,5,5));
        buttonContainer.setBackground(new Color(240,240,240));
        buttonContainer.setPreferredSize(new Dimension(600,50));
        textPanel.add(buttonContainer, BorderLayout.SOUTH);
        JButton next = new JButton("Next");
        //view.addActionListener(new viewButtonListener());
        buttonContainer.add(next);
	}
	
	public String readTranscript(){
		String text = "";
		String line = "";
		
		try{
			BufferedReader reader = new BufferedReader(new FileReader("settings/transcript/transcript.txt"));
			
			while((line = reader.readLine()) != null)
			{
				text = text +"\n"+line;
			}
	}catch(Exception e){
		e.printStackTrace();
	 }
		
		return text;
   }
	
	
}