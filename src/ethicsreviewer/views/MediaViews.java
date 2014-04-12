	package ethicsreviewer.views;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.WindowConstants;

import chrriis.dj.nativeswing.swtimpl.NativeInterface;
import chrriis.dj.nativeswing.swtimpl.components.JWebBrowser;
import ethicsreviewer.controller.CurrentSession;

public class MediaViews {
	
	// loads different media windows depending on the label clicked on
	public void loadMedia(String labelName)
	{
		if(labelName.equals("New evidence from Daily Telegraph"))
		{
	        NativeInterface.open();
	        SwingUtilities.invokeLater(new Runnable() {
	        	 public void run() {
	        		 displayMediaWindow1();
	        		 
	        		 
	             }
	         });
	        }
			
			
		
		
		if(labelName.equals("Leveson stops speaking"))
		{
			displayMediaWindow2();
		}
		
	}
    
	public static JPanel getBrowserPanel() {
	    JPanel webBrowserPanel = new JPanel(new BorderLayout());
	    JWebBrowser webBrowser = new JWebBrowser();
	    webBrowserPanel.add(webBrowser, BorderLayout.CENTER);
	    webBrowser.setBarsVisible(false);
	    webBrowser.navigate("https://www.youtube.com/v/8UZ2n_vMOzw?fs=1");
	    return webBrowserPanel;
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
        titleBar.setBackground(new Color(255,255,255));
        titleBar.setPreferredSize(new Dimension(1000,50));
        
        
        JLabel mediaTitle = new JLabel();
        mediaTitle.setText("New evidence from Daily Telegraph");
        mediaTitle.setSize(new Dimension(200,50));
        mediaTitle.setFont(new Font("Arial",1,24));
        mediaTitle.setBorder(BorderFactory.createEmptyBorder(0, 10, 0, 0));
        
        titleBar.add(mediaTitle, BorderLayout.CENTER);
        
        contentPane.add(titleBar, BorderLayout.NORTH);
                
        JPanel panel = getBrowserPanel();
        panel.setBackground(new Color(255,255,255));
        panel.setBorder(BorderFactory.createEmptyBorder(10, 0, 0, 0));
        
        contentPane.add(panel, BorderLayout.CENTER);
        
        JPanel buttonsBar = new JPanel();
        buttonsBar.setLayout(new BorderLayout());
        buttonsBar.setBackground(new Color(255,255,255));
        buttonsBar.setPreferredSize(new Dimension(1000,90));
        
        
        JPanel backButtonContainer = new JPanel();
        backButtonContainer.setLayout(new BorderLayout());
        backButtonContainer.setBorder(BorderFactory.createEmptyBorder(20, 200, 20, 200));
        backButtonContainer.setBackground(new Color(255,255,255));
        backButtonContainer.setPreferredSize(new Dimension(500,50));
        
        JPanel nextButtonContainer = new JPanel();
        nextButtonContainer.setLayout(new BorderLayout());
        nextButtonContainer.setBorder(BorderFactory.createEmptyBorder(20, 200, 20, 200));
        nextButtonContainer.setBackground(new Color(255,255,255));
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
			String user = CurrentSession.getUser();
			if (user.equals("Student"))
			   new ResponseView().openScreen();
			else 
				new GraphView().Open();
			
		}
    	
    }

}
