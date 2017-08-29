package com.znow.aquatexteditor.filemanagers;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;


public class SettingsFileManager {
	
	private String font;
	private String fontSize;
	
	
	private File settingsFile = new File("resources/settings.txt");
	
	
	public SettingsFileManager() {
		readSettings();
	}
	
	private void saveSettings() {
		FileWriter writer = null;
		
		try {
			writer = new FileWriter(settingsFile, false);
			
			String settings = "";
			settings += "font;" + font + "\n";
			settings += "fontsize;" + fontSize;
			
			writer.write(settings);
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
	
	private void readSettings() {
		BufferedReader reader = null;
		
		try {
			reader = new BufferedReader(new FileReader(settingsFile));
			
			String line;
			while ((line = reader.readLine()) != null) {
				String[] lineDiv = line.split(";");
				if (lineDiv[0].equals("font"))
					font = lineDiv[1];
				else if (lineDiv[0].equals("fontsize"))
					fontSize = lineDiv[1];
			}
		} catch (FileNotFoundException e) {
			font = "Consolas";
			fontSize = "14";
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public String getFont() {
		return font;
	}

	public void setFont(String font) {
		this.font = font;
		saveSettings();
	}

	public String getFontSize() {
		return fontSize;
	}

	public void setFontSize(String fontSize) {
		this.fontSize = fontSize;
		saveSettings();
	}

}
