package com.crm.qa.base;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import com.crm.qa.util.Constants;

public class TestBase {
	
	public static Properties prop;
	public static WebDriver driver;
	public TestBase(){
		
		try{
			prop = new Properties();
			File file = new File(System.getProperty("user.dir")+"\\src\\main\\java\\com\\crm\\qa\\config\\config.properties");
		//	File file = new File("/Users/Rahul/workspace/CRMTest/src/main/java/com/crm/qa/config/config.properties");
			FileInputStream ip = new FileInputStream(file);
			prop.load(ip);
		}catch (FileNotFoundException e){
			e.printStackTrace();
		}catch(IOException e){
			e.printStackTrace();
		}
	}
	
	public static void initialize(){
		
		
		String jenkinsBrowser = System.getProperty("browser");
		if(jenkinsBrowser!=null){
			System.out.println("Jenkins Browser choice :"+jenkinsBrowser);
		}
		else System.out.println("Jenkins Browser choice not made - RN");
		String browserName = prop.getProperty("browser");
		if("chrome".equalsIgnoreCase(browserName)){
			System.setProperty("webdriver.chrome.driver",System.getProperty("user.dir")+ "\\src\\test\\resources\\chromedriver.exe");
			driver = new ChromeDriver();
		}if("FireFox".equalsIgnoreCase(browserName)){
			System.setProperty("webdriver.gecko.driver","D:/Softwares/geckodriver-v0.16.0-win32/geckodriver.exe" );
			driver = new FirefoxDriver();
		}
		
		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();
		driver.manage().timeouts().pageLoadTimeout(Constants.PAGE_LOAD_TIMEOUT, TimeUnit.SECONDS);
		driver.manage().timeouts().implicitlyWait(Constants.IMPLICIT_WAIT,TimeUnit.SECONDS);
		driver.get(prop.getProperty("url"));
		
	}

}
 