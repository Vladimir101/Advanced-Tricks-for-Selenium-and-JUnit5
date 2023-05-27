// https://junit.org/junit5/docs/current/api/org.junit.jupiter.api/org/junit/jupiter/api/extension/package-summary.html
package common;

import java.io.File;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.api.extension.TestExecutionExceptionHandler;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.io.FileHandler;

class ScreenshotOnFailure1 implements TestExecutionExceptionHandler
{
	private static WebDriver driver;
	
	public static void setDriver(WebDriver driver)
	{
		ScreenshotOnFailure1.driver = driver;
	}
	

	public void handleTestExecutionException(ExtensionContext context, 
									Throwable throwable) throws Throwable
	{
		TakesScreenshot ts = (TakesScreenshot) driver;
		FileHandler.copy(ts.getScreenshotAs(OutputType.FILE), 
				new File("failed_page" + getTimestamp() + ".png"));
		System.out.println("\"" + context.getDisplayName() + "\" test failed!");
		
		throw throwable;
	}
	
	private String getTimestamp()
	{
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("MMdd_HHmmss");
		LocalDateTime now = LocalDateTime.now();
		return dtf.format(now);
	}
}
