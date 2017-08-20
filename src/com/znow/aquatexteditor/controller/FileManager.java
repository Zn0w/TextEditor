package com.znow.aquatexteditor.controller;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

import javax.swing.JTextArea;

import com.znow.aquatexteditor.Main;
import com.znow.aquatexteditor.domain.OpenedFile;

public class FileManager {
	
	public static void saveToNewFile(String content, File file) {
		
	}
	
	public static void saveToExistingFile(String content, File file) {
		try {
			FileWriter writer = new FileWriter(file, false);
			writer.write(content);
			writer.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static void openFile(File file, JTextArea fileContentArea) {
		Scanner scan = null;
		try {
			scan = new Scanner(file);
			scan.useDelimiter("\\Z");  
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		String content = scan.next();
		
		Main.openedFile = new OpenedFile(file, content);
		
		fileContentArea.setText(content);
	}
	
}
