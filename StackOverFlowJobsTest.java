import static org.junit.Assert.*;

import org.junit.*;
import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;

/**
 * As a user,
 * I want to be able to search companies and individual jobs,
 * So that I can pick the best job for me
 */

@SuppressWarnings("unused")
public class StackOverFlowJobsTest {

	static WebDriver driver = new HtmlUnitDriver();
	//starts at the main page
	static WebDriver jobsSearch = new HtmlUnitDriver();
	//starts at the jobs page
	
	
	@Before
	public void setUp() throws Exception {
		driver.get("http://stackoverflow.com/");
		jobsSearch.get("http://stackoverflow.com/jobs");
	}

	// Given that I am on the main page
	// When I view the title
	// Then I see that it contains the word "Stack Overflow"
	@Test
	public void testShowsCorrectTitle() {
		String title = driver.getTitle();
		assertTrue(title.contains("Stack Overflow"));
	}
	
	// Given that I am on the main page
	// When I view the header
	// Then I see that it contains "Jobs"
	@Test
	public void testHasCorrectHeaderLinks() 
	{
		try 
		{
			driver.findElement(By.linkText("Jobs"));
		} 
		catch (NoSuchElementException nseex) 
		{
			fail();
		}
	}
	
	//Given that the Jobs button is on the main page
	//When I click on it
	//Then I will be taken to another page titled "Job Listings - Stack Overflow"
	@Test
	public void testJobs()
	{
		try
		{
			driver.findElement(By.linkText("Jobs")).click();
			String newPageTitle = driver.getTitle();
			assertTrue(newPageTitle.contains("Job Listings - Stack Overflow"));
		}
		catch(NoSuchElementException nseex)
		{
			fail();
		}
	}
	
	//Given that there is a clickable Jobs button
	//When click on it
	//Then there should be a search jobs tab
	@Test
	public void testJobSearch()
	{
		try
		{
			driver.findElement(By.linkText("Jobs")).click();
			String newPageTitle = driver.getTitle();
			assertTrue(newPageTitle.contains("Job Listings - Stack Overflow"));
			jobsSearch.findElement(By.linkText("search jobs"));
		}
		catch(NoSuchElementException nseex)
		{
			fail();
		}
	}
	
	//Given that there is a clickable Jobs button
	//When click on it
	//Then there should be a search companies tab
	@Test
	public void testCompanySearch()
	{
		try
		{
			driver.findElement(By.linkText("Jobs")).click();
			//driver.findElement(By.linkText("new")).click();
			jobsSearch.findElement(By.linkText("search companies"));
			String newPageTitle = driver.getTitle();
			assertTrue(newPageTitle.contains("Job Listings - Stack Overflow"));
		}
		catch(NoSuchElementException nseex)
		{
			fail();
		}
	}

	// Given that I am on the jobs page
	// When I am looking for a job that requires a specific language
	// Then it should have a tag that labels what language is required
	@Test
	public void testHasDifferentLanguageTags() 
	{
		try 
		{
			jobsSearch.findElement(By.linkText("javascript"));
			jobsSearch.findElement(By.linkText("java"));
			jobsSearch.findElement(By.xpath("//*[contains(text(), 'php')]"));
			jobsSearch.findElement(By.xpath("//*[contains(text(), 'jquery')]"));
			jobsSearch.findElement(By.xpath("//*[contains(text(), 'html')]"));
			jobsSearch.findElement(By.xpath("//*[contains(text(), 'python')]"));
			//jobsSearch.findElement(By.linkText("jquery"));
			//jobsSearch.findElement(By.linkText("html"));
			//jobsSearch.findElement(By.linkText("python"));
		} 
		catch (NoSuchElementException nseex) 
		{
			fail();
		}
	}
}