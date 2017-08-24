package com.znow.aquatexteditor.controller;

import com.znow.aquatexteditor.gui.MainWindow;
import com.znow.aquatexteditor.domain.OpenedFile;

public class MainController {
	
	public static OpenedFile openedFile;
	
	
	public void start() {
		MainWindow window = new MainWindow();
		window.draw();
	}
	
}
