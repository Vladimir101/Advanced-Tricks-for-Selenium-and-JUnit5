package synchronization;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;

import common.BaseTest;
import common.Synchro;

class StaleElement extends BaseTest
{
	@BeforeEach
	void setUp()
	{
		driver.get("https://duckduckgo.com/");
	}

	@Test
	void staleElement()
	{
		WebElement searchField = driver.findElement(By.name("q"));
		searchField.sendKeys("maven", Keys.ENTER);
		
		String searchTerm = searchField.getAttribute("value");
		assertEquals("maven", searchTerm);
	}
	
	@Test
	void fixingStaleElementException()
	{
		WebElement searchField = driver.findElement(By.name("q"));
		searchField.sendKeys("maven", Keys.ENTER);
// fix		
		Synchro sync = new Synchro(driver);
		searchField = sync.retry1(By.name("q")); // retry2()
// =========================================		
		String searchTerm = searchField.getAttribute("value");
		assertEquals("maven", searchTerm);
	}
}
