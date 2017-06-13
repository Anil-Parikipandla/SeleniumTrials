package com.webdriverHomeTask;

import java.text.DateFormatSymbols;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class CustomActions {

	public void pickDate(WebDriver driver, String date) throws InterruptedException{

		date = (date.charAt(0)=='0')?(date.substring(1)):date;
		String[] words = date.split("\\.");

		int day = Integer.parseInt(words[0]);
		int mon = Integer.parseInt(words[1]);
		String month = (new DateFormatSymbols().getMonths()[mon-1]).toString() +" " +words[2];

		String currentMonth = driver.findElement(By.xpath("//div[@id='calendarLeft']//span[@id='monthLeft']")).getText();
		System.out.println("current Month: "+currentMonth + "Month: "+ month);
		
		for(int i =0;i<=10;i++){
			if(currentMonth.equalsIgnoreCase(month)){
				CustomWaits.waitforStaleElement(driver,"//table[@class='ui-datepicker-calendar']//tbody[@id='dataLeft']//td/a[contains(text(),'"+day+"')]" );
				driver.findElement(By.xpath("//table[@class='ui-datepicker-calendar']//tbody[@id='dataLeft']//td/a[contains(text(),'"+day+"')]")).click();
				System.out.println("selected Date is:"+day+"/"+currentMonth+"/" );
				break;
			} else{
				CustomWaits.waitforStaleElement(driver, "//a[@id='nextMonth']");
				driver.findElement(By.xpath("//a[@id='nextMonth']")).click();
				currentMonth=driver.findElement(By.xpath("//div[@id='calendarLeft']//span[@id='monthLeft']")).getText();
			}
		} 
	}
}
