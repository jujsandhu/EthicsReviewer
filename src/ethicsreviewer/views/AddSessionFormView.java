package ethicsreviewer.views;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

import java.awt.*;
import java.awt.event.*;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

import javax.swing.*;

import ethicsreviewer.views.Sessions.createButtonListener;

public class AddSessionFormView extends JDialog{
	
	 /**
	 * Doesnt do anything yet
	 */
	private static final long serialVersionUID = 1L;

	public AddSessionFormView() {
			super();
		
			final String[] labels = {"Session Name: ", "Passphrase: "};
		    int labelsLength = labels.length;
		    final JTextField[] textField = new JTextField[labels.length];
		    //Create and populate the panel.
		    JPanel p = new JPanel(new SpringLayout());
		    for (int i = 0; i < labelsLength; i++) {
		        JLabel l = new JLabel(labels[i], JLabel.TRAILING);
		        p.add(l);
		        textField[i] = new JTextField(10);
		        l.setLabelFor(textField[i]);
		        p.add(textField[i]);
		    }
		    JButton button = new JButton("Submit");
		    p.add(new JLabel());
		    p.add(button);
		   

		    //Lay out the panel.
		    SpringUtilities.makeCompactGrid(p,
		                                    labelsLength + 1, 2, //rows, cols
		                                    7, 7,        //initX, initY
		                                    7, 7);       //xPad, yPad

		    
		    //Create and set up the window.
		    final JFrame frame = new JFrame("Create New Session From");
		    

		    //Set up the content pane.
		    p.setOpaque(true);  //content panes must be opaque
		    p.setBackground(Color.white);
		    frame.setContentPane(p);
		    frame.setBackground(Color.white);

		    //Display the window.
		    frame.pack();
		    frame.setVisible(true);
		    
		    button.addActionListener(new ActionListener() {

		        public void actionPerformed(ActionEvent e)
		        {
		            for (int i = 0 ; i < labels.length ; i++)
		            {
		            	
		    			
		            	System.out.println(labels[i]+"->"+textField[i].getText());
		            	frame.dispose();
		            }
		        }
		    });  
		    
		}
	}
	class submitButtonListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			
		}
	

}

    


