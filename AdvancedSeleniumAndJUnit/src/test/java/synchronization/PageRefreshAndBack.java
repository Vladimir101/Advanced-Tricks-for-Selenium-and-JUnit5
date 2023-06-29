package synchronization;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;

import common.BaseTest;
import common.Synchro;

class PageRefreshAndBack extends BaseTest
{
	private String targetTitle = "Target : Expect More. Pay Less.";
	
	@BeforeEach
	void setUp() throws Exception
	{
		driver.navigate().to("https://www.target.com/");
	}

	@Test
	void noSuchElement()
	{
		WebElement searchField = driver.findElement(By.name("searchTerm"));
		searchField.sendKeys("milk", Keys.ENTER);
		
		driver.navigate().refresh();

		WebElement searchResults = 
				driver.findElement(By.xpath("//div[1]/div[3]/div[1]/h2[1]"));
		assertTrue(searchResults.getText().contains("results for “milk”"));

		driver.navigate().back();
		assertEquals(targetTitle, driver.getTitle());
	}

	@Test
	void testWithSleep() throws InterruptedException
	{
		WebElement searchField = driver.findElement(By.name("searchTerm"));
		searchField.sendKeys("milk", Keys.ENTER);
		
		Thread.sleep(4000);
		driver.navigate().refresh();
		
		Thread.sleep(4000);
		WebElement searchResults = 
				driver.findElement(By.xpath("//div[1]/div[3]/div[1]/h2[1]"));
		assertTrue(searchResults.getText().contains("results for “milk”"));
		
		driver.navigate().back();
		Thread.sleep(2000);
		assertEquals(targetTitle, driver.getTitle());
	}
	
	@Test
	void testWithSynchronization()
	{
		WebElement searchField = driver.findElement(By.name("searchTerm"));
		searchField.sendKeys("milk", Keys.ENTER);
		
// fix
		Synchro sync = new Synchro(driver);
		sync.pageLoaded("Milk : Target");
// =========================================		
		driver.navigate().refresh();

// fix		
		WebElement searchResults = 
				sync.findElement(By.xpath("//div[1]/div[3]/div[1]/h2[1]"));
// =========================================		
		assertTrue(searchResults.getText().contains("results for “milk”"));

		driver.navigate().back();
		
// fix		
		sync.pageLoaded(targetTitle);
// ==========================================		
		assertEquals(targetTitle, driver.getTitle());
	}
}