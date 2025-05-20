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

import genericUtilities.BaseClass;
import genericUtilities.FileUtility;
import genericUtilities.JavaUtility;
import genericUtilities.SeleniumUtility;
import objectRepository.LoginPage;

public class AddProductToCartWithGU extends BaseClass{

	public static void main(String[] args) throws IOException {
		
		//Create Object of Utilities
		FileUtility fUtil = new FileUtility();
		JavaUtility jUtil = new JavaUtility();
		SeleniumUtility sUtil = new SeleniumUtility();

		// Read data from property file
		String USERNAME = fUtil.readDataFromPropertyFile("username");
		String PASSWORD = fUtil.readDataFromPropertyFile("password");
		String URL = fUtil.readDataFromPropertyFile("url");

		// Read data from excel File
		String PRODUCTNAME = fUtil.readDataFromExcel("Products", 1, 2);// has to be added to cart-run time datat

		// Launch the browser
		WebDriver driver = new EdgeDriver();
		sUtil.maximizeWindow(driver);
		sUtil.addImplicitlyWait(driver);

		// Load the URL
		driver.get(URL);

		// Login to Application
//		driver.findElement(By.id("user-name")).sendKeys(USERNAME);
//		driver.findElement(By.id("password")).sendKeys(PASSWORD);
//		driver.findElement(By.id("login-button")).click();
		
		LoginPage lp = new LoginPage(driver);
		
//		lp.getUsernameEdt().sendKeys(USERNAME);
//		lp.getPasswordEdt().sendKeys(PASSWORD);
//		lp.getLoginBtn().click();
		
		lp.loginToApp(USERNAME, PASSWORD);

		// Click on product - use the excel data - Dynamic xpath
		WebElement ele = driver.findElement(By.xpath("//div[.='" + PRODUCTNAME + "']"));
		String productTobeAdded = ele.getText();
		ele.click();
		
		//Capture screenshot
		String screenshotName = "addProductTocart"+jUtil.getSystemDate();
		String path = sUtil.captureScreenShot(driver,screenshotName);
		System.out.println(path);

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
