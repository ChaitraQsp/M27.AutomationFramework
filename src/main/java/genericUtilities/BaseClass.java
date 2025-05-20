package genericUtilities;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;

import objectRepository.InventoryPage;
import objectRepository.LoginPage;

/**
 * This class consists of basic configuration annotations of TestNG
 * 
 * @author Chaitra M
 *
 */
public class BaseClass {

	public JavaUtility jUtil = new JavaUtility();
	public SeleniumUtility sUtil = new SeleniumUtility();
	public FileUtility fUtil = new FileUtility();

	public WebDriver driver;

	// For Listeners
	public static WebDriver sdriver;

	@BeforeSuite(alwaysRun = true)
	public void bsConfig() {
		System.out.println("======= Database Connection succesful =======");
	}

	// @Parameters("browser")
	// @BeforeTest
	@BeforeClass(alwaysRun = true)
	public void bcConfig(/* String pValue */) throws IOException {
		String URL = fUtil.readDataFromPropertyFile("url");

		driver = new EdgeDriver();

		// For Cross Browser execution - "Run time Polymorphism - driver"
//		if(pValue.equals("edge"))
//		{
//			driver = new EdgeDriver();
//		}
//		else if(pValue.equals("firefox"))
//		{
//			driver = new FirefoxDriver();
//		}
//		else
//		{
//			driver = new EdgeDriver();
//		}

		sUtil.maximizeWindow(driver);
		sUtil.addImplicitlyWait(driver);

		driver.get(URL);

		// For Listeners
		sdriver = driver;

		System.out.println("======= Browser launch succesful =======");

	}

	@BeforeMethod(alwaysRun = true)
	public void bmConfig() throws IOException {
		String USERNAME = fUtil.readDataFromPropertyFile("username");
		String PASSWORD = fUtil.readDataFromPropertyFile("password");

		LoginPage lp = new LoginPage(driver);
		lp.loginToApp(USERNAME, PASSWORD);

		System.out.println("======= Login To App succesful =======");
	}

	@AfterMethod(alwaysRun = true)
	public void amConfig() {
		InventoryPage ip = new InventoryPage(driver);
		ip.logoutOfApp();

		System.out.println("======= Logout of App succesful =======");
	}

	// @AfterTest
	@AfterClass(alwaysRun = true)
	public void acConfig() {
		driver.quit();

		System.out.println("======= Browser closure succesful =======");
	}

	@AfterSuite(alwaysRun = true)
	public void asConfig() {
		System.out.println("======= Database Closure succesful =======");
	}
}
