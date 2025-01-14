package com.testauto.main;


import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.edge.EdgeOptions;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.chrome.ChromeDriver;
public class WebDriverFactory {
    static WebDriver driver;

    public static WebDriver createDriver(String browser, String version) {
        // Ensure only one instance is created
        if (driver == null) {
            switch (browser.toLowerCase()) {
                case "chrome":
                    WebDriverManager.chromedriver().driverVersion(version).setup();
                    ChromeOptions chromeOptions = new ChromeOptions();
                    // Configure Chrome to run in headless mode
//                    chromeOptions.addArguments("--headless");  // Run headless
//                    chromeOptions.addArguments("--disable-gpu");  // Disable GPU (optional for some environments)
//                    chromeOptions.addArguments("--window-size=1920,1080");  // Set a default window size
//                    chromeOptions.addArguments("--no-sandbox");  // Recommended for Jenkins environments
//                    chromeOptions.addArguments("--disable-dev-shm-usage");  // For environments with limited resources
                    driver = new ChromeDriver(chromeOptions);
                    break;
                case "firefox":
                    WebDriverManager.firefoxdriver().driverVersion(version).setup();
                    FirefoxOptions firefoxOptions = new FirefoxOptions();
                    driver = new org.openqa.selenium.firefox.FirefoxDriver(firefoxOptions);
                    break;
                case "edge":
                    WebDriverManager.edgedriver().driverVersion(version).setup();
                    EdgeOptions edgeOptions = new EdgeOptions();
                    driver = new org.openqa.selenium.edge.EdgeDriver(edgeOptions);
                    break;
                default:
                    throw new IllegalArgumentException("Unsupported browser: " + browser);
            }
            driver.manage().window().maximize();
        }
        return driver;
    }


    public static void quitDriver() {
        // Check if driver is initialized and quit it
        if (driver != null) {
            driver.close();
            driver.quit();
            driver = null; // Reset the driver to null after quitting
        }
    }

    public static WebDriver getDriver() {
        return driver;
    }
}


