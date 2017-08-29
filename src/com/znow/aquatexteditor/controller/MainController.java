package com.znow.aquatexteditor.controller;

import java.awt.Font;
import java.io.File;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

import com.znow.aquatexteditor.domain.OpenedFile;
import com.znow.aquatexteditor.filemanagers.FileManager;
import com.znow.aquatexteditor.filemanagers.SettingsFileManager;
import com.znow.aquatexteditor.gui.MainWindow;
import com.znow.aquatexteditor.gui.SettingsWindow;

public class MainController {
	
	public OpenedFile openedFile;
	
	private MainWindow mainWindow;
	
	private FileManager fileManager;
	private SettingsFileManager settingsFileManager;
	
	private KeyboardController keyboardController;
	
	
	
	public void start() {
		settingsFileManager = new SettingsFileManager();
		
		mainWindow = new MainWindow(this);
		mainWindow.draw();
		
		mainWindow.getFileContentArea().setFont(new Font(settingsFileManager.getFont(), Font.PLAIN, Integer.valueOf(settingsFileManager.getFontSize())));
		
		keyboardController = new KeyboardController(mainWindow.getFileContentArea());
		mainWindow.getFileContentArea().addKeyListener(keyboardController);
	}
	
	public void handleNewButton() {
		checkIfSaveFile();
		mainWindow.getFileContentArea().setText("");
		mainWindow.setTitle("AquaTextEditor");
		openedFile = null;
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
			fileManager.openFile(file, mainWindow.getFileContentArea(), this);
				mainWindow.setTitle("AquaTextEditor ("+ openedFile.getFile().getName() +")");
		}
	}
	
	public void handleSettingsButton() {
		SettingsWindow settingsWindow = new SettingsWindow(mainWindow.getFileContentArea(), settingsFileManager);
		settingsWindow.draw();
	}
	
	private void checkIfSaveFile() {
		if ((openedFile != null && openedFile.getContent() != mainWindow.getFileContentArea().getText()) || (openedFile == null && !mainWindow.getFileContentArea().getText().equals(""))) {
			int option = JOptionPane.showConfirmDialog(mainWindow, "Do you want to save current file?", "Save file", JOptionPane.YES_NO_OPTION);
			if (option == JOptionPane.YES_OPTION) {
			    verifyFileSaving();
			}
		}
	}
	
	private void verifyFileSaving() {
		if (openedFile == null) {
			JFileChooser fileChooser = new JFileChooser();
			int returnValue = fileChooser.showSaveDialog(mainWindow);
			
			if (returnValue == JFileChooser.APPROVE_OPTION) {
				File file = fileChooser.getSelectedFile();
				String message = fileManager.saveToNewFile(mainWindow.getFileContentArea().getText(), file);
				
				JOptionPane.showMessageDialog(mainWindow, message);
			}
		}
		else {
			fileManager.saveToExistingFile(mainWindow.getFileContentArea().getText(), openedFile.getFile());
		}
	}
	
}
