package com.brodudeiii.evoedit.rby.swing;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.io.File;

import javax.swing.JFileChooser;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

import com.brodudeiii.evoedit.rby.data.FileManager;

public class MenuBar extends JMenuBar{
	private MainFrame mainFrame;
	
	private static final String ACTION_OPEN = "OPEN";
	private static final String ACTION_SAVE = "SAVE";
	
	public MenuBar(MainFrame mainFrame) {
		this.mainFrame = mainFrame;
		MenuListener menuListener = new MenuListener();
		
		JMenu fileMenu = new JMenu("File");
		
		fileMenu.setMnemonic(KeyEvent.VK_F);
		fileMenu.getAccessibleContext().setAccessibleDescription("Menu for managing rom files.");
		add(fileMenu);
		
		JMenuItem openItem = new JMenuItem("Open");
		openItem.setActionCommand(ACTION_OPEN);
		openItem.addActionListener(menuListener);
		
		JMenuItem saveItem = new JMenuItem("Save");
		saveItem.setActionCommand(ACTION_SAVE);
		saveItem.addActionListener(menuListener);
		
		fileMenu.add(openItem);
		fileMenu.add(saveItem);
	}
	
	private class MenuListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			JFileChooser chooser = new JFileChooser();
			if(e.getActionCommand().equals(ACTION_OPEN)) {
				int selectedOption = chooser.showOpenDialog(mainFrame);
				
			 if (selectedOption == JFileChooser.APPROVE_OPTION) {
		            File file = chooser.getSelectedFile();
		            try {
		            	FileManager.openFile(file);
		            } catch(Exception ex) {
		            	mainFrame.displayError(ex.getMessage());
		            }
		        }
			} else if(e.getActionCommand().equals(ACTION_SAVE)) {
				int selectedOption = chooser.showSaveDialog(mainFrame);
				 if (selectedOption == JFileChooser.APPROVE_OPTION) {
			            File file = chooser.getSelectedFile();
			            //TODO: Handle saving file
			        }
			}
		}

	}
}
