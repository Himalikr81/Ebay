package com.testauto.pageobjects;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import com.testauto.main.SetupDriver;

public class EbaySerachListPage {

	private WebElement lblEbaySearchTotalNumberofResults;
	private WebElement networkSection;
	
	public WebElement lblTotalNumberofResults() {
		 return lblEbaySearchTotalNumberofResults = SetupDriver.getDriver().findElement(By.xpath("//span[normalize-space()='samsung galaxy s22']"));
		 //System.out.println("Total " + lblTotalNumberofResults().getText());
		 //System.out.println("Total " + lblTotalNumberofResults().getTagName());
		 //return lblTotalNumberofResults();
		 
	}
	
	public Map<String, WebElement> getNetworkOptions() {
        // Locate the Network section
        networkSection = SetupDriver.getDriver().findElement(By.xpath("//div[normalize-space()='Network']"));
        
        // Capture all the options within the Network section
        List<WebElement> options = networkSection.findElements(By.xpath(".//following-sibling::ul//li"));
        
        // Map each option by its visible text
        Map<String, WebElement> networkMap = new HashMap<>();
        for (WebElement option : options) {
            String key = option.getText().trim(); // Extract the key
            networkMap.put(key, option); // Store in the map
        }
    
        // Print all available values
        System.out.println("Available Network Options:");
        for (String key : networkMap.keySet()) {
            System.out.println(key);
        }
        return networkMap;
    }

    public void clickNetworkOption(String key) {
        // Access and click an option by its key
        Map<String, WebElement> networkMap = getNetworkOptions();
        if (networkMap.containsKey(key)) {
            networkMap.get(key).click();
        } else {
            System.out.println("Option not found: " + key);
        }
    }
}
