package com.znow.aquatexteditor.gui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import com.znow.aquatexteditor.controller.MainController;

public class MainWindow extends JFrame {
	
	private static final long serialVersionUID = 1L;
	private JTextArea fileContentArea;
	
	private MainController controller;
	
	
	public MainWindow(MainController controller) {
		this.controller = controller;
	}
	
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
				controller.handleNewButton();
			}
			});
		}
		else if (name.equals("Save")) {
			button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controller.handleSaveButton();
			}
			});
		}
		else if (name.equals("Open")) {
			button.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					controller.handleOpenButton();
				}
			});
		}
		else if (name.equals("Save as ...")) {
			button.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					controller.handleSaveAsButton();
				}
			});
		}
		else if (name.equals("Settings")) {
			button.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					controller.handleSettingsButton();
				}
			});
		}
		
		return button;
	}

	public JTextArea getFileContentArea() {
		return fileContentArea;
	}
	
}
