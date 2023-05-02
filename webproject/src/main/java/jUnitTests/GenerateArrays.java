package jUnitTests;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

import datamodel.*;

public class GenerateArrays {
	static ArrayList<User> generateUsers() {
		String userDirectory = new File("").getAbsolutePath() + "/src/main/java/jUnitTests";
		try {
			Scanner sc = new Scanner(new File(userDirectory + "/UserList.csv"));
			sc.useDelimiter(",|\\n");   //sets the delimiter pattern 
			ArrayList<User> list = new ArrayList<User>();
			while (sc.hasNext()){  //returns a boolean value  
				String userID = sc.next();
				//System.out.println("id:"+userID);
				String username = sc.next();
				//System.out.println("name:" + username);
				String displayName = sc.next();
				if(displayName.equals("NULL")) {
					displayName = null;
				}
				//System.out.println("display:" + displayName);
				String email = sc.next();
				if(email.equals("NULL")) {
					email = null;
				}
				//System.out.println("email:" + email);
				String password = sc.next();
				//System.out.println("password:" + password);
				list.add(new User(Integer.parseInt(userID), username, displayName, email, password));
				
			}   
			sc.close();  //closes the scanner 
			return list;
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}  
		return null;
		      
	}
	
	static ArrayList<Audio_Files> generateFiles() {
		String userDirectory = new File("").getAbsolutePath() + "/src/main/java/jUnitTests";
		try {
			Scanner sc = new Scanner(new File(userDirectory + "/FileList.csv"));
			sc.useDelimiter(",|\\r\\n");   //sets the delimiter pattern 
			ArrayList<Audio_Files> list = new ArrayList<Audio_Files>();
			while (sc.hasNext()){  //returns a boolean value  
				String id = sc.next(); 
				//System.out.println(id);
				String uid = sc.next();
				//System.out.println(uid);
				String name = sc.next();
				//System.out.println(name);
				String path = sc.next();
				//System.out.println(path);
				String size = sc.next();
				//System.out.println(size);
				String pub = sc.next();
				//System.out.println(pub);
				list.add(new Audio_Files(Integer.parseInt(id), Integer.parseInt(uid), name, path, Double.parseDouble(size), pub.equals("1")));
				
			}   
			sc.close();  //closes the scanner 
			return list;
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}  
		return null;
		      
	}
	public static void main(String[] args) {
		System.out.println(generateUsers());
		System.out.println(generateFiles());
	}
	
}
