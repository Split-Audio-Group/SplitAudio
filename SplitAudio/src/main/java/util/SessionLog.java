package util;
import datamodel.*;

public class SessionLog {
	private User currentUser;
	private Audio_Files currentFile;
	
	public SessionLog() {
		super();
	}

	public User getCurrentUser(){
		return currentUser;
	}
	
	public void setUser(User user) {
		if(currentUser != null) {
			System.out.println("Changing user from " + currentUser.getName() + " to " + user.getName());
		}
		currentUser = user;
	}
	public Audio_Files getCurrentFile(){
		return currentFile;
	}
	
	public void setFile(Audio_Files file) {
		if(currentFile != null) {
			System.out.println("Changing file from " + currentFile.getName() + " to " + file.getName());
		}
		currentFile = file;
	}
}
