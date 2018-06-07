package com.brodudeiii.evoedit.rby.data;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

public class FileManager {
	private static byte[] workingFile;
	 
	 public static void openFile(File file) throws IOException {
		 workingFile = Files.readAllBytes(file.toPath());
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
}
