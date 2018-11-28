package selenium_api;

import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_04_HandleTextboxTextArea {
	WebDriver driver;
	private String newName, newDob, newAddress, newCity, newState, newPin, newPhone, newEmail, password;
	private String editAddress, editCity, editState, editPin, editPhone, editEmail, customerID;

	By nameByTextBox = By.xpath("//input[@name='name']");
	By dobByTextBox = By.xpath("//input[@name='dob']");
	By addressByTextArea = By.xpath("//textarea[@name='addr']");
	By cityByTextBox = By.xpath("//input[@name='city']");
	By stateByTextBox = By.xpath("//input[@name='state']");
	By pinByTextBox = By.xpath("//input[@name='pinno']");
	By phoneByTextBox = By.xpath("//input[@name='telephoneno']");
	By emailByTextBox = By.xpath("//input[@name='emailid']");
	By passwordByTextBox = By.xpath("//input[@name='password']");
	By submitBybutton = By.xpath("//input[@name='sub']");

	@BeforeClass
	public void beforeClass() {

		// Firefox
		driver = new FirefoxDriver();
		driver.get("http://demo.guru99.com/v4");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

		/* Input data */

		newName = "Automation Testing";
		newDob = "2000-10-01";
		newAddress = "123 Address";
		newCity = "Ho Chi Minh";
		newState = "Thu Duc";
		newPin = "123456";
		newPhone = "0917456123";
		newEmail = "autotest" + Commons.randomEmail() + "@gmail.com";
		password = "123123";

		editAddress = "234 Edit address";
		editCity = "Thai Nguyen";
		editState = "Dai tu";
		editPin = "654321";
		editPhone = "0952471852";
		editEmail = "edittest" + Commons.randomEmail() + "@gmail.com";
	}

	@Test
	public void TC_01_NewCustomer() {
		driver.findElement(By.xpath("//input[@name='uid']")).sendKeys("mngr163559");
		driver.findElement(By.xpath("//input[@name='password']")).sendKeys("vysYmuz");
		driver.findElement(By.xpath("//input[@name='btnLogin']")).click();
		Assert.assertTrue(driver.findElement(By.xpath("//marquee[text()=\"Welcome To Manager's Page of Guru99 Bank\"]")).isDisplayed());

		driver.findElement(By.xpath("//a[text()='New Customer']")).click();

		/* new customer - User input data*/

		driver.findElement(nameByTextBox).sendKeys(newName);
		driver.findElement(dobByTextBox).sendKeys(newDob);
		driver.findElement(addressByTextArea).sendKeys(newAddress);
		driver.findElement(cityByTextBox).sendKeys(newCity);
		driver.findElement(stateByTextBox).sendKeys(newState);
		driver.findElement(pinByTextBox).sendKeys(newPin);
		driver.findElement(phoneByTextBox).sendKeys(newPhone);
		driver.findElement(emailByTextBox).sendKeys(newEmail);
		driver.findElement(passwordByTextBox).sendKeys(password);
		driver.findElement(submitBybutton).click();

		/* get customer ID */

		customerID = driver.findElement(By.xpath("//td[text()='Customer ID']/following-sibling::td")).getText();
		System.out.println("Customer ID = " + customerID);

		/* Verify Input Data matching vs Output Data after create Customer success */

		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Customer Name']/following-sibling::td")).getText(), newName);
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Birthdate']/following-sibling::td")).getText(), newDob);
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Address']/following-sibling::td")).getText(), newAddress);
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='City']/following-sibling::td")).getText(), newCity);
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='State']/following-sibling::td")).getText(), newState);
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Pin']/following-sibling::td")).getText(), newPin);
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Mobile No.']/following-sibling::td")).getText(), newPhone);
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Email']/following-sibling::td")).getText(), newEmail);
		
		System.out.println("New = " + driver.findElement(By.xpath("//td[text()='Customer Name']/following-sibling::td")).getText());
		System.out.println("New = " + driver.findElement(By.xpath("//td[text()='Birthdate']/following-sibling::td")).getText());
		System.out.println("New = " + driver.findElement(By.xpath("//td[text()='Address']/following-sibling::td")).getText());
		System.out.println("New = " + driver.findElement(By.xpath("//td[text()='City']/following-sibling::td")).getText());
		System.out.println("New = " + driver.findElement(By.xpath("//td[text()='State']/following-sibling::td")).getText());
		System.out.println("New = " + driver.findElement(By.xpath("//td[text()='Pin']/following-sibling::td")).getText());
		System.out.println("New = " + driver.findElement(By.xpath("//td[text()='Mobile No.']/following-sibling::td")).getText());
		System.out.println("New = " + driver.findElement(By.xpath("//td[text()='Email']/following-sibling::td")).getText());
	
	}

	@Test
	public void TC_02_EditCustomer() {
		driver.findElement(By.xpath("//a[text()='Edit Customer']")).click();
		driver.findElement(By.xpath("//input[@name='cusid']")).sendKeys(customerID);
		driver.findElement(By.xpath("//input[@name='AccSubmit']")).click();
		
		/* verify @Name Address matching vs input data at New Customer*/
		
		Assert.assertEquals(driver.findElement(nameByTextBox).getAttribute("value"), newName);
		Assert.assertEquals(driver.findElement(addressByTextArea).getText(), newAddress);
		
		/* Edit Customer  - User input data*/
		driver.findElement(addressByTextArea).clear();		
		driver.findElement(cityByTextBox).clear();
		driver.findElement(stateByTextBox).clear();
		driver.findElement(pinByTextBox).clear();
		driver.findElement(phoneByTextBox).clear();
		driver.findElement(emailByTextBox).clear();

		driver.findElement(addressByTextArea).sendKeys(editAddress);
		driver.findElement(cityByTextBox).sendKeys(editAddress);
		driver.findElement(stateByTextBox).sendKeys(editState);
		driver.findElement(pinByTextBox).sendKeys(editPin);
		driver.findElement(phoneByTextBox).sendKeys(editPhone);
		driver.findElement(emailByTextBox).sendKeys(editEmail);
		driver.findElement(submitBybutton).click();
		
		/* Verify Input Data matching vs Output Data after edit Customer success */
		
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Address']/following-sibling::td")).getText(), editAddress);
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='City']/following-sibling::td")).getText(), editAddress);
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='State']/following-sibling::td")).getText(), editState);
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Pin']/following-sibling::td")).getText(), editPin);
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Mobile No.']/following-sibling::td")).getText(), editPhone);
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Email']/following-sibling::td")).getText(), editEmail);
		
		System.out.println("Edit =" + driver.findElement(By.xpath("//td[text()='Address']/following-sibling::td")).getText());
		System.out.println("Edit =" + driver.findElement(By.xpath("//td[text()='City']/following-sibling::td")).getText());
		System.out.println("Edit =" + driver.findElement(By.xpath("//td[text()='State']/following-sibling::td")).getText());
		System.out.println("Edit =" + driver.findElement(By.xpath("//td[text()='Pin']/following-sibling::td")).getText());
		System.out.println("Edit =" + driver.findElement(By.xpath("//td[text()='Mobile No.']/following-sibling::td")).getText());
		System.out.println("Edit =" + driver.findElement(By.xpath("//td[text()='Email']/following-sibling::td")).getText());
		
	}

	@AfterClass
	public void afterClass() {
		driver.quit();
	}

	public int randomEmail() {
		Random random = new Random();
		int number = random.nextInt(999999);
		System.out.println("Random number =" + number);
		return number;

	}

}