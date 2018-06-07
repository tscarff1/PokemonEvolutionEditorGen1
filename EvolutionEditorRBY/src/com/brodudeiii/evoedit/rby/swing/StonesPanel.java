package com.brodudeiii.evoedit.rby.swing;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;

public class StonesPanel extends JPanel {
	
	private MainFrame mainFrame;
	
	public StonesPanel(MainFrame mainFrame) {
		this.mainFrame = mainFrame;
		
		GridLayout stonesLayout = new GridLayout(0,2);
		stonesLayout.setHgap(MainFrame.RADIOHGAP);

		this.setLayout(stonesLayout);
		setPreferredSize(new Dimension(MainFrame.MID_PANEL_WIDTH,100));
		setBorder(BorderFactory.createLoweredBevelBorder());
		add(new JLabel("Evolves by using:"));
		add(new JPanel()); //spacing so label gets it's own line
		
		ButtonGroup stoneBtns = new ButtonGroup();
		JRadioButton radioThunder = new JRadioButton("THUNDER");
		JRadioButton radioWater = new JRadioButton("WATER");
		JRadioButton radioFire = new JRadioButton("FIRE");
		JRadioButton radioLeaf = new JRadioButton("LEAF");
		JRadioButton radioMoon = new JRadioButton("MOON");
		add(radioLeaf);
		radioLeaf.setSelected(true);
		add(radioFire);
		add(radioWater);
		add(radioMoon);
		add(radioThunder);
		
		stoneBtns.add(radioMoon);
		stoneBtns.add(radioLeaf);
		stoneBtns.add(radioFire);
		stoneBtns.add(radioWater);
		stoneBtns.add(radioThunder);
	}
	
	private class StonesListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			
			
		}
		
	}
}
