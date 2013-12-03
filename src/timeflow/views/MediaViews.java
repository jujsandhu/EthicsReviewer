package timeflow.views;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
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

        // Make a new border layout for the content in the window
        JPanel contentPane = new JPanel();
        contentPane.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        contentPane.setLayout(new BorderLayout());
        
        // change the size of the window
        contentPane.setPreferredSize(new Dimension(300,200));
        
        // now just add more layouts to this content pane layout
        JPanel element1 = new JPanel();
        element1.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        element1.setLayout(new BorderLayout());
        // colour it dark grey
        element1.setBackground(new Color(100,100,100));
        // half the size of the window
        element1.setPreferredSize(new Dimension(150,200));
        // add this to the west side
        contentPane.add(element1, BorderLayout.WEST);
        
        
        // add a second element
        JPanel element2 = new JPanel();
        element2.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        element2.setLayout(new BorderLayout());
        // colour it light grey
        element2.setBackground(new Color(200,200,200));
        // half the size of the window
        element2.setPreferredSize(new Dimension(150,200));
        // add this to the east side
        contentPane.add(element2, BorderLayout.EAST);
        
        
        frame.setContentPane(contentPane);
        frame.pack();
        frame.setLocationByPlatform(true);
        frame.setVisible(true);
    }
    
    private void displayMediaWindow2()
    {
        
    }

}
