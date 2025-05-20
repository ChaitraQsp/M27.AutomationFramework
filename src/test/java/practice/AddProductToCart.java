package practice;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.edge.EdgeDriver;

public class AddProductToCart {

	public static void main(String[] args) throws IOException {

		// Read data from property file
		FileInputStream fis = new FileInputStream(".\\src\\test\\resources\\CommonData.properties");
		Properties p = new Properties();
		p.load(fis);
		String URL = p.getProperty("url");
		String USERNAME = p.getProperty("username");
		String PASSWORD = p.getProperty("password");

		// Read data from excel File
		FileInputStream fise = new FileInputStream(".\\src\\test\\resources\\TestData.xlsx");
		Workbook wb = WorkbookFactory.create(fise);//documnt
		Sheet sh = wb.getSheet("Products");//sheet
		Row rw = sh.getRow(1);//row
		Cell cl = rw.getCell(2);//cell
		String PRODUCTNAME = cl.getStringCellValue();// has to be added to cart-run time datat

		// Launch the browser
		WebDriver driver = new EdgeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

		// Load the URL
		driver.get(URL);

		// Login to Application
		driver.findElement(By.id("user-name")).sendKeys(USERNAME);
		driver.findElement(By.id("password")).sendKeys(PASSWORD);
		driver.findElement(By.id("login-button")).click();

		// Click on product - use the excel data - Dynamic xpath
		WebElement ele = driver.findElement(By.xpath("//div[.='"+PRODUCTNAME+"']"));
		String productTobeAdded = ele.getText();
		ele.click();

		// Add product To Cart
		driver.findElement(By.id("add-to-cart")).click();

		// Navigate to cart
		driver.findElement(By.id("shopping_cart_container")).click();

		// Validate for the product
		String productInCart = driver.findElement(By.className("inventory_item_name")).getText();
		if (productInCart.equals(productTobeAdded)) {
			System.out.println("PASS");
			System.out.println(productInCart);
		} else {
			System.out.println("FAIL");
		}

		// Logout of Application
		driver.findElement(By.id("react-burger-menu-btn")).click();
		driver.findElement(By.linkText("Logout")).click();

		System.out.println("Logout completed");
	}

}
