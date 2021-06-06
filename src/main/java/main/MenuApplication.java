package main;

import java.util.ArrayList;
import java.util.List;

import controller.SpreadSheetController;

public class MenuApplication {

	public static void main(String args[]) {
		SpreadSheetController spreadSheetController = new SpreadSheetController();
		
		final String spreadSheetPath = "C:\\Users\\Gustavo\\Documents\\arquivos\\testePlanilha.ods";
		
		List<String> returnMessages = new ArrayList<String>();
		
		returnMessages.addAll(spreadSheetController.consistSpreadSheet(spreadSheetPath));
		System.out.println(returnMessages);
		
		//if(returnMessages.isEmpty()) {
	//		spreadSheetController.readSpreadSheet(spreadSheetPath);
		//}
	}
}
