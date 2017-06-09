package com.webdriverHomeTask;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;


public class BookAFlightTest {
	private String APPLICATION_URL = "https://www.airbaltic.com/en-ZZ/index";
	private String expectedPageTitle = "Official airBaltic Website | Cheap Flights to the Baltics, Russia, Europe";
	private String expectedFromCityName = "St Petersburg"; 
	private String expectedFromCountryName = "Russian Federation"; 
	private String expectedToCityName = "Stockholm"; 
	private String expectedToCountryName = "Sweden"; 
	private String departureDate = "12.06.2017";
	private String returnDate = "13.07.2017";
	private String actualNoOfAdults = "";
	private String expectedNoOfAdults = "1 adult";
	private String actualNoOfChildren = "";
	private String expectedNoOfChildren = "1 child";
	private String searchResultsPageHeader = "";
	private String expectedsearchResultsPageHeader = "Flights and ticket types";
	
	
	private WebElement originTextBox;
	private WebElement destinationTextBox;
	private WebElement NoOfChildren;
	
	
	@BeforeClass
	public void OpenBrowser(){
		Runner.getDriver();
	}
	
	@Test
	public void UserCanBookAFlight() throws InterruptedException{
		Runner.getDriver().get(APPLICATION_URL);
		
		String actualPageTitle = Runner.getDriver().getTitle();
		Assert.assertEquals( actualPageTitle,expectedPageTitle,"Application did not match the expected");
		System.out.println("Application Opened successfully!");
		
		originTextBox = Runner.getDriver().findElement(By.xpath("//input[@id='flt_origin_text']"));
		CustomWaits.waitforElement(Runner.getDriver(), originTextBox, 2);
		originTextBox.sendKeys("St ");
		String actualFromName = originTextBox.getAttribute("value");
		Assert.assertTrue((actualFromName.contains(expectedFromCityName)) & (actualFromName.contains(expectedFromCountryName)), "Expected City or/and Country is not found.");
		System.out.println("Selected " +expectedFromCityName+ " "+expectedFromCountryName+" in From field.");
		
		destinationTextBox = Runner.getDriver().findElement(By.xpath("//input[@id='flt_destin_text']"));
		CustomWaits.waitforElement(Runner.getDriver(), destinationTextBox, 2);
		destinationTextBox.sendKeys("Sto");
		String actualToName = destinationTextBox.getAttribute("value");
		Assert.assertTrue((actualToName.contains(expectedToCityName))&(actualToName.contains(expectedToCountryName)),"Expcted City Name or/and Country Name is not found.");
		System.out.println("Selected " +expectedToCityName+ " "+expectedToCountryName+" in To field.");
		
		CustomActions.pickDate("departuredate", departureDate);
		System.out.println("Selected the Departure date as: "+departureDate);

		CustomActions.pickDate("returnDate", returnDate);
		System.out.println("Selected the Return date as: "+returnDate);

		
		actualNoOfAdults = Runner.getDriver().findElement(By.xpath("//div[@id='fltHiddenFormPart']//div/label[contains(text(),'Adults')]/ancestor::div/div[@class='amout-line']//a[@role='button']")).getText();
		Assert.assertEquals(actualNoOfAdults, expectedNoOfAdults, "No of Adults is not matching.");
		System.out.println("Verified Number of Adults selected is: "+expectedNoOfAdults);
		
		
		NoOfChildren = Runner.getDriver().findElement(By.xpath("//div[@id='fltHiddenFormPart']//div/label[contains(text(),'Children')]/ancestor::div/div[@class='amout-line']//div/div[@class='clicker  plus-amount-light']"));
		CustomWaits.waitforElement(Runner.getDriver(), NoOfChildren, 2);
		NoOfChildren.click();
		
		actualNoOfChildren = Runner.getDriver().findElement(By.xpath("//div[@id='fltHiddenFormPart']//div/label[contains(text(),'Children')]/ancestor::div/div[@class='amout-line']//a[@role='button']")).getText();
		Assert.assertEquals(actualNoOfChildren, expectedNoOfChildren, "No fo Children is not matching.");
		System.out.println("Verified the Number of Children selected is: "+expectedNoOfChildren);
		
		Runner.getDriver().findElement(By.xpath("//div/button[@type='submit']/span[contains(text(),'Find flights & fares')]")).click();
		Runner.getDriver().findElement(By.xpath("//h2[contains(text(),'Flights and ticket types')]")).getText();
		Assert.assertEquals(searchResultsPageHeader, expectedsearchResultsPageHeader,"User clicked on Find Flights and Fare but, Search Results page is not Opened.");
					
	}

}
