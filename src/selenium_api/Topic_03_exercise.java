package selenium_api;

import java.util.concurrent.TimeUnit;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
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
	By passwordByTextBox = By.xpath("//input[@id='password']");
	By biographyTextArea = By.xpath("//textarea[@id='bio']");
	By developmentByCheckbox = By.xpath("//input[@id='development']");

	@BeforeClass
	public void beforeClass() {
		// Chrome
		// System.setProperty("webdriver.chrome.driver", ".\\lib\\chromedriver.exe");
		// driver = new ChromeDriver();

		// Firefox
		driver = new FirefoxDriver();
		driver.get("https://daominhdam.github.io/basic-form/index.html");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	}

	@Test
	public void TC_01_IsDisplayed() {

		if (isControlDisplayed(emailTextbox)) {
			driver.findElement(emailTextbox).sendKeys("Automation Testing");
		}
		if (isControlDisplayed(educationByTextArea)) {
			driver.findElement(educationByTextArea).sendKeys("Automation Testing");
		}
		if (isControlDisplayed(ageUnder18Byradio)) {
			driver.findElement(ageUnder18Byradio).click();
		}

	}

	@Test
	public void TC_02_IsEnabled() {

		// Enabled
		Assert.assertTrue(isControlEnabled(emailTextbox));
		Assert.assertTrue(isControlEnabled(educationByTextArea));
		Assert.assertTrue(isControlEnabled(ageUnder18Byradio));

		// Disabled
		Assert.assertFalse(isControlEnabled(passwordByTextBox));
		Assert.assertFalse(isControlEnabled(biographyTextArea));
	}

	@Test
	public void TC_03_IsSelected() {

		// Click để chọn
		driver.findElement(ageUnder18Byradio).click();
		driver.findElement(developmentByCheckbox).click();

		Assert.assertTrue(isControlSelected(ageUnder18Byradio));
		Assert.assertTrue(isControlSelected(developmentByCheckbox));

		// click để bỏ chọn development checkbox

		driver.findElement(developmentByCheckbox).click();
		Assert.assertFalse(isControlSelected(developmentByCheckbox));

		// Nếu chưa được chọn thì cho phép chọn lại 1 lần nữa
		if (!isControlSelected(developmentByCheckbox)) {
			driver.findElement(developmentByCheckbox).click();
			Assert.assertTrue(isControlSelected(developmentByCheckbox));
		}
	}

	@AfterClass
	public void afterClass() {
		driver.quit();
	}

	public boolean isControlDisplayed(By by) {
		WebElement element = driver.findElement(by);
		if (element.isDisplayed()) {
			System.out.println("Element [" + by + "] is displayed!");
			return true;
		} else {
			System.out.println("Element [" + by + "] is not displayed!");
			return false;
		}
	}

	public boolean isControlEnabled(By by) {
		WebElement element = driver.findElement(by);
		if (element.isEnabled()) {
			System.out.println("Element [" + by + "] is enabled!");
			return true;
		} else {
			System.out.println("Element [" + by + "] is not enabled!");
			return false;
		}
	}

	public boolean isControlSelected(By by) {
		WebElement element = driver.findElement(by);
		if (element.isSelected()) {
			System.out.println("Element [" + by + "] is selected!");
			return true;
		} else {
			System.out.println("Element [" + by + "] is not selected!");
			return false;
		}
	}
}