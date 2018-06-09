package com.brodudeiii.evoedit.rby.swing;

import java.util.Map;

import javax.swing.JList;
import javax.swing.ListSelectionModel;

public class PokemonOutputPanel {

	MainFrame mainFrame;
	
	public PokemonOutputPanel(MainFrame mainFrame) {
		this.mainFrame = mainFrame;
		Map<String,Integer> pointerData = mainFrame.getDataManager().getPointerData();
		String[] pokemonNames = (String[]) pointerData.keySet().toArray(new String[pointerData.keySet().size()]);
		JList<String> list = new JList(pokemonNames);
		list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		list.setLayoutOrientation(JList.VERTICAL);
		list.setVisibleRowCount(-1);
		
		
	}
}
