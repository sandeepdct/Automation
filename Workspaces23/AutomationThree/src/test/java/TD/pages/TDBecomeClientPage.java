package TD.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.automation.accelerators.ActionEngine;
import com.automation.report.CReporter;

public class TDBecomeClientPage extends ActionEngine{
	
	public By firstnametext=By.id("txtFirstName");
	public By lastnametext=By.id("txtLastName");
	public By designationtext=By.id("txtTitle");
	public By organizationtext=By.id("txtCompany");
	public By emailtext=By.id("txtPersonalEmailAddress");
	public By phonetext=By.id("txtphonenumber");
	public By commenttext=By.id("p_lt_ctl05_SiteContent_p_lt_ctl00_BecomeClient_txtcomments");
	public By submitbtn=By.id("ProcessButton");
	public By becomeclientlink= By.xpath("<a href=\"/become-client\">Become a Client</a>");
	public By successmsgloc= By.xpath("//*[@id=\"myModal\"]/div/div/div[2]/p");

	public TDBecomeClientPage(WebDriver Driver, CReporter reporter) {
		SuperClass(Driver, reporter);
	}

	public void SubmitBecomeClientform(String firstname, String lastname, String designation, 
			String organization, String emailaddress, String phone, String comment){
		
		try {
			
			//click(becomeclientlink, "Become a client link");
			Thread.sleep(5000);
			Driver.get("http://kenticocms.dctinc.com/become-client");
			
			type(firstnametext, firstname, "First name text box");
			type(lastnametext, lastname, "Last name text box");
			type(designationtext, designation, "Designation text box");
			type(organizationtext, organization, "Organization text box");
			type(emailtext, emailaddress, "Email address text box");
			type(phonetext, phone, "Phone number text box");
			type(commenttext, comment, "Comment box");
			click(submitbtn, "Submit Button");
						
		}catch(Throwable e){
			e.printStackTrace();
		}
		
		
	}
	
	public String verifysuccessmsg() {
		String msg= new String();
		
		try {
			
			msg= getText(successmsgloc, "Success message locator");
			return msg;
		}catch(Throwable e){
			e.printStackTrace();
		}
		return msg;
	}
	
	
   public boolean VerifySuccessfullsubmit(String SuccessMessage) throws Throwable{
		
		boolean success=true;
			
			success=verifycontainsText(successmsgloc, SuccessMessage, "Sucess Message");
			return success;
	}
	
}
