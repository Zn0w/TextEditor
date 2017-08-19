package com.znow.aquatexteditor.controller;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import javax.swing.JTextArea;

import com.znow.aquatexteditor.Main;

public class FileManager {
	
	public static void saveFile(String content) {
		
	}
	
	public static void openFile(File file, JTextArea fileContentArea) {
		Main.openedFile = file;
		
		Scanner scan = null;
		try {
			scan = new Scanner(file);
			scan.useDelimiter("\\Z");  
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		String content = scan.next();
		
		fileContentArea.setText(content);
	}
	
}
