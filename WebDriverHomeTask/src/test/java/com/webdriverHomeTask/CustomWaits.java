package com.webdriverHomeTask;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class CustomWaits {

	public static void waitforElement(WebDriver driver, WebElement object,int Timeout ){
		WebDriverWait wait = new WebDriverWait(driver, 10);
		wait.until(ExpectedConditions.elementToBeClickable(object));
	}
	
	public static void waitforElementTillDisplayed(WebDriver driver, WebElement object, String attribute, String value, int Timeout ){
		WebDriverWait wait = new WebDriverWait(driver, 10);
		wait.until(ExpectedConditions.attributeContains(object, attribute, value));
	}
	
	public static void waitforStaleElement(WebDriver driver, WebElement object) throws InterruptedException{
		WebDriverWait wait = new WebDriverWait(driver, 10);
		wait.until(ExpectedConditions.not(ExpectedConditions.stalenessOf(object)));
		Thread.sleep(2000);
	}

}
