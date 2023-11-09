package utilities;


import org.apache.poi.common.usermodel.HyperlinkType;
import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.NumberToTextConverter;
import org.apache.poi.xssf.usermodel.*;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Iterator;


public class ExcelReader_locators {

	public String path;
	public FileInputStream fis = null;
	public FileOutputStream fileOut = null;
	private XSSFWorkbook workbook = null;
	private XSSFSheet sheet = null;
	private XSSFRow row = null;
	private XSSFCell cell = null;


	public static String excel_locators(String label) throws IOException {
		ArrayList<String> a = new ArrayList<>();
		FileInputStream inputStream = new FileInputStream(System.getProperty("user.dir") + "/src/test/resources/locators.xlsx");
		XSSFWorkbook workbook = new XSSFWorkbook(inputStream);
		int sheets = workbook.getNumberOfSheets();
		for (int i = 0; i < sheets; i++) {
			if (workbook.getSheetName(i).equalsIgnoreCase("locators")) {
				XSSFSheet sheet = workbook.getSheetAt(i);
				Iterator<Row> rows = sheet.rowIterator();
				Row firstrow = rows.next();
				Iterator<Cell> ce = firstrow.cellIterator();
				while (rows.hasNext()) {
					Row r = rows.next();
					if (r.getCell(0) != null) {
						if (r.getCell(0).getStringCellValue().equalsIgnoreCase(label)) {
							Iterator<Cell> c = r.cellIterator();
							while (c.hasNext()) {
								Cell cv = c.next();
								if (cv.getCellType() == CellType.STRING) {
									a.add(cv.getStringCellValue());
								} else {
									a.add(NumberToTextConverter.toText(cv.getNumericCellValue()));
								}
							}
						}
					}
				}
			}
		}
		return a.get(1);
	}
}
