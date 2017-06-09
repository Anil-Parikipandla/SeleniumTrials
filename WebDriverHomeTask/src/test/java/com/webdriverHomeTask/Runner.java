package com.webdriverHomeTask;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

/*Create Driver Instance*/

public class Runner {
	public static WebDriver driver;
	
	public static void getDriver(){	
	System.setProperty("webdriver.chrome.driver", "src/test/resources/chromedriver.exe");
	driver = new ChromeDriver();
	driver.manage().window().maximize();
	driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	System.out.println("Opened Chrome Browser.");
	}
	
	public static void NavigateToURL(String URL){
		driver.get(URL);
		System.out.println("Navigated to the Application URL");
	}
	
}
