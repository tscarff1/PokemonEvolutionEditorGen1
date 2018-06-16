package com.brodudeiii.evoedit.rby.swing;

import java.util.Map;

import javax.swing.JList;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

public class PokemonInputPanel extends JScrollPane {

	MainFrame mainFrame;
	JList<String> pokemonList = null;
	
	public PokemonInputPanel(MainFrame mainFrame) {
		this.mainFrame = mainFrame;
		pokemonList = new JList(mainFrame.getDataManager().getPokemonNamesArrayPokedexOrder());
		pokemonList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		pokemonList.setLayoutOrientation(JList.VERTICAL);
		pokemonList.setVisibleRowCount(-1);
		setViewportView(pokemonList);
		pokemonList.addListSelectionListener(new ListListener());
	}
	
	private class ListListener implements ListSelectionListener {

		@Override
		public void valueChanged(ListSelectionEvent e) {
			mainFrame.getDataManager().setActiveInput(pokemonList.getSelectedValue().toString());
		}
		
	}
}
