package service;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class SpreadSheetService {

	FileInputStream file;
	
	XSSFWorkbook workbook;
	XSSFSheet genericSheet;
	
	List<String> fieldNames = new ArrayList<String>();
	
	Integer headerLine;
	
	public void readSpreadSheet(File spreadSheetFile) {
		readHeaders(spreadSheetFile);
		mapDataTypes(genericSheet);
	}
	
	public void readHeaders(File spreadSheetFile) {
		try {
			file = new FileInputStream(spreadSheetFile);
			workbook = new XSSFWorkbook(file);
			genericSheet  = workbook.getSheetAt(0);
			
			headerLine = genericSheet.getFirstRowNum();
			
			Row row = genericSheet.getRow(headerLine);
			Iterator<Cell> cellIterator = row.cellIterator();
			
			Integer i = 0;
			
			while(cellIterator.hasNext()) {	
				Cell cell = cellIterator.next();
				String dataType = "";
				
				fieldNames.add(i, cell.getCellType() == 1 ? cell.getStringCellValue() : Double.toString(cell.getNumericCellValue()));
				i++;
			}
			file.close();		
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}	
	
	public void mapDataTypes(XSSFSheet spreadSheet) {
		
		//Remove header line
		spreadSheet.removeRow(spreadSheet.getRow(headerLine));
		
		Iterator<Row> rowIterator = spreadSheet.iterator();
		String defaultDataType = "NUMERIC";
		
		while(rowIterator.hasNext()) {
			Row row = rowIterator.next();
			Cell cell = row.getCell(0);
			
			if(cell.getCellType() == 1)
				defaultDataType = "VARCHAR";
			
		}
	}
}
