package com.crm.qa.tests;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.crm.qa.base.TestBase;
import com.crm.qa.pages.HomePage;
import com.crm.qa.pages.LoginPage;
import com.crm.qa.util.CheckPageFor;
import com.qa.ExtentReport.Listener.ExtentTestManager;

public class LoginPageTest extends TestBase {
	
	LoginPage loginPage;
	HomePage homePage;
	//Constructor Added
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
		
		ExtentTestManager.startTest(Thread.currentThread().getStackTrace()[1].getMethodName(), "Test the tile of login page");		
		String title = loginPage.validatePageTitle();
		Assert.assertTrue(title.equals("Cogmento CRM"));
	}
	
	@Test(priority = 2)
	public void loginTest(){
		ExtentTestManager.startTest(Thread.currentThread().getStackTrace()[1].getMethodName(), "Test Login");
		homePage = loginPage.login(prop.getProperty("username"), prop.getProperty("password"));
		Assert.assertEquals("Cogmento CRM", homePage.getHomePageTitle());
	}
	
	@AfterMethod
	public void tearDown(){
		driver.quit();
	}
	
}
