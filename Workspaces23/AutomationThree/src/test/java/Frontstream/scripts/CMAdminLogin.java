package Frontstream.scripts;

import java.util.List;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.automation.accelerators.ActionEngine;
import com.selenium.project.pages.bfg.cmHomePage;
import com.selenium.project.pages.bfg.cmLoginPage;


public class CMAdminLogin extends ActionEngine{
	
	
	@Test(groups={"regression"},dataProvider="getUserCredentials")
	public void adminlogin(String Username, String Password) throws Throwable{
		try		
		{
			
			this.reporter.initTestCaseDescription("Admin login to CM");
			
			new cmLoginPage(Driver, reporter).login(Username, Password);
			Thread.sleep(10000);
			if(new cmHomePage(Driver, reporter).VerifySuccessfullLogin(Username)==false){
				this.reporter.initTestCaseDescription(new cmLoginPage(Driver, reporter).verifyLoginErrors());
				System.out.println("Error : "+new cmLoginPage(Driver, reporter).verifyLoginErrors());
			}else{
				this.reporter.initTestCaseDescription("Admin Login Success");
			}
			
			
			
			
			
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
	}
	
	
	
	@DataProvider
	public Object[][] getUserCredentials() throws Throwable
	{
		Object[][] data=xlsrdr.getDataArrayBySheet("LoginDetails");
		System.out.println(data);
		return data;
	}

}
