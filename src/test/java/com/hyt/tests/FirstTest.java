package com.hyt.tests;

import static org.testng.Assert.*;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.testng.annotations.Ignore;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import io.github.bonigarcia.wdm.WebDriverManager;

@Ignore
public class FirstTest {
	@Test
	public void testGoogle() {
		WebDriverManager.edgedriver().setup();
		WebDriver driver = new EdgeDriver();
		driver.manage().window().maximize();
		driver.get("https://www.google.com");
		driver.findElement(By.name("q")).sendKeys("Anand Chenniappan", Keys.ENTER);
		System.out.println(driver.getTitle());
		assertEquals(driver.getTitle(), "Anand Chenniappan - Google Search", "Title is mismatched.");
		driver.quit();
	}

	@Test
	public void testBing() throws Exception {
		WebDriverManager.chromedriver().setup();
		WebDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.get("https://www.bing.com/");
		driver.findElement(By.name("q")).sendKeys("Anand Chenniappan", Keys.ENTER);
		System.out.println(driver.getTitle());
		driver.quit();
	}
	
	@Test(enabled = true)
	public void testFacebook() {
		WebDriverManager.chromedriver().setup();
		WebDriver driver = new ChromeDriver();
		SoftAssert softAssert = new SoftAssert();
		driver.get("https://www.facebook.com");
		driver.findElement(By.id("email")).sendKeys("++++++99890556)))==", Keys.ENTER);
		System.out.println(driver.getTitle());
		System.out.println(driver.getCurrentUrl());
		softAssert.assertEquals(driver.getTitle(), "Log in to Facebook", "Title is mismatched.");
		softAssert.assertTrue(driver.getCurrentUrl().contains("privacy_mutation_token"), "Wrong URL");
		softAssert.assertEquals(driver.findElement(By.id("email")).getText(), "", "Email text box is not reset to empty.");
		softAssert.assertEquals(driver.findElement(By.id("email")).getCssValue("border"), "1px solid rgb(240, 40, 73)", "Border is not correct.");
		softAssert.assertEquals(driver.findElement(By.xpath("(//div[@id='email_container']/div)[last()]")).getText(), "The email address or mobile number you entered isn't connected to an account. Find your account and log in.", "Error message is wrong.");
		driver.quit();
		softAssert.assertAll();
	}

}
