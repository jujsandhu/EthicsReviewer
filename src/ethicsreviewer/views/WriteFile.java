package ethicsreviewer.views;

import java.io.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.applet.Applet;
import java.net.*;

public class WriteFile extends Applet{
	Button write = new Button("WriteToFile");
	Label label1 = new Label("Enter the file name:");
	TextField text = new TextField(20);
	Label label2 = new Label("Write your text:");
	TextArea area = new TextArea(10,20);
	public void init(){
		add(label1);
		label1.setBackground(Color.lightGray);
		add(text);
		add(label2);
		label2.setBackground(Color.lightGray);
		add(area);
		add(write,BorderLayout.CENTER);
		write.addActionListener(new ActionListener (){
			public void actionPerformed(ActionEvent e){
				new WriteText();
			}
		});
	}
	public class WriteText {
		WriteText(){
			try {
				String str = text.getText();
				if(str.equals("")){
					JOptionPane.showMessageDialog(null,"Please enter the file name!");
					text.requestFocus();
				}
				else{
				File f = new File(str);
				if(f.exists()){
					BufferedWriter out = new BufferedWriter(new FileWriter(f,true));
					if(area.getText().equals("")){
						JOptionPane.showMessageDialog(null,"Please enter your text!");
						area.requestFocus();
					}
					else{
						out.write(area.getText());
						if(f.canWrite()){
							JOptionPane.showMessageDialog(null,"Text is written in "+str);
							text.setText("");
							area.setText("");
							text.requestFocus();
						}
						else{
							JOptionPane.showMessageDialog(null,"Text isn't written in "+str);
						}
						out.close();
						}
					}
					else{
						JOptionPane.showMessageDialog(null,"File not found!");
						text.setText("");
						text.requestFocus();
					}
				}
			}
			catch(Exception x){
				x.printStackTrace();
			}
		}
	}
}