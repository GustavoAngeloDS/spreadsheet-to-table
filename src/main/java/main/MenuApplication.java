package main;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import controller.SpreadSheetController;

public class MenuApplication {

	public static void main(String args[]) throws IOException {
		SpreadSheetController spreadSheetController = new SpreadSheetController();
		
		final String spreadSheetPath = "C:\\Users\\Gustavo\\Documents\\arquivos\\planilhaTeste.xlsx";
		
		List<String> returnMessages = new ArrayList<String>();
		
		returnMessages.addAll(spreadSheetController.consistSpreadSheet(spreadSheetPath));
		System.out.println(returnMessages);
		
	}
}
