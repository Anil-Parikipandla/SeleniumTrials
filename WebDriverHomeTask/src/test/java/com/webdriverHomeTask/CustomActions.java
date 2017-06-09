package com.webdriverHomeTask;

import java.text.DateFormatSymbols;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;

public class CustomActions {

	private WebElement Yearobject;
	private WebElement monthobject;
	private WebElement dayobject;

	
	public static void keyPress(WebDriver driver ){
		Actions act = new Actions(driver);
		act.sendKeys(Keys.TAB);
	}

	public static void pickDate(String dateType, String date){
		
		int day = Integer.parseInt(date.substring(0,2));
		int mon = Integer.parseInt(date.substring(3,5));
		String month = (new DateFormatSymbols().getMonths()[mon-1]).toString();
		int year = Integer.parseInt(date.substring(6));
		
		String fromDate = "//div[@id='depDateGroup']";
		String toDate = "//div[@id='return-date-div']";
		String sdate = "";
		CustomActions csactions = new CustomActions();
		if(dateType.equalsIgnoreCase("DepartureDate")){
			sdate = fromDate;
		}else if(dateType.equalsIgnoreCase("ReturnDate")){
			sdate = toDate;
		} else {
			Assert.fail("please provide proper dateType"); 
		}
		csactions.Yearobject = Runner.getDriver().findElement(By.xpath(sdate+"//div/span[@class='ui-datepicker-year']"));
		csactions.monthobject = Runner.getDriver().findElement(By.xpath(sdate+"//div/span[@class='ui-datepicker-month']"));
		csactions.dayobject = Runner.getDriver().findElement(By.xpath(sdate+"//table[@class='ui-datepicker-calendar']//td/a[contains(text(),'"+day+"')]"));
		WebElement nextArrow = Runner.getDriver().findElement(By.xpath(sdate+"//div//span[contains(text(),'Next')]"));

		int currentyear = Integer.parseInt(csactions.Yearobject.getText());
		String currentMonth = csactions.monthobject.getText();

		for(int i =0;i<=12;i++){
			if(currentyear==year){
				if(currentMonth.equalsIgnoreCase(month)){
					csactions.dayobject.click();
					System.out.println("selected Date is:"+day+"/"+currentMonth+"/"+currentyear );
					break;
				} else{
					nextArrow.click();
					nextArrow = Runner.getDriver().findElement(By.xpath(sdate+"//div//span[contains(text(),'Next')]"));
					csactions.monthobject = Runner.getDriver().findElement(By.xpath(sdate+"//div/span[@class='ui-datepicker-month']"));
					currentMonth=csactions.monthobject.getText();
					csactions.dayobject = Runner.getDriver().findElement(By.xpath(sdate+"//table[@class='ui-datepicker-calendar']//td/a[contains(text(),'"+day+"')]"));
				}
			} else{
				nextArrow.click();
				currentyear = Integer.parseInt(csactions.Yearobject.getText());
			}
		}

	}
}
