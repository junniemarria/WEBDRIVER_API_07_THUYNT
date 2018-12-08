package selenium_api;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_05_HandleDropdownList {
	WebDriver driver;
	WebDriverWait waitExplicit;
	JavascriptExecutor javaExecutor;
	@BeforeClass
	public void beforeClass() {

		// Firefox
		driver = new FirefoxDriver();
		waitExplicit = new WebDriverWait(driver, 30);
		javaExecutor = (JavascriptExecutor) driver;
		
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	}


	public void TC_01_CheckTitle() throws Exception {

		driver.get("https://daominhdam.github.io/basic-form/index.html");
		Select select = new Select(driver.findElement(By.xpath("//select[@id='job1']")));

		Assert.assertFalse(select.isMultiple());
		select.selectByVisibleText("Automation Tester");
		Assert.assertEquals(select.getFirstSelectedOption().getText(), "Automation Tester");
		Thread.sleep(3000);
		
		select.selectByValue("manual");
		Assert.assertEquals(select.getFirstSelectedOption().getText(), "Manual Tester");
		Thread.sleep(3000);
		
		select.selectByIndex(3);
		Assert.assertEquals(select.getFirstSelectedOption().getText(), "Mobile Tester");
		Thread.sleep(3000);

		Assert.assertEquals(select.getOptions().size(), 5);
		
	}
	

	@Test
	
	public void TC_02_CustomDropdown() throws Exception {
		driver.get("http://jqueryui.com/resources/demos/selectmenu/default.html");
		
		selectItemInCustomDropdown("//span[@id='number-button']", "//ul[@id='number-menu']//li[@class='ui-menu-item']" , "19");
		Assert.assertTrue(driver.findElement(By.xpath("//span[@id='number-button']//span[@class='ui-selectmenu-text' and text()='19']")).isDisplayed());
		Thread.sleep(3000);
		
		selectItemInCustomDropdown("//span[@id='number-button']", "//ul[@id='number-menu']//li[@class='ui-menu-item']" , "15");
		Assert.assertTrue(driver.findElement(By.xpath("//span[@id='number-button']//span[@class='ui-selectmenu-text' and text()='15']")).isDisplayed());
		Thread.sleep(3000);
		
		selectItemInCustomDropdown("//span[@id='number-button']", "//ul[@id='number-menu']//li[@class='ui-menu-item']" , "5");
		Assert.assertTrue(driver.findElement(By.xpath("//span[@id='number-button']//span[@class='ui-selectmenu-text' and text()='5']")).isDisplayed());
		Thread.sleep(3000);
		
		driver.get("https://demos.telerik.com/kendo-ui/dropdownlist/index ");
		
		selectItemInCustomDropdown("//span[@aria-owns='color_listbox']", "//ul[@id='color_listbox']/li" , "Orange");
		Assert.assertTrue(driver.findElement(By.xpath("//span[@aria-owns='color_listbox']//span[@class='k-input' and text()='Orange']")).isDisplayed());
		Thread.sleep(3000);
		
		
		selectItemInCustomDropdown("//span[@aria-owns='color_listbox']", "//ul[@id='color_listbox']/li" , "Grey");
		Assert.assertTrue(driver.findElement(By.xpath("//span[@aria-owns='color_listbox']//span[@class='k-input' and text()='Grey']")).isDisplayed());
		Thread.sleep(3000);
		
		selectItemInCustomDropdown("//span[@aria-owns='color_listbox']", "//ul[@id='color_listbox']/li" , "Black");
		Assert.assertTrue(driver.findElement(By.xpath("//span[@aria-owns='color_listbox']//span[@class='k-input' and text()='Black']")).isDisplayed());
		Thread.sleep(3000);
		
	}
	public void selectItemInCustomDropdown(String parentXpath, String childXpath , String expectedItem) {
		
		//Click vao dropdown
		WebElement element = driver.findElement(By.xpath(parentXpath));
		
		//Scroll tới element (Cha)
		javaExecutor.executeScript("arguments[0].scrollIntoViewIfNeeded(true);", element);
		element.click();
		
		//Get tất cả item trong dropdown vào 1 list element (List <WebElement>)
		List<WebElement> childList = driver.findElements(By.xpath(childXpath));
		
		//Wait để tất cả phần tử trong dropdown được hiển thị
		waitExplicit.until(ExpectedConditions.visibilityOfAllElements(childList));
		
		//Dùng vòng lặp for duyệt qua từng phần tử sau đó getText
		for(WebElement child: childList) {
			String textItem = child.getText();
			System.out.println("Text in dropdown = " + textItem);
			
			//Nếu actual text = expected text thì click vào phần tử đó và break khỏi vòng lặp
			if(textItem.equals(expectedItem)) {
				//Scroll tới expected item để click(item nó không visible)
				javaExecutor.executeScript("arguments[0].scrollIntoViewIfNeeded(true);", child);
				child.click();
				break;
			}
		}
	}

	@AfterClass
	public void afterClass() {
		driver.quit();
	}

}