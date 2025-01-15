package com.testauto.test;

import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.openqa.selenium.WebDriver;
import com.testauto.pageobjects.EbayHomePage;
import com.testauto.pageobjects.EbaySearchLefNavFilters;
import com.testauto.pageobjects.EbaySerachListPage;
import com.testauto.main.TestResultsWriter;
import com.testauto.main.ResourceReader;
import com.testauto.main.SetupDriver;
import com.testauto.main.SetupTestData;
import com.testauto.main.ScreenshotUtil;

public class EbaySearchTestSuite {

	private final String key = "samsung galaxy s22";
	private final String url = "http://www.ebay.com";
	 private  String network = "";
	 private  String option = "";
	 private  String filterOP1 = "";
	 private  String filterOP2 = "";

	private EbayHomePage ebayHome;
	private EbaySerachListPage ebaySerachList;
	private EbaySearchLefNavFilters ebaySearchLefNavFilters;

	private final TestResultsWriter excelReader = new TestResultsWriter();
	private WebDriver driver;

	@BeforeMethod
	public void setUp() {
		// Initialize WebDriver and Page Objects
		if (SetupDriver.getDriver() == null) {
			SetupDriver.SetupDriver(url); // Only initialize WebDriver once
		}
		SetupTestData.setUp();
		driver = SetupDriver.getDriver();
		ebayHome = new EbayHomePage();
		ebaySerachList = new EbaySerachListPage();
		ebaySearchLefNavFilters = new EbaySearchLefNavFilters(driver);
		network= SetupTestData.ebayFilter;
		option = SetupTestData.ebayFilterOp1;
		filterOP1 = SetupTestData.assertFOP1;
		filterOP2 = SetupTestData.assertFOP2;
		
		
		//SetupTestData.setUp();
		System.out.println("ebayFilter: " + SetupTestData.ebayFilter);
		System.out.println("ebayFilterOp1: " + SetupTestData.ebayFilterOp1);

		
	}

	@Test(priority = 1, description = "Verify the eBay logo is displayed on the homepage.")
	public void verifyEbayLogo() {
		  System.out.println("TEST1 -  Verify the eBay logo is displayed on the homepage");
		try {
			Assert.assertTrue(ebayHome.lblEbayLogo().isDisplayed(), "eBay logo is not displayed.");
			excelReader.writeTestResult("Verify Ebay Logo", "Pass");
		} catch (Exception ex) {
			excelReader.writeTestResult("Verify Ebay Logo", "Fail");
			ex.printStackTrace();
		} finally {
			ScreenshotUtil.captureScreenshot(driver, "verifyEbayLogo"); // Capture screenshot after the test
		}
	}

	@Test(priority = 2, dependsOnMethods = "verifyEbayLogo", description = "Search for an item and verify the results label is displayed.")
	public void searchItemAndVerifyResultsLabel() {
		System.out.println("TEST2 -  Search for an item and verify the results label is displayed");
		try {
			ebayHome.txtSearch().sendKeys(key);
			ebayHome.btnSearch().click();

			// Verify that the search results label is visible (handled by WebDriverWait in
			// EbaySearchLefNavFilters)
			Assert.assertTrue(ebaySerachList.lblTotalNumberofResults().isDisplayed(),
					"Total number of results label is not displayed.");
			excelReader.writeTestResult("Search Item and Verify Results Label", "Pass");
		} catch (Exception ex) {
			excelReader.writeTestResult("Search Item and Verify Results Label", "Fail");
			ex.printStackTrace();
		} finally {
			ScreenshotUtil.captureScreenshot(driver, "searchItemAndVerifyResultsLabel"); // Capture screenshot after the
																							// test
		}
	}

	// @Test(priority = 3, dependsOnMethods = "searchItemAndVerifyResultsLabel")
	@Test(priority = 3, dependsOnMethods = "searchItemAndVerifyResultsLabel", 
		      description = "Filter the item further from left navigation verify the results - positive test case ")
		public void filterSearchResultsPositiveTest() {
		System.out.println("TEST3 - Filter the item further from left navigation verify the results - positive test case");
		    try {
		        // Apply the left navigation filter
		        ebaySearchLefNavFilters.leftNavFilter(network, option);

		        if (ebaySearchLefNavFilters.elementfound) {
		            System.out.println(ebaySearchLefNavFilters.filterfound + " Test passed");

		            // Verify if the filter label is displayed
		            boolean isDisplayed = ebaySearchLefNavFilters.isFilterLabelDisplayed(filterOP1);
		            Assert.assertTrue(isDisplayed, "Filter label 'Unlocked' is displayed.");
		            excelReader.writeTestResult("Filter Search Results by Left Navigation", "Pass");
		        } else {
		            System.out.println(ebaySearchLefNavFilters.filterfound + " Test failed");
		            excelReader.writeTestResult("Filter Search Results by Left Navigation", "Fail");
		            Assert.fail("Filter element not found.");
		        }
		    } catch (Exception ex) {
		        System.out.println("Unexpected exception during test execution: " + ex.getMessage());
		        ex.printStackTrace();
		        excelReader.writeTestResult("Filter Search Results by Left Navigation", "Fail");
		        Assert.fail("An exception occurred: " + ex.getMessage());
		    } finally {
		        ScreenshotUtil.captureScreenshot(driver, "filterSearchResults");
		    }
		}

	/*
 		//This test is designed to demonstrate a failure test case. We only filter for 'unlock'.  Hence, the test cannot locate the 'T-Mobile' option and will result in failure  
   
		@Test(priority = 4, description = "Filter the item further from left navigation verify the results - negative test case ")
			public void filterSearchResultsNegativeTest() {
			System.out.println("TEST4 - Filter the item further from left navigation verify the results - negative test case");
			    try {
			    	 ebaySearchLefNavFilters.leftNavFilter(network, "T-Mobile");
		            // Verify if the filter label is displayed
			            boolean isDisplayed = ebaySearchLefNavFilters.isFilterLabelDisplayed(filterOP2);
			            if(isDisplayed) {
			            	 System.out.println(ebaySearchLefNavFilters.filterfound + " Test passed");
			            Assert.assertTrue(isDisplayed, "Filter label 'T-Mobile' is  displayed.");
			            excelReader.writeTestResult("Filter Search Results by Left Navigation", "Pass");
			        } else {
			            System.out.println(ebaySearchLefNavFilters.filterfound + " Test failed");
			            excelReader.writeTestResult("Filter Search Results by Left Navigation", "Fail");
			            Assert.fail("Filter element not found.");
			        }
			    }
			   catch (Exception ex) {
			        System.out.println("Unexpected exception during test execution: " + ex.getMessage());
			        ex.printStackTrace();
			        excelReader.writeTestResult("Filter Search Results by Left Navigation", "Fail");
			        Assert.fail("An exception occurred: " + ex.getMessage());
			    } finally {
			        ScreenshotUtil.captureScreenshot(driver, "filterSearchResults");
			    }
			}
*/
	
	@AfterTest
	public void tearDown() {
		excelReader.saveWorkbook();
		if (SetupDriver.getDriver() != null) {
			SetupDriver.quitDriver(); // Close the browser after all tests
		}
	}
}
