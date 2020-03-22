package com.crm.qa.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import com.crm.qa.base.TestBase;

public class HomePage extends TestBase {
	
	//Object repo
	@FindBy (xpath="//span[@class='user-display']")
	WebElement userDisplayed;
	
	@FindBy(xpath ="//a[@class='item']/span[contains(text(),'Contacts')]")
	WebElement contactsLink;
	
	@FindBy(xpath="//a[@class='item']/span[contains(text(),'Deals')]")
	WebElement dealsLink;
	
	public HomePage(){
		PageFactory.initElements(driver, this);
	}
	
	//Actions
	public String getHomePageTitle(){
		return driver.getTitle();
	}
	
	public Boolean verifyUserDisplayed(String un){
		Boolean flag = false;
		if (un.equalsIgnoreCase(userDisplayed.getText())){
			flag = true;
		}
		
		return flag;
		
	}
	
	public ContactsPage clickOnContacts(){
		contactsLink.click();
		return new ContactsPage();
	}
	
	public DealsPage  clickOnDeals(){
		dealsLink.click();
		return new DealsPage();
	}

}
