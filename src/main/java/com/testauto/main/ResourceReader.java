package com.testauto.main;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;

public class ResourceReader {
	static String browser = "";
	static String version = "";
	public static String filter1 = "";
	public static String filteroption1 = "";
	
	

	public static void ConfigRead() {
		// Config related  resources file
		String resourceFile = "config.properties";
		
		// Test data related file
		String TestDataFile = "TestData";
				
				
				
		
		// Read the resource file as a String
		String content = readResourceFile(resourceFile);
		
	    // Print the assigned values to check
        System.out.println("Browser: " + browser);
        System.out.println("Version: " + version);
        
        // Read the Test Data file as a String
        String testDataContent =  readTestDataFile(resourceFile);
		
     // Print the assigned values to check
        System.out.println("filter1: " + filter1);
        System.out.println("filteroption1: " + filteroption1);
	}

	
		
	private static String readResourceFile(String resourceFile) {
		StringBuilder content = new StringBuilder();

		try (BufferedReader reader = new BufferedReader(
				new InputStreamReader(ResourceReader.class.getClassLoader().getResourceAsStream(resourceFile)))) {

			String line;
			while ((line = reader.readLine()) != null) {
				// Split the line into key and value by '='
				String[] keyValue = line.split("=");

				if (keyValue.length == 2) {
					String key = keyValue[0].trim();
					String value = keyValue[1].trim();

					// Assign the values to the static variables based on the key
					if ("browser".equalsIgnoreCase(key)) {
						browser = value;
					} else if ("version".equalsIgnoreCase(key)) {
						version = value;
					}
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}

		return content.toString();
	}
	
	private static String readTestDataFile(String resourceFile) {
		StringBuilder testDatacontent = new StringBuilder();

		try (BufferedReader reader = new BufferedReader(
				new InputStreamReader(ResourceReader.class.getClassLoader().getResourceAsStream(resourceFile)))) {

			String line;
			while ((line = reader.readLine()) != null) {
				// Split the line into key and value by '='
				String[] keyValue = line.split("=");

				if (keyValue.length == 2) {
					String key = keyValue[0].trim();
					String value = keyValue[1].trim();

					// Assign the values to the static variables based on the key
					if ("filter1".equalsIgnoreCase(key)) {
						filter1 = value;
					} else if ("filteroption1".equalsIgnoreCase(key)) {
						filteroption1 = value;
					}
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}

		return testDatacontent.toString();
	}
	
	
}