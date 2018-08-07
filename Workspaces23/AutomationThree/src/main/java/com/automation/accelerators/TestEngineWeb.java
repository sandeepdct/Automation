package com.automation.accelerators;

import io.appium.java_client.AppiumDriver;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Method;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.safari.SafariDriver;
import org.testng.SkipException;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;

import com.applitools.eyes.Eyes;
import com.applitools.eyes.RectangleSize;
import com.automation.report.CReporter;
import com.automation.report.ReporterConstants;
import com.automation.support.ExcelReader;

/**
 * The Class TestEngineWeb.
 *
 * @author in01518
 */
public class TestEngineWeb {

	public boolean proceedExecution;
	/** The Constant LOG. */
	protected static final Logger LOG = Logger.getLogger(TestEngineWeb.class);

	/** The appium driver. */
	protected AppiumDriver appiumDriver = null;

	protected Eyes eyes;
	/** The Driver. */
	protected WebDriver Driver = null;

	/** The reporter. */
	protected CReporter reporter = null;

	/** The browser. */
	/*cloud platform*/
	public String browser = null;

	/** The version. */
	public String version = null;

	/** The platform. */
	public String platform = null;

	/** The environment. */
	public String environment = null;

	/** The local base url. */
	public String localBaseUrl = null;

	/** The cloud base url. */
	public String cloudBaseUrl = null;

	/** The user name. */
	public String userName = null;

	/** The access key. */
	public String accessKey = null;

	/** The cloud implicit wait. */
	public String cloudImplicitWait = null;

	/** The cloud page load time out. */
	public String cloudPageLoadTimeOut = null;

	/** The update jira. */
	public String updateJira = null;

	/** The build number. */
	public String buildNumber = "";

	/** The job name. */
	public String jobName = "";

	/** The executed from. */
	public String executedFrom = null;       

	/** The url rem. */
	public String urlRem = null;

	/** The nodes selected. */
	public String nodes = null;
	/** The nodes selected. */
	public String nodeParam = null;
	/**Test Environment URL **/
	public String urlOpps = null;
	public String urlOpcs = null;
	public String urlBaseURL = null;
	
	
	public static ExcelReader xlsrdr = new ExcelReader(System.getProperty("user.dir") + "\\TestData\\TestData.xls",
			"Test_Data");
	/*@BeforeSuite(alwaysRun = true)
	public void beforeSuite() throws IOException{
		if(System.getProperty("os.name").toLowerCase().contains("windows")){
			String command="cmd /c start  "+" CALL "+"\""+System.getProperty("user.dir")+"\\Drivers\\MakeDisplayActive.bat"+"\"";
			String command1="cmd /c start  "+" CALL "+"\""+System.getProperty("user.dir")+"\\Drivers\\EnableDisplayContentIESetting.bat"+"\"";
			String command2="cmd /c start  "+" CALL "+"\""+System.getProperty("user.dir")+"\\Drivers\\ReturnSessionToConsole.bat"+"\"";
			Runtime runtime=Runtime.getRuntime();
			@SuppressWarnings("unused")
			Process pr=runtime.exec(command);
			@SuppressWarnings("unused")
			Process pr1=runtime.exec(command1);
			@SuppressWarnings("unused")
			Process pr2=runtime.exec(command2);
		}else{
			System.out.println("****** Batch file to make display active is not execeuted because this is not the windows platform. ******");
		}
	}*/

	/**
	 * Before test. - Create Driver & Reporter instance
	 *
	 * @param automationName the automation name
	 * @param browser the browser
	 * @param browserVersion the browser version
	 * @param environment the environment
	 * @param platformName the platform name
	 * @throws IOException Signals that an I/O exception has occurred.
	 * @throws InterruptedException the interrupted exception
	 */
	@BeforeClass(dependsOnGroups={"initGroup"} , alwaysRun = true)
	@Parameters({"automationName","browser","browserVersion","environment","platformName","nodeUrl"})
	public void beforeClass(String automationName, String browser, String browserVersion,String environment,String platformName,String nodeUrl) throws IOException, InterruptedException
	{

		/*get configuration */

		this.browser = browser;
		this.version = browserVersion;
		this.platform = platformName;
		this.environment = environment;

		this.userName = ReporterConstants.SAUCELAB_USERNAME;
		this.accessKey = ReporterConstants.SAUCELAB_ACCESSKEY;
		this.executedFrom = System.getenv("COMPUTERNAME");
		this.cloudImplicitWait = ReporterConstants.CLOUD_IMPLICIT_WAIT;
		this.cloudPageLoadTimeOut = ReporterConstants.CLOUD_PAGELOAD_TIMEOUT;        
		this.updateJira = "";




		if(environment.equalsIgnoreCase("local"))
		{
			proceedExecution = true;
			this.setWebDriverForLocal(browser);
			
		}
	
		if(environment.equalsIgnoreCase("grid")){
			this.setWebdriverForGrid(browser,nodeUrl);
		}
		/* Start Reporter Instance*/
		reporter = CReporter.getCReporter(browser, browserVersion , environment, true);
		Driver.get(ReporterConstants.APP_BASE_URL);
		 //eyes.checkWindow("Main Page");
		 

		if(!this.browser.equalsIgnoreCase("safari")){
			Driver.manage().timeouts().pageLoadTimeout(60, TimeUnit.SECONDS);
			Driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		}
		Driver.manage().window().maximize();
		if(this.reporter.getBrowserContext().getBrowserName().equals("ie")){
			if(this.Driver.getPageSource().trim().contains("There is a problem with this website")){
				this.Driver.navigate().to("javascript:document.getElementById('overridelink').click()");
				Thread.sleep(Long.parseLong("3000"));
			}
		}
		
		reporter.calculateSuiteStartTime();
		

	}

	/**
	 * Before method.
	 *
	 * @param method the method
	 */
	@BeforeMethod(alwaysRun = true)
	public void beforeMethod(Method method)
	{
		if(proceedExecution == false)
		{
			System.out.println("Should Exit");
			throw new SkipException("Skipped");
		}
		/* Reporter Instance*/
		
		reporter.initTestCase(this.getClass().getName().substring(0,this.getClass().getName().lastIndexOf(".")), method.getName(), null, true);

	}

	/**
	 * After test. - Close Driver & Reporter instance
	 *
	 * @throws Exception the exception
	 */
	@AfterClass(alwaysRun = true)
	public void afterClass() throws Exception
	{

		if(proceedExecution == false)
		{
			System.out.println("Should Exit");
			throw new SkipException("Skipped");
		}

		if(!this.reporter.getBrowserContext().getBrowserName().equalsIgnoreCase("edge")){
			Driver.close();
			
		}
		Driver.quit();
		eyes.close();
		//Close Reporter Instance
		reporter.calculateSuiteExecutionTime();	
		reporter.createHtmlSummaryReport(ReporterConstants.APP_BASE_URL,true);
		reporter.closeSummaryReport();
	}




	/**
	 * After method.
	 * @throws Exception 
	 */
	@AfterMethod(alwaysRun = true)
	public void afterMethod() throws Exception
	{
		if(proceedExecution == false)
		{
			System.out.println("Should Exit");
			throw new SkipException("Skipped");
		}
		//get browser info				
		reporter.calculateTestCaseExecutionTime();		
		reporter.closeDetailedReport();		
		reporter.updateTestCaseStatus();
		

	}

	public void setWebdriverForGrid(String browser,String nodeUrl) throws MalformedURLException{
		DesiredCapabilities caps = new DesiredCapabilities();
		if(browser.equalsIgnoreCase("IE")){
			caps = DesiredCapabilities.internetExplorer();
			caps.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true);
		}
		else if(browser.equalsIgnoreCase("Firefox")){
			caps = DesiredCapabilities.firefox();
			//caps.setBrowserName("firefox");
			//caps.setVersion("33.0");
			caps.setPlatform(Platform.WINDOWS);
		}
		else if(browser.equalsIgnoreCase("chrome")){
			caps = DesiredCapabilities.chrome();
			//caps.setPlatform(Platform.ANY);
		}
		else {
			caps = DesiredCapabilities.safari();
			//caps.setPlatform(Platform.ANY);
		}
		//String Node = "http://172.16.14.172:5555/wd/hub";
		//URL commandExecutorUri = new URL("http://ondemand.saucelabs.com/wd/hub");
		this.Driver = new RemoteWebDriver(new URL(nodeUrl), caps);
	}

	/**
	 * Sets the web driver for local.
	 *
	 * @param browser the new web driver for local
	 * @throws IOException Signals that an I/O exception has occurred.
	 * @throws InterruptedException the interrupted exception
	 */
	private void setWebDriverForLocal(String browser) throws IOException, InterruptedException
	{

		/**get environment details**/

		
		/*this.urlOpps = ReporterConstants.ADMINOPPS_STAGE_URL;
		this.urlOpcs = ReporterConstants.DONEROPCS_STAGE_URL;
		 */

		switch(browser)
		{
		case "firefox":
		/*	DesiredCapabilities objCapabilities = new DesiredCapabilities();
			objCapabilities = DesiredCapabilities.firefox();
			FirefoxProfile firefoxProfile = new FirefoxProfile(new File(System.getProperty("user.dir") + "/Drivers/BrowserProfiles/Firefox"));
			firefoxProfile.setPreference("geo.enabled", true);
			firefoxProfile.setPreference("extensions.enabledAddons", "georelocate%40netzgewitter.com:0.2.3");
			firefoxProfile.setPreference("extensions.georelocate@netzgewitter.com.install-event-fired", true);
			firefoxProfile.setPreference("browser.cache.disk.enable", false);
			firefoxProfile.setPreference("browser.cache.memory.enable", false);
			firefoxProfile.setPreference("browser.cache.offline.enable", false);
			firefoxProfile.setPreference("network.http.use-cache", false);
			firefoxProfile.setAcceptUntrustedCertificates(true);
			firefoxProfile.setEnableNativeEvents(true);
			firefoxProfile.setPreference("extensions.ui.locale.hidden", true);
			ProfilesIni profile = new ProfilesIni();
			profile.getProfile("default");
			objCapabilities.setCapability(FirefoxDriver.PROFILE, firefoxProfile);
			this.Driver = new FirefoxDriver(objCapabilities);*/
			
			
			System.setProperty("webdriver.gecko.driver", "Drivers\\geckodriver.exe");
			//DesiredCapabilities firecap=DesiredCapabilities.firefox();
			//firecap.setCapability("marionette", true);
			
			this.Driver=new FirefoxDriver();
			
			//eyes = new Eyes();
	        // This is your api key, make sure you use it in all your tests.
	        //eyes.setApiKey("4zS0PkmG3rTVEnVi7kAcQt104oFopo9xws7EXFisvuCnw110");
	        //Driver = eyes.open(Driver, "Applitools", "Test Web Page", new RectangleSize(1024, 768));
	        
			break;
		case "ie":

			DesiredCapabilities capab = DesiredCapabilities.internetExplorer();		
			File file = new File("Drivers\\IEDriverServer.exe");
			System.setProperty("webdriver.ie.driver",file.getAbsolutePath());
			capab.setPlatform(Platform.WINDOWS);
			capab.setCapability(InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS,true);
			capab.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true);
			capab.setJavascriptEnabled(true);
			capab.setCapability("requireWindowFocus", true);
			capab.setCapability("enablePersistentHover", false);
			this.Driver = new InternetExplorerDriver(capab);
			Process p = Runtime
					.getRuntime()
					.exec("RunDll32.exe InetCpl.cpl,ClearMyTracksByProcess 255");
			p.waitFor();
			Thread.sleep(1000);
			break;

		case "chrome":
			System.setProperty("webdriver.chrome.driver",
					"D:\\chromedriver.exe");

			/*DesiredCapabilities capabilities = DesiredCapabilities.chrome(); 
			ChromeOptions options = new ChromeOptions();
			options.addArguments("test-type");
			capabilities.setCapability(ChromeOptions.CAPABILITY, options);
			capabilities.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true);
			this.Driver = new ChromeDriver(capabilities);*/
			this.Driver = new ChromeDriver();
			
			break;

		case "edge":
			DesiredCapabilities cap = DesiredCapabilities.edge();
			cap.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true);
			System.setProperty("webdriver.edge.driver", "Drivers\\MicrosoftWebDriver.exe");
			this.Driver = new EdgeDriver();
			Thread.sleep(50);
			break;

		case "safari":
			System.setProperty("webdriver.safari.noinstall", "true");
			DesiredCapabilities cap1 = DesiredCapabilities.safari();
			if(this.platform.equalsIgnoreCase("mac")){
				cap1.setPlatform(Platform.MAC);
			} else {
				cap1.setPlatform(Platform.WINDOWS);
			}
			cap1.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true);
			cap1.setCapability("nativeEvents", true);
			cap1.setCapability("acceptSslCerts", true);
			cap1.setCapability("safariIgnoreFraudWarning", true);
			cap1.setCapability("WarnAboutFraudulentWebsite", false);
			this.Driver = new SafariDriver(cap1);
			Thread.sleep(5000);
			break;
		}
	}

	
}