package jUnitTests;

import java.util.regex.Pattern;
import java.util.concurrent.TimeUnit;
import org.junit.*;
import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.apache.commons.io.FileUtils;
import java.io.File;
import java.time.Duration;

public class TestLogin {
  private WebDriver driver;
  private String baseUrl;
  private boolean acceptNextAlert = true;
  private StringBuffer verificationErrors = new StringBuffer();
  JavascriptExecutor js;
  @Before
  public void setUp() throws Exception {
	System.setProperty("webdriver.chrome.driver", "C:/Users/andra/workspaceCIST4830-Williams/workspace-SplitAudio/SplitAudio/lib/win/chromedriver.exe");
    driver = new ChromeDriver();
    baseUrl = "https://www.google.com/";
    driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(60));
    js = (JavascriptExecutor) driver;
  }

  @Test
  public void testLogin() throws Exception {
    driver.get("http://csci4840-williams.ddns.net:8080/SplitAudio/Home.html");
    driver.findElement(By.linkText("Home")).click();
    driver.findElement(By.linkText("login")).click();
    driver.get("http://csci4840-williams.ddns.net:8080/SplitAudio/LogIn.html");
    driver.findElement(By.linkText("Home")).click();
    driver.get("http://csci4840-williams.ddns.net:8080/SplitAudio/Home.html");
    driver.findElement(By.linkText("Home")).click();
    driver.findElement(By.linkText("login")).click();
    driver.get("http://csci4840-williams.ddns.net:8080/SplitAudio/LogIn.html");
    driver.findElement(By.name("username")).click();
    driver.findElement(By.name("username")).clear();
    driver.findElement(By.name("username")).sendKeys("loginTest");
    driver.findElement(By.name("password")).click();
    driver.findElement(By.name("password")).clear();
    driver.findElement(By.name("password")).sendKeys("test");
    driver.findElement(By.xpath("//input[@name='']")).click();
    driver.findElement(By.xpath("//form[2]/input")).click();
    driver.get("http://csci4840-williams.ddns.net:8080/SplitAudio/Signup.html");
    driver.findElement(By.name("username")).click();
    driver.findElement(By.name("username")).clear();
    driver.findElement(By.name("username")).sendKeys("loginTest");
    driver.findElement(By.name("displayname")).click();
    driver.findElement(By.name("displayname")).clear();
    driver.findElement(By.name("displayname")).sendKeys("Login");
    driver.findElement(By.name("email")).click();
    driver.findElement(By.name("email")).clear();
    driver.findElement(By.name("email")).sendKeys("login@email.com");
    driver.findElement(By.id("myInput")).click();
    driver.findElement(By.id("myInput")).clear();
    driver.findElement(By.id("myInput")).sendKeys("test");
    driver.findElement(By.xpath("//input[@type='checkbox']")).click();
    driver.findElement(By.xpath("//input[@type='checkbox']")).click();
    driver.findElement(By.xpath("//input[@type='checkbox']")).click();
    driver.findElement(By.xpath("//input[@type='checkbox']")).click();
    driver.findElement(By.xpath("//input[@name='']")).click();
    driver.get("http://csci4840-williams.ddns.net:8080/SplitAudio/Signup");
    driver.findElement(By.linkText("Log In")).click();
    driver.get("http://csci4840-williams.ddns.net:8080/SplitAudio/LogIn.html");
    driver.findElement(By.name("username")).click();
    driver.findElement(By.name("username")).clear();
    driver.findElement(By.name("username")).sendKeys("loginTest");
    driver.findElement(By.name("password")).click();
    driver.findElement(By.name("password")).clear();
    driver.findElement(By.name("password")).sendKeys("test");
    driver.findElement(By.xpath("//input[@name='']")).click();
    driver.get("http://csci4840-williams.ddns.net:8080/SplitAudio/Home.html");
    driver.findElement(By.linkText("login")).click();
    driver.get("http://csci4840-williams.ddns.net:8080/SplitAudio/LogIn.html");
    driver.findElement(By.name("username")).click();
    driver.findElement(By.name("username")).clear();
    driver.findElement(By.name("username")).sendKeys("loginTest");
    driver.findElement(By.name("password")).click();
    driver.findElement(By.name("password")).clear();
    driver.findElement(By.name("password")).sendKeys("test");
    driver.findElement(By.xpath("//input[@name='']")).click();
    driver.findElement(By.xpath("//form[2]/input")).click();
    driver.get("http://csci4840-williams.ddns.net:8080/SplitAudio/Signup.html");
    driver.findElement(By.name("username")).click();
    driver.findElement(By.name("username")).clear();
    driver.findElement(By.name("username")).sendKeys("loginTest");
    driver.findElement(By.name("displayname")).click();
    driver.findElement(By.name("displayname")).clear();
    driver.findElement(By.name("displayname")).sendKeys("Login");
    driver.findElement(By.name("email")).click();
    driver.findElement(By.name("email")).click();
    driver.findElement(By.name("email")).clear();
    driver.findElement(By.name("email")).sendKeys("test@email.com");
    driver.findElement(By.id("myInput")).click();
    driver.findElement(By.id("myInput")).clear();
    driver.findElement(By.id("myInput")).sendKeys("test");
    driver.findElement(By.xpath("//input[@type='checkbox']")).click();
    driver.findElement(By.xpath("//input[@type='checkbox']")).click();
    driver.findElement(By.xpath("//input[@name='']")).click();
    driver.get("http://csci4840-williams.ddns.net:8080/SplitAudio/Signup");
    driver.findElement(By.linkText("Log In")).click();
    driver.get("http://csci4840-williams.ddns.net:8080/SplitAudio/LogIn.html");
    driver.findElement(By.name("username")).click();
    driver.findElement(By.name("username")).clear();
    driver.findElement(By.name("username")).sendKeys("loginTest");
    driver.findElement(By.name("password")).click();
    driver.findElement(By.name("password")).clear();
    driver.findElement(By.name("password")).sendKeys("test");
    driver.findElement(By.xpath("//input[@name='']")).click();
  }

  @After
  public void tearDown() throws Exception {
    driver.quit();
    String verificationErrorString = verificationErrors.toString();
    if (!"".equals(verificationErrorString)) {
      fail(verificationErrorString);
    }
  }

  private boolean isElementPresent(By by) {
    try {
      driver.findElement(by);
      return true;
    } catch (NoSuchElementException e) {
      return false;
    }
  }

  private boolean isAlertPresent() {
    try {
      driver.switchTo().alert();
      return true;
    } catch (NoAlertPresentException e) {
      return false;
    }
  }

  private String closeAlertAndGetItsText() {
    try {
      Alert alert = driver.switchTo().alert();
      String alertText = alert.getText();
      if (acceptNextAlert) {
        alert.accept();
      } else {
        alert.dismiss();
      }
      return alertText;
    } finally {
      acceptNextAlert = true;
    }
  }
}
