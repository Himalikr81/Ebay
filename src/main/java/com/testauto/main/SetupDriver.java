package com.testauto.main;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import org.junit.After;
import org.junit.Test;
import org.openqa.selenium.WebDriver;

public class SetupDriver {
	static WebDriver driver;

	public static WebDriver SetupDriver() {
		if (driver == null) {
		ResourceReader.ConfigRead();
		final String BROWSER = ResourceReader.browser;
		final String VERSION = ResourceReader.version;
		driver = WebDriverFactory.createDriver(BROWSER, VERSION);
		}
        return driver;

	}

	public static void SetupDriver(String url) {
	SetupDriver().get(url);

	}

	public static WebDriver getDriver() {
		return driver;
	}

	public static void quitDriver() {
		if (WebDriverFactory.getDriver() != null) {
			WebDriverFactory.quitDriver(); // Close the browser
		}
	}

}
