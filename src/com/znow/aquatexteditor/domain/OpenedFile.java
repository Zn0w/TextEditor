package com.znow.aquatexteditor.domain;

import java.io.File;

public class OpenedFile {
	
	private File file;
	private String content;
	
	
	public OpenedFile(File file, String content) {
		this.file = file;
		this.content = content;
	}

	public File getFile() {
		return file;
	}

	public String getContent() {
		return content;
	}
	
}
