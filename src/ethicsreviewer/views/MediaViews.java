package ethicsreviewer.views;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class MediaViews {
	
	// loads different media windows depending on the label clicked on
	public void loadMedia(String labelName)
	{
		if(labelName.equals("Leveson starts speaking"))
		{
			displayMediaWindow1();
		}
		
		if(labelName.equals("Leveson stops speaking"))
		{
			displayMediaWindow2();
		}
		
	}
    
    private void displayMediaWindow1()
    {
    	// Create window, give it a title
        JFrame frame = new JFrame("Introduction");
        
        // Define what happens when you click the "x" button
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        // Make a new border layouts for the content in the window
        JPanel contentPane = new JPanel();
        contentPane.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        contentPane.setLayout(new BorderLayout());
        contentPane.setPreferredSize(new Dimension(1000,600));
        
        JPanel titleBar = new JPanel();
        titleBar.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        titleBar.setLayout(new BorderLayout());
        titleBar.setBackground(new Color(100,100,100));
        titleBar.setPreferredSize(new Dimension(1000,50));
        
        
        JLabel mediaTitle = new JLabel();
        mediaTitle.setText("Leveson starts speaking");
        mediaTitle.setSize(new Dimension(200,50));
        mediaTitle.setFont(new Font("Arial",1,24));
        mediaTitle.setBorder(BorderFactory.createEmptyBorder(0, 10, 0, 0));
        
        titleBar.add(mediaTitle, BorderLayout.CENTER);
        
        contentPane.add(titleBar, BorderLayout.NORTH);
        
        
        JPanel mediaContainer = new JPanel();
        mediaContainer.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        mediaContainer.setLayout(new BorderLayout());
        mediaContainer.setBackground(new Color(200,200,200));
        mediaContainer.setPreferredSize(new Dimension(1000,500));
        contentPane.add(mediaContainer, BorderLayout.CENTER);
        
        
        JPanel buttonsBar = new JPanel();
        buttonsBar.setLayout(new BorderLayout());
        buttonsBar.setBackground(new Color(230,230,230));
        buttonsBar.setPreferredSize(new Dimension(1000,50));
        
        
        JPanel backButtonContainer = new JPanel();
        backButtonContainer.setLayout(new BorderLayout());
        backButtonContainer.setBorder(BorderFactory.createEmptyBorder(5, 200, 5, 200));
        backButtonContainer.setBackground(new Color(150,150,150));
        backButtonContainer.setPreferredSize(new Dimension(500,50));
        
        JPanel nextButtonContainer = new JPanel();
        nextButtonContainer.setLayout(new BorderLayout());
        nextButtonContainer.setBorder(BorderFactory.createEmptyBorder(5, 200, 5, 200));
        nextButtonContainer.setBackground(new Color(111,111,111));
        nextButtonContainer.setPreferredSize(new Dimension(500,50));
        
        JButton backButton = new JButton();
        backButton.setText("Back");
        
        JButton nextButton = new JButton();
        nextButton.addActionListener(new nextOnClickListener());
        nextButton.setText("Next");
        
        nextButtonContainer.add(nextButton, BorderLayout.CENTER);
        backButtonContainer.add(backButton, BorderLayout.CENTER);
        
        buttonsBar.add(backButtonContainer, BorderLayout.WEST);
        buttonsBar.add(nextButtonContainer, BorderLayout.EAST);
        
        contentPane.add(buttonsBar, BorderLayout.SOUTH);
        
        frame.setContentPane(contentPane);
        frame.pack();
        frame.setLocationByPlatform(true);
        frame.setVisible(true);
    }
    
    private void displayMediaWindow2()
    {
        
    }
    
    class nextOnClickListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent arg0) {
			new ResponseView().openScreen();
			
		}
    	
    }

}
