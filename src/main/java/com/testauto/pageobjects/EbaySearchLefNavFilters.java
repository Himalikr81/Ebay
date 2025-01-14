package com.testauto.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.testauto.main.ScreenshotUtil;
import com.testauto.main.SetupDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.List;

public class EbaySearchLefNavFilters {

	WebDriver driver;
	WebDriverWait wait;
	public boolean elementfound = false;
	public static String filterfound = "";

	public EbaySearchLefNavFilters(WebDriver driver) {
		this.driver = driver;
		this.wait = new WebDriverWait(driver, 15);
	}

	// Check if filter label is displayed
	public static boolean isFilterLabelDisplayed(String filterName) {
		try {
			String xpath = String.format("//li[contains(@class, 'srp-carousel-list__item')]//*[text()='%s']",
					filterName);
			WebElement element = SetupDriver.getDriver().findElement(By.xpath(xpath));
			return element.isDisplayed();
		} catch (NoSuchElementException e) {
			System.out.println("Filter label not found: " + filterName);
			return false; // Indicate element not found gracefully
		} catch (Exception e) {
			System.out.println("Unexpected error while locating filter label: " + filterName);
			e.printStackTrace();
			return false;
		}
	}

	// Apply left navigation filter
	public void leftNavFilter_bk(String filterCategory, String filterOption) {
		try {
			String currentUrl = SetupDriver.getDriver().getCurrentUrl();
			SetupDriver.SetupDriver(currentUrl);

			List<WebElement> filterGroups = driver.findElements(By.xpath("//div[@class='x-refine__group']"));
			for (WebElement group : filterGroups) {
				String groupText = group.getText();
				if (groupText.contains(filterCategory)) {
					System.out.println("Found filter group: " + filterCategory);

					List<WebElement> networkOptions = group
							.findElements(By.xpath(".//span[contains(@class, 'cbx x-refine__multi-select-cbx')]"));
					// li[@class='carousel__snap-point srp-carousel-list__item
					// srp-carousel-list__item--group-has-title
					// srp-multi-aspect__item--applied']//div//div[1]
					for (WebElement option : networkOptions) {
						String optionText = option.getText();
						if (optionText.contains(filterOption)) {
							option.click();
							System.out.println("Clicked on filter option: " + filterOption);
							elementfound = true;
							return;
						}
					}
					System.out.println("Filter option not found: " + filterOption);
					filterfound = "Option " + filterOption + " under " + filterCategory + " catogery is not found.";
					return;
				}
			}
			System.out.println("Filter category not found: " + filterCategory);
			filterfound = filterCategory + " category not found.";
		} catch (Exception e) {
			System.out.println("Error while applying left navigation filter: " + e.getMessage());
		}
	}

	public void leftNavFilter(String filterCategory, String filterOption) {

		try {

			String networkOptionsXpath = "";

			// Setup the driver and load the eBay URL
			// String url =
			// "https://www.ebay.com/sch/i.html?_from=R40&_nkw=samsung+galaxy+s22&_sacat=0&rt=nc&_oaa=1&_dcat=9355";
			String url = SetupDriver.getDriver().getCurrentUrl();
			SetupDriver.SetupDriver(url);

			WebDriver driver = SetupDriver.getDriver();
			JavascriptExecutor js = (JavascriptExecutor) driver;
			WebDriverWait wait = new WebDriverWait(driver, 15);

			// Wait for filters to load
			WebElement filterPanel = wait
					.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='x-refine__group']")));
			js.executeScript("window.scrollTo(0, document.body.scrollHeight / 2);");

			// Locate the 'Network' filter group
			List<WebElement> filterGroups = driver.findElements(By.xpath("//div[@class='x-refine__group']"));
			System.out.println("Found " + filterGroups.size() + " filter groups.");

			for (WebElement group : filterGroups) {
				js.executeScript("arguments[0].scrollIntoView(true);", group);
				String groupText = group.getText();

				if (groupText.contains(filterCategory)) {
					System.out.println("+++++++++++++++++++++++ Found '" + filterCategory
							+ "' filter group ++++++++++++++++++++++++++++++++");

					// Wait for options under 'Network'
					List<WebElement> networkOptions = group
							.findElements(By.xpath(".//span[contains(@class, 'cbx x-refine__multi-select-cbx')]"));
					/*
					 * if (networkOptions.isEmpty()) { System.out.
					 * println("No options found under 'Network'. Trying alternative methods...");
					 */

					elementfound = true;

					// Try a direct XPath if options are nested differently
					networkOptionsXpath = "//li[@name='" + filterCategory
							+ "']//span[contains(@class, 'cbx x-refine__multi-select-cbx')]";
					networkOptions = driver.findElements(By.xpath(networkOptionsXpath));

					if (!networkOptions.isEmpty()) {
						System.out.println("Options under " + filterCategory + "filter:");
						for (WebElement option : networkOptions) {
							String optionText = option.getText();
							System.out.println("- " + optionText);

							if (optionText.contains(filterOption)) {
								js.executeScript("arguments[0].click();", option);
								System.out.println("Clicked on  option " + filterOption);
								break;
							}
						}
					} else {
						System.out.println("Still no options found under 'Network'.");
					}
					break;
				} else {
					filterfound = filterCategory + "Not Found";
				}

			}
		} catch (Exception e) {
			System.out.println("Error: " + e.getMessage());
		} finally {
			// Ensure the driver quits
			// SetupDriver.quitDriver();
		}
	}

	public void filterSearchResults(String network, String option, String assertvalue) {
		try {
			// Apply the left navigation filter
			leftNavFilter(network, option);

			if (elementfound) {
				System.out.println(filterfound + " Test passed");

				// Verify if the filter label is displayed
				boolean isDisplayed = isFilterLabelDisplayed(assertvalue);

			} else {
				System.out.println(filterfound + " Test failed");

			}
		} catch (Exception ex) {
			System.out.println("Unexpected exception during test execution: " + ex.getMessage());
			ex.printStackTrace();

		}
	}

}
