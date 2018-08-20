package com.appium.base;
  import java.io.FileInputStream;
       import java.io.FileNotFoundException;
       import java.io.FileOutputStream;
       import java.io.IOException;
       import org.apache.poi.ss.usermodel.Cell;
       import org.apache.poi.xssf.usermodel.XSSFCell;
       import org.apache.poi.xssf.usermodel.XSSFRow;
       import org.apache.poi.xssf.usermodel.XSSFSheet;
       import org.apache.poi.xssf.usermodel.XSSFWorkbook;

   public class ExcelReader {
       public String path;
       public FileInputStream file;
       public XSSFWorkbook workbook;
       public XSSFSheet sheet;
       public XSSFRow row;
       public XSSFCell cell;
       public FileOutputStream fos;
 
       //constructor for path setting
      public ExcelReader(String path) throws IOException{
  
	      this.path =path;
	      file = new FileInputStream(path);
	       workbook= new XSSFWorkbook(file);
      }
      
      
	     //Reusable method for reading data from excel
	     public  String getCellData(String sheetName,int rowNum,int colNum){
	     int index = workbook.getSheetIndex(sheetName);
	     sheet =workbook.getSheetAt(index);
	     row = sheet.getRow(rowNum);
	     cell = row.getCell(colNum);
	     String data=cell.getStringCellValue();
	     return data; 
    }
 
    //Reusable method for writing data to the excel
    public void setCellData(String sheetName,String data,int rowNum,int colNum) throws IOException{
 
	    int index = workbook.getSheetIndex(sheetName);
	   sheet =workbook.getSheetAt(index);
	   row = sheet.getRow(rowNum);
	
	   if(row==null){
	   
	   row =sheet.createRow(rowNum);
	  }
	  cell = row.getCell(colNum);
	  if(cell==null){
	   cell= row.createCell(colNum); 
	  }
	    cell.setCellValue(data);
	
	   fos = new FileOutputStream(path);
	   workbook.write(fos);
	   fos.close();
   }


}