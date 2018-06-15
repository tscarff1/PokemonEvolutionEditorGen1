package com.brodudeiii.evoedit.rby.swing;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Container;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;

import com.brodudeiii.evoedit.rby.data.DataManager;

public class MainFrame extends JFrame {
	private static String[] pokemonNames;
	
	public static final int RADIOHGAP = 40;
	public static final int MID_PANEL_WIDTH = 260;
	
	
	private MethodsPanel methodsPanel;
	private JPanel detailsPanel;
	private StonesPanel stonesPanel;
	private LevelPanel levelPanel;
	private ButtonsPanel buttonsPanel;
	private JPanel blankPanel;
	
	private DataManager dataManager;
	
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
		
		dataManager = new DataManager(this);
		
		Container content = this.getContentPane();
		content.setLayout(new BorderLayout());
		content.add(new PokemonInputPanel(this), BorderLayout.LINE_START);
		content.add(new JPanel(), BorderLayout.LINE_END);
		
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
		buttonsPanel = new ButtonsPanel(this);
		
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
	
	public DataManager getDataManager() {
		return dataManager;
	}
	
	public void setEvolutionMethod(int method) {
		switch(method) {
			case 0:
				methodsPanel.selectButton(Method.NONE);
				setDetailsPanel(Method.NONE);
				break;
			case 1:
				methodsPanel.selectButton(Method.LEVEL);
				setDetailsPanel(Method.LEVEL);
				break;
			case 2:
				methodsPanel.selectButton(Method.STONE);
				setDetailsPanel(Method.STONE);
				break;
			case 3:
				methodsPanel.selectButton(Method.TRADE);
				setDetailsPanel(Method.TRADE);
				break;
			default:
				//TODO: Handle errors better
				break;
		}
	}
	
	public void setEvolutionDetail(int evoDetail) {
		String selectedMethod = methodsPanel.getSelectedMethod();
		if(selectedMethod == null) {
			displayError("ERROR: No evolution selected.");
		}
		if(selectedMethod.equals(Method.LEVEL)) {
			levelPanel.setLevel(evoDetail);
		} else if(selectedMethod.equals(Method.STONE)) {
			stonesPanel.setStone(evoDetail);
		}
		
		
	}
}
