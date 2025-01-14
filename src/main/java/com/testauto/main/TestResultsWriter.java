package com.testauto.main;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class TestResultsWriter {

    private static XSSFWorkbook workbook;
    private static Sheet sheet;
    private static int rowIndex = 1;

    // Create the Excel file and header if not already created
    public TestResultsWriter() {
        if (workbook == null) {
            workbook = new XSSFWorkbook();  // No need for try-catch here
            sheet = workbook.createSheet("Test Results");
            createHeader();
        }
    }

    // Create the header row in the Excel file
    private void createHeader() {
        Row header = sheet.createRow(0);
        header.createCell(0).setCellValue("Test Method");
        header.createCell(1).setCellValue("Result");
        header.createCell(2).setCellValue("Execution Time");
    }

    // Method to write test results to the Excel file
    public void writeTestResult(String testMethod, String result) {
    	// Get the current timestamp
        long currentTimeMillis = System.currentTimeMillis();
        
        // Convert to date and time format
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String formattedDate = formatter.format(new Date(currentTimeMillis));
        
        // Print or write to Excel
        System.out.println("Current Timestamp: " + formattedDate);
        
        Row row = sheet.createRow(rowIndex++);
        row.createCell(0).setCellValue(testMethod);
        row.createCell(1).setCellValue(result);
        row.createCell(2).setCellValue(formattedDate);  // Add timestamp

        try (FileOutputStream fileOut = new FileOutputStream("TestResults.xlsx")) {
            workbook.write(fileOut);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Method to read test results from the Excel file
    public void readTestResults() {
        try (FileInputStream fis = new FileInputStream("TestResults.xlsx");
             Workbook workbook = new XSSFWorkbook(fis)) {

            Sheet sheet = workbook.getSheetAt(0); // Access the first sheet
            for (Row row : sheet) {
                String testMethod = row.getCell(0).getStringCellValue();
                String result = row.getCell(1).getStringCellValue();
                double executionTime = row.getCell(2).getNumericCellValue();

                // Print or process the values as needed
                System.out.println("Test Method: " + testMethod);
                System.out.println("Result: " + result);
                System.out.println("Execution Time: " + executionTime);
                System.out.println("=====================================");
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Method to save the workbook (this is done automatically within writeTestResult)
    public void saveWorkbook() {
        try (FileOutputStream fileOut = new FileOutputStream("TestResults.xlsx")) {
            workbook.write(fileOut);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
