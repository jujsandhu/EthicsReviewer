package timeflow.app.ui.filter;

import javax.swing.*;

import java.awt.event.*;

import timeflow.data.db.*;
import timeflow.data.time.*;
import timeflow.model.*;

import java.awt.*;

public class SearchPanel extends ModelPanel {

	JTextField entry;
    JCheckBox invert;
	
	public SearchPanel(TFModel model, final FilterControlPanel f) {
		super(model);
		setBackground(Color.white);
		setBorder(BorderFactory.createEmptyBorder(15, 5,15,0));
		setLayout(new GridLayout(1,1));
		JPanel top=new JPanel();
		top.setLayout(new BorderLayout());
		add(top);
		top.setBackground(Color.white);
		JLabel label=model.getDisplay().label("Search");
		top.add(label, BorderLayout.WEST);
		entry=new JTextField(8);
		top.add(entry, BorderLayout.CENTER);
		entry.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				f.makeFilter();
			}});
		
		
		JPanel east=new JPanel();
		top.add(east, BorderLayout.EAST);
		east.setBackground(Color.white);
		
		invert=new JCheckBox("Invert", false);
		east.add(invert, BorderLayout.CENTER);
		invert.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				f.setInverted(invert.isSelected());
			}});
		invert.setFont(f.getModel().getDisplay().small());
		invert.setForeground(Color.gray);
		invert.setBackground(Color.white);
		
		
		JPanel clearPanel=new JPanel();
		clearPanel.setBackground(new Color(245, 245, 245));
		clearPanel.setLayout(new GridLayout(1,1));
		JButton clear=new JButton(new ImageIcon("images/button_clear_all.gif"));
		clear.setBorder(null);
		clear.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				f.clearFilters();
			}
		});
		clearPanel.add(clear);
		east.add(clearPanel, BorderLayout.EAST);
		
	}

	@Override
	public void note(TFEvent e) {
	}

}
