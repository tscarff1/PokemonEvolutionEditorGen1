package com.brodudeiii.evoedit.rby.logging;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Logger {
	private static String logFile = null;
	
	public static void defaultLogFile() {
		 logFile = "C:/Development/PokemonEvoEditor.log";
	}

	public static String getLogFile() {
		return logFile;
	}

	public static void setLogFile(String logFile) {
		Logger.logFile = logFile;
	}
	
	public static void log(String message) {
		if(logFile != null) {
			SimpleDateFormat sdf = new SimpleDateFormat("YYYYMMDD HH:mm:ss");
			String now = sdf.format(new Date());
			try (BufferedWriter bufWriter =  new BufferedWriter(new FileWriter(logFile))){
				bufWriter.write(now + " - " + message);
				
			} catch (IOException e) {
				System.err.println("Error logging message: " + e.getMessage());
			}
		}
	}
}