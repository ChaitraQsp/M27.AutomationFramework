package genericUtilities;

import java.io.File;
import java.io.IOException;
import java.time.Duration;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.io.FileHandler;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 * This class consists of reusable methods related to Selenium tool
 * @author Chaitra M
 *
 */
public class SeleniumUtility {
	
	/**
	 * This method will maximize the window
	 * @param driver
	 */
	public void maximizeWindow(WebDriver driver)
	{
		driver.manage().window().maximize();
	}

	/**
	 * This method will minimize window
	 * @param driver
	 */
	public void minimizeWindow(WebDriver driver)
	{
		driver.manage().window().minimize();
	}
	
	/**
	 * This method will add 10 seconds of implicitly wait
	 * @param driver
	 */
	public void addImplicitlyWait(WebDriver driver)
	{
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
	}
	
	/**
	 * This method will wait for 10 seconds for particular element to be visible
	 * @param driver
	 * @param element
	 */
	public void waitForElementToBeVisible(WebDriver driver, WebElement element)
	{
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		wait.until(ExpectedConditions.visibilityOf(element));
	}
	
	/**
	 * This method will wait for 10 seconds for particular element to be clickable
	 * @param driver
	 * @param element
	 */
	public void waitForElementToBeclickable(WebDriver driver, WebElement element)
	{
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		wait.until(ExpectedConditions.elementToBeClickable(element));
	}
	
	/**
	 * This method will handle drop-down by index
	 * @param element
	 * @param index
	 */
	public void handleDropDown(WebElement element, int index)
	{
		Select s = new Select(element);
		s.selectByIndex(index);
	}
	
	/**
	 * This method will handle drop down by visible text
	 * @param element
	 * @param text
	 */
	public void handleDropDown(WebElement element, String text)
	{
		Select s = new Select(element);
		s.selectByVisibleText(text);
	}
	
	/**
	 * This method will handle drop down by value
	 * @param value
	 * @param element
	 */
	public void handleDropDown(String value, WebElement element)
	{
		Select s = new Select(element);
		s.selectByValue(value);
	}
	
	/**
	 * This method will perform mouse hovering action
	 * @param driver
	 * @param element
	 */
	public void mouseOverAction(WebDriver driver,WebElement element)
	{
		Actions act = new Actions(driver);
		act.moveToElement(element).perform();
	}
	//click and hold , double click, right click, drag and drop, scroll to element
	
	/**
	 * This method will perform click and hold action
	 * @param driver
	 * @param element
	 */
	public void clickAndHoldAction(WebDriver driver,WebElement element)
	{
		Actions act = new Actions(driver);
		act.clickAndHold(element).perform();
	}
	
	/**
	 * This method will perform double click action
	 * @param driver
	 * @param element
	 */
	public void doubleClickAction(WebDriver driver,WebElement element)
	{
		Actions act = new Actions(driver);
		act.doubleClick(element).perform();
	}
	
	/**
	 * This method will perform right click action
	 * @param driver
	 * @param element
	 */
	public void rightClickAction(WebDriver driver,WebElement element)
	{
		Actions act = new Actions(driver);
		act.contextClick(element).perform();
	}
	
	/**
	 * This method will perform drag and DRop action
	 * @param driver
	 * @param srcelement
	 * @param Tarelement
	 */
	public void dragAndDropAction(WebDriver driver,WebElement Tarelement, WebElement srcElement)
	{
		Actions act = new Actions(driver);
		act.dragAndDrop(srcElement,Tarelement).perform();
	}
	
	/**
	 * This method will perform scroll to element action
	 * @param driver
	 * @param element
	 */
	public void scrollToElementAction(WebDriver driver,WebElement element)
	{
		Actions act = new Actions(driver);
		act.scrollToElement(element).perform();
	}
	
	/**
	 * This method will handle Frame by index
	 * @param driver
	 * @param frameIndex
	 */
	public void handleFrame(WebDriver driver, int frameIndex)
	{
		driver.switchTo().frame(frameIndex);
	}
	
	/**
	 * This method will handle Frame by framenameOrID
	 * @param driver
	 * @param frameIndex
	 */
	public void handleFrame(WebDriver driver, String framenameOrID)
	{
		driver.switchTo().frame(framenameOrID);
	}
	
	/**
	 * This method will handle Frame by frameElement
	 * @param driver
	 * @param frameIndex
	 */
	public void handleFrame(WebDriver driver, WebElement frameElement)
	{
		driver.switchTo().frame(frameElement);
	}

	/**
	 * This method will accept alert popup
	 * @param driver
	 */
	public void acceptAlert(WebDriver driver)
	{
		driver.switchTo().alert().accept();
	}
	
	/**
	 * This method will cancel alert popup
	 * @param driver
	 */
	public void dismissAlert(WebDriver driver)
	{
		driver.switchTo().alert().dismiss();
	}
	
	/**
	 * This method will enter text to alert popup
	 * @param driver
	 */
	public void enterAlertText(WebDriver driver, String text)
	{
		driver.switchTo().alert().sendKeys(text);
	}
	
	/**
	 * This method will capture the text in  alert popup
	 * @param driver
	 * @return 
	 */
	public String getAlerttext(WebDriver driver)
	{
		return driver.switchTo().alert().getText();
	}
	
	/**
	 * This method will capture screenshot and return the path to caller
	 * @param driver
	 * @param screenshotName
	 * @return
	 * @throws IOException
	 */
	public String captureScreenShot(WebDriver driver, String screenshotName) throws IOException
	{
		TakesScreenshot ts = (TakesScreenshot) driver;
		File src = ts.getScreenshotAs(OutputType.FILE);
		File dst = new File(".\\ScreenShots\\"+screenshotName+".png");
		FileHandler.copy(src, dst);
		
		return dst.getAbsolutePath();//For Extent reports
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
