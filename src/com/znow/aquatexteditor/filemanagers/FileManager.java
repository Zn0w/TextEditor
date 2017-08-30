package com.znow.aquatexteditor.filemanagers;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.NoSuchElementException;
import java.util.Scanner;

import com.znow.aquatexteditor.domain.OpenedFile;

public class FileManager {
	
	private OpenedFile openedFile;
	
	
	public String saveToNewFile(String content, File file) {
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
		
		openedFile = new OpenedFile(file, "");
		
		saveToCurrentFile(content);
		
		return message;
	}
	
	public void saveToCurrentFile(String content) {
		content = content.replaceAll("(?!\\r)\\n", "\r\n");
		
		FileWriter writer = null;
		
		try {
			writer = new FileWriter(openedFile.getFile(), false);
			writer.write(content);
			writer.flush();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (writer != null)
					writer.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	public void openFile(File file) {
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
		
		openedFile = new OpenedFile(file, content);
	}

	public OpenedFile getOpenedFile() {
		return openedFile;
	}

	public void setOpenedFile(OpenedFile openedFile) {
		this.openedFile = openedFile;
	}
	
}
