package jUnitTests;

import static org.junit.jupiter.api.Assertions.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import datamodel.*;
import util.*;
import jUnitTests.*;

class logTest {
	ArrayList<User> userList;
	ArrayList<Audio_Files> fileList;
	
	@ParameterizedTest//(name = "{index} => input1={0}, input2= {1}, result={2}")
	@CsvFileSource(resources = "SessionLog.csv")
	void testLog(int userID, int fileID, int newUser, int newFile) {
		SessionLog testedLog = new SessionLog();
		userList = GenerateArrays.generateUsers();
		fileList = GenerateArrays.generateFiles();
		User selectedUser = null;
		Audio_Files selectedFile = null;
		//System.out.println("Pass 1:");
		for(User user : userList) {
			if(user.getId() == userID) {
				testedLog.setUser(user);
				selectedUser = user;
			}
		}
		//System.out.println("\tExpected: " + selectedUser +"\n\tActual: " + testedLog.getCurrentUser());
		Assert.assertEquals(selectedUser, testedLog.getCurrentUser());
		
		for(Audio_Files file : fileList) {
			if(file.getId() == fileID) {
				testedLog.setFile(new File(file.getFilepath()));
				selectedFile = file;
			}
		}
		//System.out.println("\tExpected: " + new File(selectedFile.getFilepath()) +"\n\tActual: " + testedLog.getCurrentFile());
		Assert.assertEquals(new File(selectedFile.getFilepath()), testedLog.getCurrentFile());
		//System.out.println("\tExpected: " + selectedUser +"\n\tActual: " + testedLog.getCurrentUser());
		Assert.assertEquals(selectedUser, testedLog.getCurrentUser());
		
		//System.out.println("Pass 2:");
		for(User user : userList) {
			if(user.getId() == newUser) {
				testedLog.setUser(user);
				selectedUser = user;
			}
		}
		//System.out.println("\tExpected: " + selectedUser +"\n\tActual: " + testedLog.getCurrentUser());
		Assert.assertEquals(selectedUser, testedLog.getCurrentUser());
		
		for(Audio_Files file : fileList) {
			if(file.getId() == newFile) {
				testedLog.setFile(new File(file.getFilepath()));
				selectedFile = file;
			}
		}
		//System.out.println("\tExpected: " + new File(selectedFile.getFilepath()) +"\n\tActual: " + testedLog.getCurrentFile());
		Assert.assertEquals(new File(selectedFile.getFilepath()), testedLog.getCurrentFile());
		//System.out.println("\tExpected: " + selectedUser +"\n\tActual: " + testedLog.getCurrentUser());
		Assert.assertEquals(selectedUser, testedLog.getCurrentUser());
	    //System.out.println();
	}
	
	

}
