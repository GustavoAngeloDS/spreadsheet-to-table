package util;

public class GetFileExtension {

	public static String getFileExtension(String fileName) {
		return fileName.substring(fileName.lastIndexOf(".")+1);
	}
}
