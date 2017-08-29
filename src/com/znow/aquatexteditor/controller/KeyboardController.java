package com.znow.aquatexteditor.controller;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JTextArea;


public class KeyboardController implements KeyListener {
	
	private JTextArea fileContentArea;
	private boolean shiftIsPressed = false;
	
	
	public KeyboardController(JTextArea fileContentArea) {
		this.fileContentArea = fileContentArea;
	}
	
	public void keyTyped(KeyEvent e) {
		
	}
	
	public void keyPressed(KeyEvent e) {
		int key = e.getKeyCode();
		if (key == 16)
			shiftIsPressed = true;
	}
	
	public void keyReleased(KeyEvent e) {
		int key = e.getKeyCode();
		String text = fileContentArea.getText();
		
		if (key == 222 && !shiftIsPressed) {
			text += "'";
			fileContentArea.setText(text);
		}
		else if (key == 222 && shiftIsPressed) {
			text += '"';
			fileContentArea.setText(text);
		}
		else if (key == 44 && shiftIsPressed) {
			text += ">";
			fileContentArea.setText(text);
		}
		
		if (key == 16)
			shiftIsPressed = false;
	}
	
}
