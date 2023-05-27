package common;

import java.util.Set;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class BaseTestCookies
{
	protected WebDriver driver;
	protected static Set<Cookie> cookies;
	@BeforeEach
	public void setUpBase()
	{
		driver = new ChromeDriver();
		driver.manage().window().maximize();
	}

	@Test
	void getCookies()
	{
		driver.findElement(By.id("username")).sendKeys("tomsmith");
		driver.findElement(By.id("password")).sendKeys("SuperSecretPassword!");
		driver.findElement(By.className("radius")).click();
		cookies = driver.manage().getCookies();
	}
	
	@AfterEach
	void tearDown()
	{
		driver.quit();
	}
}
