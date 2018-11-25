package selenium_api;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_02_Xpath {
	// Khai báo biến của Selenium WebDriver
	WebDriver driver;

	@BeforeClass
	public void beforeClass() {

		// Firefox
		driver = new FirefoxDriver();

		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	}

	@Test
	public void TC_01_CheckurlAndTitle() {
		// Step 1: Truy cập vào trang http://live.guru99.com
		driver.get("http://live.guru99.com");
		// Step 2: Kiểm tra title của page là Home page
		String homePageTitle = driver.getTitle();

		// Equals (input = output)
		Assert.assertEquals(homePageTitle, "Home page");

		// Step 3: Click vào link "My account" để tới trang đăng nhập
		driver.findElement(By.xpath("//div[@class=\"footer\"]//a[@title='My Account']")).click();
		// Step 4: Click vào Create an account button để tới trang đăng ký tk
		driver.findElement(By.xpath("//span[text()='Create an Account']")).click();
		// Step 5: Quay lại trang ĐN
		driver.navigate().back();
		// Step 6: Ktra URL của page đăng nhập là
		// http://live.guru99.com/index.php/customer/account/login/

		String LoginURL = driver.getCurrentUrl();

		Assert.assertEquals(LoginURL, "http://live.guru99.com/index.php/customer/account/login/");

		// Step 7: Forward tới trang tạo tài khoản

		driver.navigate().forward();

		// Step 8: Ktra URL của page tạo TK là
		// http://live.guru99.com/index.php/customer/account/create/

		String CreateAnAccountURL = driver.getCurrentUrl();

		Assert.assertEquals(CreateAnAccountURL, "http://live.guru99.com/index.php/customer/account/create/");
	}

	@Test
	public void TC_02_EmailAndPasswordEmpty() {

		// Step 1: Truy cập vào trang http://live.guru99.com
		driver.get("http://live.guru99.com");

		// Step 2: Click vào link "My account" để tới trang đăng nhập
		driver.findElement(By.xpath("//div[@class=\"footer\"]//a[@title='My Account']")).click();

		// Step 3: Để trống username/password
		driver.findElement(By.xpath("//input[@id='email']")).sendKeys();
		driver.findElement(By.xpath("//input[@id='pass']")).sendKeys();

		// Step 4: Click login button
		driver.findElement(By.id("send2")).click();

		// Step 5: Verify error message xuất hiện tại 2 field: This is a required field.

		String emailErrorMessage = driver.findElement(By.id("advice-required-entry-email")).getText();
		Assert.assertEquals(emailErrorMessage, "This is a required field.");

		String PasswordErrorMessage = driver.findElement(By.id("advice-required-entry-email")).getText();
		Assert.assertEquals(PasswordErrorMessage, "This is a required field.");
	}

	@Test
	public void TC_03_EmailIncorrectAndPasswordValid() {

	}

	@Test
	public void TC_04_EmailCorrectAndPasswordLessThan6Chars() {

	}

	@Test
	public void TC_05_EmailCorrectAndPasswordIncorrect() {

	}

	@Test
	public void TC_06_ReturnToSystem() {
		
		String firstname = "Selenium", lastname ="Online 07", email= "seleniumonline" + Commons.randomEmail() + "@gmail.com", password ="123456";
		
		System.out.println("Email random =" + email);
		// Step 1: Truy cập vào trang http://live.guru99.com
		driver.get("http://live.guru99.com");

		// Step 2: Click vào link "My account" để tới trang đăng nhập
		driver.findElement(By.xpath("//div[@class=\"footer\"]//a[@title='My Account']")).click();

		// Step 3: Click vào Create an account button để tới trang đăng ký tk
		driver.findElement(By.xpath("//span[text()='Create an Account']")).click();
		
		//Step 4: Nhập thông tin hợp lệ vào tất cả các field: First Name/Last Name/ Email Address/ Password/ Confirm Password
		driver.findElement(By.id("firstname")).sendKeys(firstname);
		driver.findElement(By.id("lastname")).sendKeys(lastname);
		driver.findElement(By.name("email")).sendKeys(email);
		driver.findElement(By.className("validate-password")).sendKeys(password);
		driver.findElement(By.className("validate-cpassword")).sendKeys(password);
		
		//Step 5: Click REGISTER button
		
		driver.findElement(By.xpath("//button[@title='Register']")).click();
		
		//Step 6: Verify message xuất hiện khi đăng ký thành công: Thank for your registering with Main Website Store. 
		
		Assert.assertTrue(driver.findElement(By.xpath("//span[text()='Thank you for registering with Main Website Store.']")).isDisplayed());
		
		//Step 7: Logout khỏi hệ thống 
		driver.findElement(By.xpath("//header[@id='header']//span[text()='Account']")).click();
		driver.findElement(By.xpath("//a[text()='Log Out']")).click();
		
		//Step 8: Kiểm tra hệ thống mavigate về Homepage sau khi logout thành công
		
		Assert.assertTrue(driver.findElement(By.xpath("//h2[contains(text(),'This is demo site for')]")).isDisplayed());
	}

	@AfterClass
	public void afterClass() {
		driver.quit();
	}


}