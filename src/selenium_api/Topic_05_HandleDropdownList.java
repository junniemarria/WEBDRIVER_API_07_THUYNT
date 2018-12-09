package selenium_api;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
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
		Thread.sleep(1000);
		
		select.selectByValue("manual");
		Assert.assertEquals(select.getFirstSelectedOption().getText(), "Manual Tester");
		Thread.sleep(1000);
		
		select.selectByIndex(3);
		Assert.assertEquals(select.getFirstSelectedOption().getText(), "Mobile Tester");
		Thread.sleep(1000);

		Assert.assertEquals(select.getOptions().size(), 5);
		
	}
	

	@Test
	
	public void TC_02_CustomDropdown() throws Exception {
		//Jquery UI
		driver.get("http://jqueryui.com/resources/demos/selectmenu/default.html");
		
		selectItemInCustomDropdown("//div[@class='demo']", "//span[@id='number-button']", "//ul[@id='number-menu']//li[@class='ui-menu-item']" , "19");
		Assert.assertTrue(driver.findElement(By.xpath("//span[@id='number-button']//span[@class='ui-selectmenu-text' and text()='19']")).isDisplayed());
		Thread.sleep(1000);
		
		selectItemInCustomDropdown("//div[@class='demo']", "//span[@id='number-button']", "//ul[@id='number-menu']//li[@class='ui-menu-item']" , "15");
		Assert.assertTrue(driver.findElement(By.xpath("//span[@id='number-button']//span[@class='ui-selectmenu-text' and text()='15']")).isDisplayed());
		Thread.sleep(1000);
		
		selectItemInCustomDropdown("//div[@class='demo']", "//span[@id='number-button']", "//ul[@id='number-menu']//li[@class='ui-menu-item']" , "5");
		Assert.assertTrue(driver.findElement(By.xpath("//span[@id='number-button']//span[@class='ui-selectmenu-text' and text()='5']")).isDisplayed());
		Thread.sleep(1000);
		
		//Kendo UI
		driver.get("https://demos.telerik.com/kendo-ui/dropdownlist/index ");
		
		selectItemInCustomDropdown("//div[@id='cap-view']", "//span[@aria-owns='color_listbox']", "//ul[@id='color_listbox']/li" , "Orange");
		Assert.assertTrue(driver.findElement(By.xpath("//span[@aria-owns='color_listbox']//span[@class='k-input' and text()='Orange']")).isDisplayed());
		Thread.sleep(1000);
		
		
		selectItemInCustomDropdown("//div[@id='cap-view']", "//span[@aria-owns='color_listbox']", "//ul[@id='color_listbox']/li" , "Grey");
		Assert.assertTrue(driver.findElement(By.xpath("//span[@aria-owns='color_listbox']//span[@class='k-input' and text()='Grey']")).isDisplayed());
		Thread.sleep(1000);
		
		Assert.assertTrue(driver.findElement(By.xpath("//span[@aria-owns='color_listbox']//span[@class='k-input' and text()='Black']")).isDisplayed());
		Thread.sleep(1000);
		
		// Angular 2
		
		driver.get("https://material.angular.io/components/select/examples");
		
		selectItemInCustomDropdown("//div[text()='Select with reset option']", "//mat-select[@placeholder='State']", "//mat-option/span", "Washington");
		Assert.assertTrue(driver.findElement(By.xpath("//mat-select[@placeholder='State']//span[text()='Washington']")).isDisplayed());
		Thread.sleep(1000);
		
		selectItemInCustomDropdown("//div[text()='Select with reset option']", "//mat-select[@placeholder='State']", "//mat-option/span", "New York");
		Assert.assertTrue(driver.findElement(By.xpath("//mat-select[@placeholder='State']//span[text()='New York']")).isDisplayed());
		Thread.sleep(1000);
		
		//VueJS
		
		driver.get("https://mikerodham.github.io/vue-dropdowns/");		
		
		selectItemInCustomDropdown("//div[@id='app']", "//div[@id='app']/div/li", "//ul[@class='dropdown-menu']/li", "Second Option");
		Assert.assertTrue(driver.findElement(By.xpath("//div[@id='app']/div/li[contains(text(),'Second Option')]")).isDisplayed());
		Thread.sleep(1000);
		
		selectItemInCustomDropdown("//div[@id='app']", "//div[@id='app']/div/li", "//ul[@class='dropdown-menu']/li", "Third Option");
		Assert.assertTrue(driver.findElement(By.xpath("//div[@id='app']/div/li[contains(text(),'Third Option')]")).isDisplayed());
		Thread.sleep(1000);
		
		//Editable
		
		driver.get("http://indrimuska.github.io/jquery-editable-select/");
		
		driver.findElement(By.xpath("//div[@id='default-place']/input")).sendKeys("Ford");
		Thread.sleep(1000);
		driver.findElement(By.xpath("//div[@id='default-place']/input")).sendKeys(Keys.TAB);
		Thread.sleep(2000);
		
		Assert.assertEquals(driver.findElement(By.xpath("//*[@id='default-place']//li[text()='Ford']")).getAttribute("class"), "es-visible selected");
		Thread.sleep(1000);


	}
	public void selectItemInCustomDropdown(String scrollToXpath, String parentXpath, String childXpath , String expectedItem) throws Exception {
		//Scroll tới element (Cha)
		javaExecutor.executeScript("arguments[0].scrollIntoView(true);", driver.findElement(By.xpath(scrollToXpath)));
		Thread.sleep(1000);
	
		
		//Click vao dropdown
		WebElement element = driver.findElement(By.xpath(parentXpath));
		element.click();
		Thread.sleep(1000);
		
		
		//Get tất cả item trong dropdown vào 1 list element (List <WebElement>)
		List<WebElement> childList = driver.findElements(By.xpath(childXpath));
		
		//Wait để tất cả phần tử trong dropdown được hiển thị
		waitExplicit.until(ExpectedConditions.visibilityOfAllElements(childList));
		
		//Dùng vòng lặp for duyệt qua từng phần tử sau đó getText
		
		//trim():Xóa khoảng cách ở đầu, ở cuối, phím tab, xuống dòng ở đầu và cuối câu
		for(WebElement child: childList) {
			String textItem = child.getText().trim();
			System.out.println("Text in dropdown = " + textItem);
			
			//Nếu actual text = expected text thì click vào phần tử đó và break khỏi vòng lặp
			if(textItem.equals(expectedItem)) {
				//Scroll tới expected item để click(item nó không visible)
				javaExecutor.executeScript("arguments[0].scrollIntoView(true);", child);
				Thread.sleep(1000);
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