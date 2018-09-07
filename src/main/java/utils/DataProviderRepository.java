package utils;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;


import org.testng.annotations.DataProvider;

import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;



public class DataProviderRepository {

	public static String[][] getExcelData(String fileName, String sheetName) {
		String[][] arrayExcelData = null;
		try {
			FileInputStream fs = new FileInputStream(fileName);
			Workbook wb = Workbook.getWorkbook(fs);
			Sheet sh = wb.getSheet(sheetName);

			int totalNoOfCols = sh.getColumns();
			int totalNoOfRows = sh.getRows();

			arrayExcelData = new String[totalNoOfRows-1][totalNoOfCols];

			for (int i= 1 ; i < totalNoOfRows; i++) {

				for (int j=0; j < totalNoOfCols; j++) {
					arrayExcelData[i-1][j] = sh.getCell(j, i).getContents();
				}

			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();

		} catch (BiffException e) {
			e.printStackTrace();
		}
		return arrayExcelData;
	}

	//Test data to use login scenario.  
	@DataProvider(name="userLoginData") //parallel=true
	public static Object[][] LogInData(){

		Object[][] arrayObject = getExcelData("src/test/resources/config/ebay.xlsx","Login");
		/* Object[][] arrayObject = new Object[2][2];
	    arrayObject[0][0] = "test";
	    arrayObject[0][1] = "tests";

	    arrayObject[1][0] = "test";
	    arrayObject[1][1] = "tesr"; */

		return arrayObject;

	}
	@DataProvider(name="contactDetails") //parallel=true
	public static Object[][] contactDetails(){

		Object[][] arrayObject = getExcelData("src/test/resources/config/ebay.xlsx","ContactDetails");
		return arrayObject;

	}

}
