package inventoryTests;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

import genericUtilities.BaseClass;
import genericUtilities.FileUtility;
import genericUtilities.JavaUtility;
import genericUtilities.SeleniumUtility;
import objectRepository.CartPage;
import objectRepository.InventoryItemPage;
import objectRepository.InventoryPage;
import objectRepository.LoginPage;

public class AddLowestPriceProductToCartTest extends BaseClass {


	@Test
	public void tc_02_AddLowestPriceProductToCartTest() throws IOException
	{
		// Read data from excel File
		String SORTOPTION = fUtil.readDataFromExcel("Products", 4, 2);
		String PRODUCTNAME = fUtil.readDataFromExcel("Products", 4, 3);// has to be added to cart-run time datat

		// click on product
		InventoryPage ip = new InventoryPage(driver);
		ip.clickOnLowestPriceProduct(driver, SORTOPTION, PRODUCTNAME);

		// Add Product to cart
		InventoryItemPage iip = new InventoryItemPage(driver);
		iip.clickOnAddTocartBtn();

		// Click on cart container
		ip.clickOnCartContainer();

		// Validate in cart
		CartPage cp = new CartPage(driver);
		String productInCart = cp.getItemName();
		
		Assert.assertTrue(productInCart.equals(PRODUCTNAME));
		System.out.println(productInCart);
		
		
//		if (productInCart.equals(PRODUCTNAME)) {
//			System.out.println("pass");
//			System.out.println(productInCart);
//		} else {
//			System.out.println("fail");
//		}

	}

}
