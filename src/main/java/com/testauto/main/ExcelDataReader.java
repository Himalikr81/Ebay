package com.testauto.main;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

public class ExcelDataReader {

    private static Map<String, String> testData = new HashMap<>();

    public static void readExcelData(String fileName, String sheetName) throws IOException {
        try (InputStream inputStream = ExcelDataReader.class.getClassLoader().getResourceAsStream(fileName)) {
            if (inputStream == null) {
                throw new IllegalArgumentException("File '" + fileName + "' not found in resources.");
            }

            try (Workbook workbook = new XSSFWorkbook(inputStream)) {
                System.out.println("File successfully opened: " + fileName);
                printAllSheetNames(workbook);

                Sheet sheet = workbook.getSheet(sheetName);
                if (sheet == null) {
                    throw new IllegalArgumentException("Sheet '" + sheetName + "' not found in the Excel file.");
                }

                for (Row row : sheet) {
                    Cell keyCell = row.getCell(0);
                    Cell valueCell = row.getCell(1);

                    if (keyCell != null && valueCell != null) {
                        String key = getStringCellValue(keyCell);
                        String value = getStringCellValue(valueCell);
                        testData.put(key, value);
                        System.out.println("Read Key: " + key + ", Value: " + value);
                    }
                }
            }
        }
    }

    private static String getStringCellValue(Cell cell) {
        if (cell == null) {
            return "";
        }
        switch (cell.getCellType()) {
            case STRING:
                return cell.getStringCellValue();
            case NUMERIC:
                return String.valueOf((int) cell.getNumericCellValue());
            case BOOLEAN:
                return String.valueOf(cell.getBooleanCellValue());
            case FORMULA:
                try {
                    return String.valueOf((int) cell.getNumericCellValue());
                } catch (IllegalStateException e) {
                    return cell.getStringCellValue();
                }
            default:
                return "";
        }
    }

    private static void printAllSheetNames(Workbook workbook) {
        System.out.println("Available Sheets:");
        for (int i = 0; i < workbook.getNumberOfSheets(); i++) {
            System.out.println("Sheet " + (i + 1) + ": " + workbook.getSheetName(i));
        }
    }

    public static String getTestData(String key) {
        return testData.getOrDefault(key, null);
    }
}
