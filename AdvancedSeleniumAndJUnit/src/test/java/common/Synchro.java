package common;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Synchro
{
	private WebDriver driver;
	
	public Synchro(WebDriver driver)
	{
		this.driver = driver;
	}
	
// solving stale element	
	public WebElement retry1(By locator)
	{
		WebElement element = null;
		for (int i = 0; i < 5; i++)
		{
			try
			{
				element = driver.findElement(locator);
				break;
			}
			catch (StaleElementReferenceException e)
			{
				e.printStackTrace();
			}
		}
		return element;
	}
	
	public WebElement retry2(By locator)
	{
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		return wait.until(ExpectedConditions.refreshed(
                ExpectedConditions.presenceOfElementLocated(locator)));
	}
	
// solving refresh and back	
	public void pageLoaded(String title)
	{
		new WebDriverWait(driver, Duration.ofSeconds(15))
			.until(ExpectedConditions.titleIs(title)); 
	}
	
	public WebElement findElement(By by)
	{
		return new WebDriverWait(driver, Duration.ofSeconds(10))
				.until(ExpectedConditions.presenceOfElementLocated(by));
	}
}
