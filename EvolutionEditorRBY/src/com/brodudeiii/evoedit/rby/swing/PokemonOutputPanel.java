package com.brodudeiii.evoedit.rby.swing;

import javax.swing.JList;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

public class PokemonOutputPanel extends JScrollPane {

	MainFrame mainFrame;
	JList<String> pokemonList = null;
	
	public PokemonOutputPanel(MainFrame mainFrame) {
		this.mainFrame = mainFrame;
		pokemonList = new JList(mainFrame.getDataManager().getPokemonNamesArrayPokedexOrder());
		pokemonList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		pokemonList.setLayoutOrientation(JList.VERTICAL);
		pokemonList.setVisibleRowCount(-1);
		
		setViewportView(pokemonList);
		pokemonList.addListSelectionListener(new ListListener());
	}
	
	public void setSelection(int selection) {
		pokemonList.setSelectedIndex(selection);
		pokemonList.ensureIndexIsVisible(pokemonList.getSelectedIndex());
	}
	
	public void setSelection(String selection) {
		if(selection  == null) {
			pokemonList.clearSelection();
		}
		for(int i =0; i < pokemonList.getModel().getSize(); i++) {
			if(pokemonList.getModel().getElementAt(i) != null && pokemonList.getModel().getElementAt(i).equals(selection)) {
				setSelection(i);
			}
			
		}
	}
	
	private class ListListener implements ListSelectionListener {

		@Override
		public void valueChanged(ListSelectionEvent e) {
		}
		
	}
}
