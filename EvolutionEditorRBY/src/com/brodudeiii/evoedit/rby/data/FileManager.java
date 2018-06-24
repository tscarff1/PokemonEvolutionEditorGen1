package com.brodudeiii.evoedit.rby.data;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

public class FileManager {
	private static byte[] workingFile;
	private static boolean gameLoaded = false;
	
	 public static void openFile(File file) throws IOException {
		 workingFile = Files.readAllBytes(file.toPath());
		 gameLoaded = true;
	 }
	 
	 public static void saveFile(File file) {
		 
	 }
	 
	 public static byte[] getBytes(int offset, int length) {
		 byte[] returnData = new byte[length];
		 for(int i = 0; i < length; i++) {
			 returnData[i] = workingFile[offset+i];
		 }
		 return returnData;
	 }
	 
	 public static boolean isGameLoaded() {
		  return gameLoaded;
	 }
	 
	 public static void setEvoMethod(int pointer, int val) {
		 workingFile[pointer] = (byte) val;
	 }
	 
	 public static void setEvoDetail(int pointer, int val) {
		 workingFile[pointer+1] = (byte) val;
	 }
}
