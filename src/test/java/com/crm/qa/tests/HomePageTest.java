package com.crm.qa.tests;

// Follows Page Object Model - RN
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.crm.qa.base.TestBase;
import com.crm.qa.pages.HomePage;
import com.crm.qa.pages.LoginPage;
import com.crm.qa.util.CheckPageFor;
import com.qa.ExtentReport.Listener.ExtentTestManager;

public class HomePageTest extends TestBase {

	LoginPage loginPage;
	HomePage homePage;

	public HomePageTest() {
		super();

	}

	@BeforeMethod
	public void setup() {
		initialize();
		loginPage = new LoginPage();
		homePage = loginPage.login(prop.getProperty("username"), prop.getProperty("password"));
		// CheckPageFor.BrokenLinks();
	}

	@Test(priority = 1)
	public void verifyHomePageTitle() {
		ExtentTestManager.startTest(Thread.currentThread().getStackTrace()[1].getMethodName(), "Verify the tile of Home page");
		Assert.assertEquals("Cogmento CRM", homePage.getHomePageTitle());
	
	}

	@Test(priority = 2)
	public void verifyHomePageUserName() {
		ExtentTestManager.startTest(Thread.currentThread().getStackTrace()[1].getMethodName(), "verify the tile of user name in Home Page");
		Assert.assertTrue(homePage.verifyUserDisplayed("Rahul Nair"));
	}

	@AfterMethod
	public void tearDown() {
		driver.quit();
	}

}
