package com.brodudeiii.evoedit.rby.swing;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;

public class StonesPanel extends JPanel {
	
	private MainFrame mainFrame;
	List<JRadioButton> buttons;
	
	public static class STONE {
		public final static int MOON = 10;
		public final static int LEAF = 47;
		public final static int WATER = 34;
		public final static int FIRE = 32;
		public final static int THUNDER = 33;
	}
	
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
		
		buttons = new ArrayList<JRadioButton>();
		buttons.add(radioLeaf);
		buttons.add(radioFire);
		buttons.add(radioWater);
		buttons.add(radioThunder);
		buttons.add(radioMoon);
		
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
	
	public void setStone(int stone) {
		switch(stone) {
			case STONE.MOON:
				setSelectedStone("MOON");
				break;
			case STONE.LEAF:
				setSelectedStone("LEAF");
				break;
			case STONE.FIRE:
				setSelectedStone("FIRE");
				break;
			case STONE.THUNDER:
				setSelectedStone("THUNDER");
				break;
			case STONE.WATER:
				setSelectedStone("WATER");
				break;
			default:
				mainFrame.displayError("ERROR: " + stone + " is not a valid entry for stone type.");
				break;
		}
	}
	
	public void setSelectedStone(String stone) {
		for(JRadioButton button : buttons) {
			if(button.getActionCommand().equals(stone)) {
				button.setSelected(true);
			}
		}
	}
	
	private class StonesListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			
			
		}
		
	}
}
