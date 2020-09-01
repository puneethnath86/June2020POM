package com.qa.hubspot.utils;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

public class ExcelUtils {

	private static String TEST_DATA_SHEET_PATH=Constants.EXCEL_FILE_PATH;
	private static Sheet sh;
	
	public static Object[][] getTestData(String sheetName) {
		Object[][] data=null;
		try {
		FileInputStream fis=new FileInputStream(TEST_DATA_SHEET_PATH);
		Workbook wb=WorkbookFactory.create(fis);
		sh = wb.getSheet(sheetName);
//		This is a 2D object array-Object array since we can have any kind of data in our excel file, 
//		we are getting the max values of row and columns here and that would be size of the 2D object array
		data=new Object[sh.getLastRowNum()][sh.getRow(0).getLastCellNum()];
		
//		2-D array is populated using the below nested for loop
		for(int i=0; i<sh.getLastRowNum(); i++) {
			for(int j=0; j<sh.getRow(i).getLastCellNum(); j++) 
			{
//				We are fetching data from excel and storing it in the 2-D object array
				data[i][j]=sh.getRow(i+1).getCell(j).toString();
			}
		}
		
		}	catch (FileNotFoundException e) {
			e.printStackTrace();
			} 
			catch (InvalidFormatException e) {
			e.printStackTrace();
			} catch (IOException e) {
			e.printStackTrace();
			}
	
		return data;
	
	}
}
