package ethicsreviewer.views;

import java.awt.event.ActionListener;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

import ethicsreviewer.controller.CurrentSession;
import ethicsreviewer.controller.Login;

import java.awt.*;
import java.awt.event.*;
import java.util.*;

public class StudentLogin extends JDialog implements ActionListener{
	private JPanel myPanel = null;
    private JButton loginButton = null;
    private JButton noButton = null;
    private JTextField passPhrase = null; 
    private boolean answer = false;
    public boolean getAnswer() { return answer; }
    
    public StudentLogin(JFrame frame, boolean modal, String myMessage) {
    	// Constructor creates dialog box and opens login panel
        super(frame, modal);
        myPanel = new JPanel();
        getContentPane().add(myPanel);
        myPanel.setBackground(new Color(255,255,255));
        myPanel.add(new JLabel(myMessage));
        
        passPhrase = new JTextField("Enter Passphrase Here");
        passPhrase.setPreferredSize( new Dimension( 200, 24 ) );
        myPanel.add(passPhrase); 
      
        loginButton = new JButton("Log in");
        loginButton.addActionListener(this);
        myPanel.add(loginButton); 
        
        pack();
        setLocationRelativeTo(frame);
        setVisible(true);
        
        
    }

    public void actionPerformed(ActionEvent e) {
    	// Checks is input has changed
        if(loginButton == e.getSource()) {
        	Login login = new Login();
            System.err.println("Logging in");
            if (login.verifyPassphrase(passPhrase.getText()) == false){
            	dispose();
            	new StudentLogin(null, rootPaneCheckingEnabled, "You need to type a passprahse");
            	loginButton.setEnabled(false); 
              }
              else {
                loginButton.setEnabled(true);
                answer = true;
                dispose();
                CurrentSession.setUser("Student");
                timeflow.app.TimeflowAppLauncher.launch("settings/examples/8. Leveson Inquiry1.start");
             }
            
        }
    }

}
