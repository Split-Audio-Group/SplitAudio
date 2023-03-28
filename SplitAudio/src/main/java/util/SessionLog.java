package util;
import datamodel.User;

public class SessionLog {
	private User currentUser;
	
	public SessionLog() {
		super();
	}

	public User getCurrentUser(){
		return currentUser;
	}
	
	public void setUser(User user) {
		if(currentUser != null) {
			System.out.println("Changing user from " + currentUser.getUserName() + " to " + user.getUserName());
		}
		currentUser = user;
	}
}
