package service;

import java.io.File;
import java.io.IOException;

import org.jopendocument.dom.spreadsheet.Sheet;
import org.jopendocument.dom.spreadsheet.SpreadSheet;

public class SpreadSheetService {

	public void readSpreadSheet(File spreadSheetPath) {
		Sheet spreadSheet;
		try {
			spreadSheet = SpreadSheet.createFromFile(spreadSheetPath).getSheet(0);
			
			
			Integer colCount = spreadSheet.getColumnCount();
			Integer rowCount = spreadSheet.getRowCount();
		
			System.out.println(colCount);
			System.out.println(rowCount);
			
			spreadSheet.getCellAt("B5").setValue("Teste");
//			
//			for(int rowIndex = 1; rowIndex < rowCount; rowIndex ++) {
//				for(int colIndex = 1; colIndex < colCount; colIndex ++) {
//					if(spreadSheet.getCellAt(colIndex, rowIndex).getTextValue() != null) {
//						System.out.println(spreadSheet.getCellAt(colIndex, rowIndex).getTextValue());
//					}
//					
//				}
//			}

		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
