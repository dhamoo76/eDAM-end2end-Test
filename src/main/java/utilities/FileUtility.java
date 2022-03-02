package utilities;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.WorkbookFactory;

import factory.Common;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Cell;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * This Class is developed for test data from Excel file (Read & Write purpose)
 */
public class FileUtility {
	private static final Logger LOGGER = Logger.getLogger(FileUtility.class.getName());

	public FileInputStream fisObj;

	/**
	 * This used to read the value from specified cell from the specified sheet of
	 * the Excel file
	 * 
	 * @param fileFullPath full path of Excel File
	 * @param sheetName    Sheet name
	 * @param rowNum       Row number to be read
	 * @param colNum       Column number to be read
	 * @return cell value as string from the specified cell from the specified sheet
	 * @throws IOException
	 * @throws InvalidFormatException
	 */
	public static String getExcelData(String fileFullPath, String sheetName, int rowNum, int colNum)
			throws IOException, InvalidFormatException {
		String data = "";
		try {
			FileInputStream fis = new FileInputStream(fileFullPath);
			Workbook wb = WorkbookFactory.create(fis);
			Sheet sh = wb.getSheet(sheetName);
			Row row = sh.getRow(rowNum);
			data = row.getCell(colNum).toString();
			wb.close();
		} catch (Exception e) {
			LOGGER.log(Level.ALL, "Error while get data from excell - ", e.getMessage());
		}
		return data;
	}

	/**
	 * This used to write the value from specified cell from the specified sheet of
	 * the Excel file
	 * 
	 * @param sheetName Sheet name
	 * @param rowNum    Row number to be read
	 * @param colNum    Column number to be read
	 * @return cell value as string from the specified cell from the specified sheet
	 * @throws IOException
	 * @throws InvalidFormatException
	 */
	public static void setExcelData(String filePath, String sheetName, int rowNum, int colNum, String data)
			throws IOException, InvalidFormatException {
		try {
			FileInputStream fis = new FileInputStream(filePath);
			Workbook wb = WorkbookFactory.create(fis);
			Sheet sh = wb.getSheet(sheetName);
			Row row = sh.getRow(rowNum);
			Cell cell = row.createCell(colNum);
			cell.setCellValue(data);
			FileOutputStream fos = new FileOutputStream(filePath);
			wb.write(fos);
		} catch (Exception e) {
			LOGGER.log(Level.ALL, "Error while write excell file - Name :  " + filePath, e.getMessage());
		}
	}

	public String getProperty(String key) {
		String value = null;
		try {
			fisObj = new FileInputStream("./data/commondata.properties");
			Properties pObj = new Properties();
			pObj.load(fisObj);
			value = pObj.getProperty(key);

		} catch (IOException e) {
			LOGGER.log(Level.ALL, "Error while read properties - ", e.getMessage());
		}
		return value;
	}
}
