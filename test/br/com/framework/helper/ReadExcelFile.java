package br.com.framework.helper;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.WorkbookFactory;
 
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ReadExcelFile {
	
    public FileInputStream fis = null;
    public XSSFWorkbook workbook = null;
    public XSSFSheet sheet = null;
    public XSSFRow row = null;
    public XSSFCell cell = null;
    
	public void readExcelFile( String filePath, String fileName, String sheetName) throws IOException{
		//Create an object of File class to open xlsx file
		File file = new File(filePath+"\\"+fileName);
		
		//Create an objetct of FileInputStream class to read excel file
		FileInputStream inputStream = new FileInputStream(file);
		
		Workbook fileWorkbook = null;
		
		//Find the file extension  by splitting file name in substring and getting only extension name
		String fileExtensionName = fileName.substring(fileName.indexOf("."));
		
		//Check condition if the file is xlsx file
		if(fileExtensionName.equals(".xlsx")){
			
		//If it is xlsx file then create object of XSSWorkbook class
		fileWorkbook = new XSSFWorkbook(inputStream);
		}
		
		//Check condition if file is xls file
		else if (fileExtensionName.equals(".xls")){
		
		//If it is xls file then create object of HSSFWorkbook class
			fileWorkbook = new HSSFWorkbook(inputStream);	
		}
		
		//Read inside the workbook by its name
		
		Sheet fileSheet = fileWorkbook.getSheet(sheetName);
		
		//Find number of rows in excel file
		
		int rowCount = fileSheet.getLastRowNum()-fileSheet.getFirstRowNum();
		
		//Create a loop over all the rows of excel file to read it
		for (int i=0; i < rowCount+1; i++)
		{
			Row row = fileSheet.getRow(i);
		//Create a loop to print cell values in a row
			for (int j = 0; j< row.getLastCellNum(); j++){
				//Print Excel data in console
				System.out.println(row.getCell(j).toString()+"||");
				//System.out.println(row.getCell(j).getStringCellValue()+"||");
				//sheet.getRow(i).getCell(0).toString()
			}
			System.out.println();
			
		}
		
	}
	
	 public static String readdatafromExcelusingcolumnName(String ColumnName)
			   throws EncryptedDocumentException, InvalidFormatException, IOException {
			  String SheetName = "sheet1";
			  File file = new File(System.getProperty("user.dir")+"\\test\\QA_Automation_datasheet.xlsx");
			 // C:\Users\marcia.cardoso\git\automation--framework\test
			 // File file = new File(".\\testData\\data.xlsx");
			  FileInputStream fi = new FileInputStream(file);
			  Workbook wb = WorkbookFactory.create(fi);
			  Sheet sheet = wb.getSheet(SheetName);
			  // it will take value from first row
			  Row row = sheet.getRow(0);
			// it will give you count of row which is used or filled
			  short lastcolumnused = row.getLastCellNum();

			  int colnum = 0;
			  for (int i = 0; i < lastcolumnused; i++) {
			   if (row.getCell(i).getStringCellValue().equalsIgnoreCase(ColumnName)) {
			    colnum = i;
			    break;
			   }
			  }

			  // it will take value from Second row
			  row = sheet.getRow(1);
			  Cell column = row.getCell(colnum);
			  String CellValue = column.getStringCellValue();

			  return CellValue;

			 }}
	 
	   
	


