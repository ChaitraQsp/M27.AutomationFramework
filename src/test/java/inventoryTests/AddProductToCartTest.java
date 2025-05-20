package inventoryTests;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import genericUtilities.BaseClass;
import genericUtilities.FileUtility;
import genericUtilities.JavaUtility;
import genericUtilities.SeleniumUtility;
import objectRepository.CartPage;
import objectRepository.InventoryItemPage;
import objectRepository.InventoryPage;
import objectRepository.LoginPage;

@Listeners(genericUtilities.ListenersImplementation.class)
public class AddProductToCartTest extends BaseClass {

	@Test(groups = "SmokeSuite")
	public void tc_01_AddProductTocartTest() throws IOException {
		// Read data from excel File
		String PRODUCTNAME = fUtil.readDataFromExcel("Products", 1, 2);// has to be added to cart-run time datat

		// click on product
		InventoryPage ip = new InventoryPage(driver);
		ip.clickOnAProduct(driver, PRODUCTNAME);

		// Add Product to cart
		InventoryItemPage iip = new InventoryItemPage(driver);
		iip.clickOnAddTocartBtn();

		// Click on cart container
		ip.clickOnCartContainer();

		// Validate in cart
		CartPage cp = new CartPage(driver);
		String productInCart = cp.getItemName();
		
		Assert.assertEquals(productInCart, PRODUCTNAME);
		
		Assert.assertTrue(productInCart.equals(PRODUCTNAME));
		System.out.println(productInCart);
		
	}
	
	@Test
	public void sampleTest()
	{
		
		System.out.println("Sample");
	}

}
