package jUnitTests;

import org.junit.platform.suite.api.SelectClasses;
import org.junit.platform.suite.api.Suite;

@Suite
@SelectClasses({ FileTest.class, logTest.class, UserTest.class })
public class AllTests {

}
