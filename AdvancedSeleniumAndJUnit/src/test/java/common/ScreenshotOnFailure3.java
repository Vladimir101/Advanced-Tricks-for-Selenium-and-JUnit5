package common;

import java.io.File;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.junit.jupiter.api.extension.AfterTestExecutionCallback;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.io.FileHandler;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

class ScreenshotOnFailure3 implements AfterTestExecutionCallback
{
	private static WebDriver driver;
	
	public static void setDriver(WebDriver driver)
	{
		ScreenshotOnFailure3.driver = driver;
	}

	public void afterTestExecution(ExtensionContext context) throws Exception
	{
		if (context.getExecutionException().isPresent())
		{
			TakesScreenshot ts = (TakesScreenshot) driver;
			FileHandler.copy(ts.getScreenshotAs(OutputType.FILE), 
					new File("failed_page" + getTimestamp() + ".png"));
		}
		System.out.println("\"" + context.getDisplayName() + "\" test failed!");
	}

	private String getTimestamp()
	{
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("MMdd_HHmmss");
		LocalDateTime now = LocalDateTime.now();
		return dtf.format(now);
	}
}
