package com.qa.listeners;

import com.crm.qa.base.TestBase;
import com.qa.ExtentReport.Listener.ExtentTestManager;
import com.relevantcodes.extentreports.LogStatus;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;
 
public class Retry extends TestBase implements IRetryAnalyzer {
 
    private int count = 0; 
    private static int maxTry = Integer.parseInt(prop.getProperty("maxRetryCount")); //Run the failed test the count +1 times
//    private static int maxTry =1;
    
    @Override
    public boolean retry(ITestResult iTestResult) {
        if (!iTestResult.isSuccess()) {                      //Check if test not succeed
            if (count < maxTry) {                            //Check if maxTry count is reached
                count++;                                     //Increase the maxTry count by 1
                iTestResult.setStatus(ITestResult.FAILURE);  //Mark test as failed and take base64Screenshot
                extendReportsFailOperations(iTestResult);    //ExtentReports fail operations
                return true;                                 //Tells TestNG to re-run the test
            }
        }
        else {
            iTestResult.setStatus(ITestResult.SUCCESS);      //If test passes, TestNG marks it as passed
        }
        return false;
    }
 
    public void extendReportsFailOperations(ITestResult iTestResult) {
        Object testClass = iTestResult.getInstance();
       
        String base64Screenshot = "data:image/png;base64," + ((TakesScreenshot) driver).getScreenshotAs(OutputType.BASE64);
        ExtentTestManager.getTest().log(LogStatus.FAIL, "Test Failed",
            ExtentTestManager.getTest().addBase64ScreenShot(base64Screenshot));
    }
}