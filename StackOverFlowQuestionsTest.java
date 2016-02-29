import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;

/**
 * As a user,
 * I want to be able to search and post questions,
 * So that I can solve my problems
 */

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
	
	// Given that I am on the questions page
	// When I am looking for a question related to what I am looking at
	// Then it should have a tag that labels what language it is for
	@Test
	public void testHasDifferentLanguageTags() 
	{
		try 
		{
			questionsSearch.findElement(By.linkText("javascript"));
			questionsSearch.findElement(By.linkText("java"));
			questionsSearch.findElement(By.linkText("c#"));
			questionsSearch.findElement(By.linkText("php"));
			questionsSearch.findElement(By.linkText("jquery"));
			questionsSearch.findElement(By.linkText("html"));
			questionsSearch.findElement(By.linkText("python"));
		} 
		catch (NoSuchElementException nseex) 
		{
			fail();
		}
	}
	
	
	// Given that I am on the questions page
	// When I am looking for at a question
	// Then it should have a votes, views, and status as to it being answered	
	@Test
	public void testHasVotedAndAnsweres() 
	{
		try 
		{
			questionsSearch.findElement(By.className("votes"));
			questionsSearch.findElement(By.className("views"));
			questionsSearch.findElement(By.className("status"));
		} 
		catch (NoSuchElementException nseex) 
		{
			fail();
		}
	}
}
