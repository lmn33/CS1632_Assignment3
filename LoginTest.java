import static org.junit.Assert.*;

import java.util.concurrent.TimeUnit;

import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import org.openqa.selenium.Keys;

/*
 * As a registered user,
 * I would like to log in and out of my account
 * So that I can manage my information and activity on the site.
 */

public class LoginTest {

	static WebDriver driver = new HtmlUnitDriver();
	
	@Before
	public void setUp() throws Exception {
		driver.get("https://stackoverflow.com");
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
	}

	//Scenario 1 
	//Given that I am on the user login page
	//When I try to log in with a valid email and password
	//Then I will log in successfully and see my name displayed accordingly
	@Test
	public void testSuccessfulLogin() {
		driver.get("https://stackoverflow.com/users/login");
		driver.findElement(By.id("email")).clear();
		driver.findElement(By.id("email")).sendKeys("davidneiman2015@gmail.com");
		driver.findElement(By.id("password")).clear();
		driver.findElement(By.id("password")).sendKeys("correctPass11");
		driver.findElement(By.id("email")).submit();
		
		String pageTitle = driver.findElement(By.xpath("//div[@class='gravatar-wrapper-24']")).getAttribute("title");
		assertEquals(pageTitle, "Throwaway Account");
		
		//Log out after done testing login
		driver.get("http://stackoverflow.com/users/logout");
		driver.findElement(By.xpath("//form[@method='post']")).submit();
	}
	
	//Scenario 2
	//Given that I am logged in
	//When I try to log out
	//Then I will be successfully logged out and see links to log in again.
	@Test
	public void testLogOut() {
		//Log in properly, so we can log out later.
		driver.get("https://stackoverflow.com/users/login");
		driver.findElement(By.id("email")).clear();
		driver.findElement(By.id("email")).sendKeys("davidneiman2015@gmail.com");
		driver.findElement(By.id("password")).clear();
		driver.findElement(By.id("password")).sendKeys("correctPass11");
		driver.findElement(By.id("email")).submit();
		
		//Log out
		driver.get("http://stackoverflow.com/users/logout");
		driver.findElement(By.xpath("//form[@method='post']")).submit();
		
		//Once logged out, the log in link should display.
		WebElement loginLink = driver.findElement(By.linkText("log in"));
		assertNotNull(loginLink);
	}
	
	//Scenario 3
	//Given that I am on the user login page
	//When I try to log in with an incorrect password (but registered email)
	//Then the browser should stay on the login page.
	@Test
	public void loginIncorrectPassword() {
		driver.get("https://stackoverflow.com/users/login");
		driver.findElement(By.id("email")).clear();
		driver.findElement(By.id("email")).sendKeys("dsn9@pitt.edu");
		driver.findElement(By.id("password")).clear();
		driver.findElement(By.id("password")).sendKeys("wrongPass11");
		driver.findElement(By.id("email")).submit();
		assertTrue(driver.getTitle().contains("Log In"));
	}
	
	//Scenario 4
	//Given that I am on the user login page
	//When I try to log in with an invalid username
	//Then the browser should stay on the login page.
	@Test
	public void loginInvalidUsername() {
		driver.get("https://stackoverflow.com/users/login");
		driver.findElement(By.id("email")).clear();
		driver.findElement(By.id("email")).sendKeys("dneiman11@punahou.edu");
		driver.findElement(By.id("password")).clear();
		driver.findElement(By.id("password")).sendKeys("wrongPass11");
		driver.findElement(By.id("email")).submit();
		String page = driver.findElement(By.xpath("//*[contains(text(),'Log In')]")).getAttribute("innerHTML");
		assertTrue(page.contains("Log In"));
	}
	
	//Scenario 5
	//Given that I am not logged in
	//When I try to access the log out page
	//Then the site should redirect to the main page and show the log in link
	@Test
	public void invalidLogOut() {
		driver.get("http://stackoverflow.com/users/logout");
		assertTrue(driver.getTitle().equals("Stack Overflow"));
		WebElement loginLink = driver.findElement(By.linkText("log in"));
		assertNotNull(loginLink);
	}
	
}