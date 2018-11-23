package selenium_api;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_03_WebBrowser_WebElement {
	WebDriver driver;

	@BeforeClass
	public void beforeClass() {
		
		//Khởi tạo browser;
		driver = new FirefoxDriver();
		
		
		driver.get("https://www.google.com.vn/");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	}

	@Test
	public void TC_01_WebBrowser() {

		/*----------WEB BROWSER----------*/

		// Đóng tab mà nó đang active
		driver.close();

		// Đóng browser
		driver.quit(); /**/

		// Open URL
		driver.get("URL"); /**/

		// Get ra title của page hiện tại
		String loginPageURL = driver.getCurrentUrl(); /**/

		// get ra source code của page hiện tại
		String homePageSource = driver.getPageSource();

		// Dùng kiểm tra 1 cách tương đối - performance chạy nhanh

		Assert.assertTrue(homePageSource.contains("SELENIUM WEBDRIVER FORM DEMO"));

		// get ra title của page hiện tại
		String homepageTitle = driver.getTitle(); /**/
		Assert.assertEquals(homepageTitle, "SELENIUM WEBDRIVER FORM DEMO");

		// Get ra windows id của page hiện tại (GUID - Uniqe)

		String homePageID = driver.getWindowHandle(); /**/

		// Get ra tất cả window ID của tất cả các tab hiện tại
		driver.getWindowHandles();

		// Wait cho element (findelement/findelements)
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS); /**/

		// Wait cho page dc load thành công
		driver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);

		// Set timeout cho script
		driver.manage().timeouts().setScriptTimeout(30, TimeUnit.SECONDS);

		// F11
		driver.manage().window().fullscreen();

		// phóng to browser ra hết cỡ
		driver.manage().window().maximize(); /**/

		driver.manage().window().setSize(new Dimension(1024, 768));
		driver.manage().window().setPosition(new Point(300, 300));

		Dimension initial_size = driver.manage().window().getSize();
		int heigh = initial_size.getHeight();
		int width = initial_size.getWidth();

		driver.navigate().back();
		driver.navigate().forward();
		driver.navigate().refresh();

		// Tracking history (back/forward)
		driver.navigate().to("URL");

		driver.switchTo().alert(); /**/

		driver.switchTo().frame(""); /**/
		driver.switchTo().defaultContent();
		driver.switchTo().window("GUID"); /**/

	}

	@Test

	public void TC_02_WebElement() {
		/*-----WEB ELEMENT------*/

		// Cách 1: Action trực tiếp lên step ( click/sendkey/gettext/...)
		driver.findElement(By.id("email")).sendKeys("");

		// Cách 2: Dùng lại element này

		WebElement emailTextbox = driver.findElement(By.id("email"));
		emailTextbox.clear(); /**/
		emailTextbox.sendKeys(""); /**/
		emailTextbox.click(); /**/

		// Xóa data trước khi sendkey chỉ có textbox/textarea/dropdown
		emailTextbox.clear();
		// input data vào
		emailTextbox.sendKeys("Automation Testing");

		// Nếu không tìm thấy element nào thì sẽ throw exception và đánh fail testcase

		emailTextbox.findElement(By.xpath("//input[@id='pass']"));

		List<WebElement> homePageLink = driver.findElements(By.xpath("//a"));
		homePageLink.get(0).click(); /**/
		homePageLink.get(1).getText();/**/

		int homepageNumber = homePageLink.size();/**/

		// Nếu không tìm thấy element nào thì sẽ return ra 1 list rỗng rồi chạy step
		// tiếp theo chứ ko đánh fail testcase

		/*
		 * 
		 * <input id="email" class="input-text required-entry validate-email"
		 * type="email" title="Email Address" value="" name="login[username]"
		 * spellcheck="false" autocorrect="off" autocapitalize="off"/>
		 */

		// Giá trị mình cần đang nằm trong 1 attribute của thẻ
		WebElement emailTxt = driver.findElement(By.xpath("//input[@name='name']"));
		String emailNameValue = emailTextbox.getAttribute("value"); /**/
		// Automation Test

		// </td>Custom Name</td> - nằm bên ngoài thẻ
		emailTxt.getText(); /**/

		String emailBackground = emailTxt.getCssValue("background-color");
		// #f89b51
		String emailFontSize = emailTxt.getCssValue("font-size");
		// 27px

		emailTxt.getLocation();
		emailTxt.getSize();

		String emailTagname = emailTxt.getTagName();
//input

		// Dynamic Locator

		// Element đầu tiên sẽ get ra phần tử cho element sau sử dụng.

		// Kiểm tra 1 element có hiển thị ở trên page hay không?

		Assert.assertTrue(emailTxt.isDisplayed()); /**/

		// Kiểm tra element có bị disable hay không?

		Assert.assertTrue(emailTxt.isEnabled()); /**/
		Assert.assertFalse(emailTxt.isEnabled()); /**/

		// Kiểm tra 1 element có được chọn thành công ở trên page hay ko? radio
		// button/checkbox - chỉ work với thẻ input)
		Assert.assertTrue(emailTxt.isSelected()); /**/
		Assert.assertFalse(emailTxt.isSelected()); /**/

		// Click vào 1 button/checkbox/radio/link

		emailTxt.click();

		// Form search/Login/Register/( Nhận sự kiện enter)
		driver.findElement(By.xpath(".//input[@id='pass']")).sendKeys("1111");
		driver.findElement(By.xpath(".//input[@id='pass']")).submit();
		;
		emailTxt.submit();
	}

	@AfterClass
	public void afterClass() {
		driver.quit();
	}

}