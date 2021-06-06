package controller;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import service.SpreadSheetService;
import util.ConvertByteToMegaByte;
import util.GetFileExtension;

public class SpreadSheetController {
	
	SpreadSheetService spreadSheetService = new SpreadSheetService();
	
	Properties properties = new Properties();
	
	private void loadPropertiesFile() throws IOException {
		InputStream inputStream = null;
		final String propFileName = "config.properties";
		
		try {			
			inputStream = getClass().getClassLoader().getResourceAsStream(propFileName);
			properties.load(inputStream);

		}catch(Exception e) {
			System.out.println(e);
		}
		finally {
			inputStream.close();
		}
	}

	public List<String> consistSpreadSheet(String spreadSheetPath){
		try {
			loadPropertiesFile();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		List<String> messagesReturnList = new ArrayList<String>();
		File spreadSheetLocal = new File(spreadSheetPath);
		
		if(properties.isEmpty()) {
			messagesReturnList.add("O arquivo properties não pode ser lido. \n");
			return messagesReturnList;
		}
		
		if(!spreadSheetLocal.exists()) {
			messagesReturnList.add("O endereço da planilha informado é inválido. \n");
		}
		
		if(!GetFileExtension.getFileExtension(spreadSheetPath).toUpperCase().equals(properties.getProperty("acceptableFileExtensions"))) {
			messagesReturnList.add("O arquivo informado não possui um formato aceitável. \n");
		}
		
		if(!spreadSheetLocal.canRead()) {
			messagesReturnList.add("O arquivo informado não pode ser lido pelo sistema. \n");
		}
		
		if(ConvertByteToMegaByte.ConvertSizes(spreadSheetLocal.length()) > Integer.parseInt(properties.getProperty("fileMaxLengthInMB"))) {
			messagesReturnList.add(MessageFormat.format("O arquivo informado é superior a {0}mb e não pode ser lido pelo sistema. \n", properties.getProperty("fileMaxLengthInMB")));
		}
		
		if(!messagesReturnList.isEmpty()) {
			return messagesReturnList;
		}else {
			File spreadSheetFile = new File(spreadSheetPath);
			spreadSheetService.readSpreadSheet(spreadSheetFile);
			return messagesReturnList;
		}
	}
}
