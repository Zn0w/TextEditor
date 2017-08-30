package com.znow.aquatexteditor.controller;

import java.awt.Font;
import java.io.File;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

import com.znow.aquatexteditor.filemanagers.FileManager;
import com.znow.aquatexteditor.filemanagers.SettingsFileManager;
import com.znow.aquatexteditor.gui.MainWindow;
import com.znow.aquatexteditor.gui.SettingsWindow;

public class MainController {
	
	private MainWindow mainWindow;
	
	private FileManager fileManager;
	private SettingsFileManager settingsFileManager;
	
	
	public void start() {
		fileManager = new FileManager();
		
		settingsFileManager = new SettingsFileManager();
		
		mainWindow = new MainWindow(this);
		mainWindow.draw();
		
		mainWindow.getFileContentArea().setFont(new Font(settingsFileManager.getFont(), Font.PLAIN, Integer.valueOf(settingsFileManager.getFontSize())));
	}
	
	public void handleNewButton() {
		checkIfSaveFile();
		mainWindow.getFileContentArea().setText("");
		mainWindow.setTitle("AquaTextEditor");
		fileManager.setOpenedFile(null);
	}
	
	public void handleSaveButton() {
		verifyFileSaving();
	}
	
	public void handleOpenButton() {
		checkIfSaveFile();
		
		JFileChooser fileChooser = new JFileChooser();
		fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
		int returnValue = fileChooser.showOpenDialog(mainWindow);
		
		if (returnValue == JFileChooser.APPROVE_OPTION) {
			File file = fileChooser.getSelectedFile();
			fileManager.openFile(file);
			mainWindow.setTitle("AquaTextEditor ("+ fileManager.getOpenedFile().getFile().getName() +")");
			mainWindow.getFileContentArea().setText(fileManager.getOpenedFile().getContent());
		}
	}
	
	public void handleSettingsButton() {
		SettingsWindow settingsWindow = new SettingsWindow(mainWindow.getFileContentArea(), settingsFileManager);
		settingsWindow.draw();
	}
	
	private void checkIfSaveFile() {
		if ((fileManager.getOpenedFile() != null && fileManager.getOpenedFile().getContent() != mainWindow.getFileContentArea().getText()) || (fileManager.getOpenedFile() == null && !mainWindow.getFileContentArea().getText().equals(""))) {
			int option = JOptionPane.showConfirmDialog(mainWindow, "Do you want to save current file?", "Save file", JOptionPane.YES_NO_OPTION);
			if (option == JOptionPane.YES_OPTION) {
			    verifyFileSaving();
			}
		}
	}
	
	private void verifyFileSaving() {
		if (fileManager.getOpenedFile() == null) {
			JFileChooser fileChooser = new JFileChooser();
			int returnValue = fileChooser.showSaveDialog(mainWindow);
			
			if (returnValue == JFileChooser.APPROVE_OPTION) {
				File file = fileChooser.getSelectedFile();
				String message = fileManager.saveToNewFile(mainWindow.getFileContentArea().getText(), file);
				
				JOptionPane.showMessageDialog(mainWindow, message);
			}
		}
		else {
			fileManager.saveToCurrentFile(mainWindow.getFileContentArea().getText());
		}
	}
	
}
