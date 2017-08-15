package com.znow.aquatexteditor.gui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JTextArea;

public class MainWindow {
	
	private JFrame frame;
	private JButton newButton;
	private JTextArea fileContent;
	
	
	public MainWindow() {
		init();
	}
	
	private void init() {
		frame = new JFrame("AquaTextEditor");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		newButton = new JButton("New");
		newButton.setSize(25, 20);
		newButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println("Open new file");
				System.out.println(fileContent.getText());
			}
		});
		frame.getContentPane().add(newButton, BorderLayout.NORTH);
		
		fileContent = new JTextArea();
		frame.getContentPane().add(fileContent, BorderLayout.CENTER);
		
		frame.pack();
		
		Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
	    frame.setBounds(100, 100, (int) screen.getWidth(), (int) screen.getHeight());
	    frame.setLocationRelativeTo(null);
		
		frame.setVisible(true);
	}
	
}
