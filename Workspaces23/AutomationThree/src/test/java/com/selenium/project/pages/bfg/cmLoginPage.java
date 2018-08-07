package com.selenium.project.pages.bfg;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.automation.accelerators.ActionEngine;
import com.automation.report.CReporter;

public class cmLoginPage extends ActionEngine{

	public By usernameText=By.id("UserNameTextBox");
	public By passwordText=By.id("PasswordTextBox");
	public By loginButton=By.id("LoginButton");
	
	
	public cmLoginPage(WebDriver Driver, CReporter reporter){
		SuperClass(Driver, reporter);
	}
	
	public void login(String username, String password){
		try{
			
			type(usernameText, username, "Username Text");
			type(passwordText, password, "Password Text");
			click(loginButton, "Login Button");
		}catch(Throwable e){
			e.printStackTrace();
		}
		
	}
	
	
	
	public String verifyLoginErrors(){
		String errors= new String();
		
		try{
			errors= getText(By.id("InvalidLoginLabel"), "Error Locator");
			return errors;
		}catch(Throwable e){
			e.printStackTrace();
			
		}
		return errors;
	}
	
	
	
}
