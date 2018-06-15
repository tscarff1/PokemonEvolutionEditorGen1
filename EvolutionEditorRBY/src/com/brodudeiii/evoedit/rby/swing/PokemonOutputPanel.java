package com.brodudeiii.evoedit.rby.swing;

import java.util.Map;

import javax.swing.JList;
import javax.swing.ListSelectionModel;

public class PokemonOutputPanel {

	MainFrame mainFrame;
	
	public PokemonOutputPanel(MainFrame mainFrame) {
		this.mainFrame = mainFrame;
		JList<String> list = new JList(mainFrame.getDataManager().getPokemonNamesArray());
		list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		list.setLayoutOrientation(JList.VERTICAL);
		list.setVisibleRowCount(-1);
		
		
	}
}
