package common;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

@ExtendWith(ScreenshotOnFailure2.class)
public class BaseTest2
{
	protected WebDriver driver;

	@BeforeEach
	void setUpBase() 
	{
		driver = new ChromeDriver();
		ScreenshotOnFailure2.setDriver(driver);
		driver.manage().window().maximize();
	}
}
