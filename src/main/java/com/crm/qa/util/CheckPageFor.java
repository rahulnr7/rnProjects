package com.crm.qa.util;

import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Iterator;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import com.crm.qa.base.TestBase;

public class CheckPageFor extends  TestBase{
	
	public  static void  BrokenLinks(){
		
		HttpURLConnection huc = null;
		int respCode = 200;
		String url = null;
		
		
		List<WebElement> links = driver.findElements(By.tagName("a"));
	    
		Iterator<WebElement> itr = links.iterator();
		
		while(itr.hasNext()){
			url = itr.next().getAttribute("href");
			
			if(url==null){
				
				System.out.println("Url is empty");
				continue;
			}
			
			try{
				
				huc =  (HttpURLConnection) (new URL(url).openConnection());
				huc.setRequestMethod("HEAD");
				huc.connect();
				
				respCode = huc.getResponseCode();
				
				if(respCode >=400){
					
					System.out.println(url + "  is broken! HTTP Resonse code : " +respCode);
					
				}else {
					System.out.println(url + " is valid. HTTP code : " +respCode);
				}
			}catch (Exception e){
				e.printStackTrace();							
			}
			
		}
		
		
	}

}
