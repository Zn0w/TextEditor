package com.znow.aquatexteditor.filemanager;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.NoSuchElementException;
import java.util.Scanner;

import javax.swing.JTextArea;

import com.znow.aquatexteditor.controller.MainController;
import com.znow.aquatexteditor.domain.OpenedFile;

public class FileManager {
	
	public static String saveToNewFile(String content, File file) {
		String message = "";
		
		try {
			if (file.createNewFile()) {
				message = "File has been succesfully created!";
			}
			else {
				message = "File with this name and location already exists!";
			}
		} catch (IOException e) {
			e.printStackTrace();
			message = "Error! Couldn't create new file.";
		}
		
		saveToExistingFile(content, file);
		
		return message;
	}
	
	public static void saveToExistingFile(String content, File file) {
		content = content.replaceAll("(?!\\r)\\n", "\r\n");
		
		FileWriter writer = null;
		
		try {
			writer = new FileWriter(file, false);
			writer.write(content);
			writer.flush();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				writer.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
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
		
		String content = "";
		
		try {
			content = scan.next();
		} catch (NoSuchElementException ex) {
			
		}
		
		MainController.openedFile = new OpenedFile(file, content);
		
		fileContentArea.setText(content);
	}
	
}