package Frontstream.scripts;

import java.util.Properties;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;




import com.automation.accelerators.ActionEngine;
import com.automation.report.CReporter;


/**

 */
public class MyTestCase extends ActionEngine {
	//private static final Logger LOG = Logger.getLogger(AIRS_TC24_Create_Organization_with_Sponsorships.class);

	
	@Test(groups={"regression"},dataProvider="getSearchTextValue")
	public void MyTestCase1(String SearchText) throws Throwable{
		try		
		{
			
			this.reporter.initTestCaseDescription("Create Organization with Sponsorships");
			//new dbsHomePage();
			//new dbsHomePage(Driver,reporter).search(SearchText);
			//new searchResultsPage(Driver,reporter).verifySearchResultTitle();
			eyes.checkWindow("Search page");
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	
	@DataProvider
	public Object[][] getSearchTextValue() throws Throwable
	{
		Object[][] data=xlsrdr.getDataArrayBySheet("Search");
		System.out.println(data);
		return data;
	}
	
}