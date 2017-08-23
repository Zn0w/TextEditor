package com.znow.aquatexteditor.gui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.io.File;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import com.znow.aquatexteditor.Main;
import com.znow.aquatexteditor.controller.FileManager;

public class Window extends JFrame{
	
	private static final long serialVersionUID = 1L;
	private JTextArea fileContentArea;
	
	
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
				Main.openedFile = null;
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
					int returnValue = fileChooser.showOpenDialog(Window.this);
					
					if (returnValue == JFileChooser.APPROVE_OPTION) {
						File file = fileChooser.getSelectedFile();
						FileManager.openFile(file, fileContentArea);
						setTitle("AquaTextEditor ("+ Main.openedFile.getFile().getName() +")");
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
					JFrame settingsFrame = getSettingsFrame();
					settingsFrame.setVisible(true);
				}
			});
		}
		
		return button;
	}
	
	private JFrame getSettingsFrame() {
		JFrame settingsFrame = new JFrame();
		settingsFrame.setTitle("AquaTextEditor (Settings)");
		settingsFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		settingsFrame.setSize(350, 400);
		settingsFrame.setLocationRelativeTo(null);
		
		JPanel panel = new JPanel();
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
		
		settingsFrame.setContentPane(panel);
		
		String[] fontOptions = {"Serif", "Agency FB", "Arial", "Cosolas", "Calibri", "Cambrian", "Century Gothic", "Comic Sans MS", "Courier New", "Forte", "Garamond", "Monospaced", "Segoe UI", "Times New Roman", "Trebuchet MS"};
		
		JComboBox fontSelector = new JComboBox(fontOptions);
		for (int i = 0; i < fontOptions.length; i++) {
			if (fontOptions[i].equals(fileContentArea.getFont().getFontName()))
				fontSelector.setSelectedIndex(i);
		}
		fontSelector.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String font = (String) fontSelector.getSelectedItem();
				
				Font oldFont = fileContentArea.getFont();
				fileContentArea.setFont(new Font(font, oldFont.getStyle(), oldFont.getSize()));
			}
		});
		
		String[] sizeOptions = {"8", "10", "12", "14", "16", "18", "20", "22", "24", "26", "28"};
		
		JComboBox fontSizeSelector = new JComboBox(sizeOptions);
		for (int i = 0; i < sizeOptions.length; i++) {
			if (Integer.valueOf(sizeOptions[i]) == fileContentArea.getFont().getSize())
				fontSizeSelector.setSelectedIndex(i);
		}
		fontSizeSelector.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String size = (String) fontSizeSelector.getSelectedItem();
				
				Font oldFont = fileContentArea.getFont();
				fileContentArea.setFont(new Font(oldFont.getFontName(), oldFont.getStyle(), Integer.valueOf(size)));
			}
		});
		
		panel.add(fontSelector);
		panel.add(fontSizeSelector);
		
		return settingsFrame;
	}
	
	private void checkIfSaveFile() {
		if ((Main.openedFile != null && Main.openedFile.getContent() != fileContentArea.getText()) || (Main.openedFile == null && !fileContentArea.getText().equals(""))) {
			int option = JOptionPane.showConfirmDialog(this, "Do you want to save current file?", "Save file", JOptionPane.YES_NO_OPTION);
			if (option == JOptionPane.YES_OPTION) {
			    verifyFileSaving();
			}
		}
	}
	
	private void verifyFileSaving() {
		if (Main.openedFile == null) {
			JFileChooser fileChooser = new JFileChooser();
			int returnValue = fileChooser.showSaveDialog(this);
			
			if (returnValue == JFileChooser.APPROVE_OPTION) {
				File file = fileChooser.getSelectedFile();
				String message = FileManager.saveToNewFile(fileContentArea.getText(), file);
				
				JOptionPane.showMessageDialog(this, message);
			}
		}
		else {
			FileManager.saveToExistingFile(fileContentArea.getText(), Main.openedFile.getFile());
		}
	}
	
}
