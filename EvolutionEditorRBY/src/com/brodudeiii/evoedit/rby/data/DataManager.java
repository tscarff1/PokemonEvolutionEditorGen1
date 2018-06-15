package com.brodudeiii.evoedit.rby.data;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Map;

import com.brodudeiii.evoedit.rby.swing.MainFrame;

public class DataManager {

	//For inputs we want the pointer given the pokemon name
	private Map<String, Integer> pointerDataByName;
	//For setting outputs we want the name given the pointer
	private Map<Integer, String> nameDataByPointer;
	
	private String activeInput; //The input Pokemon currently being modified
	private MainFrame mainFrame;
	
	public DataManager(MainFrame mainFrame) {
		this.mainFrame = mainFrame;
		pointerDataByName = new LinkedHashMap<String, Integer>();
		File inputFile = new File(".\\src\\com\\brodudeiii\\evoedit\\rby\\data/evo-pointers.txt");
		BufferedReader reader = null;
		try {
			reader = new BufferedReader(new FileReader(inputFile));
			String line;
			while((line = reader.readLine()) != null) {
				int offset = Integer.parseInt(reader.readLine());
				pointerDataByName.put(line, offset);
			}
		} catch (FileNotFoundException e) {
			
			//TODO: Handle errors better
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Handle errors better
			e.printStackTrace();
		} finally {
			if(reader != null) {
				try {
					reader.close();
				} catch (IOException e) {
					//At this point we're just kinda screwed
					e.printStackTrace();
				}
			}
		}
		
	}
	
	public String[] getPokemonNamesArray() {
		return (String[]) pointerDataByName.keySet().toArray(new String[pointerDataByName.keySet().size()]);
	}
	
	public void setActiveInput(String pokemon) {
		this.activeInput = pokemon;
		byte[] data = FileManager.getBytes(pointerDataByName.get(pokemon), 4);
		mainFrame.setEvolutionMethod(Integer.valueOf(data[0]));
		mainFrame.setEvolutionDetail(Integer.valueOf(data[1]));
	}
}
