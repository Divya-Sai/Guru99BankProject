package com.guru99.demo.library;

import java.io.FileInputStream;
import java.io.IOException;

import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;


public class ExcelUtils {
	
	// returns the data from a cell
		public String getCellData(String FileName,String sheetName,int colName,int rowNum) throws IOException{
			FileInputStream  f = new FileInputStream("E:\\Divya\\SeleniumwithJava\\workspace\\SampleMVN\\TestData\\"+FileName+".xlsx");
			
			XSSFWorkbook  wb = new XSSFWorkbook(f);
			XSSFSheet  sh = wb.getSheet(sheetName);
			/*XSSFRow  rw  = sh.getRow(rowNum);*/
			/*String rowName  = sh.getRow(rowName).toString();*/
			XSSFRow  rw  = sh.getRow(rowNum);
			XSSFCell  cl = rw.getCell(colName);
			System.out.println("value of colName in "+cl);
			
			if(cl.getCellType()==CellType.STRING)
			{
				return String.valueOf(cl.getStringCellValue());
			}
			else if(cl.getCellType()==CellType.NUMERIC || cl.getCellType()==CellType.FORMULA ) {
				return  String.valueOf(cl.getNumericCellValue());				
			}
			else if(cl.getCellType()==CellType.BLANK)
			      return ""; 
			  else 
				  return String.valueOf(cl.getBooleanCellValue());
			
		}

}
