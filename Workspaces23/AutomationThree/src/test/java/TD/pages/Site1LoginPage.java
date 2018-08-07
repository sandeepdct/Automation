package TD.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.automation.accelerators.ActionEngine;
import com.automation.report.CReporter;

public class Site1LoginPage extends ActionEngine{
		
	public By usernametext=By.xpath("//*[@id=\"auth0-login-form\"]/div/div/form/div/div/div[2]/span/div/div/div/div/div/div/div/div/div[4]/div[1]/div/input");
	public By passwordtext=By.xpath("//*[@id=\"auth0-login-form\"]/div/div/form/div/div/div[2]/span/div/div/div/div/div/div/div/div/div[4]/div[2]/div/input");
	public By loginbtn=By.xpath("//*[@id=\"auth0-login-form\"]/div/div/form/div/div/button/span/span/svg");
	
	public Site1LoginPage(WebDriver Driver, CReporter reporter) {
		SuperClass(Driver, reporter);
	}
	
	public void WpLogin(String username, String password) {
try {
			
			Thread.sleep(5000);
			Driver.get("http://www.rapidowebs.com/site1/wp-login");
			
			type(usernametext, username, "Username text box");
			type(passwordtext, password, "Password text box");
			click(loginbtn, "Click Login Button");
						
		}catch(Throwable e){
			e.printStackTrace();
		}
	}
}
