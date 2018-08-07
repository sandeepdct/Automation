package com.selenium.project.pages.bfg;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.automation.accelerators.ActionEngine;
import com.automation.report.CReporter;




public class cmHomePage extends ActionEngine{
	
	public By usernameLoc= By.xpath(".//*[@id='BannerForm']/table/tbody/tr/td[3]/span[2]/a[1]");
	public By topframe= By.name("topnav");
	public By mainframe= By.name("content");
	public By treeframe= By.name("tree");
	public By searchcampaign= By.id("CampaignGrid_ctl29_FilterTextBox");
	public By campaignnameloc= By.xpath(".//*[@id='CampaignGrid']/tbody/tr[2]/td[1]");
	public By viewcampaignloc= By.xpath(".//*[@id='CampaignGrid_ctl03_ViewEditButton']");
	public By activatecampbtn= By.xpath(".//*[@id='CampaignGrid_ctl03_ActivateButton']");
	public By addcamplink= By.linkText("AddButton");
	
	
	public cmHomePage(WebDriver Driver, CReporter reporter){
		SuperClass(Driver, reporter);
	}
	
	public boolean VerifySuccessfullLogin(String username) throws Throwable{
		
		boolean success=true;
		
			SwitchToFrame(topframe, "Top Navigation");
			success=verifycontainsText(usernameLoc, username, "Admin username");
			return success;
	}
	
	public void SearchCampaign(String CampaignCode) throws Throwable{
		SwitchToFrame(mainframe, "Main Frame");
		type(searchcampaign, CampaignCode, "Search Campaign Field");
		
	}
	
	public String VerifyCampaignName() throws Throwable{
		SwitchToFrame(mainframe, "Main Frame");
		String cname=getText(campaignnameloc, "Campaign Name location");
		return cname;
		
	}
	
	public void ViewCampaignDetails(String CampaignCode) throws Throwable{
		SwitchToFrame(mainframe, "Main Frame");
		click(viewcampaignloc, "View Campaign Button");
		
	}
	
	public void ActivateCampaign(String CampaignCode) throws Throwable{
		SwitchToFrame(mainframe, "Main Frame");
		click(activatecampbtn, "Activate Campaign Button");
	}
	
	public void AddCampaignLink() throws Throwable{
		SwitchToFrame(mainframe, "Main Frame");
		click(addcamplink, "Add Campaign link");
	}
	
	public void GetCampaignNames() throws Throwable{
		SwitchToFrame(mainframe, "Main Frame");
		List<WebElement> e= (List<WebElement>) Driver.findElement(By.id("CampaignGrid"));
		
		java.util.Iterator<WebElement> itr= e.iterator();
		while(itr.hasNext()){
			WebElement row= itr.next();
			System.out.println(row.getText());
		}
		
		
	}

}
