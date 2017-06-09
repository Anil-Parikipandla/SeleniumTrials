package com.webdriverHomeTask;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

/*Create Driver Instance*/

public class Runner {
	public static WebDriver driver;

	public static WebDriver getDriver(){
		if(driver==null){
			System.setProperty("webdriver.chrome.driver", "src/test/resources/chromedriver.exe");
			driver = new ChromeDriver();
			driver.manage().window().maximize();
			driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
			System.out.println("Opened Chrome Browser.");
		}
		return driver;

	}

	public static void NavigateToURL(String URL){
		driver.get(URL);
		System.out.println("Navigated to the Application URL");
	}

	public static WebDriver closeDriver(){
		if(driver!=null){
			driver.close();
			driver.quit();
			driver=null;
		} return driver;
	}

}
