package com.znow.aquatexteditor.gui;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;

@SuppressWarnings("serial")
class SettingsWindow extends JFrame {
	
	private String[] fontOptions = { "Serif", "Agency FB", "Arial", "Consolas", "Calibri", "Century Gothic", "Comic Sans MS", "Courier New", "Forte", "Garamond", "Monospaced", "Segoe UI", "Times New Roman", "Trebuchet MS" };
	private String[] sizeOptions = { "8", "10", "12", "14", "16", "18", "20", "22", "24", "26", "28" };
	
	private JTextArea fileContentArea;
	
	
	public SettingsWindow(JTextArea fileContentArea) {
		this.fileContentArea = fileContentArea;
	}
	
	void draw() {
		setTitle("AquaTextEditor (Settings)");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setSize(350, 200);
		setLocationRelativeTo(null);
		
		JPanel panel = new JPanel();
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
		setContentPane(panel);
		
		panel.add(getFontSelector());
		panel.add(getFontSizeSelector());
		
		setVisible(true);
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	private JComboBox getFontSelector() {
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
		
		return fontSelector;
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	private JComboBox getFontSizeSelector() {
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
		
		return fontSizeSelector;
	}
	
}
