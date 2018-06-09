package com.brodudeiii.evoedit.rby.data;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Map;

public class DataManager {

	private Map<String, Integer> pointerData;
	private String activeInput; //The input Pokemon currently being modified
	
	
	public DataManager() {
		pointerData = new LinkedHashMap<String, Integer>();
		File inputFile = new File(".\\src\\com\\brodudeiii\\evoedit\\rby\\data/evo-pointers.txt");
		BufferedReader reader = null;
		try {
			reader = new BufferedReader(new FileReader(inputFile));
			String line;
			while((line = reader.readLine()) != null) {
				int offset = Integer.parseInt(reader.readLine());
				pointerData.put(line, offset);
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
	
	public Map<String, Integer> getPointerData() {
		return pointerData;
	}
	
	public void setActiveInput(String pokemon) {
		this.activeInput = pokemon;
		System.out.println(pokemon + ": " + pointerData.get(pokemon));
	}
}
