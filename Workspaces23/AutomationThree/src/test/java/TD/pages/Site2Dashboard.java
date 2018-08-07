package TD.pages;

import static org.testng.Assert.assertEquals;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

import com.automation.accelerators.ActionEngine;

import com.automation.report.CReporter;

public class Site2Dashboard extends ActionEngine{

	public By profilelink=By.xpath("//*[@id=\"menu-users\"]/a/div[3]");
	public By emailfield=By.xpath("//*[@id=\"email\"]");
		
	public Site2Dashboard(WebDriver Driver, CReporter reporter) {
		SuperClass(Driver, reporter);
	}
	
	public void accessSite2dashboard(String username) {
		try {
			
			Thread.sleep(5000);
			Driver.get("http://thesmartrmarketingapp.com/web/site2/wp-admin/");
			
			click(profilelink, "ProfileLink");
			String actualemail=Driver.findElement(emailfield).getText();
			Assert.assertEquals(actualemail, username);
						
			}catch(Throwable e){
				e.printStackTrace();
			}
	}
	
		
}
