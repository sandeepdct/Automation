package TD.scripts;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.automation.accelerators.ActionEngine;

import TD.pages.TDBecomeClientPage;

public class TD_BecomeClient extends ActionEngine{
	
	@Test(groups={"regression"},dataProvider="BecomeClientData")
	public void SubmitBecomeClient(String FirstName, String LastName, String Designation, String Organization, 
			String EmailAddress, String Phone, String Comment, String SuccessMessage) throws Throwable{
		try		
		{
			
			this.reporter.initTestCaseDescription("Become a Client");
			new TDBecomeClientPage(Driver, reporter).SubmitBecomeClientform(FirstName, LastName, Designation, 
					Organization, EmailAddress, Phone, Comment);
			Thread.sleep(10000);
			if(new TDBecomeClientPage(Driver, reporter).VerifySuccessfullsubmit(SuccessMessage)==false){
				
			}else{
				this.reporter.initTestCaseDescription("Form Submitted successfully");
			}
			
				
			
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}

	}	
	
	@DataProvider
	public Object[][] BecomeClientData() throws Throwable
	{
		Object[][] data=xlsrdr.getDataArrayBySheet("BecomeClient");
		System.out.println(data);
		return data;
	}
}
