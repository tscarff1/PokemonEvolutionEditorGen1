package com.brodudeiii.evoedit.rby.swing;

import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class LevelPanel extends JPanel {
	private MainFrame mainFrame;
	private JTextField levelText;
	
	
	public LevelPanel(MainFrame mainFrame) {
		this.mainFrame = mainFrame;
		setLayout(new GridLayout(0,1));
		setPreferredSize(new Dimension(MainFrame.MID_PANEL_WIDTH,100));
		setBorder(BorderFactory.createLoweredBevelBorder());
		add(new JLabel("Evolves starting at level: "));
		JPanel textPanel = new JPanel();
		levelText = new JTextField(20);
		textPanel.add(levelText);
		add(textPanel);
	}
	
	public int getLevel() throws Exception {
		String levelContent = levelText.getText();
		if(levelContent.isEmpty()) {
			throw new Exception("Error: Level cannot be empty.");
		}
		
		int level = Integer.parseInt(levelContent);
		
		if(level < 1 || level > 100) {
			throw new Exception("Error: Level must be in the range 1-100.");
		}
		
		return level;
	}
}
