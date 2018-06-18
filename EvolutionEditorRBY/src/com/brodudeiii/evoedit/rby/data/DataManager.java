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
	private Map<String, PokemonData>pokemonDataByName;
	//For setting outputs we want the name given the pointer
	private Map<Integer, String> namesByPointer;
	
	private String activeInput; //The input Pokemon currently being modified
	private MainFrame mainFrame;
	
	public DataManager(MainFrame mainFrame) {
		this.mainFrame = mainFrame;
		pokemonDataByName = new LinkedHashMap<String, PokemonData>();
		namesByPointer = new LinkedHashMap<Integer, String>();
		File inputFile = new File(".\\src\\com\\brodudeiii\\evoedit\\rby\\data/evo-pointers.txt");
		BufferedReader reader = null;
		try {
			reader = new BufferedReader(new FileReader(inputFile));
			String line;
			while((line = reader.readLine()) != null) {
				int offset = Integer.parseInt(reader.readLine());
				int pokedex = Integer.parseInt(reader.readLine());
				pokemonDataByName.put(line, new PokemonData(line, offset, pokedex));
				namesByPointer.put(offset, line);
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
		return pokemonDataByName.keySet().toArray(new String[pokemonDataByName.keySet().size()]);
	}
	
	public String[] getPokemonNamesArrayPokedexOrder() {
		String[] names = new String[pokemonDataByName.keySet().size()];
		for(String name : pokemonDataByName.keySet()) {
			names[pokemonDataByName.get(name).getPokedex()] = name;
		}
		return names;
	}
	
	public String getPokemonNameByPosition(int pos) {
		//Account for multiple Eeveelutions
		//The game evolution indexes only count eevee as one pokemon, but I'm counting it as multiple for now
		//Since eevee has 3 evolutions in gen 1, we need to increase the index by two any time we passed eevee's third evolution (one for each extra Eevee)
		if(pos >= 104)
			pos += 2;
		return (String) pokemonDataByName.keySet().toArray()[pos];
	}
	
	public void setActiveInput(String pokemon) {
		this.activeInput = pokemon;
		int currentPtr =pokemonDataByName.get(pokemon).getPointer();
		byte[] data = FileManager.getBytes(currentPtr, 4);
		mainFrame.setEvolutionMethod(Integer.valueOf(data[0]));
		mainFrame.setEvolutionDetail(Integer.valueOf(data[1]));
		String name = null;
		//Being evolution output logic
		if(Integer.valueOf(data[0]) != 0) {
			//Need the "& 0xFF" to process the value as an unsigned byte
			//Basic logic here: Assume the third data byte is the evolution-to. This is the case for most pokemon.
			//However, if the fourth byte is not zero, that instead will hold the evolution-to info and we use that instead
			int evo = Integer.valueOf(data[2] & 0xFF);
			if(Integer.valueOf(data[3] & 0xFF) != 0)	{
				evo = Integer.valueOf(data[3] & 0xFF);
			}
			//evo is currently the position in the game's table of pokemon, so we need to use this to get the pokemon name
			name = this.getPokemonNameByPosition(evo-1);
		}
		mainFrame.setEvolutionOutput(name);
			
	}
	
	private static class PokemonData {
		private int pokedex;
		private String name;
		private int pointer;
		
		public PokemonData(String name, int pointer, int pokedex) {
			this.name = name;
			this.pointer = pointer;
			this.pokedex = pokedex;
		}

		public int getPokedex() {
			return pokedex;
		}

		public void setPokedex(int pokedex) {
			this.pokedex = pokedex;
		}

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		public int getPointer() {
			return pointer;
		}

		public void setPointer(int pointer) {
			this.pointer = pointer;
		}
	}
}
