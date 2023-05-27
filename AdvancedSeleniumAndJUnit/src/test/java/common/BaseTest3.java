package common;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

@ExtendWith(ScreenshotOnFailure3.class)
public class BaseTest3
{
	protected WebDriver driver;

	@BeforeEach
	void setUpBase()
	{
		driver = new ChromeDriver();
		ScreenshotOnFailure3.setDriver(driver);
		driver.manage().window().maximize();
	}

	@AfterEach
	void tearDown()
	{
		driver.quit();
	}
}
