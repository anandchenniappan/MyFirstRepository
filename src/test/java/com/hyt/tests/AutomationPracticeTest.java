package com.hyt.tests;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Ignore;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

@Ignore
public class AutomationPracticeTest {
	WebDriver driver;

	@Parameters("browserName")
	@BeforeTest
	public void initializeBrowser(@Optional("chrome") String browserName) {
		switch (browserName.toLowerCase()) {
		case "chrome":
			WebDriverManager.chromedriver().setup();
			driver = new ChromeDriver();
			break;
		case "firefox":
			WebDriverManager.firefoxdriver().setup();
			driver = new FirefoxDriver();
			break;
		case "edge":
			WebDriverManager.edgedriver().setup();
			driver = new EdgeDriver();
			break;
		default:
			System.out.println("Given browser "+browserName+" is not supported.");
			break;
		}
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
		driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(30));
		driver.manage().timeouts().scriptTimeout(Duration.ofSeconds(30));
	}

	@AfterTest
	public void tearDown() {
		driver.quit();
	}

	@Parameters({"url", "userName", "passWord"})
	@Test
	public void loginTest(String url, String username, String password) {
		driver.get(url);
		driver.findElement(By.partialLinkText("Sign in")).click();
		driver.findElement(By.id("email")).sendKeys(username);
		driver.findElement(By.id("passwd")).sendKeys(password, Keys.ENTER);
	}

	@Test(enabled = false)
	public void VerifyLoginTest() {
		System.out.println(driver.findElement(By.partialLinkText("Sign out")).isDisplayed());
	}

	@Test
	public void NavigateToContactUsTest() {
		driver.findElement(By.linkText("Contact us")).click();
		System.out.println(driver.findElement(By.xpath("//span[contains(text(), 'Contact')]")).isDisplayed());
	}

	@Test
	public void logOutTest() {
		driver.findElement(By.partialLinkText("Sign out")).click();
		System.out.println(driver.findElement(By.partialLinkText("Sign in")).isDisplayed());
	}
}
