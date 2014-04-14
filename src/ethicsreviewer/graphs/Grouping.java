package ethicsreviewer.graphs;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.TransferHandler;

public class Grouping extends JFrame {

	private static final long serialVersionUID = 1L;

	public Grouping(String applicationTitle, String chartTitle, ArrayList<JLabel> responses, String[] categoryNames, int qnum){
	
		//Create view
		super(applicationTitle);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        // Make a new border layout for the content in the window
        JPanel contentPane = new JPanel();
        contentPane.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
        contentPane.setLayout(new BorderLayout());
        contentPane.setPreferredSize(new Dimension(1500,800));
        
        
        
        //Top panel
        JPanel titlePanel = createPanel(1500,100, Color.white);
        contentPane.add(titlePanel, BorderLayout.NORTH);
        
        //Bottom panel
        JPanel responsePanel = createPanel(1500,700, Color.white);
        contentPane.add(responsePanel, BorderLayout.SOUTH);
        
        //A response panel
        JPanel aPanel = createPanel(500,700, Color.white);
        responsePanel.add(aPanel, BorderLayout.WEST);
        JPanel aBox = createBoxPanel(500,700, Color.white);
        aPanel.add(aBox);
        
        //B response panel
        JPanel bPanel = createPanel(500,700, Color.white);
        responsePanel.add(bPanel, BorderLayout.CENTER);
        JPanel bBox = createBoxPanel(500,700, Color.white);
        bPanel.add(bBox);
        
        //C response panel
        JPanel cPanel = createPanel(500,700, Color.white);
        responsePanel.add(cPanel, BorderLayout.EAST);
        JPanel cBox = createBoxPanel(500,700, Color.white);
        cPanel.add(cBox);
        
        //A title panel
        JLabel aTitlePanel = createTitle(categoryNames[0 + 3*(qnum -1)], 500,100, Color.blue);
        titlePanel.add(aTitlePanel, BorderLayout.WEST);
        
     	//B title panel
        JLabel bTitlePanel = createTitle(categoryNames[1 + 3*(qnum -1)], 500,100, Color.red);
        titlePanel.add(bTitlePanel, BorderLayout.CENTER);
        
        //C title panel
        JLabel cTitlePanel = createTitle(categoryNames[2 + 3*(qnum -1)], 500,100, Color.green);
        titlePanel.add(cTitlePanel, BorderLayout.EAST);
        
        
        
        for(JLabel response : responses)
        {
        	JLabel copy = new JLabel(response.getText());
        	copy.setForeground(response.getForeground());
        	copy.setFont(response.getFont());
        	copy.setAlignmentX(response.getAlignmentX());
        	copy.setBorder(response.getBorder());
        	copy.setPreferredSize(response.getPreferredSize());
        	
        	if(copy.getForeground().equals(Color.blue))
        		aBox.add(copy);
        	
        	if(copy.getForeground().equals(Color.red))
        		bBox.add(copy);
        	
        	if(copy.getForeground().equals(Color.green))
        		cBox.add(copy);
        	
        }
        
        //responsePanel.repaint();
        //responsePanel.revalidate();
        
        
        
       //contentPane.add(responsePanel);
       setContentPane(contentPane);
       pack();
       setLocationByPlatform(true);
       setVisible(true);
	}
	
	private JPanel createPanel(int width, int height, Color color)
    {
		JPanel panel = new JPanel();
		
		panel.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
		panel.setBackground(color);
		panel.setPreferredSize(new Dimension(width,height));
		panel.setLayout(new BorderLayout());
		
		return panel;
    }
	
	private JPanel createBoxPanel(int width, int height, Color color)
    {
		JPanel panel = new JPanel();
		
		panel.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
		panel.setBackground(color);
		panel.setPreferredSize(new Dimension(width,height));
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
		
		return panel;
    }
	
	private JLabel createTitle(String text, int width, int height, Color color)
    {
		JLabel title = new JLabel(text);
		
		title.setBorder(BorderFactory.createEmptyBorder(0, 20, 0, 0));
		title.setForeground(color);
		title.setFont(new Font("Calibri", Font.BOLD, 35));
		title.setPreferredSize(new Dimension(width,height));
		title.setLayout(new BorderLayout());
		
		return title;
    }
	
}

