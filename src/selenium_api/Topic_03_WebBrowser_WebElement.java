package selenium_api;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Dimension;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_03_WebBrowser_WebElement {
    WebDriver driver;

	@BeforeClass
	public void beforeClass() {
		
	}

	@Test
	public void TC_01_WebBrowser() {
		
		/*----------WEB BROWSER----------*/
		
		//Đóng tab mà nó đang active
		driver.close();
		
		//Đóng browser
		driver.quit(); /**/
		
		//Open URL
		driver.get("URL"); /**/
		
		//Get ra title của page hiện tại
		String loginPageURL = driver.getCurrentUrl(); /**/
		
		//get ra source code của page hiện tại
		String homePageSource = driver.getPageSource();
		
		//Dùng kiểm tra 1 cách tương đối - performance chạy nhanh
		
		Assert.assertTrue(homePageSource.contains("SELENIUM WEBDRIVER FORM DEMO"));
		
		//get ra title của page hiện tại 
		String homepageTitle = driver.getTitle(); /**/
		Assert.assertEquals(homepageTitle, "SELENIUM WEBDRIVER FORM DEMO");
		
		//Get ra windows id của page hiện tại (GUID - Uniqe)
		
		String homePageID = driver.getWindowHandle(); /**/
		
		//Get ra tất cả window ID của tất cả các tab hiện tại
		driver.getWindowHandles();
		
		//Wait cho element (findelement/findelements)
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS); /**/
		
		//Wait cho page dc load thành công
		driver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);
		
		//Set timeout cho script
		driver.manage().timeouts().setScriptTimeout(30, TimeUnit.SECONDS);
		
		//F11
		driver.manage().window().fullscreen();
		
		// phóng to browser ra hết cỡ
		driver.manage().window().maximize(); /**/
		
		driver.manage().window().setSize(new Dimension(1024,768));
		driver.manage().window().setPosition(new Point(300,300));
		
		Dimension initial_size = driver.manage().window().getSize();
		int heigh = initial_size.getHeight();
		int width = initial_size.getWidth();
		
		driver.navigate().back();
		driver.navigate().forward();
		driver.navigate().refresh();
		
		//Tracking history (back/forward)
		driver.navigate().to("URL");
		
		
		driver.switchTo().alert(); /**/
		
		driver.switchTo().frame(""); /**/
		driver.switchTo().defaultContent();
		driver.switchTo().window("GUID"); /**/
				 
	}
	
	@Test
	
	public void TC_02_WebElement() {
		
	}

	@AfterClass
	public void afterClass() {
		driver.quit();
	}

}