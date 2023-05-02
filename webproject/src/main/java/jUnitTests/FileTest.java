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

import org.junit.jupiter.api.Test;

class FileTest {

	@ParameterizedTest//(name = "{index} => input1={0}, input2= {1}, result={2}")
	@CsvFileSource(resources = "FileList.csv")
	void testConstructor(int id, int uid, String name, String path, Double size, Boolean pub) {
		Audio_Files testFile = new Audio_Files(id, uid, name, path, size, pub);
		testFile(id, uid, name, path, size, pub, testFile);
	}
	
	@ParameterizedTest//(name = "{index} => input1={0}, input2= {1}, result={2}")
	@CsvFileSource(resources = "FileList.csv")
	void testSet(int id, int uid, String name, String path, Double size, Boolean pub) {
		Audio_Files testFile = new Audio_Files();
		testNotFile(id, uid, name, path, size, pub, testFile);
		testFile.setId(id);
		testFile.setuid(uid);
		testFile.setName(name);
		testFile.setFilepath(path);
		testFile.setSize(size);
		testFile.setpub(pub);
		testFile(id, uid, name, path, size, pub, testFile);
	}
	
	void testFile(int id, int uid, String name, String path, Double size, Boolean pub, Audio_Files testFile) {
		Assert.assertEquals((Integer) id, testFile.getId());
		Assert.assertEquals((Integer) uid, testFile.getuid());
		Assert.assertEquals(name, testFile.getName());
		Assert.assertEquals(path, testFile.getFilepath());
		Assert.assertEquals(size, testFile.getSize());
		Assert.assertEquals(pub, testFile.getpub());
	}
	void testNotFile(int id, int uid, String name, String path, Double size, Boolean pub, Audio_Files testFile) {
		Assert.assertNotEquals((Integer) id, testFile.getId());
		Assert.assertNotEquals((Integer) uid, testFile.getuid());
		Assert.assertNotEquals(name, testFile.getName());
		Assert.assertNotEquals(path, testFile.getFilepath());
		Assert.assertNotEquals(size, testFile.getSize());
		Assert.assertNotEquals(pub, testFile.getpub());
	}

}
