package selenium_api;

import java.util.concurrent.TimeUnit;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_05_HandleDropdownList {
	WebDriver driver;

	@BeforeClass
	public void beforeClass() {

		// Firefox
		driver = new FirefoxDriver();
		driver.get("https://www.google.com/");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	}

	@Test
	public void TC_01_CheckTitle() throws Exception {

		/*
		 * Test Script 02: Xử lí HTML Dropdown/ list 
		 * Step 01 - Truy cập vào trang: https://daominhdam.github.io/basic-form/index.html 
		 * Step 02 - Kiểm tra dropdown Job Role 01 không hỗ trợ thuộc tính multi-select 
		 * Step 03 - Chọn giá trị Automation Tester trong dropdown bằng phương thức selectVisible
		 * Step 04 - Kiểm tra giá trị đã được chọn thành công 
		 * Step 05 - Chọn giá trị Manual Tester trong dropdown bằng phương thức selectValue 
		 * Step 06 - Kiểm tra giá trị đã được chọn thành công 
		 * Step 07 - Chọn giá trị Mobile Tester trong dropdown bằng phương thức selectIndex 
		 * Step 08 - Kiểm tra giá
		 * trị đã được chọn thành công Step 09 - Kiểm tra dropdown có đủ 5 giá trị
		 */

		driver.get("https://daominhdam.github.io/basic-form/index.html");
		Select select = new Select(driver.findElement(By.xpath("//select[@id='job1']")));

		/*
		 * <select id="job1" name="user_job1"> 
		 * <option value="automation">Automation Tester</option> 
		 * <option value="manual">Manual Tester</option> 
		 * <option value="website">Website Tester</option> 
		 * <option value="mobile">Mobile Tester</option> 
		 * <option value="disabled" disabled="disabled">
		 * disable</option> 
		 * </select>
		 */

		// Index: array 5 phần từ ( 0 1 2 3 4)
		// Chọn

		Assert.assertFalse(select.isMultiple());
		select.selectByVisibleText("Automation Tester");
		Assert.assertEquals(select.getFirstSelectedOption().getText(), "Automation Tester");
		Thread.sleep(3000);
		
		select.selectByValue("manual");
		Assert.assertEquals(select.getFirstSelectedOption().getText(), "Manual Tester");
		
		select.selectByIndex(3);
		Assert.assertEquals(select.getFirstSelectedOption().getText(), "Mobile Tester");

	}

	@AfterClass
	public void afterClass() {
		driver.quit();
	}

}