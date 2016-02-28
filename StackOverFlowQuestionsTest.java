import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;

public class StackOverFlowQuestionsTest {

	
	static WebDriver driver = new HtmlUnitDriver();
	//starts at the main page
	static WebDriver questionsSearch = new HtmlUnitDriver();
	//starts at the questions page
	@Before
	public void setUp() throws Exception {
		driver.get("http://stackoverflow.com/");
		questionsSearch.get("http://stackoverflow.com/questions");
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
	// Then I see that it contains "Questions"
	@Test
	public void testHasCorrectHeaderLinks() 
	{
		try 
		{
			driver.findElement(By.linkText("Questions"));
		} 
		catch (NoSuchElementException nseex) 
		{
			fail();
		}
	}
	
	//Given that the Questions button is on the main page
	//When I click on it
	//Then I will be taken to another page titled "Newest Questions - Stack Overflow"
	@Test
	public void testQuestions()
	{
		try
		{
			driver.findElement(By.linkText("Questions")).click();
			String newPageTitle = driver.getTitle();
			assertTrue(newPageTitle.contains("Newest Questions - Stack Overflow"));
		}
		catch(NoSuchElementException nseex)
		{
			fail();
		}
	}
		
	
	//Given that there is a clickable questions button
	//When I click on it
	//Then there should be tabs on the screen labeled newest, featured, frequent, votes, active, and unanswered	
	@Test
	public void testQuestionsSearch()
	{
		try
		{
			driver.findElement(By.linkText("Questions")).click();
			String newPageTitle = driver.getTitle();
			assertTrue(newPageTitle.contains("Newest Questions - Stack Overflow"));
			questionsSearch.findElement(By.id("tabs"));
			questionsSearch.findElement(By.partialLinkText("newest"));
			questionsSearch.findElement(By.partialLinkText("featured"));
			questionsSearch.findElement(By.partialLinkText("frequent"));
			questionsSearch.findElement(By.partialLinkText("votes"));
			questionsSearch.findElement(By.partialLinkText("active"));
			questionsSearch.findElement(By.partialLinkText("unanswered"));
			//questionsSearch.findElement(By.linkText("Questions with the most links"));
		}
		catch(NoSuchElementException nseex)
		{
			fail();
		}
	}
	
	
}
