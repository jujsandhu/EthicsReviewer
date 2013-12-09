package ethicsreviewer.views;

import java.awt.event.ActionListener;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

import java.awt.*;
import java.awt.event.*;
import java.util.*;

public class studentLogin extends JDialog implements ActionListener{
	private JPanel myPanel = null;
    private JButton loginButton = null;
    private JButton noButton = null;
    private JTextField passPhrase = null; 
    private boolean answer = false;
    public boolean getAnswer() { return answer; }
    
    public studentLogin(JFrame frame, boolean modal, String myMessage) {
    	// Constructor creates dialog box and opens login panel
        super(frame, modal);
        myPanel = new JPanel();
        getContentPane().add(myPanel);
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
            System.err.println("Logging in");
            if (passPhrase.getText().equals("Enter Passphrase Here")){
            	dispose();
            	new studentLogin(null, rootPaneCheckingEnabled, "You need to type a passprahse");
            	loginButton.setEnabled(false); 
              }
              else {
                loginButton.setEnabled(true);
                answer = true;
                dispose();
                Sessions startSession = new Sessions();
                startSession.openScreen(); 
             }
            
        }
    }

}
