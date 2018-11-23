package selenium_api;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_03_exercise {
	WebDriver driver;
	By emailTextbox = By.xpath("//input[@id='mail']");
	By ageUnder18Byradio = By.xpath("//input[@id='under_18']");
	By educationByTextArea = By.xpath("//textarea[@id='edu']");
	By AddressByTextBox = By.xpath("//input[@id='address']");
	

	@BeforeClass
	public void beforeClass() {
		// Chrome
//		System.setProperty("webdriver.chrome.driver", ".\\lib\\chromedriver.exe");
//		driver = new ChromeDriver();

		// Firefox
		driver = new FirefoxDriver();
		driver.get("https://daominhdam.github.io/basic-form/index.html");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	}

	@Test
	public void TC_01_IsDisplayed() {
		if (driver.findElement(emailTextbox).isDisplayed()) {

			driver.findElement(emailTextbox).sendKeys("Automation Testing");
		}
		if (driver.findElement(educationByTextArea).isDisplayed()) {

			driver.findElement(educationByTextArea).sendKeys("Automation Testing");
		}
		
		if (driver.findElement(ageUnder18Byradio).isDisplayed()) {

			driver.findElement(ageUnder18Byradio).click();
		}
		
		if (driver.findElement(AddressByTextBox).isDisplayed()){
			
			driver.findElement(AddressByTextBox).sendKeys("Automation Testing");
		} else {
			System.out.println("Element [" + AddressByTextBox +"] is not displayed!");
		}

	}

	@AfterClass
	public void afterClass() {
		driver.quit();
	}

}