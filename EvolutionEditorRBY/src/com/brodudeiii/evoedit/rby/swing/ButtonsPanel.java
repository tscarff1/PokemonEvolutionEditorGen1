package com.brodudeiii.evoedit.rby.swing;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JPanel;

public class ButtonsPanel extends JPanel {
	private MainFrame mainFrame;
	
	public static final String ACTION_APPLY="APPLY";
	public static final String ACTION_REVERT="REVERT";
	
	public ButtonsPanel(MainFrame mainFrame) {
		this.mainFrame = mainFrame;
		setPreferredSize(new Dimension(MainFrame.MID_PANEL_WIDTH, 45));
		setBorder(BorderFactory.createLoweredBevelBorder());
		
		ButtonListener btnListener = new ButtonListener();
		
		JButton applyBtn = new JButton("APPLY");
		applyBtn.setActionCommand(ACTION_APPLY);
		applyBtn.setPreferredSize(new Dimension(110, 30));
		applyBtn.addActionListener(btnListener);
		
		JButton revertBtn = new JButton("REVERT");
		revertBtn.setActionCommand(ACTION_REVERT);
		revertBtn.setPreferredSize(new Dimension(110, 30));
		revertBtn.addActionListener(btnListener);
		
		add(applyBtn);
		add(revertBtn);
	}
	
	
	private class ButtonListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			if(e.getActionCommand().equals(ACTION_APPLY)) {
				
			} else if(e.getActionCommand().equals(ACTION_REVERT)) {
				
			}
		}
		
	}
}
