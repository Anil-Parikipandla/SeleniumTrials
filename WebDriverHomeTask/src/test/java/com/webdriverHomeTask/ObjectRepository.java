package com.webdriverHomeTask;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class ObjectRepository {
	
	public ObjectRepository() {
		Runner runner = new Runner();
		WebDriver driver = runner.getDriver();
	}
	
	private static final String appURL = "https://www.emirates.com/uk/english/";
	private static final String bookAFlight = "//div[@id='navSecondary']//li/a[contains(text(), 'Book a flight')]";
	private static final String departureAirport = "//input[@id='seldcity1-suggest']";
	private static final String arrivalAirport = "//input[@id='selacity1-suggest']";
	private static final String departureDate = "//div[@id='navSecondary']//div[@id='flightSearch']//div/label[contains(text(), 'Departure date')]";
	private static final String adults = "//div[@id='navSecondary']//div[@id='seladults_chosen']/a";
	private static final String addchildren = "//div[@id='navSecondary']//div//span[contains(text(), 'Add teenager/child/infant')]";
	private static final String children = ".//div[@id='navSecondary']//div[@id='selchildren_chosen']/a/span";
	private static final String noOfChildren = "//div[@id='navSecondary']//ul[@class='chosen-results']/li[@text='1']";
	
	
	
	
	
	//Application Home page 
	WebElement bookAFlightButton =  Runner.getDriver().findElement(By.xpath(bookAFlight));
			

	
	
	
	
}
