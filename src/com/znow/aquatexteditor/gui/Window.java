package com.znow.aquatexteditor.gui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;

import com.znow.aquatexteditor.controller.FileManager;

public class Window extends JFrame{
	
	private JFrame frame;
	private JTextArea fileContent;
	
	
	public Window() {
		init();
	}
	
	private void init() {
		setTitle("AquaTextEditor");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JPanel buttonsPanel = new JPanel();
		getContentPane().add(buttonsPanel, BorderLayout.NORTH);
		
		JButton newButton = getButton("New");
		buttonsPanel.add(newButton);
		
		JButton openButton = getButton("Open");
		buttonsPanel.add(openButton);
		
		JButton saveButton = getButton("Save");
		buttonsPanel.add(saveButton);
		
		JButton saveAsButton = getButton("Save as ...");
		buttonsPanel.add(saveAsButton);
		
		JButton settingsButton = getButton("Settings");
		buttonsPanel.add(settingsButton);
		
		fileContent = new JTextArea();
		getContentPane().add(fileContent, BorderLayout.CENTER);
		
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
				System.out.println("Open new file");
				System.out.println(fileContent.getText());
			}
			});
		}
		else if (name.equals("Save")) {
			button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				FileManager.saveFile(fileContent.getText());
			}
			});
		}
		else if (name.equals("Open")) {
			button.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					JFileChooser fileChooser = new JFileChooser();
					fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
					int returnValue = fileChooser.showOpenDialog(Window.this);
					
					if (returnValue == JFileChooser.APPROVE_OPTION) {
						File file = fileChooser.getSelectedFile();
						FileManager.openFile(file, fileContent);
					}
				}
			});
		}
		else if (name.equals("Save as ...")) {
			button.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					System.out.println("Save as...");
					System.out.println(fileContent.getText());
				}
			});
		}
		else if (name.equals("Settings")) {
			button.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					System.out.println("Settings");
					System.out.println(fileContent.getText());
				}
			});
		}
		
		return button;
	}
	
}
