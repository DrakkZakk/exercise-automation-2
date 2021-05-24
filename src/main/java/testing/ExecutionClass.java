package testing;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class ExecutionClass {
	
	WebDriver driver;
	
	@BeforeTest
	public void beforeTest() {
		SetUp set = new SetUp();
		driver = set.setUpBrowser();
	}
	
	@Test
	public void facebookCheck() {
		WebElement element;
		WebDriverWait wait = new WebDriverWait(driver, 15);
		Select select;
		//Expected URL to be find
		String expectedURL = "https://www.facebook.com/";
		//Current URL found
		String actualURL = driver.getCurrentUrl();
		//Expected Page Title
		String expectedTitle = "Facebook - Log In or Sign Up";
		//Current Page Title found
		String actualTitle = driver.getTitle();
		//URL validation
		Assert.assertEquals(actualURL, expectedURL);
		//Page Title validation
		Assert.assertEquals(actualTitle, expectedTitle);
		//Create New Account button
		element = driver.findElement(By.xpath("//div[@class='_6ltg']//child::a[@role='button']"));
		clickElement(element);
		wait.until(ExpectedConditions.elementToBeClickable(element));
		//Sign Up form
		element = driver.findElement(By.cssSelector("div._8ien"));
		wait.until(ExpectedConditions.visibilityOf(element));
		//Form fill up
		element = driver.findElement(By.name("firstname"));
		sendText(element, "Jose");
		element = driver.findElement(By.name("lastname"));
		sendText(element, "Morales");
		element = driver.findElement(By.name("reg_email__"));
		sendText(element, "jose.morales@unosquare.com");
		element = driver.findElement(By.xpath("//input[@name='reg_email__']//following::input[@name='reg_passwd__']"));
		sendText(element, "12345678");
		//Select Birthday Month
		element = driver.findElement(By.id("month"));
		select = new Select(element);
		select.selectByVisibleText("Dec");
		//Select Birthday Day
		element = driver.findElement(By.id("day"));
		select = new Select(element);
		select.selectByVisibleText("22");
		//Select Birthday Year
		element = driver.findElement(By.id("year"));
		select = new Select(element);
		select.selectByVisibleText("1992");
		//Select Female option
		element = driver.findElement(By.xpath("//input[@type='radio' and @value='1']"));
		clickElement(element);
		//Close window
		element = driver.findElement(By.cssSelector("div._8ien > img._8idr.img"));
		clickElement(element);
		//Verify text is present
		boolean textPresent = driver.getPageSource().contains("Connect with friends and the world around you on Facebook.");
		Assert.assertTrue(textPresent);
		//Click on Forgot Password
		element = driver.findElement(By.linkText("Forgot Password?"));
		clickElement(element);
		//Verify text is present
		textPresent = driver.getPageSource().contains("Find Your Account");
		Assert.assertTrue(textPresent);
		//Enter an invalid Email
		element = driver.findElement(By.id("identify_email"));
		clickElement(element);
		sendText(element, "jos39211@mail.com");
		element = driver.findElement(By.name("did_submit"));
		clickElement(element);
		wait.until(ExpectedConditions.elementToBeClickable(element));
		//Verify Error message is displayed
		element = driver.findElement(By.cssSelector("div.pam.uiBoxRed > div._9o4g.fsl.fwb.fcb"));
		textPresent= driver.findElement(By.cssSelector("div.pam.uiBoxRed > div._9o4g.fsl.fwb.fcb")).isDisplayed();
		Assert.assertTrue(textPresent);
		wait.until(ExpectedConditions.visibilityOf(element));
	}
	
	public void clickElement(WebElement element) {
		element.click();
	}
	
	public void sendText(WebElement element, String text) {
		element.sendKeys(text);
	}
	
	@AfterTest
	public void afterTest() {
		driver.close();
	}
}
