package com.webdriverHomeTask;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;


public class BookAFlightTest {
	
	private WebDriver driver;
	private ObjectRepository OR;
	private CustomActions CA;
	private static final String APPLICATION_URL = "https://www.emirates.com/uk/english/";
	private static final String expectedPageTitle = "Emirates flights – Book a flight, browse our flight offers and explore the Emirates Experience";
	private static final String expectedFromCityName = "St Petersburg (LED)"; 
	private static final String expectedToCityName = "Dubai (DXB)"; 
	private static final String departureDate = "14.07.2017";
	private static final String returnDate = "15.07.2017";
	private static final String expectedNoOfAdults = "1";
	private static final String expectedNoOfChildren = "1";
	private static final String expectedsearchResultsPageHeader = "Make a booking";
	private static final String expectedConnectionIn = "via DME";
	private static String actualNoOfAdults = "";
	private static String actualNoOfChildren = "";
	private static String searchResultsPageHeader = "";
	private static String expectedDepartingDate = "14 July 2017";
	private static String expectedReturningDate = "15 July 2017";
	
	@BeforeClass
	public void OpenBrowser(){
		driver = Runner.getDriver();
		OR = new ObjectRepository();
		CA = new CustomActions();
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
		CustomWaits.waitforElement(driver, "//input[@id='seldcity1-suggest']");
		driver.findElement(OR.lDepartureAirport).clear();
		driver.findElement(OR.lDepartureAirport).sendKeys("St Petersburg");
		driver.findElement(OR.lDepartureAirport).sendKeys(Keys.ENTER);
		System.out.println("clicked Enter");
		String actualFromName = driver.findElement(OR.lDepartureAirport).getAttribute("value");
		Assert.assertEquals(actualFromName, expectedFromCityName, "Expected City is not found.");
		System.out.println("Selected " +expectedFromCityName+" in From field.");
		
		//select the Arrival Airport
		CustomWaits.waitforElement(driver, "//input[@id='selacity1-suggest']");
		//driver.findElement(OR.lArrivalAirport).clear();
		driver.findElement(OR.lArrivalAirport).sendKeys("Dubai");
		driver.findElement(OR.lArrivalAirport).sendKeys(Keys.ENTER);
		String actualToName = driver.findElement(OR.lArrivalAirport).getAttribute("value");
		Assert.assertEquals(actualToName, expectedToCityName,"Expcted City Name or/and Country Name is not found.");
		System.out.println("Selected " +expectedToCityName+ " in To field.");
		
		//Select the departure Date
		driver.findElement(OR.lDepartureDateCalendar).click();
		CA.pickDate(driver,departureDate);
		System.out.println("Selected the Departure date as: "+departureDate);
		
		//select the return date
		CA.pickDate(driver,returnDate);
		System.out.println("Selected the Return date as: "+returnDate);

		//Verify the Number of Adults selected
		actualNoOfAdults = driver.findElement(OR.lAdults).getText();
		Assert.assertEquals(actualNoOfAdults, expectedNoOfAdults, "No of Adults is not matching.");
		System.out.println("Verified Number of Adults selected is: "+expectedNoOfAdults);
		Thread.sleep(2000);
		
		//Select the Number of children
//		CustomWaits.waitforElement(driver, "//div[@id='navSecondary']//div/a[@class='add-teen-child-infant blue-outline-link add-childinfant-link add-child switchteenagerLink']");
		driver.findElement(OR.lAddchildren).click();
		driver.findElement(OR.lChildren).click();
		driver.findElement(OR.lNoOfChildren).click();
		Thread.sleep(2000);
		
		//verify the Number of children
		actualNoOfChildren = driver.findElement(By.xpath("//a[@aria-label='Select the number of children aged between two and 11 years of age - 1  selected']//span")).getText();
		Assert.assertEquals(actualNoOfChildren, expectedNoOfChildren, "No fo Children is not matching.");
		System.out.println("Verified the Number of Children selected is: "+expectedNoOfChildren);
		
		//click on the Find flights button and verify search page is displayed successfully.
		driver.findElement(OR.lFindFlights).click();
		searchResultsPageHeader=driver.findElement(By.xpath("//h1[contains(text(),'Make a booking')]")).getText();
		Assert.assertEquals(searchResultsPageHeader, expectedsearchResultsPageHeader,"User clicked on Find Flights and Fare but, Search Results page is not Opened.");
					
		//verify the different prices available for Outbound and Inbound
		
		
		//verify the departing and returning dates
		Assert.assertEquals(driver.findElement(OR.ldepartingdate).getText(),expectedDepartingDate,"Departure date is not matching with the provided departure date.");
		Assert.assertEquals(driver.findElement(OR.lreturningDate).getText(), expectedReturningDate, "Returning date is not matching with the provided Return date.");
		
		//verify the flight has connection to moscow
		System.out.println(driver.findElement(By.xpath("//*[@class='via-text']")).getText());
		List<WebElement> listOfMoscowFlights = driver.findElements(By.xpath("//*[@class='via-text']"));
		for(WebElement i : listOfMoscowFlights){
			Assert.assertEquals(i.getText(), expectedConnectionIn, "Fligths connecting to moscow not found.");
		}
		
		
		
	}

}