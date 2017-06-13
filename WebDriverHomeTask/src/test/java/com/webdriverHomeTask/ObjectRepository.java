package com.webdriverHomeTask;

import org.openqa.selenium.By;

public class ObjectRepository {
	
	private static final String bookAFlight = "//div[@id='navSecondary']//li/a[contains(text(), 'Book a flight')]";
	private static final String departureAirport = "//input[@id='seldcity1-suggest']";
	private static final String arrivalAirport = "//input[@id='selacity1-suggest']";
	private static final String departureDate = "//div[@id='navSecondary']//div[@id='flightSearch']//div/label[contains(text(), 'Departure date')]";
	private static final String adults = "//div[@id='navSecondary']//div[@id='seladults_chosen']/a/span";
	private static final String addchildren = "//div[@id='navSecondary']//*[contains(text(),'Add child or infant.')]/..";
	private static final String children = "//div[@id='navSecondary']//div[@id='selchildren_chosen']/a[@role='combobox']";
	private static final int noOfChildren = 1;
	private static final String noOfChildrenxpath = "//div[@id='navSecondary']//ul[@class='chosen-results']/li[@text='"+noOfChildren+"']";
	private static final String findFlights = "//input[@id='btnStartBooking']";
	private static final String departingDate = "//*[@id='resultSearchHolder']/table//*[normalize-space(text())='Departing']/..";
	private static final String returningDate = "//*[@id='resultSearchHolder']/table//*[normalize-space(text())='Returning']/..";
	
	
	
	
	
	//Application Home page 
	By lBookAFlight =  By.xpath(bookAFlight);
	By lDepartureAirport = By.xpath(departureAirport);
	By lArrivalAirport = By.xpath(arrivalAirport);
	By lDepartureDateCalendar = By.xpath(departureDate);
	By lAdults = By.xpath(adults);
	By lAddchildren = By.xpath(addchildren);
	By lChildren = By.xpath(children);
	By lNoOfChildren = By.xpath(noOfChildrenxpath);
	By lFindFlights = By.xpath(findFlights);
	By ldepartingdate = By.xpath(departingDate);
	By lreturningDate = By.xpath(returningDate);



	

	
	
	
	
}