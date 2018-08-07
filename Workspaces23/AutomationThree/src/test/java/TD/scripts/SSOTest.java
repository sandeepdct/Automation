package TD.scripts;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.automation.accelerators.ActionEngine;

import TD.pages.Site1LoginPage;
import TD.pages.Site2Dashboard;
import TD.pages.TDBecomeClientPage;

public class SSOTest extends ActionEngine{

	@Test(groups={"regression"},dataProvider="SSOtest")
	public void TestSSO(String Username, String Password) throws Throwable{
		try		
		{
			
			this.reporter.initTestCaseDescription("Test Single Sign-On");
			new Site1LoginPage(Driver, reporter).WpLogin(Username, Password);
			Thread.sleep(10000);
			new Site2Dashboard(Driver, reporter).accessSite2dashboard(Username);
			this.reporter.initTestCaseDescription("Form Submitted successfully");
							
			
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}

	}	
	
	@DataProvider
	public Object[][] BecomeClientData() throws Throwable
	{
		Object[][] data=xlsrdr.getDataArrayBySheet("SSOtest");
		System.out.println(data);
		return data;
	}
	
}
