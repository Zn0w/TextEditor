package com.znow.aquatexteditor.gui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.io.File;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import com.znow.aquatexteditor.controller.MainController;
import com.znow.aquatexteditor.filemanager.FileManager;

public class MainWindow extends JFrame {
	
	private static final long serialVersionUID = 1L;
	private JTextArea fileContentArea;
	
	
	public void draw() {
		setTitle("AquaTextEditor");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JPanel buttonsPanel = new JPanel();
		getContentPane().add(buttonsPanel, BorderLayout.NORTH);
		
		JButton newButton = getButton("New");
		buttonsPanel.add(newButton);
		
		JButton openButton = getButton("Open");
		buttonsPanel.add(openButton);
		
		JButton saveButton = getButton("Save");
		saveButton.setMnemonic(KeyEvent.VK_S);
		buttonsPanel.add(saveButton);
		
		JButton saveAsButton = getButton("Save as ...");
		buttonsPanel.add(saveAsButton);
		
		JButton settingsButton = getButton("Settings");
		buttonsPanel.add(settingsButton);
		
		fileContentArea = new JTextArea();
		JScrollPane scrollPane = new JScrollPane(fileContentArea);
		scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		getContentPane().add(scrollPane, BorderLayout.CENTER);
		
		pack();
		
		Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
	    setBounds(100, 100, (int) screen.getWidth(), (int) screen.getHeight());
	    setLocationRelativeTo(null);
		
		setVisible(true);
	}
	
	private JButton getButton(String name) {
		JButton button = new JButton(name);
		
		Dimension size = new Dimension(90, 25);
		button.setMaximumSize(size);
		button.setPreferredSize(size);
		
		if (name.equals("New")) {
			button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				checkIfSaveFile();
				fileContentArea.setText("");
				setTitle("AquaTextEditor");
				MainController.openedFile = null;
			}
			});
		}
		else if (name.equals("Save")) {
			button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				verifyFileSaving();
			}
			});
		}
		else if (name.equals("Open")) {
			button.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					checkIfSaveFile();
					
					JFileChooser fileChooser = new JFileChooser();
					fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
					int returnValue = fileChooser.showOpenDialog(MainWindow.this);
					
					if (returnValue == JFileChooser.APPROVE_OPTION) {
						File file = fileChooser.getSelectedFile();
						FileManager.openFile(file, fileContentArea);
						setTitle("AquaTextEditor ("+ MainController.openedFile.getFile().getName() +")");
					}
				}
			});
		}
		else if (name.equals("Save as ...")) {
			button.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					System.out.println("Save as...");
					System.out.println(fileContentArea.getText());
				}
			});
		}
		else if (name.equals("Settings")) {
			button.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					SettingsWindow settingsWindow = new SettingsWindow(fileContentArea);
					settingsWindow.draw();
				}
			});
		}
		
		return button;
	}
	
	private void checkIfSaveFile() {
		if ((MainController.openedFile != null && MainController.openedFile.getContent() != fileContentArea.getText()) || (MainController.openedFile == null && !fileContentArea.getText().equals(""))) {
			int option = JOptionPane.showConfirmDialog(this, "Do you want to save current file?", "Save file", JOptionPane.YES_NO_OPTION);
			if (option == JOptionPane.YES_OPTION) {
			    verifyFileSaving();
			}
		}
	}
	
	private void verifyFileSaving() {
		if (MainController.openedFile == null) {
			JFileChooser fileChooser = new JFileChooser();
			int returnValue = fileChooser.showSaveDialog(this);
			
			if (returnValue == JFileChooser.APPROVE_OPTION) {
				File file = fileChooser.getSelectedFile();
				String message = FileManager.saveToNewFile(fileContentArea.getText(), file);
				
				JOptionPane.showMessageDialog(this, message);
			}
		}
		else {
			FileManager.saveToExistingFile(fileContentArea.getText(), MainController.openedFile.getFile());
		}
	}
	
}
