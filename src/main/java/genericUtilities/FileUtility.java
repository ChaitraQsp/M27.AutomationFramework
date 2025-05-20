package genericUtilities;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

/**
 * This class consists of generic methods related Files 
 * @author Chaitra M
 *
 */
public class FileUtility {
	
	
	/**
	 * This method will read data from property file and return the value 
	 * to caller
	 * @param key
	 * @return
	 * @throws IOException
	 */
	public String readDataFromPropertyFile(String key) throws IOException
	{
		FileInputStream fis = new FileInputStream(".\\src\\test\\resources\\CommonData.properties");
		Properties p = new Properties();
		p.load(fis);
		String value = p.getProperty(key);
		return value;
	}
	
	
	/**
	 * This method will read data from excel file and return the value to caller
	 * @param sheetname
	 * @param rowNo
	 * @param celNo
	 * @return value
	 * @throws EncryptedDocumentException
	 * @throws IOException
	 */
	public String readDataFromExcel(String sheetname, int rowNo, int celNo) throws EncryptedDocumentException, IOException
	{
		FileInputStream fis = new FileInputStream(".\\src\\test\\resources\\TestData.xlsx");
		Workbook wb = WorkbookFactory.create(fis);
		Sheet sh = wb.getSheet(sheetname);
		Row rw = sh.getRow(rowNo);
		Cell cl = rw.getCell(celNo);
		String value = cl.getStringCellValue();
		return value;
	}
	
	
	
	
	
	
	
	
	
	
	
	

}
