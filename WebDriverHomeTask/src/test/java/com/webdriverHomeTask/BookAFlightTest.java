package com.webdriverHomeTask;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import com.webdriverHomeTask.CustomActions;
import com.webdriverHomeTask.CustomWaits;
import com.webdriverHomeTask.Runner;


public class BookAFlightTest {
	
	private WebDriver driver;
	private ObjectRepository OR;
	private String APPLICATION_URL = "https://www.emirates.com/uk/english/";
	private String expectedPageTitle = "Emirates flights – Book a flight, browse our flight offers and explore the Emirates Experience";
	private String expectedFromCityName = "St Petersburg (LED)"; 
	private String expectedToCityName = "Dubai (DXB)"; 
	private String departureDate = "14.07.2017";
	private String returnDate = "15.07.2017";
	private String actualNoOfAdults = "";
	private String expectedNoOfAdults = "1";
	private String actualNoOfChildren = "";
	private String expectedNoOfChildren = "1";
	private String searchResultsPageHeader = "";
	private String expectedsearchResultsPageHeader = "Flights and ticket types";
	

/*	WebElement bookAFlightlink =  driver.findElement(OR.lBookAFlight);
	WebElement depatureAirportTextbox = driver.findElement(OR.lDepartureAirport);
	WebElement arrivalAirportTextbox = driver.findElement(OR.lArrivalAirport);
	WebElement adultsdropdown = driver.findElement(OR.lAdults);
	WebElement addChildren = driver.findElement(OR.lAddchildren);
	WebElement childrendropdown = driver.findElement(OR.lChildren);
	WebElement NoOfChildren = driver.findElement(OR.lNoOfChildren);*/

	
	@BeforeClass
	public void OpenBrowser(){
		driver = Runner.getDriver();
		OR = new ObjectRepository();
	}
	
	@Test
	public void UserCanBookAFlight() throws InterruptedException{
		//Open the Emirates Flight Application
		driver.get(APPLICATION_URL);
		
		//Verify Emirates Website is opened successfully
		String actualPageTitle = driver.getTitle();
		Assert.assertEquals( actualPageTitle,expectedPageTitle,"Application did not match the expected");
		System.out.println("Application Opened successfully!");
		
		//Click on Book a Flight button
		driver.findElement(OR.lBookAFlight).click();
		
		//select the Departure Airport
		CustomWaits.waitforElement(driver, driver.findElement(OR.lDepartureAirport), 2);
		driver.findElement(OR.lDepartureAirport).clear();
		driver.findElement(OR.lDepartureAirport).sendKeys("St Petersburg");
		driver.findElement(OR.lDepartureAirport).sendKeys(Keys.ENTER);
		System.out.println("clicked Enter");
		String actualFromName = driver.findElement(OR.lDepartureAirport).getAttribute("value");
		Assert.assertEquals(actualFromName, expectedFromCityName, "Expected City is not found.");
		System.out.println("Selected " +expectedFromCityName+" in From field.");
		
		//select the Arrival Airport
		CustomWaits.waitforElement(driver, driver.findElement(OR.lArrivalAirport), 2);
		driver.findElement(OR.lArrivalAirport).clear();
		driver.findElement(OR.lArrivalAirport).sendKeys("Dubai");
		driver.findElement(OR.lArrivalAirport).sendKeys(Keys.ENTER);
		String actualToName = driver.findElement(OR.lArrivalAirport).getAttribute("value");
		Assert.assertEquals(actualToName, expectedToCityName,"Expcted City Name or/and Country Name is not found.");
		System.out.println("Selected " +expectedToCityName+ " in To field.");
		
		//Select the departure Date
		driver.findElement(OR.lDepartureDateCalendar).click();
		CustomActions.pickDate(driver,departureDate);
		System.out.println("Selected the Departure date as: "+departureDate);
		
		//select the return date
		CustomActions.pickDate(driver,returnDate);
		System.out.println("Selected the Return date as: "+returnDate);

		//Verify the Number of Adults selected
		actualNoOfAdults = driver.findElement(OR.lAdults).getText();
		Assert.assertEquals(actualNoOfAdults, expectedNoOfAdults, "No of Adults is not matching.");
		System.out.println("Verified Number of Adults selected is: "+expectedNoOfAdults);
		Thread.sleep(2000);
		
		//Select the Number of children
		driver.findElement(OR.lAddchildren).click();
		driver.findElement(OR.lChildren).click();
		driver.findElement(OR.lNoOfChildren).click();
		Thread.sleep(2000);
		
		//verify the Number of children
		actualNoOfChildren = driver.findElement(By.xpath("//a[@aria-label='Select the number of children aged between two and 11 years of age - 1  selected']//span")).getText();
		Assert.assertEquals(actualNoOfChildren, expectedNoOfChildren, "No fo Children is not matching.");
		System.out.println("Verified the Number of Children selected is: "+expectedNoOfChildren);
		
		//
		driver.findElement(By.xpath("//div/button[@type='submit']/span[contains(text(),'Find flights & fares')]")).click();
		driver.findElement(By.xpath("//h2[contains(text(),'Flights and ticket types')]")).getText();
		Assert.assertEquals(searchResultsPageHeader, expectedsearchResultsPageHeader,"User clicked on Find Flights and Fare but, Search Results page is not Opened.");
					
	}

}