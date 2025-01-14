package com.testauto.pageobjects;

import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import com.testauto.main.SetupDriver;


public class EbayHomePage {

	private WebElement lblEbayLogo;
	private WebElement txtSearch;
	private WebElement searchBtn;
	private List<WebElement> listLeftPanelItems;
	private WebElement Model;
	private WebElement lblFilter;

	
	
		
	public WebElement lblEbayLogo() {
		return lblEbayLogo = SetupDriver.getDriver().findElement(By.id("gh-logo"));
	}

	
	
	public WebElement txtSearch() {
		return txtSearch = SetupDriver.getDriver().findElement(By.xpath("//input[@id='gh-ac']"));
		
		

	}

	public WebElement btnSearch() {
		return txtSearch = SetupDriver.getDriver().findElement(By.xpath("//input[@id='gh-btn']"));
	}

	/*
	public WebElement leftPanel(String driver, String subCatogery) {
		return txtSearch = WebDriverFactory.getDriver().findElement(By.xpath("//input[@id='gh-btn']"));
	}

	public WebElement leftPanelItems(int i) {
		listLeftPanelItems = driver
				.findElements(By.xpath(".//li[@class='x-refine__main__list x-refine__main__list--aspect']"));
		return listLeftPanelItems.get(i);
	}

	public WebElement leftPanelItem(String catogery, String content) {
		System.out.println(
				"XPath " + ".//li[@class='x-refine__main__list--value x-refine__main__list--aspect' and @name='"
						+ catogery + "']/*[1]/*[1]/span[.='" + content + "']");

		Model = driver.findElement(
				By.xpath("//span[@class='cbx x-refine__multi-select-cbx'][contains(text(),'" + content + "')]"));
		return Model;
	}

	public WebElement leftPanelItem(String content) {
		System.out.println("//span[@class='cbx x-refine__multi-select-cbx'][contains(text(),'" + content + "')]");

		Model = driver.findElement(
				By.xpath("//span[@class='cbx x-refine__multi-select-cbx'][contains(text(),'" + content + "')]"));
		return Model;
	}

	public WebElement leftPanelItemModel(String content) {
		Model = driver.findElement(By.xpath(
				".//li[@class='x-refine__main__list--value x-refine__main__list--aspect' and @name='Model']/*[1]/*[1]/span[.='"
						+ content + "']"));
		return Model;
	}

	public WebElement topfilterLabel(String filter) {
		String xpath1 = "//a[@class='srp-carousel-list__item-link']//div[contains(text(),'" + filter + "')]";
		lblFilter = driver.findElement(By.xpath(xpath1));
		return lblFilter;
	}
	
	*/

}
