
package com.automation.accelerators;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.datatransfer.StringSelection;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import java.util.Set;
import java.util.TimeZone;
import java.util.concurrent.TimeUnit;
import java.util.function.Function;

import org.joda.time.LocalDate;
import org.joda.time.Years;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.interactions.internal.Coordinates;
import org.openqa.selenium.internal.Locatable;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.automation.report.CReporter;


// TODO: Auto-generated Javadoc
/**
 * The Class ActionEngine.
 */
public class ActionEngine  extends TestEngineWeb  {

	/** The Constant LOG. */
	private static final Logger LOG = Logger.getLogger(ActionEngine.class);

	/** The msg click success. */
	private final String msgClickSuccess = "Successfully Clicked On ";

	/** The msg click failure. */
	private final String msgClickFailure = "Unable to Click on";

	/** The msg type success. */
	private final String msgTypeSuccess = "Successfully Typed On ";

	/** The msg type failure. */
	private final String msgTypeFailure = "Unable To Type On ";

	/** The msg is element found success. */
	private final String msgIsElementFoundSuccess = "Successfully Found ";

	/** The msg is element found failure. */
	private final String msgIsElementFoundFailure = "Unable To Found Element ";

	/**
	 * Setdriver.
	 *
	 * @param driver the driver
	 * @param reporter the reporter
	 * @return 
	 */
	public void SuperClass(WebDriver driver,CReporter reporter)
	{
		this.Driver = driver;
		this.reporter = reporter;

	}

	/**
	 * Click.
	 *
	 * @param locator the locator
	 * @param locatorName the locator name
	 * @return true, if successful
	 * @throws Throwable the throwable
	 */
	public boolean click(By locator, String locatorName) throws Throwable
	{
		boolean status = false;
		try
		{
			//WebDriverWait wait = new WebDriverWait(this.Driver, 60);
			//ScrollToElementVisible(locator);
			//wait.until(ExpectedConditions.elementToBeClickable(locator));
			Driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
			this.Driver.findElement(locator).click();
			this.reporter.SuccessReport("Click" , this.msgClickSuccess + locatorName);
			
			
			status = true;

		}
		catch(Exception e)
		{

			status = false;
			LOG.info(e.getMessage());
			this.reporter.failureReport("Click", this.msgClickFailure + locatorName, this.Driver);
			throw e;

		}
		return status;

	}

	/**
	 * Checks if is element present.
	 *
	 * @param by the by
	 * @param locatorName the locator name
	 * @param expected the expected
	 * @return true, if is element present
	 * @throws Throwable the throwable
	 */
	public boolean isElementPresent(By by, String locatorName,boolean expected) throws Throwable
	{
		boolean status = false;
		try
		{
			//WebDriverWait wait = new WebDriverWait(this.Driver, 30);
			//ScrollToElementVisible(by);
			//wait.until(ExpectedConditions.elementToBeClickable(by));
			Driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
			this.Driver.findElement(by);
			this.reporter.SuccessReport("isElementPresent" , this.msgIsElementFoundSuccess + locatorName);
			
			status = true;
		}
		catch(Exception e)
		{
			status = false;
			LOG.info(e.getMessage());
			if(expected == status)
			{
				this.reporter.SuccessReport("isElementPresent", locatorName + "is ElementPresent");
			}
			else
			{
				//	this.reporter.failureReport(locatorName + "is ElementPresent", this.msgIsElementFoundFailure + locatorName, this.Driver);
			}
		}
		return status;
	}

	/**
	 * Switch to frame.
	 *
	 * @param locator the locator
	 * @param locatorName the locator name
	 * @return true, if successful
	 * @throws Throwable the throwable
	 */
	public  boolean SwitchToFrame(By locator, String locatorName)
			throws Throwable{
		boolean flag = false;

		try {

			this.Driver.switchTo().defaultContent();
			//WebDriverWait wait = new WebDriverWait(this.Driver, 60);
			//ScrollToElementVisible(locator);
			//wait.until(ExpectedConditions.elementToBeClickable(locator));
			//wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(By.name("tree")));
			Driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
			this.Driver.switchTo().frame(this.Driver.findElement(locator));
			flag = true;
			return flag;

		} catch (Exception e) {

			return false;
		} finally {
			if (flag==false) {
				this.reporter.failureReport("Switch to Frame", "Switch to Frame failed" + locatorName);

			} else if (flag==true) {
				this.reporter.SuccessReport("Switch to Frame", "Switch to Frame done" + locatorName);
			}
		}


	}

	/**
	 * Type.
	 *
	 * @param locator the locator
	 * @param testdata the testdata
	 * @param locatorName the locator name
	 * @return true, if successful
	 * @throws Throwable the throwable
	 */
	public boolean type(By locator, String testdata, String locatorName) throws Throwable
	{
		boolean status = false;
		try
		{
			
			//WebDriverWait wait = new WebDriverWait(this.Driver, 60);
			
			//ScrollToElementVisible(locator);
			//wait.until(ExpectedConditions.elementToBeClickable(locator));
			//wait.until(ExpectedConditions.presenceOfElementLocated(locator));
			Driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
			this.Driver.findElement(locator).clear();
			this.Driver.findElement(locator).sendKeys(testdata);
			this.reporter.SuccessReport("type" , this.msgTypeSuccess + locatorName);
			status = true;
		}
		catch(Exception e)
		{
			status = false;
			e.printStackTrace();
			LOG.info(e.getMessage());
			this.reporter.failureReport("type", this.msgTypeFailure + locatorName, this.Driver);
			//throw new RuntimeException("Skipped");
			//RuntimeException ee = new RuntimeException();
			throw e;
		}

		return status;
	}

	/**
	 * Moves the mouse to the middle of the element. The element is scrolled
	 * into view and its location is calculated using getBoundingClientRect.
	 *
	 * @param locator            : Action to be performed on element (Get it from Object
	 *            repository)
	 * @param locatorName            : Meaningful name to the element (Ex:link,menus etc..)
	 * @return true, if successful
	 * @throws Throwable the throwable
	 */
	public boolean mouseover(By locator, String locatorName)
			throws Throwable {
		boolean flag = false;
		try {
			WebElement mo = this.Driver.findElement(locator);
			new Actions(this.Driver).moveToElement(mo).click(mo).build().perform();
			flag = true;
			return true;
		} catch (Exception e) {

			return false;
		} finally {
			if (flag == false) {
				this.reporter.failureReport("MouseOver",
						"MouseOver action is not perform on" + locatorName,this.Driver);

			} else if (flag == true) {

				this.reporter.SuccessReport("MouseOver",
						"MouserOver Action is Done on" + locatorName);
			}
		}
	}

	public void Mouseover(WebElement webelement) throws Throwable {
		try {
			JavascriptExecutor js = (JavascriptExecutor) (this.Driver);
			js.executeScript("$(arguments[0]).trigger('mouseover');", webelement);
		} catch (Exception e) {
			/*this.reporter.failureReport("MouseOver",
					"Failed to perform 'MouseOver' action",this.Driver);*/
		}
	}

	/**
	 * Tab.
	 *
	 * @param locator the locator
	 * @param locatorName the locator name
	 * @return true, if successful
	 * @throws Throwable the throwable
	 */
	public boolean tab(By locator, String locatorName)
			throws Throwable {
		boolean flag = false;
		try {
			//WebDriverWait wait = new WebDriverWait(this.Driver, 60);
			//ScrollToElementVisible(locator);
			//wait.until(ExpectedConditions.elementToBeClickable(locator));
			Driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
			WebElement mo = this.Driver.findElement(locator);
			mo.sendKeys(Keys.TAB);
			flag = true;
			return true;
		} catch (Exception e) {

			return false;
		} finally {
			if (flag == false) {
				this.reporter.failureReport("Tab",
						"Tab action is not perform on " + locatorName,this.Driver);

			} else if (flag == true) {

				this.reporter.SuccessReport("Tab",
						"Tab Action is Done on " + locatorName);
			}
		}
	}

	/**
	 * JS click.
	 *
	 * @param locator the locator
	 * @param locatorName the locator name
	 * @return true, if successful
	 * @throws Throwable the throwable
	 */
	public  boolean JSClick(By locator, String locatorName)
			throws Throwable {

		boolean flag = false;
		try {if(!this.reporter.getBrowserContext().getBrowserName().equalsIgnoreCase("safari")){
			//WebDriverWait wait = new WebDriverWait(this.Driver, 60);
			//ScrollToElementVisible(locator);
			//wait.until(ExpectedConditions.elementToBeClickable(locator));
			Driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
			WebElement element = this.Driver.findElement(locator);
			JavascriptExecutor executor = (JavascriptExecutor) this.Driver;
			executor.executeScript("arguments[0].click();", element);
			// driver.executeAsyncScript("arguments[0].click();", element);

			flag = true;
		} else{
			//WebDriverWait wait = new WebDriverWait(this.Driver, 60);
			//wait.until(ExpectedConditions.elementToBeClickable(locator));
			Driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
			this.Driver.findElement(locator).click();
			this.reporter.SuccessReport("Click" , this.msgClickSuccess + locatorName);
			flag = true; 
		}
		}

		catch (Exception e) {
			e.printStackTrace();

		} finally {
			if (flag == false) {
				this.reporter.failureReport("Click",
						"Click perform on" + locatorName);
				return flag;
			} else if (flag == true) {
				this.reporter.SuccessReport("Click",
						"Click is Done on" + locatorName);
				return flag;
			}
		}
		return flag;  

	}
	/**
	 * Wait for element present.
	 *
	 * @param by the by
	 * @param locator the locator
	 * @param secs the secs
	 * @return true, if successful
	 * @throws Throwable the throwable
	 */
	public boolean waitForElementPresent(By by, String locator, int secs)
			throws Throwable {
		boolean status = false;

		try {
			//WebDriverWait wait = new WebDriverWait(this.Driver, 60);
			//ScrollToElementVisible(by);
			//wait.until(ExpectedConditions.elementToBeClickable(by));
			Driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
			for (int i = 0; i < secs/2; i++)
			{
				List<WebElement> elements = this.Driver.findElements(by);
				if (elements.size()>0)
				{
					status = true;
					return status;

				} 
				else
				{
					this.Driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
				}
			}
		} 
		catch (Exception e) {

			return status;
		} 

		return status;

	}



	public boolean waitForElement(By by, String locator, int secs) throws Throwable {
		boolean status = false;

		try {
			//WebDriverWait wait = new WebDriverWait(this.Driver, 60);
			//ScrollToElementVisible(by);
			//wait.until(ExpectedConditions.presenceOfElementLocated(by));
			Driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
			for (int i = 0; i < secs/2; i++)
			{
				WebElement element = this.Driver.findElement(by);
				if (element.isDisplayed()) 
				{
					status = true;
					return status;

				} 
				else
				{
					this.Driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
				}
			}
		} 
		catch (Exception e) {

			return status;
		} 

		return status;

	}

	/**
	 * Assert text on element.
	 *
	 * @param by            : Action to be performed on element (Get it from Object
	 *            repository)
	 * @param text            : expected text to assert on the element
	 * @param locatorName            : Meaningful name to the element (Ex:link text, label text
	 *            etc..)
	 * @return true, if successful
	 * @throws Throwable the throwable
	 */
	public boolean verifyText(By by, String text, String locatorName)
			throws Throwable {
		boolean flag = false;

		try {
			//WebDriverWait wait = new WebDriverWait(this.Driver, 60);
			//ScrollToElementVisible(by);
			//wait.until(ExpectedConditions.elementToBeClickable(by));
			Driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
			String vtxt = getText(by, locatorName).trim();
			if(vtxt.equalsIgnoreCase(text.trim()))
			{
				flag = true;
			}
			else
			{
				flag = false;
			}
		} catch (Exception e) {
			return false;
		} finally {
			if (flag==false) {
				this.reporter.failureReport("VerifyText", text
						+ " is not present in the location" + locatorName);
				flag = false;
			} else if (flag==true) {
				this.reporter.SuccessReport("VerifyText", text
						+ " is present in the location " + locatorName);
				flag = true;
			}
		}
		return flag;

	}

	/**
	 * The innerText of this element.
	 *
	 * @param locator            : Action to be performed on element (Get it from Object
	 *            repository)
	 * @param locatorName            : Meaningful name to the element (Ex:label text, SignIn Link
	 *            etc..)
	 * @return the text
	 * @throws Throwable the throwable
	 * @return: String return text on element
	 */

	public String getText(By locator, String locatorName)
			throws Throwable {
		String text = "";
		boolean flag = false;
		try {
			//WebDriverWait wait = new WebDriverWait(this.Driver, 60);
			//ScrollToElementVisible(locator);
			//wait.until(ExpectedConditions.elementToBeClickable(locator));
			Driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
			if (isElementPresent(locator, locatorName,true)) {
				text = this.Driver.findElement(locator).getText();
				flag = true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (flag ==false) {
				this.reporter.warningReport("GetText", "Unable to get Text from "
						+ locatorName);
			} else if (flag == true) {
				this.reporter.SuccessReport("GetText", "Able to get Text from "
						+ locatorName);
			}
		}
		return text;
	}

	/*
	 * public static int getResponseCode(String url) { try { return
	 * Request.Get(url).execute().returnResponse().getStatusLine()
	 * .getStatusCode(); } catch (Exception e) { throw new RuntimeException(e);
	 * } }
	 */
	/**
	 * This method verify check box is checked or not.
	 *
	 * @param locator            : Action to be performed on element (Get it from Object
	 *            repository)
	 * @param locatorName            : Meaningful name to the element (Ex:sign in Checkbox etc..)
	 * @return true, if is checked
	 * @throws Throwable the throwable
	 * @return: boolean value(True: if it is checked, False: if not checked)
	 */
	public boolean isChecked(By locator, String locatorName)
			throws Throwable {
		boolean bvalue = false;
		boolean flag = false;
		try {
			//WebDriverWait wait = new WebDriverWait(this.Driver, 60);
			//ScrollToElementVisible(locator);
			//wait.until(ExpectedConditions.elementToBeClickable(locator));
			Driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
			if (this.Driver.findElement(locator).isSelected()) {
				flag = true;
				bvalue = true;
			}
		} catch (NoSuchElementException e) {

			bvalue = false;
		} finally {
			if (flag==true) {
				this.reporter.SuccessReport("IsChecked", locatorName
						+ " is Selected");
				// throw new ElementNotFoundException("", "", "");

			} else if (flag==false) {
				this.reporter.failureReport("IsChecked", locatorName
						+ " is not Select");
			}
		}
		return bvalue;
	}

	/**
	 * Verify alert present or not.
	 *
	 * @return true, if successful
	 * @throws Throwable the throwable
	 * @return: Boolean (True: If alert preset, False: If no alert)
	 */
	public boolean AlertAccept() throws Throwable {
		boolean flag = false;
		try {

			// Check the presence of alert
			org.openqa.selenium.Alert alert = this.Driver.switchTo().alert();
			// if present consume the alert
			alert.accept();
			flag = true;
		} catch (NoAlertPresentException ex) {
			// Alert present; set the flag

			// Alert not present
			ex.printStackTrace();
		} finally {
			if (flag==false) {
				this.reporter.failureReport("Alert", "There was no alert to handle");
			} else if (flag==true) {
				this.reporter.SuccessReport("Alert",
						"The Alert is handled successfully");
			}
		}

		return flag;
	}

	/**
	 * Verify alert present or not for Safari.
	 *
	 * @return true, if successful
	 * @throws Throwable the throwable
	 * @return: Boolean (True: If alert preset, False: If no alert)
	 */
	public  boolean JSAcceptAlert()
			throws Throwable {

		boolean flag = false;
		try {
			JavascriptExecutor executor = (JavascriptExecutor) this.Driver;
			executor.executeScript("confirm = function(message){return true;};");  
			executor.executeScript("alert = function(message){return true;};");  
			executor.executeScript("prompt = function(message){return true;}");
			flag = true;

		}

		catch (Exception e) {



		} finally {
			if (flag == false) {
				this.reporter.failureReport("Accept Alert",
						"Alert Accept performed " );
				return flag;
			} else if (flag == true) {
				this.reporter.SuccessReport("Accept Alert",
						"Alert Accept performed ");
				return flag;
			}
		}
		return flag;  

	}

	/**
	 * Verify alert present or not.
	 *
	 * @param filePath the file path
	 * @param locator the locator
	 * @param locatorName the locator name
	 * @return true, if successful
	 * @throws Throwable the throwable
	 * @return: Boolean (True: If alert preset, False: If no alert)
	 */
	public boolean uploadFile(String filePath, By locator, String locatorName) throws Throwable {
		boolean flag = false;
		try {
			//WebDriverWait wait = new WebDriverWait(this.Driver, 60);
			//wait.until(ExpectedConditions.elementToBeClickable(locator));
			Driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
			click(locator, locatorName);
			Thread.sleep(5000);
			Actions KeyboardEvent = new Actions(this.Driver);
			KeyboardEvent.sendKeys(filePath);
			Thread.sleep(1000);
			KeyboardEvent.sendKeys(Keys.ENTER);	

		} catch (NoAlertPresentException ex) {
			// Alert present; set the flag

			// Alert not present
			ex.printStackTrace();
		} finally {
			if (flag==false) {
				this.reporter.failureReport("Alert", "There was no alert to handle");
			} else if (flag==true) {
				this.reporter.SuccessReport("Alert",
						"The Alert is handled successfully");
			}
		}

		return flag;
	}


	/**
	 * Select a value from Dropdown using send keys.
	 *
	 * @param locator            : Action to be performed on element (Get it from Object
	 *            repository)
	 * @param value            : Value wish type in dropdown list
	 * @param locatorName            : Meaningful name to the element (Ex:Year Dropdown, items
	 *            Listbox etc..)
	 * @return true, if successful
	 * @throws Throwable the throwable
	 */
	public boolean selectBySendkeys(By locator, String value,
			String locatorName) throws Throwable {

		boolean flag = false;
		try {
			//WebDriverWait wait = new WebDriverWait(this.Driver, 60);
			//ScrollToElementVisible(locator);
			//wait.until(ExpectedConditions.elementToBeClickable(locator));
			Driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
			if(this.reporter.getBrowserContext().getBrowserName().equalsIgnoreCase("ie") || this.reporter.getBrowserContext().getBrowserName().equalsIgnoreCase("safari")){
				this.Driver.findElement(locator).click();
				Select drp = new Select(this.Driver.findElement(locator));
				drp.selectByVisibleText(value);
			}
			else{
				this.Driver.findElement(locator).sendKeys(value);	
			}
			flag = true;
			return true;
		} catch (Exception e) {

			return false;

		} finally {
			if (flag==false) {
				this.reporter.failureReport("Select", value
						+ "is Not Select from the DropDown " + locatorName);
				// throw new ElementNotFoundException("", "", "");

			} else if (flag==true) {
				this.reporter.SuccessReport("Select", value
						+ " is Selected from the DropDown " + locatorName);
			}
		}
	}

	/**
	 * select value from DropDown by using selectByIndex.
	 *
	 * @param locator            : Action to be performed on element (Get it from Object
	 *            repository)
	 * @param index            : Index of value wish to select from dropdown list.
	 * @param locatorName            : Meaningful name to the element (Ex:Year Dropdown, items
	 *            Listbox etc..)
	 * @return true, if successful
	 * @throws Throwable the throwable
	 */
	public boolean selectByIndex(By locator, int index,
			String locatorName) throws Throwable {
		boolean flag = false;
		try {
			//WebDriverWait wait = new WebDriverWait(this.Driver, 60);
			//ScrollToElementVisible(locator);
			//wait.until(ExpectedConditions.elementToBeClickable(locator));
			Driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
			Select s = new Select(this.Driver.findElement(locator));
			s.selectByIndex(index);
			flag = true;
			return true;
		} catch (Exception e) {

			return false;
		} finally {
			if (flag==false) {
				this.reporter.failureReport("Select", "Option at index " + index
						+ " is Not Select from the DropDown" + locatorName);

			} else if (flag==true) {
				this.reporter.SuccessReport("Select", "Option at index " + index
						+ "is Selected from the DropDown" + locatorName);
			}
		}
	}

	/**
	 * Assert text on element.
	 *
	 * @param by            : Action to be performed on element (Get it from Object
	 *            repository)
	 * @param text            : expected text to assert on the element
	 * @param locatorName            : Meaningful name to the element (Ex:link text, label text
	 *            etc..)
	 * @return true, if successful
	 * @throws Throwable the throwable
	 */
	public boolean verifycontainsText(By by, String text, String locatorName)
			throws Throwable {
		boolean flag = false;

		try {
			//WebDriverWait wait = new WebDriverWait(this.Driver, 60);
			//ScrollToElementVisible(by);
			//wait.until(ExpectedConditions.elementToBeClickable(by));
			Driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
			String vtxt = getText(by, locatorName).trim();
			if(vtxt.contains(text.trim()))
			{
				flag = true;
			}
			else
			{
				flag = false;
			}
		} catch (Exception e) {
			return false;
		} finally {
			if (flag==false) {
				this.reporter.failureReport("VerifyText", text
						+ " is not present in the location" + locatorName);
				flag = false;
			} else if (flag==true) {
				this.reporter.successwithscreenshot("VerifyText", text
						+ " is present in the location " + locatorName);
				flag = true;
			}
		}
		return flag;

	}

	/**
	 * Switch to.
	 *
	 * @param url the url
	 * @return true, if successful
	 * @throws Throwable the throwable
	 */
	public boolean switchTo(String url)throws Throwable {
		boolean flag = false;
		try {
			Driver.navigate().to(url);
			flag = true;
		} catch (NoAlertPresentException ex) {
			// Alert present; set the flag

			// Alert not present
			ex.printStackTrace();
		} finally {
			if (flag==false) {
				this.reporter.failureReport("Alert", "There was no alert to handle");
			} else if (flag==true) {
				this.reporter.SuccessReport("Alert",
						"The Alert is handled successfully");
			}
		}

		return flag;

	}

	public String strwithtimestamp(String str) {
		DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
		Date date = new Date();
		String strDateStamp = dateFormat.format(date);
		strDateStamp = ((strDateStamp.replace(" ", "_")).replace("/", "_"))
				.replace(":", "_");

		return str+strDateStamp;
	}

	public String RandomClass(){
		Random rnd = new Random();
		int n = 100000 + rnd.nextInt(900000);
		String str =Integer.toString(n);
		return str;
	}

	/*public void ScrollToTop(){

		   try{

		    JavascriptExecutor js = (JavascriptExecutor) this.Driver;

		    js.executeScript("window.scrollBy(0,-250)", "");

		   }catch(Exception e){

		    System.out.println("Failed to Swipe on Top on Non-Desktop device. Check 'ScrollToTop' method under CommonFunctionLib");

		   }

		}*/

	public void ScrollToPageBottom(){

		try{

			JavascriptExecutor js = (JavascriptExecutor) this.Driver;

			js.executeScript("window.scrollBy(0,1500)", "");

		}catch(Exception e){

			System.out.println("Failed to Swipe bottom on Non-Desktop device. Check 'ScrollToBottom' method under CommonFunctionLib");

		}

	}

	public boolean enter(By locator, String locatorName)
			throws Throwable {
		boolean flag = false;
		try {
			//WebDriverWait wait = new WebDriverWait(this.Driver, 60);
			//ScrollToElementVisible(locator);
			//wait.until(ExpectedConditions.elementToBeClickable(locator));
			Driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
			WebElement mo = this.Driver.findElement(locator);
			mo.sendKeys(Keys.ENTER);
			Thread.sleep(2000);
			flag = true;
			return true;
		} catch (Exception e) {

			return false;
		} finally {
			if (flag == false) {
				this.reporter.failureReport("Enter",
						"Tab action is not perform on " + locatorName,this.Driver);

			} else if (flag == true) {

				this.reporter.SuccessReport("Enter",
						"Tab Action is Done on " + locatorName);
			}
		}
	}

	public static enum Mode {
		ALPHA, ALPHANUMERIC, NUMERIC
	}

	public static String generateRandomString(int length, Mode mode){
		StringBuffer buffer = new StringBuffer();
		String characters = "";
		Boolean isNumericOnly=false;
		String automationTextPrefix="";
		switch (mode) {
		case ALPHA:
			characters = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
			break;

		case ALPHANUMERIC:
			characters = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";
			break;

		case NUMERIC:
			isNumericOnly=true;
			characters = "123456789";
			break;
		}
		int charactersLength = characters.length();
		if(length>=3&&!isNumericOnly){
			length=length-2;
			automationTextPrefix="AT";
		}
		for (int i = 0; i < length; i++) {
			double index = Math.random() * charactersLength;
			buffer.append(characters.charAt((int) index));
		}
		if (isNumericOnly) {
			return buffer.toString();
		}
		else{
			return automationTextPrefix+buffer.toString();
		}
	}

	public Integer getRandomNumberBetween(Integer upper, Integer lower){
		return (int) ((Math.random() * (upper - lower)) + lower);
	}

	public void FilterCheck(By Filter_Locator, By Grid_Locator, String Value)
			throws Throwable {
		//WebDriverWait wait = new WebDriverWait(this.Driver, 60);
		//wait.until(ExpectedConditions.elementToBeClickable(Filter_Locator));
		Driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		type(Filter_Locator, Value, "Filter");
		if(this.reporter.getBrowserContext().getBrowserName().equalsIgnoreCase("safari")){
			enter(Filter_Locator, "presss the Enter key");
			Thread.sleep(5000);
		}else{
			tab(Filter_Locator, "presss the Enter key");
			Thread.sleep(5000);
			String Str_optionset_name = getText(Grid_Locator, "Name");

			if (Value.equals(Str_optionset_name)) {
				boolean validationPoint = isElementPresent(Grid_Locator,
						"Optionset name", true);
				if (validationPoint) {
					this.reporter.successwithscreenshot(
							"User is able to verify Optionset name from grid",
							"User is able to verify Optionset name from grid",this.Driver);
				} else {
					this.reporter.failureReport(
							"User is not able to verify Optionset name from grid",
							"User is not able to verify Optionset name from grid",
							this.Driver);
				}
			}

		}
	}

	public boolean ScrollToElementVisible(By elementBy)
	{
		try{
			WebElement elem = (new WebDriverWait(this.Driver, 1)).until(ExpectedConditions.presenceOfElementLocated(elementBy));
			return ScrollToElementVisible(elem);
		}
		catch(Exception ex){
			return false;
		}
	}

	public boolean ScrollToElementVisible(WebElement element)
	{
		if(this.reporter.getBrowserContext().getBrowserPlatform().contains("windows")){
			try{
				ScrollToTop();
				Point p = element.getLocation();
				if(p.getX()==0 && p.getY()==0){
					return false; 
				}
				else{
					((JavascriptExecutor) this.Driver).executeScript("window.scroll(" + p.getX() + "," + (p.getY()-120) + ");");
					try{
						if(this.reporter.getBrowserContext().getBrowserName().contains("internet")
								||this.reporter.getBrowserContext().getBrowserName().contains("ie") || this.reporter.getBrowserContext().getBrowserName().contains("edge")){
							Thread.sleep(1000);
						}
					}
					catch(Exception e){}
					return true;
				}
			}
			catch(Exception ex){
				return false;
			}
		}
		else{
			try
			{
				Dimension d = element.getSize();
				if(d.height!=0 && d.width!=0)
				{
					new Actions(this.Driver).moveToElement(element).perform();
					return true;
				}
				else
				{
					JavascriptExecutor js = (JavascriptExecutor)this.Driver;
					int height_covered=0,pageHeightLeft=0;
					Long pageCurrentHeight=(Long)js.executeScript("return window.innerHeight");
					ScrollToTop();
					Long pageheight1=(Long)js.executeScript("return window.innerHeight");
					Long maxPageHeight1=(Long)js.executeScript("return Math.max(document.documentElement.scrollHeight, document.body.scrollHeight," +
							"document.documentElement.clientHeight, window.innerHeight)");
					float sections=(float)maxPageHeight1/pageheight1;
					int numberOfRows=(int)Math.ceil(sections);
					int pageheight=pageheight1.intValue();
					int maxPageHeight=maxPageHeight1.intValue();
					for (int row=0; row<numberOfRows ; row++) {
						pageHeightLeft=maxPageHeight-height_covered;
						if((pageHeightLeft < pageheight)){
							d = element.getSize();
							if(d.height==0 && d.width==0){
								js.executeScript("window.scrollTo(0,"+pageCurrentHeight+")");
								return false;
							}
							else{
								new Actions(this.Driver).moveToElement(element).perform();
								return true;
							}
						}
						else{
							d =element.getSize();
							if(!(d.height!=0 && d.width!=0))
							{
								height_covered=height_covered+pageheight;
								js.executeScript("window.scrollTo(0,"+height_covered+")");
								try{Thread.sleep(100);}catch(InterruptedException e){}
							}
							else{
								new Actions(this.Driver).moveToElement(element).perform();
								return true;
							}
						}
					}
					ScrollToTop();
					return false;
				}
			}
			catch(org.openqa.selenium.ElementNotVisibleException e){
				return false;
			}
			catch(Exception ex){
				return false;
			}
		}
	}

	public void ScrollToTop(){
		if(this.reporter.getBrowserContext().getBrowserPlatform().contains("windows")){
			try{
				JavascriptExecutor js = (JavascriptExecutor)this.Driver;
				js.executeScript("window.scrollTo(0,0);");
			}catch(Exception e){  }
		}
		else{
			try{
				JavascriptExecutor js = (JavascriptExecutor) this.Driver;
				js.executeScript("$('body').scrollTop(0);");
			}catch(Exception e){ 
				System.out.println("Failed to Swipe on Top on Non-Desktop device. Check 'ScrollToTop' method under CommonFunctionLib");
			}
		}
	}

	public void ScrollToBottom(){
		if(this.reporter.getBrowserContext().getBrowserPlatform().contains("windows")){
			try{
				JavascriptExecutor js = (JavascriptExecutor)this.Driver;
				js.executeScript("window.scrollTo(0,document.documentElement.scrollHeight);");
			}catch(Exception e){  }
		}
		else{
			JavascriptExecutor js = (JavascriptExecutor)this.Driver;
			int height_covered=0,pageHeightLeft=0;
			Long pageheight1=(Long)js.executeScript("return window.innerHeight");
			Long maxPageHeight1=(Long)js.executeScript("return Math.max(document.documentElement.scrollHeight, document.body.scrollHeight," +
					"document.documentElement.clientHeight, window.innerHeight)");
			float sections=(float)maxPageHeight1/pageheight1;
			int numberOfRows=(int)Math.ceil(sections);
			int pageheight=pageheight1.intValue();
			int maxPageHeight=maxPageHeight1.intValue();
			for (int row=0; row<numberOfRows ; row++) {
				pageHeightLeft=maxPageHeight-height_covered;
				if((pageHeightLeft < pageheight)){
				}
				else{
					height_covered=height_covered+pageheight;
					js.executeScript("window.scrollTo(0,"+height_covered+")");
				}
			}
		}
	}

	public boolean javascriptSendKeys (String value,WebElement webElement, String locatorName) throws Throwable
	{
		boolean state = false;
		try
		{
			//WebDriverWait wait = new WebDriverWait(this.Driver, 60);
			//ScrollToElementVisible(webElement);
			//wait.until(ExpectedConditions.elementToBeClickable(webElement));
			Driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
			JavascriptExecutor js = (JavascriptExecutor) (this.Driver);
			js.executeScript("arguments[0].value=\""+value+"\";", webElement);
			this.reporter.SuccessReport("type" , this.msgTypeSuccess + locatorName);
			state=true;
		}
		catch(Exception e){
			state = false;
			e.printStackTrace();
			LOG.info(e.getMessage());
			this.reporter.failureReport("type", this.msgTypeFailure + locatorName, this.Driver);
		}
		return state;
	}

	public void selectListValueEdge(By elementBy,String selectionValue)
	{
		try{
			WebElement webElement = this.Driver.findElement(elementBy);
			webElement.click();
			webElement.sendKeys(selectionValue);
			webElement.sendKeys(Keys.ENTER);
		}
		catch(Exception ex){
			ex.printStackTrace();
		}
	}
	public boolean browseAndUpload(String filePath, By locator, String locatorName) throws Throwable {
		boolean flag = false;
		try {
			//enter(locator,locatorName);
			click(locator, locatorName);
			Thread.sleep(4000);

			//Copy the file's absolute path to the clipboard
			StringSelection ss = new StringSelection(filePath);
			Toolkit.getDefaultToolkit().getSystemClipboard().setContents(ss, null);

			//native key strokes for CTRL, V and ENTER keys
			Robot robot = new Robot();
			Thread.sleep(2000);
			robot.keyPress(KeyEvent.VK_CONTROL);
			Thread.sleep(2000);
			robot.keyPress(KeyEvent.VK_V);
			Thread.sleep(2000);
			robot.keyRelease(KeyEvent.VK_V);
			Thread.sleep(2000);
			robot.keyRelease(KeyEvent.VK_CONTROL);
			Thread.sleep(2000);
			robot.keyPress(KeyEvent.VK_ENTER);
			Thread.sleep(2000);
			robot.keyRelease(KeyEvent.VK_ENTER);
			Thread.sleep(2000);
			flag= true;

		} catch (NoAlertPresentException ex) {
			// Alert present; set the flag

			// Alert not present
			ex.printStackTrace();
		} finally {
			if (flag==false) {
				this.reporter.failureReport("Alert", "There was no alert to handle");
			} else if (flag==true) {
				this.reporter.SuccessReport("Alert",
						"The Alert is handled successfully");
			}
		}

		return flag;
	}

	public boolean VerifyNameInTable(By tblLocator,String Name) throws Throwable{
		boolean flag = false;

		try{

			WebElement tbl=this.Driver.findElement(tblLocator);

			List<WebElement> trows=tbl.findElements(By.tagName("tr"));
			for(int itRow=0; itRow<trows.size();itRow++)
			{

				List<WebElement> tcols=trows.get(itRow).findElements(By.tagName("td"));
				for(int itCol=0;itCol<tcols.size();itCol++)
				{
					WebElement cell=tcols.get(itCol);


					if (Name.equalsIgnoreCase(cell.getText()))
					{
						System.out.println(cell.getText());
						flag=true;


					}
				}
			}


			flag= true;
			return flag;
		}
		catch (Exception e)
		{

			return false;

		} finally {
			if (flag==false) {
				this.reporter.failureReport("Not created ", Name
						+ "  In the list ");
				// throw new ElementNotFoundException("", "", "");

			} else if (flag==true) {
				this.reporter.SuccessReport("Created ", Name
						+ "   successfully In the list " );
			}
		}
	}
	public boolean webDriverSendKeys (WebElement element, String value)
	{
		boolean state = false;
		try
		{
			element.clear();
			element.sendKeys(value);
			state=true;
		}
		catch(Exception ex){
			state = false;
		}
		return state;
	}
	public int RecordCountInTableNoofPages(By tblLocator,By pages,By pagenotxt,String Name) throws Throwable{
		int HeaderCount=0;
		int PagerCount=0;
		int recordCount=0;
		String vtxt = this.Driver.findElement(pages).getText();
		String mxtx = vtxt.substring(vtxt.lastIndexOf(' ')).trim();
		int pageno = Integer.parseInt(mxtx);
		recordCount = (pageno - 1)*20;
		this.Driver.findElement(pagenotxt).clear();
		this.Driver.findElement(pagenotxt).sendKeys(""+pageno);
		enter(pagenotxt,"pageno text box");

		try{

			WebElement tbl=this.Driver.findElement(tblLocator);
			List<WebElement> trows=tbl.findElements(By.tagName("tr"));
			for(WebElement row: trows){

				if(row.getAttribute("class").contains("EnhancedDataGridItem") || row.getAttribute("class").contains("EnhancedDataGridItemAlt"))

				{
					recordCount=recordCount+1;
				}
			}
		}
		catch (Exception e)
		{


		}
		return recordCount;
	}

	public int RecordCountInTable(By tblLocator,String Name) throws Throwable{
		int HeaderCount=0;
		int PagerCount=0;
		int recordCount=0; 
		try{

			WebElement tbl=this.Driver.findElement(tblLocator);
			List<WebElement> trows=tbl.findElements(By.tagName("tr"));
			for(WebElement row: trows){

				if(row.getAttribute("class").contains("EnhancedDataGridItem") || row.getAttribute("class").contains("EnhancedDataGridItemAlt"))

				{
					recordCount=recordCount+1;
				}
			}
		}
		catch (Exception e)
		{


		}
		return recordCount;
	}

	public void dynamicWait(final By by, String sleepTime) throws Throwable{
		try{
			for(int i=1;i<=20;i++){
				if(isElementPresentfordynamic(by,true)){
					ScrollToElementVisible(by);
					break;
				}else{
					Thread.sleep(Long.parseLong(sleepTime));
				}
			}
		}catch(Exception e){
			e.printStackTrace();
		}
	}

	public boolean isElementPresentfordynamic(By by,boolean expected) throws Throwable
	{
		boolean status = false;
		try
		{
			WebDriverWait wait = new WebDriverWait(this.Driver, 30);
			ScrollToElementVisible(by);
			wait.until(ExpectedConditions.elementToBeClickable(by));
			this.Driver.findElement(by);
			status = true;
		}
		catch(Exception e)
		{
			status = false;
			LOG.info(e.getMessage());

		}
		return status;
	}

	public boolean selectByVisibleText(By locator, String value, String locatorName)
			throws Throwable {

		boolean flag = false;
		try {
			ScrollToElementVisible(locator);
			WebElement dropDownListBox = Driver.findElement(locator);
			Select clickThis = new Select(dropDownListBox);
			clickThis.selectByVisibleText(value);
			flag = true;
			return true;
		} catch (Exception e) {

			return false;
		} finally {
			if (flag == false) {
				this.reporter.failureReport("Select", value
						+ "is Not Select from the DropDown " + locatorName);
				// throw new ElementNotFoundException("", "", "");

			} else if (flag == true) {
				this.reporter.SuccessReport("Select", value
						+ " is Selected from the DropDown " + locatorName);
			}
		}
	}

	public void ScrollTreeTop(){

		try{

			JavascriptExecutor js = (JavascriptExecutor) this.Driver;

			js.executeScript("window.scrollBy(0,-1000)", "");

		}catch(Exception e){

			System.out.println("Failed to Swipe on Top on Non-Desktop device. Check 'ScrollToTop' method under CommonFunctionLib");

		}
	}

	public String getMonthNumericValue(String monthCharValue){
		switch(monthCharValue.trim().toUpperCase()){
		case "JAN":
		case "JANUARY":
			return "01";
		case "FEB":
		case "FEBRUARY":
			return "02";
		case "MAR":
		case "MARCH":
			return "03";
		case "APR":
		case "APRIL":
			return "04";
		case "MAY":
			return "05";
		case "JUN":
		case "JUNE":
			return "06";
		case "JULY":
		case "JUL":
			return "07";
		case "AUGUST":
		case "AUG":
			return "08";
		case "SEP":
		case "SEPTEMBER":
			return "09";
		case "OCT":
		case "OCTOBER":
			return "10";
		case "NOV":
		case "NOVEMBER":
			return "11";
		case "DECEMBER":
		case "DEC":
			return "12";
		default:
			return "";
		}
	}

	public String getMonthName(String monthNumericValue){
		switch(monthNumericValue.trim()){
		case "01":
			return "January";
		case "02":
			return "February";
		case "03":
			return "March";
		case "04":
			return "April";
		case "05":
			return "May";
		case "06":
			return "June";
		case "07":
			return "July";
		case "08":
			return "August";
		case "09":
			return "September";
		case "10":
			return "October";
		case "11":
			return "November";
		case "12":
			return "December";
		default:
			return "";
		}
	}

	public String getCurrentDataTimeinFormat(String dateformat){
		Calendar cal = Calendar.getInstance();
		SimpleDateFormat sdf = new SimpleDateFormat(dateformat);
		try {
			return sdf.format(cal.getTime());
		} catch (Exception e) {
			return "";
		}
	}

	public Date getDateTimeFromProvidedString(String dateValue, String format){
		SimpleDateFormat formatter = new SimpleDateFormat(format);
		try {
			Date date = formatter.parse(dateValue);
			return date;
		} catch (Exception e) {
			return null;
		}
	}

	public String getDateTimeFromProvidedString(Date date, String dateformat){
		SimpleDateFormat sdf = new SimpleDateFormat(dateformat);
		try {
			return sdf.format(date);
		} catch (Exception e) {
			return "";
		}
	}

	public static String getCurrentTimeZone(){
		String timeZone=null;
		//get Calendar instance
		Calendar now = Calendar.getInstance();
		//get current TimeZone using getTimeZone method of Calendar class
		TimeZone tZone = now.getTimeZone();
		//display current TimeZone using getDisplayName() method of TimeZone class
		timeZone=tZone.getDisplayName();
		return timeZone;
	}

	public String AddNoOfDaysInServerTime(String format, int AddNoOfDays)
	{
		String Timezone = getCurrentTimeZone();
		SimpleDateFormat sdf = new SimpleDateFormat(format);
		sdf.setTimeZone(TimeZone.getTimeZone(Timezone));
		Calendar c = Calendar.getInstance();
		c.add(Calendar.DATE, AddNoOfDays);
		String fdate = sdf.format(c.getTime());
		return fdate;
	}

	public String ChangeStringFirstCharToCapital(String str)
	{
		StringBuilder b = new StringBuilder(str);
		int i = 0;
		do 
		{
			b.replace(i, i + 1, b.substring(i,i + 1).toUpperCase());
			i =  b.indexOf(" ", i) + 1;
		} 
		while (i > 0 && i < b.length());
		return b.toString();
	}

	public String getFirstDayOfCurrentWeek(String format) throws ParseException {
		String fdate;
		Calendar cal = Calendar.getInstance();
		SimpleDateFormat sdf = new SimpleDateFormat(format);
		cal.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
		fdate = sdf.format(cal.getTime());
		Date date1 = sdf.parse(fdate);
		Date date = new Date();
		SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy/MM/dd HH:mm");
		Date date2 = sdf2.parse(sdf2.format(date));
		if(date1.after(date2))
		{
			Calendar cal1 = Calendar.getInstance();
			SimpleDateFormat sdf1 = new SimpleDateFormat(format);
			cal1.add(Calendar.DAY_OF_WEEK, -6);
			fdate = sdf1.format(cal1.getTime());
		}
		return fdate;
	}

	public String getLastDayOfCurrentWeek(String format) {
		String ldate;
		Calendar cal = Calendar.getInstance();
		SimpleDateFormat sdf = new SimpleDateFormat(format);

		cal.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
		cal.add(Calendar.DAY_OF_WEEK, 6);
		ldate = sdf.format(cal.getTime());
		return ldate;
	}

	public String getLastDayOfMonth(String format) {
		Calendar cal = Calendar.getInstance();
		SimpleDateFormat sdf = new SimpleDateFormat(format);

		cal.set(Calendar.DATE, cal.getActualMaximum(Calendar.DATE));
		String ldate = sdf.format(cal.getTime());
		return ldate;
	}

	public String getLastDayOfMonth(int month, int year) {
		Calendar calendar = Calendar.getInstance();
		// passing month-1 because 0-->jan, 1-->feb... 11-->dec
		calendar.set(year, month - 1, 1);
		calendar.set(Calendar.DATE, calendar.getActualMaximum(Calendar.DATE));
		Date date = calendar.getTime();
		DateFormat DATE_FORMAT = new SimpleDateFormat("dd");
		SimpleDateFormat sdf = new SimpleDateFormat("dd");
		sdf.format(date);
		DATE_FORMAT.format(date);
		return DATE_FORMAT.format(date);
	}

	public boolean GetResponseCode(String URL){
		boolean passflag = true;
		try{			
			URL url = new URL(URL);
			HttpURLConnection connection = (HttpURLConnection)url.openConnection();
			connection.setRequestMethod("GET");
			connection.setDoInput(true);
			connection.setRequestProperty("Authorization", "Basic " + "RGV2UHJldmlldzpFcXVpbm94MSE="); 
			connection.connect();
			int code = connection.getResponseCode();
			if(HttpURLConnection.HTTP_OK==code && code!=200){
				passflag = false;
			}
		}catch (Exception e) {
			e.printStackTrace();
			passflag = false;
		} 
		return passflag;
	}

	public String getDateInExpectedFormat(String format,String dateInString, String returnformat)
	{
		SimpleDateFormat formatter = new SimpleDateFormat(format);
		SimpleDateFormat returnFormatter = new SimpleDateFormat(returnformat);
		Date date = null;
		try 
		{
			date = formatter.parse(dateInString);
			//			formatter = new SimpleDateFormat(returnformat);
			return returnFormatter.format(date);
		} 
		catch (ParseException e) 
		{
			e.printStackTrace();
			return null;
		}

	}

	public int getTotalNoofdaysinMonth()
	{
		int CurrentMonthDays=0;
		try
		{
			Calendar c = Calendar.getInstance();
			int monthMaxDays = c.getActualMaximum(Calendar.DAY_OF_MONTH);
			CurrentMonthDays=monthMaxDays;
		}
		catch(Exception e)
		{
			e.getMessage();
		}
		return CurrentMonthDays;
	}

	public int calculateAge(int year, int month, int day) // Enter Date od Birth //
	{
		try{
			LocalDate birthdate = new LocalDate (year, month, day);
			LocalDate now = new LocalDate();
			Years ageInYear = Years.yearsBetween(birthdate, now);
			return ageInYear.getYears();
		}
		catch(Exception ex){
			throw ex;
		}
	}

	public int calculateAge(String dob, String format) throws Exception// Enter Date od Birth //
	{
		try{
			Date dob_DateObj=  getDateTimeFromProvidedString(dob,format);
			long ageInMillis = new Date().getTime() - dob_DateObj.getTime();
			return new Date(ageInMillis).getYear();
		}
		catch(IllegalArgumentException ex){
			LOG.info("Failed to get Age from provided format ("+format+") and date value ("+dob+")");
			throw ex;
		}
		catch(NullPointerException ex){
			LOG.info("Failed to get Age from provided format ("+format+") and dob value ("+dob+")");
			throw ex;
		}
	}

	public String getCurrentMonth()
	{
		String CurrentMonth="";
		try{
			SimpleDateFormat sdf= new SimpleDateFormat("MMMM");
			Calendar cal = Calendar.getInstance();
			CurrentMonth=sdf.format(cal.getTime());
			System.out.println("Current Month :"+CurrentMonth);
		}
		catch(Exception e)
		{
			e.getStackTrace();
		}
		return CurrentMonth;
	}

	public String getNextMonth()
	{
		String NextMonth="";
		try{
			SimpleDateFormat sdf= new SimpleDateFormat("MMMM");
			Calendar cal = Calendar.getInstance();
			cal.add(Calendar.MONTH, 1);
			cal.set(Calendar.DAY_OF_MONTH, cal.getActualMaximum(Calendar.DAY_OF_MONTH));
			NextMonth=sdf.format(cal.getTime());
			System.out.println("Next Month :"+NextMonth);
		}
		catch(Exception e)
		{
			e.getStackTrace();
		}
		return NextMonth;
	}

	public String getMainWindowHandle(WebDriver driver) {
		return driver.getWindowHandle();
	}

	public static boolean closeAllOtherWindows(WebDriver driver, String openWindowHandle) {
		Set<String> allWindowHandles = driver.getWindowHandles();
		for (String currentWindowHandle : allWindowHandles) {
			if (!currentWindowHandle.equals(openWindowHandle)) {
				driver.switchTo().window(currentWindowHandle);
				driver.close();
			}
		}

		driver.switchTo().window(openWindowHandle);
		if (driver.getWindowHandles().size() == 1)
			return true;
		else
			return false;
	}

	public boolean waitForText(By by, String locator, int secs)
			throws Throwable {
		boolean status = false;

		try {
			WebDriverWait wait = new WebDriverWait(this.Driver, 60);
			ScrollToElementVisible(by);
			wait.until(ExpectedConditions.textToBePresentInElementLocated(by, locator));
			for (int i = 0; i < secs/2; i++)
			{
				List<WebElement> elements = this.Driver.findElements(by);
				if (elements.size()>0)
				{
					status = true;
					return status;

				} 
				else
				{
					this.Driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
				}
			}


		} 
		catch (Exception e) {

			return status;
		} 

		return status;

	}
	public static String windowHandles(WebDriver driver)
	{  
		String strhwndval=null;
		Set<String> handleVal_Coll=driver.getWindowHandles();
		Iterator<String> IT_hwndColl=handleVal_Coll.iterator();
		while(IT_hwndColl.hasNext()==true)
		{
			strhwndval=IT_hwndColl.next();
			driver.switchTo().window(strhwndval);
		}
		return strhwndval;
	}

	/**
	 * @description : Switch In to iFrame
	 * @param iFrameID
	 * @return
	 */
	public boolean SwitchIntoFrame(WebDriver driver,WebElement iFrameID)
	{
		try
		{
			driver.switchTo().frame(iFrameID);
			System.out.println("switch to frame sucecssfully.");
			return true;
		}
		catch(Exception e)
		{
			System.out.println("Failed to switch to frame due to error :"+e.getMessage());
			return false;
		}
	}
	public void ScrollToElement(By by){

		try{

			WebElement element = Driver.findElement(by);
			Coordinates coordinate = ((Locatable)element).getCoordinates(); 
			coordinate.onPage(); 
			coordinate.inViewPort();
			Thread.sleep(4000);
		}catch(Exception e){

			System.out.println("Failed to Swipe bottom on Non-Desktop device. Check 'ScrollToBottom' method under CommonFunctionLib");

		}

	}

	/**
	 * @return
	 */
	public WebDriver SwitchOutfromFrame(WebDriver driver)
	{
		return driver.switchTo().defaultContent();

	}

	public void robotMouseOver(By by) throws Exception{
		try{
			Point point = this.Driver.findElement(by).getLocation(); 
			int xcord = point.getX(); 
			int ycord = point.getY(); 
			Robot robot = new Robot();  
			robot.mouseMove(xcord+30, ycord+100); // move mouse point to specific location 
			robot.delay(1500);        // delay is to make code wait for mentioned milliseconds before executing next step   
			/*robot.mousePress(InputEvent.BUTTON1_DOWN_MASK); // press left click 
			robot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK); // release left click 
			robot.delay(1500);  
			robot.keyPress(KeyEvent.VK_DOWN); // press keyboard arrow key to select Save radio button   
			Thread.sleep(2000); 
			robot.keyPress(KeyEvent.VK_ENTER);
			Thread.sleep(3000);*/
		}catch(Exception e){
			throw e;
		}
	}

	public void setFocusByXpath(String xpath) {
		try {
			JavascriptExecutor js = (JavascriptExecutor) (this.Driver);
			js.executeScript(
					"$(document.evaluate(\"" + xpath + "\", document, null, 9, null).singleNodeValue).focusin();");
		} catch (NullPointerException ex) {
			try {
				JavascriptExecutor js = (JavascriptExecutor) (this.Driver);
				js.executeScript(
						"$(document.evaluate(\"" + xpath + "\", document, null, 9, null).singleNodeValue).focusin();");
			} catch (Exception e) {

			}
		}
	}

}