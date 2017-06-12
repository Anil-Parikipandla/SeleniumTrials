package com.webdriverHomeTask;

import java.text.DateFormatSymbols;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;

public class CustomActions {

	private WebElement monthobject;
	private WebElement dayobject;
	


	public static void keyPress(WebDriver driver, Keys key ){
		Actions act = new Actions(driver);
		Action kepress = act.sendKeys(Keys.ENTER).build();
				
	}

	public static void pickDate(WebDriver driver, String date) throws InterruptedException{

		date = (date.charAt(0)=='0')?(date.substring(1)):date;
		String[] words = date.split("\\.");

		int day = Integer.parseInt(words[0]);
		int mon = Integer.parseInt(words[1]);
		String month = (new DateFormatSymbols().getMonths()[mon-1]).toString() +" " +words[2];

		CustomActions csactions = new CustomActions();
		csactions.monthobject = Runner.getDriver().findElement(By.xpath("//div[@id='calendarLeft']//span[@id='monthLeft']"));
		csactions.dayobject = Runner.getDriver().findElement(By.xpath("//table[@class='ui-datepicker-calendar']//tbody[@id='dataLeft']//td/a[contains(text(),'"+day+"')]"));
		WebElement nextArrow = Runner.getDriver().findElement(By.xpath("//a[@id='nextMonth']"));

		String currentMonth = csactions.monthobject.getText();

		for(int i =0;i<=10;i++){
			if(currentMonth.equalsIgnoreCase(month)){
				CustomWaits.waitforStaleElement(driver, csactions.dayobject);
				csactions.dayobject.click();
				System.out.println("selected Date is:"+day+"/"+currentMonth+"/" );
				break;
			} else{
				CustomWaits.waitforStaleElement(driver, nextArrow);
				nextArrow.click();
				nextArrow = Runner.getDriver().findElement(By.xpath("//a[@id='nextMonth']"));
				csactions.monthobject = Runner.getDriver().findElement(By.xpath("//div[@id='calendarLeft']//span[@id='monthLeft']"));
				currentMonth=csactions.monthobject.getText();
				csactions.dayobject = Runner.getDriver().findElement(By.xpath("//table[@class='ui-datepicker-calendar']//tbody[@id='dataLeft']//td/a[contains(text(),'"+day+"')]"));
			}
		} 
	}
}
