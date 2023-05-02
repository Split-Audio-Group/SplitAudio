package util;
import java.io.File;

import datamodel.*;

public class SessionLog {
	private static User currentUser;
	private File currentFile;
	
	public SessionLog() {
		super();
	}

	public static User getCurrentUser(){
		return currentUser;
	}
	
	public void setUser(User user) {
		if(currentUser != null) {
			System.out.println("Changing user from " + currentUser.getName() + " to " + user.getName());
		}
		currentUser = user;
	}
	public File getCurrentFile(){
		return currentFile;
	}
	
	public void setFile(File file) {
		if(currentFile != null) {
			System.out.println("Changing file from " + currentFile.getName() + " to " + file.getName());
		}
		currentFile = file;
	}
}
