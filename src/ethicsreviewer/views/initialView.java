package ethicsreviewer.views;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import java.awt.event.ActionEvent;

import javax.swing.SwingUtilities;

import timeflow.app.TimeflowApp;
import timeflow.format.file.FileExtensionCatalog;

public class initialView extends JFrame implements ActionListener {

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public initialView(){
	
		//Create view
		super("InitialView");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        // Make a new border layout for the content in the window
        JPanel contentPane = new JPanel();
        contentPane.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        contentPane.setLayout(new BorderLayout());
        contentPane.setPreferredSize(new Dimension(800,400));
        
        //middle panel
        JPanel element1 = new JPanel();
        element1.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        element1.setBackground(Color.white);
        element1.setPreferredSize(new Dimension(100,75));
		        
        JLabel title = new JLabel("Leveson Inquiry Interactive Presentation Tool");
        title.setFont(new Font("Calibri", Font.PLAIN, 40));
        title.setForeground(Color.black);
        title.setLocation(0,400);
        element1.add(title, BorderLayout.CENTER);
        contentPane.add(element1, BorderLayout.NORTH);
        
        //button panel
        JPanel buttonContainer = new JPanel();
        buttonContainer.setBorder(BorderFactory.createEmptyBorder(5,5,5,5));
        buttonContainer.setBackground(new Color(100,100,100));
        buttonContainer.setPreferredSize(new Dimension(600,50));
        
       
        // Create 3 buttons
        JButton student = new JButton("Student");
        student.addActionListener((ActionListener) this);
        student.setActionCommand("Open Student Panel"); //Create action commands
        buttonContainer.add(student);
        
        JButton lecturer = new JButton("Lecturer");
        lecturer.addActionListener((ActionListener) this);
        lecturer.setActionCommand("Open Lecturer Panel");
        buttonContainer.add(lecturer);
        
        JButton Closebtn;
        Closebtn = new JButton("Close");
        Closebtn.addActionListener((ActionListener) this);
        Closebtn.setActionCommand("Close");
        buttonContainer.add(Closebtn);
       
        
        
       contentPane.add(buttonContainer);
       setContentPane(contentPane); 
       pack();
       setLocationByPlatform(true);
       setVisible(true);
	}
	
	 public void actionPerformed(ActionEvent e)
     {
         String cmd = e.getActionCommand();

         if(cmd.equals("Close"))
         {
        	// Closes current panel 
            dispose();
            System.out.println("Closing");
          
         }
         if(cmd.equals("Open Lecturer Panel"))
         {
        	// Closes current and opens lecturer login 
            dispose();
            System.out.println("Opening Lecturer Login Panel");
            final String NOT_LOGGED_IN = "LoginPanel Test - Currently Logged Out";
            final JFrame frame = new JFrame(NOT_LOGGED_IN);
            frame.getContentPane().add(new lecturerLogin());
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.pack();
            frame.setSize(500, frame.getHeight());
            frame.setVisible(true);
        }
         
         if(cmd.equals("Open Student Panel"))
         {
        	// Closes current and opens student login panel 
            dispose();
            System.out.println("Opening Student Login Panel");
            new studentLogin(null, rootPaneCheckingEnabled, "Type in session passphrase");
         }
     }
	
}
