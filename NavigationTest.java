import static org.junit.Assert.*;

import java.util.concurrent.TimeUnit;

import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;

public class NavigationTest {

	static WebDriver driver = new HtmlUnitDriver();
	
	@Before
	public void setUp() throws Exception {
		driver.get("https://www.stackoverflow.com");
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
	}
	
	//As a user,
	//I want to be able to navigate between pages
	//So that I can load the different pages.
	
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
	//When I click on the jobs tab
	//Then the job listings page will be loaded.
	@Test
	public void navigateJobs() {
		driver.get("https://stackoverflow.com/Questions");
		driver.findElement(By.linkText("Jobs")).click();
		assertTrue(driver.getTitle().contains("Job"));
	}
	
	//Scenario 3
	//Given that I am on the users page
	//When I click on the Stack Overflow icon
	//Then I will be returned to the main page.
	@Test
	public void returnToMain() {
		driver.get("https://stackoverflow.com/Users");
		driver.findElement(By.linkText("Stack Overflow")).click();
		assertTrue(driver.getTitle().contains("Stack Overflow"));
	}
	
	//Scenario 4
	//Given that I am on the tabs page
	//When I click on the Programmers tab
	//Then I will be directed to the Programmers Stack Exchange
	@Test
	public void navigateToProgrammerStackExchange() {
		driver.get("https://stackoverflow.com/Tags");
		driver.findElement(By.linkText("Programmers")).click();
		assertTrue(driver.getTitle().contains("Programmers"));
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
		assertTrue(driver.getTitle().contains("Questions"));
	}
}
