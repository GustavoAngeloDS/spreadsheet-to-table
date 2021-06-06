package service;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Iterator;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class SpreadSheetService {

	public void readSpreadSheet(File spreadSheetFile) {
		
		try {
			FileInputStream file = new FileInputStream(spreadSheetFile);
			
			XSSFWorkbook workbook = new XSSFWorkbook (file);
			XSSFSheet genericSheet = workbook.getSheetAt(0);
			
			Iterator<Row> rowIterator = genericSheet.iterator();
			
			while(rowIterator.hasNext()) {
				Row row = rowIterator.next();
				
				Iterator<Cell> cellIterator = row.cellIterator();
				
				while(cellIterator.hasNext()) {
					Cell cell = cellIterator.next();
				
					if(cell.getCellType() == 0) {
						System.out.println(cell.getNumericCellValue());
					}
					if(cell.getCellType() == 1) {
						System.out.println(cell.getStringCellValue());
					}
				}
			}
			file.close();		
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
