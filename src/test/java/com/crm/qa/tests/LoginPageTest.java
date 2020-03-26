package com.crm.qa.tests;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.crm.qa.base.TestBase;
import com.crm.qa.pages.HomePage;
import com.crm.qa.pages.LoginPage;
import com.crm.qa.util.CheckPageFor;

public class LoginPageTest extends TestBase {
	
	LoginPage loginPage;
	HomePage homePage;
	
	public  LoginPageTest(){
		super();
	}

	@BeforeMethod
	public void setUp(){
		initialize();
		 loginPage = new LoginPage();	
	     CheckPageFor.BrokenLinks();
		
	}
	
	
	@Test(priority = 1)
	public void loginPageTitleTest(){ 
		
		String title = loginPage.validatePageTitle();
		Assert.assertTrue(title.equals("Cogmento CRM"));
	}
	
	@Test(priority = 2)
	public void loginTest(){
		homePage = loginPage.login(prop.getProperty("username"), prop.getProperty("password"));
		Assert.assertEquals("Cogmento CRM", homePage.getHomePageTitle());
	}
	
	@AfterMethod
	public void tearDown(){
		driver.quit();
	}
	

}
