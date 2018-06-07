package com.brodudeiii.evoedit.rby.swing;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Container;
import java.awt.Dimension;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;

public class MainFrame extends JFrame {
	private static String[] pokemonNames = {"Bulbasaur", "Ivysaur", "Venusaur", "Squirtle", "Wartortle", "Blastoise", "Charmander", "Charmeleon", 
			"Charizard", "Caterpie", "Metapod", "Butterfree", "Weedle", "Kakuna", "Beedrill", "Pidgey", "Pidgeotto", "Pidgeot", "Rattata", 
			"Raticate", "Meowth", "Persian", "Pikachu", "Raichu", "Nidoran", "Nidorino", "Nidoking", "Nidorina", "Nidoqueen", "Mankey", 
			"Primeape", "Machop", "Machoke", "Machamp", "Poliwag", "Poliwhirl", "Poliwrath", "Spearow", "Fearow", "Geodude", "Graveler", "Golem",
			"Onix", "Diglett", "Dugtrio", "Zubat", "Golbat", "Magikarp", "Gyarados", "Clefairy", "Clefable", "Jigglypuff", "Wigglytuff", "Paras", 
			"Parasect", "Ekans", "Arbok", "Staryu", "Starmie", "Horsea", "Seadra", "Sandshrew", "Sandslash", "Bellsprout", "Weepinbell", 
			"Victreebel", "Oddish", "Gloom", "Vileplume", "Abra", "Kadabra", "Alakazam", "Slowpoke", "Slowbro", "Eevee", "Flareon", "Jolteon"
			,"Vaporeon", "Magnemite", "Magneton", "Drowzee", "Hypno", "Exeggcute", "Exeggutor", "Seel", "Dewgong", "Jynx", "Tangela", "Scyther",
			"Pinsir", "Chansey", "Dratini", "Dragonair", "Dragonite", "Tentacool", "Tentacruel", "Venonat", "Venomoth", "Articuno", "Zapdos", 
			"Moltres", "Snorlax", "Mewtwo", "Mew", "Growlithe", "Arcanine", "Ponyta", "Rapidash", "Ditto", "Koffing", "Weezing", "Grimer", "Muk",
			"Magmar", "Voltorb", "Electrode", "Electabuzz", "Kangaskhan", "Gastly", "Haunter", "Gengar", "Cubone", "Marowak", "Psyduck", "Golduck",
			"Hitmonlee", "Hitmonchan", "Kabuto", "Kabutops", "Omanyte", "Omastar", "Lapras", "Aerodactyl", "Rhyhorn", "Rhydon", "Tauros", "Vulpix"
			,"Ninetales", "Farfetch'd", "Doduo", "Dodrio", "Krabby", "Kingler"};
	
	public static final int RADIOHGAP = 40;
	public static final int MID_PANEL_WIDTH = 260;
	
	
	private MethodsPanel methodsPanel;
	private JPanel detailsPanel;
	private StonesPanel stonesPanel;
	private LevelPanel levelPanel;
	private JPanel buttonsPanel;
	private JPanel blankPanel;
	
	public static class Method {
		public static final String NONE = "NONE";
		public static final String LEVEL = "LEVEL";
		public static final String STONE = "STONE";
		public static final String TRADE = "TRADE";
	}
	
	public MainFrame() {
		super("Evolution Editor - RBY");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(500, 320);
		this.setResizable(false);
		
		this.setJMenuBar(new MenuBar(this));
		
		JList<String> list = new JList(pokemonNames);
		list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		list.setLayoutOrientation(JList.VERTICAL);
		list.setVisibleRowCount(-1);
		
		JList<String> list2 = new JList(pokemonNames);
		list2.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		list2.setLayoutOrientation(JList.VERTICAL);
		list2.setVisibleRowCount(-1);
		
		JScrollPane pokemonScroll1 = new JScrollPane(list);
		JScrollPane pokemonScroll2 = new JScrollPane(list2);
		Container content = this.getContentPane();
		content.setLayout(new BorderLayout());
		content.add(pokemonScroll1, BorderLayout.LINE_START);
		content.add(pokemonScroll2, BorderLayout.LINE_END);
		
		setupMiddlePanel();
		
		this.setVisible(true);
	}

	
	private void setupMiddlePanel() {
		Container content = this.getContentPane();
		JPanel middlePanel = new JPanel();
		middlePanel.setBorder(BorderFactory.createLoweredBevelBorder()); 
		
		methodsPanel = new MethodsPanel(this);
		stonesPanel = new StonesPanel(this);
		levelPanel = new LevelPanel(this);
		setupBlankPanel();
		setupButtonsPanel();
		
		detailsPanel = new JPanel(new CardLayout());
		detailsPanel.add(blankPanel, Method.NONE);
		detailsPanel.add(stonesPanel, Method.STONE);
		detailsPanel.add(levelPanel, Method.LEVEL);
		
		middlePanel.add(methodsPanel);
		middlePanel.add(detailsPanel);
		middlePanel.add(buttonsPanel);
		content.add(middlePanel, BorderLayout.CENTER);
	}
	
	private void setupBlankPanel() {
		blankPanel = new JPanel();
		levelPanel.setPreferredSize(new Dimension(MID_PANEL_WIDTH,100));
		levelPanel.setBorder(BorderFactory.createLoweredBevelBorder());
	}
	
	public void setDetailsPanel(String method){
		CardLayout container = (CardLayout) detailsPanel.getLayout();
		switch (method) {
			case Method.NONE:
			case Method.TRADE:
				container.show(detailsPanel, Method.NONE);
				break;
			case Method.LEVEL:
				container.show(detailsPanel, Method.LEVEL);
				break;
			case Method.STONE:
				container.show(detailsPanel, Method.STONE);
				break;
			default:
				break;
		}
	}
	
	public void displayError(String message) {
		JOptionPane.showMessageDialog(null, message, "Error", JOptionPane.ERROR_MESSAGE);
	}
	
	private void setupButtonsPanel() {
		buttonsPanel = new JPanel();
		buttonsPanel.setPreferredSize(new Dimension(MID_PANEL_WIDTH, 45));
		buttonsPanel.setBorder(BorderFactory.createLoweredBevelBorder());
		JButton applyBtn = new JButton("APPLY");
		applyBtn.setPreferredSize(new Dimension(110, 30));
		JButton revertBtn = new JButton("REVERT");
		revertBtn.setPreferredSize(new Dimension(110, 30));
		buttonsPanel.add(applyBtn);
		buttonsPanel.add(revertBtn);
	}
}
