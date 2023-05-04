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
import java.util.concurrent.TimeUnit;
import org.junit.*;
import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.apache.commons.io.FileUtils;
import java.io.File;

public class TestNavigation {
  private WebDriver driver;
  private String baseUrl;
  private boolean acceptNextAlert = true;
  private StringBuffer verificationErrors = new StringBuffer();
  JavascriptExecutor js;
  @Before
  public void setUp() throws Exception {
    System.setProperty("webdriver.chrome.driver", "");
    driver = new ChromeDriver();
    baseUrl = "https://www.google.com/";
    driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(60));
    js = (JavascriptExecutor) driver;
  }

  @Test
  public void testNavigation() throws Exception {
    driver.get("http://csci4840-williams.ddns.net:8080/SplitAudio/Home.html");
    driver.findElement(By.linkText("Files")).click();
    driver.get("http://csci4840-williams.ddns.net:8080/SplitAudio/Files.html");
    driver.findElement(By.linkText("Edit")).click();
    driver.get("http://csci4840-williams.ddns.net:8080/SplitAudio/EditPage.html");
    driver.findElement(By.linkText("Account")).click();
    driver.get("http://csci4840-williams.ddns.net:8080/SplitAudio/Account.html");
    driver.findElement(By.linkText("Home")).click();
    driver.get("http://csci4840-williams.ddns.net:8080/SplitAudio/Home.html");
    driver.findElement(By.linkText("Account")).click();
    driver.get("http://csci4840-williams.ddns.net:8080/SplitAudio/Account.html");
    driver.findElement(By.linkText("Home")).click();
    driver.get("http://csci4840-williams.ddns.net:8080/SplitAudio/Home.html");
    driver.findElement(By.linkText("sign-up")).click();
    driver.get("http://csci4840-williams.ddns.net:8080/SplitAudio/Signup.html");
    driver.findElement(By.linkText("Home")).click();
    driver.get("http://csci4840-williams.ddns.net:8080/SplitAudio/Home.html");
    driver.findElement(By.linkText("login")).click();
    driver.get("http://csci4840-williams.ddns.net:8080/SplitAudio/LogIn.html");
    driver.findElement(By.linkText("Files")).click();
    driver.get("http://csci4840-williams.ddns.net:8080/SplitAudio/Files.html");
    driver.findElement(By.linkText("Edit")).click();
    driver.get("http://csci4840-williams.ddns.net:8080/SplitAudio/EditPage.html");
    driver.findElement(By.linkText("Account")).click();
    driver.get("http://csci4840-williams.ddns.net:8080/SplitAudio/Account.html");
    driver.findElement(By.xpath("//div[@id='page_wrapper']/div/form[2]/input")).click();
    driver.get("http://csci4840-williams.ddns.net:8080/SplitAudio/Signup.html");
    driver.findElement(By.linkText("Edit")).click();
    driver.get("http://csci4840-williams.ddns.net:8080/SplitAudio/EditPage.html");
    driver.findElement(By.linkText("Account")).click();
    driver.get("http://csci4840-williams.ddns.net:8080/SplitAudio/Account.html");
    driver.findElement(By.xpath("//input[@name='']")).click();
    driver.get("http://csci4840-williams.ddns.net:8080/SplitAudio/LogIn.html");
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
