package com.hyt.tests;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import io.github.bonigarcia.wdm.WebDriverManager;

public class DataProviderDemo {

	WebDriver driver;
	SoftAssert softAssert = new SoftAssert();

	@Parameters("browserName")
	@BeforeMethod
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
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
		driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(20));
		driver.manage().timeouts().scriptTimeout(Duration.ofSeconds(30));
	}

	@AfterMethod
	public void tearDown() {
		driver.quit();
		softAssert.assertAll();
	}

	@Test(dataProvider = "loginData", dataProviderClass = DataSupplier.class)
	public void loginTest(String username, String password) {
		driver.get("http://automationpractice.com/index.php");
		driver.findElement(By.partialLinkText("Sign in")).click();
		driver.findElement(By.id("email")).sendKeys(username);
		driver.findElement(By.id("passwd")).sendKeys(password, Keys.ENTER);
		softAssert.assertTrue(driver.findElement(By.partialLinkText("Sign out")).isDisplayed());
	}
	
}
