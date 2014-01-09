package ethicsreviewer.views;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import timeflow.app.TimeflowApp;

public class Sessions {
	
	private int row;
	String[] examples;
	
	public Sessions(){
		row = -1;
	}
	
	public void openScreen(){
		// Create window, give it a title
        JFrame frame = new JFrame("Timeline Sessions");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        // Make a new border layout for the content in the window
        JPanel contentPane = new JPanel();
        contentPane.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        contentPane.setLayout(new BorderLayout());
        contentPane.setPreferredSize(new Dimension(800,400));
        contentPane.setBackground(Color.white);
        
        //middle panel
        JPanel element1 = new JPanel();
        element1.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        element1.setBackground(Color.WHITE);
        element1.setPreferredSize(new Dimension(100,100));
       
        createTable(element1, contentPane);
        
        //title panel
        JPanel titleContainer = new JPanel(null);
        titleContainer.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        titleContainer.setLayout(new BorderLayout());
        titleContainer.setBackground(Color.white);
        titleContainer.setPreferredSize(new Dimension(600,50));

        
        JLabel title = new JLabel("Timeline Sessions");
        title.setFont(new Font("Calibri", Font.PLAIN, 40));
        title.setForeground(Color.black);
        title.setHorizontalAlignment(JLabel.CENTER);
        title.setVerticalAlignment(JLabel.CENTER);
        titleContainer.add(title, BorderLayout.CENTER);
        contentPane.add(titleContainer, BorderLayout.NORTH);
        
        //button panel
        JPanel buttonContainer = new JPanel();
        buttonContainer.setBorder(BorderFactory.createEmptyBorder(5,5,5,5));
        buttonContainer.setBackground(Color.white);
        buttonContainer.setPreferredSize(new Dimension(600,50));
        
        JButton view = new JButton("View");
        view.addActionListener(new viewButtonListener());
        buttonContainer.add(view);
        
        JButton remove = new JButton("Remove");
        buttonContainer.add(remove);
        
        JButton create = new JButton("New");
        create.addActionListener(new createButtonListener());
        buttonContainer.add(create);
        contentPane.add(buttonContainer, BorderLayout.SOUTH);
        
        frame.setContentPane(contentPane);
        frame.pack();
        frame.setLocationByPlatform(true);
        frame.setVisible(true);
	}

	public void createTable(JPanel element1, JPanel contentPane){
		String columnNames[] = {"Description","Date","Time","Focus Group"};
		
		JTable table = new JTable();
        DefaultTableModel model = new DefaultTableModel(getData(),columnNames){
        	@Override
           	 public boolean isCellEditable(int row, int column){  
           		    return false;  
             }
        };
        
        table.setModel(model);
        table.getColumn("Description").setPreferredWidth(150);
        table.addMouseListener(new tableMouseListener());
        //set size of table
        JScrollPane scroll = new JScrollPane(table);
        scroll.setPreferredSize(new Dimension(700,250));
		element1.add(scroll);
		contentPane.add(element1, BorderLayout.CENTER);
		getData();
		table.setBackground(Color.white);
	}
	
	public String[][] getData(){
		examples = readExampleDirectory("settings/examples");
		String[][] data = new String[examples.length][4];
		for(int i = 0; i< examples.length; i++){
			data[i][0]=examples[i];
		}
		return data;
	}
	
	static String[] readExampleDirectory(String dir)
	{
		String[] s=new File(dir).list();
		ArrayList<String> real=new ArrayList<String>();
		for (int i=0; i<s.length; i++)
			if (!s[i].startsWith(".")){

				if (s[i].endsWith("time")){real.add(s[i].substring(0, s[i].length()-5) + "~");}
				if (s[i].endsWith("start")){real.add(s[i].substring(0, s[i].length()-6) + "`");}
				}
				
		return (String[])real.toArray(new String[0]);
	}
	
	class viewButtonListener implements ActionListener{
		String file;
		
		@Override
		public void actionPerformed(ActionEvent e) {
			if(row >= 0){
				final TimeflowApp t=new TimeflowApp();
				int length = examples[row].length();
				if (examples[row].endsWith("~")){file = "settings/examples/"+examples[row].substring(0, length -1) + ".time";}
				if (examples[row].endsWith("`")){file = "settings/examples/"+examples[row].substring(0, length -1) + ".start";}
				timeflow.app.TimeflowAppLauncher.launch(file);
			}	
		}
	}
	
	
	class createButtonListener implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
				new AddSessionFormView();
		}
	}
	
	class tableMouseListener implements MouseListener{

		@Override
		public void mouseEntered(MouseEvent arg0) {
		}
		@Override
		public void mouseExited(MouseEvent arg0) {
		}
		@Override
		public void mousePressed(MouseEvent arg0) {
		}
		@Override
		public void mouseReleased(MouseEvent arg0) {
		}
		@Override
		public void mouseClicked(MouseEvent e) {
			if(e.getClickCount() == 1){
				final JTable target = (JTable)e.getSource();
		        row = target.getSelectedRow();
			}
		}
		
	}
}
