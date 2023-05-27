package common;

import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Optional;

import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.api.extension.TestWatcher;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.io.FileHandler;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

class ScreenshotOnFailure2 implements TestWatcher
{
	private static WebDriver driver;
	
	public static void setDriver(WebDriver driver)
	{
		ScreenshotOnFailure2.driver = driver;
	}
	
	public void testAborted(ExtensionContext context, Throwable cause)
	{
		driver.quit();
	}
	
	public void testDisabled(ExtensionContext context, Optional<String> reason)
	{
		driver.quit();
	}
	
	public void testFailed(ExtensionContext context, Throwable cause)
	{
		TakesScreenshot ts = (TakesScreenshot)driver;
		try
		{
			FileHandler.copy(ts.getScreenshotAs(OutputType.FILE), 
					new File("failed_page" + getTimestamp() + ".png"));
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
		System.out.println("\"" + context.getDisplayName() + "\" test failed!");
		
		driver.quit();
	}
	
	public void testSuccessful(ExtensionContext context)
	{
		driver.quit();
	}
	
	private String getTimestamp()
	{
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("MMdd_HHmmss");
		LocalDateTime now = LocalDateTime.now();
		return dtf.format(now);
	}
}
