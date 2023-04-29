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

class UserTest {

	@ParameterizedTest//(name = "{index} => input1={0}, input2= {1}, result={2}")
	@CsvFileSource(resources = "UserList.csv")
	void testConstructor(int users_id, String username, String display_name, String email, String password) {
		User testUser = new User(users_id, username, display_name, email, password);
		testUser(users_id, username, display_name, email, password, testUser);
	}
	
	@ParameterizedTest//(name = "{index} => input1={0}, input2= {1}, result={2}")
	@CsvFileSource(resources = "UserList.csv")
	void testSet(int users_id, String username, String display_name, String email, String password) {
		User testUser = new User();
		testNotUser(users_id, username, display_name, email, password, testUser);
		testUser.setId(users_id);
		testUser.setusername(username);
		testUser.setdisplay_name(display_name);
		testUser.setEmail(email);
		testUser.setPassword(password);
		testUser(users_id, username, display_name, email, password, testUser);
	}
	void testUser(int users_id, String username, String display_name, String email, String password, User testUser) {
		Assert.assertEquals((Integer) users_id, testUser.getId());
		Assert.assertEquals(username, testUser.getName());
		Assert.assertEquals(display_name, testUser.getdisplay_Name());
		Assert.assertEquals(email, testUser.getEmail());
		Assert.assertEquals(password, testUser.getPassword());
	}
	void testNotUser(int users_id, String username, String display_name, String email, String password, User testUser) {
		Assert.assertNotEquals((Integer) users_id, testUser.getId());
		Assert.assertNotEquals(username, testUser.getName());
		Assert.assertNotEquals(display_name, testUser.getdisplay_Name());
		Assert.assertNotEquals(email, testUser.getEmail());
		Assert.assertNotEquals(password, testUser.getPassword());
	}

}
