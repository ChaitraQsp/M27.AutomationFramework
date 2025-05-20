package practice;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class TestNGSample {
	
	@Test(dataProvider = "getData")
	public void createEmpData(String name, int id)
	{
		System.out.println("Name id - "+name);
		System.out.println("ID is - "+id);
	}
	
	@DataProvider
	public Object[][] getData()
	{
		Object[][] data = new Object[3][2];//3x2 - three data sets and each consists of 2 info
	   
		//1 st data set
		data[0][0] = "Chaitra";
		data[0][1] = 12;
		
		//2nd data set
		data[1][0] = "Duvith";
		data[1][1] = 8;
		
		//3rd data set
		data[2][0] = "sree";
		data[2][1] = 14;
		
		return data;
		
	
	
	}

}
