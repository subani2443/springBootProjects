package com.TejaITB2.FactoryDesignPattern;

public class FileGenUtil {
	public static IFileGen fileGeneration(String types) {
		IFileGen iFileGen = null;
		switch (types) {
		case "txt":
			iFileGen = new TextFile();
			break;
		case "docx":
			iFileGen = new DocxFile();
			break;
		case "xlsx":
			iFileGen = new ExcelFile();
			break;
		case "pdf":
			iFileGen = new PdfFile();
			break;

		default:
			break;
		}
		return iFileGen;

	}
}
