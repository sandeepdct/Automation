package Frontstream.scripts;

import org.testng.annotations.Test;

import com.automation.accelerators.ActionEngine;

public class CMVerifyCampaignName extends ActionEngine{
	
	@Test(groups={"regression"}, dataProvider="getCampaignDetails")
	public void verifycampaigname(String CampaignCode, String ExpectedCampaignName){
		
		this.reporter.initTestCaseDescription("Verify Campaign Name");
		
	}
	

}
