package com.brodudeiii.evoedit.rby.swing;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Container;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import com.brodudeiii.evoedit.rby.data.DataManager;
import com.brodudeiii.evoedit.rby.data.FileManager;

public class MainFrame extends JFrame {
	private static String[] pokemonNames;
	
	public static final int RADIOHGAP = 40;
	public static final int MID_PANEL_WIDTH = 260;
	
	private JPanel contentPanel;
	
	private MethodsPanel methodsPanel;
	private JPanel detailsPanel;
	private StonesPanel stonesPanel;
	private LevelPanel levelPanel;
	private ButtonsPanel buttonsPanel;
	private JPanel blankPanel;
	
	private PokemonInputPanel inputPanel;
	private PokemonOutputPanel outputPanel;
	
	private DataManager dataManager;
	
	public static class Method {
		public static final String NONE = "NONE";
		public static final String LEVEL = "LEVEL";
		public static final String STONE = "STONE";
		public static final String TRADE = "TRADE";
	}
	
	public static class DisplayMode {
		public static final String CONTENT = "CONTENT";
		public static final String EMPTY = "EMPTY";
	}
	
	public MainFrame() {
		super("Evolution Editor - RBY");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(500, 320);
		this.setResizable(false);
		
		this.setJMenuBar(new MenuBar(this));
		
		dataManager = new DataManager(this);
		
		Container content = this.getContentPane();
		content.setLayout(new CardLayout());
		contentPanel = new JPanel(new BorderLayout());

		JPanel emptyPanel = new JPanel();
		emptyPanel.add(new JLabel("Please Open a ROM to begin."));
		content.add(emptyPanel, DisplayMode.EMPTY);
		content.add(contentPanel, DisplayMode.CONTENT);
		
		inputPanel = new PokemonInputPanel(this);
		outputPanel = new PokemonOutputPanel(this);
		contentPanel.add(inputPanel, BorderLayout.LINE_START);
		contentPanel.add(outputPanel, BorderLayout.LINE_END);
		setupMiddlePanel();
		
		this.setVisible(true);
	}

	
	private void setupMiddlePanel() {
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
		contentPanel.add(middlePanel, BorderLayout.CENTER);
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
	
	public void setDisplayMode(String displayMode) {
		CardLayout container = (CardLayout) this.getContentPane().getLayout();
		switch(displayMode) {
			case DisplayMode.CONTENT:
				container.show(this.getContentPane(), displayMode);
				break;
			case DisplayMode.EMPTY:
				
				break;
			default:
				displayError("Error: invalid display option " + displayMode);
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
				displayError("Error setting Evolution Method: " + method + " is not a valid evolution method.");
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
	
	public void setEvolutionOutput(String name) {
		outputPanel.setSelection(name);
	}
	
	public void applyEvolutionData() {
		int evoMethod = -1;
		String errors = "The following errors occurred while applying evolution data: \n";
		boolean hasError = false;
		try {
			evoMethod = methodsPanel.getSelectedMethodAsInt();
		} catch(Exception e) {
			
		}
		if(evoMethod == -1) {
			errors += "Error setting evolution method: " + methodsPanel.getSelectedMethod() + " is not a valid evolution method. \n";
			hasError = true;
		}
		
		int evoDetail = -1;
		//Level up
		if(evoMethod == 1) {
			try {
				evoDetail = levelPanel.getLevel();
			} catch(Exception e) {
				errors += e.getMessage() + "\n";
				hasError = true;
			}
		//Stone
		} else if(evoMethod == 2) {
			try {
				evoDetail = stonesPanel.getSelectedStoneValue();
			} catch(Exception e) {
				errors += e.getMessage() + "\n";
				hasError = true;
			}
		}
		
		if(hasError) {
			displayError(errors);
		}
		
		int activePointer = dataManager.getActivePointer();
		FileManager.setEvoMethod(activePointer, evoMethod);
		FileManager.setEvoDetail(activePointer, evoDetail);
	}
	
	public void revertEvolutionData() {
		dataManager.setActiveInput(dataManager.getActiveInput());
	}
}
