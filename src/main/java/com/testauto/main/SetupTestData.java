package com.testauto.main;

import java.io.IOException;

public class SetupTestData {
    public static String ebayFilter;
    public static String ebayFilterOp1;
    public static String assertFOP1;
    public static String assertFOP2;

    public static void setUp() {
    	 try {
			ExcelDataReader.readExcelData("TestData.xlsx", "Sheet1");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
    	ebayFilter = ExcelDataReader.getTestData("Filter1");
        ebayFilterOp1 = ExcelDataReader.getTestData("FilterOption1");
        assertFOP1 = ExcelDataReader.getTestData("AssertPass");
        assertFOP2 = ExcelDataReader.getTestData("AssertFail");
        System.out.println("Setup Test Data:");
        System.out.println("ebayFilter: " + ebayFilter);
        System.out.println("ebayFilterOp1: " + ebayFilterOp1);
        System.out.println("Assert pass value: " + assertFOP1);
        System.out.println("Fail Assert Value " + assertFOP2);
        
    }
}
