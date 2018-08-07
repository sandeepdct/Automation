package com.selenium.project.pages.bfg;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.automation.accelerators.ActionEngine;
import com.automation.report.CReporter;

public class dbsHomePage extends ActionEngine {
	
	public By searchBtn=By.xpath(".//*[@class='icons-search']");
	public By searchTxt = By.xpath(".//*[@id='search']");
	public By submitBtn = By.xpath("//*[@id='hsearchbutton']");
	
	public dbsHomePage(WebDriver Driver, CReporter reporter){
		SuperClass(Driver,reporter);
	}
	
	public  void search(String SearchText){
		try {
		
			click(searchBtn, "Search Btn");
			type(searchTxt, SearchText, "enter search");
			click(submitBtn, "Search Btn");
		} catch (Throwable e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
