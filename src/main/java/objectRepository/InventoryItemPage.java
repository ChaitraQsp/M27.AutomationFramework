package objectRepository;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class InventoryItemPage {
	
	@FindBy(id = "add-to-cart")
	private WebElement addTocartBtn;
	
	public InventoryItemPage(WebDriver driver)
	{
		PageFactory.initElements(driver, this);
	}

	public WebElement getAddTocartBtn() {
		return addTocartBtn;
	}

	//Business library
	
	/**
	 * This method will click on add to cart button
	 */
	public void clickOnAddTocartBtn()
	{
		addTocartBtn.click();
	}
	
	
}
