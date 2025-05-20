package objectRepository;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CartPage {
	
	@FindBy(className = "inventory_item_name")
	private WebElement itemInfo;
	
	@FindBy(xpath = "//button[.='Remove']")
	private WebElement removeBtn;
	
	public CartPage(WebDriver driver)
	{
		PageFactory.initElements(driver, this);
	}

	public WebElement getItemInfo() {
		return itemInfo;
	}

	public WebElement getRemoveBtn() {
		return removeBtn;
	}
	
	//Business library
	
	/**
	 * This method will capture the product-name and return to caller
	 * @return
	 */
	public String getItemName()
	{
		return itemInfo.getText();
	}
	
	/**
	 * This method will click on remove button
	 */
	public void clickOnRemoveBtn()
	{
		removeBtn.click();
	}
	
	
}
