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

import com.brodudeiii.evoedit.rby.swing.MainFrame.Method;

public class MethodsPanel extends JPanel {
	private MainFrame mainFrame;
	
	public MethodsPanel(MainFrame mainFrame) {
		super();
		this.mainFrame = mainFrame;
		
		GridLayout methodsLayout = new GridLayout(0,2);
		methodsLayout.setHgap(MainFrame.RADIOHGAP);
		this.setLayout(methodsLayout);
		

		setPreferredSize(new Dimension(MainFrame.MID_PANEL_WIDTH,100));
		setBorder(BorderFactory.createLoweredBevelBorder());
		
		JLabel methodsLabel = new JLabel("Evolution Method");
		JRadioButton methodNone = new JRadioButton(Method.NONE);
		JRadioButton methodLevelUp = new JRadioButton(Method.LEVEL);
		JRadioButton methodStone = new JRadioButton(Method.STONE);
		JRadioButton methodTrade = new JRadioButton(Method.TRADE);
		ButtonGroup evolveMethodBtnGrp = new ButtonGroup();
		methodNone.setSelected(true);

		List<JRadioButton> methodButtons = new ArrayList<>();
		methodButtons.add(methodNone);
		methodButtons.add(methodLevelUp);
		methodButtons.add(methodStone);
		methodButtons.add(methodTrade);
		
		add(methodsLabel);
		add(new JPanel());
	
		MethodsListener methodsRadioListener = new MethodsListener();
		
		for(JRadioButton radioBtn : methodButtons) {
			evolveMethodBtnGrp.add(radioBtn);
			radioBtn.addActionListener(methodsRadioListener);
			add(radioBtn);
		}
	}
	
	private class MethodsListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			JRadioButton  radioAction = (JRadioButton) e.getSource();
			if(radioAction.getText().equals(MainFrame.Method.STONE)) {
				mainFrame.setDetailsPanel(MainFrame.Method.STONE);
			} else if(radioAction.getText().equals(MainFrame.Method.LEVEL)) {
				mainFrame.setDetailsPanel(MainFrame.Method.LEVEL);
			} else {
				mainFrame.setDetailsPanel(MainFrame.Method.NONE);
			}
			
		}
	}
}
