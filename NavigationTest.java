import static org.junit.Assert.*;

import java.util.concurrent.TimeUnit;

import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;

//As a user,
//I want to be able to navigate between pages
//So that I can change the page I am on.

public class NavigationTest {

	static WebDriver driver = new HtmlUnitDriver();
	
	@Before
	public void setUp() throws Exception {
		driver.get("https://www.stackoverflow.com");
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
	}
	
	//Scenario 1
	//Given that I am on the main page,
	//When I click the questions tab
	//Then the newest questions page will be loaded.
	@Test
	public void navigateQuestions() {
		driver.get("https://stackoverflow.com");
		driver.findElement(By.linkText("Questions")).click();
		assertTrue(driver.getTitle().contains("Questions"));
	}

	//Scenario 2
	//Given that I am on the questions page
	//When I click on the tags tab
	//Then the tags page will be loaded.
	@Test
	public void navigateTags() {
		driver.get("https://stackoverflow.com/Questions");
		driver.findElement(By.linkText("Tags")).click();
		assertTrue(driver.findElement(By.xpath("//*[@class='youarehere']")).getText().contains("Tags"));
	}
	
	//Scenario 3
	//Given that I am on the users page
	//When I click on the Stack Overflow icon
	//Then I will be returned to the main page.
	@Test
	public void returnToMain() {
		driver.get("https://stackoverflow.com/Users");
		driver.findElement(By.linkText("Stack Overflow")).click();
		WebElement home = driver.findElement(By.xpath("//*[contains(text(), 'home-page')]"));
		assertTrue(driver.getTitle().contains("Stack Overflow"));
		assertNotNull(home);
	}
	
	//Scenario 4
	//Given that I am on the tabs page
	//When I click on the Programmers tab on the bottom of the page
	//Then I will be directed to the Programmers Stack Exchange
	@Test
	public void navigateToProgrammerStackExchange() {
		driver.get("https://stackoverflow.com/Tags");
		driver.findElement(By.linkText("Programmers")).click();
		assertTrue(driver.findElement(By.tagName("title")).getAttribute("innerHTML").contains("Programmers Stack Exchange"));
	}
	
	//Scenario 5
	//Given that I am on the main page
	//When I click on multiple navigation tabs successively
	//Then I will be directed to the latest one I clicked.
	@Test
	public void navigateMultiplePages() {
		driver.get("https://stackoverflow.com");
		driver.findElement(By.linkText("Tags")).click();
		driver.findElement(By.linkText("Jobs")).click();
		driver.findElement(By.linkText("Users")).click();
		driver.findElement(By.linkText("Questions")).click();
		assertTrue(driver.findElement(By.className("youarehere")).getText().contains("Questions"));
	}
}
