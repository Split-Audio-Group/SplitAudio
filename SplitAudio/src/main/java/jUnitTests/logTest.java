package jUnitTests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import util.*;

class logTest {

	@ParameterizedTest//(name = "{index} => input1={0}, input2= {1}, result={2}")
	@CsvFileSource(resources = "SessionLog.csv")
	void testLog(int user, int file, int expected) {
	      
	}

}
