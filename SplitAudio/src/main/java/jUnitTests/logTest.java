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
	void testLog(int userID, int fileID, int expectedUserID, int expectedFileID) {
		userList = GenerateArrays.generateUsers();
		fileList = GenerateArrays.generateFiles();
	      
	}
	
	

}
