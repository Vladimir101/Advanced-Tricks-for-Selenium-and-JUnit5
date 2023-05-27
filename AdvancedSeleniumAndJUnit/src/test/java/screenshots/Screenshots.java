package screenshots;

import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.io.FileHandler;

import common.BaseTest;
import ru.yandex.qatools.ashot.AShot;
import ru.yandex.qatools.ashot.Screenshot;
import ru.yandex.qatools.ashot.shooting.ShootingStrategies;

class Screenshots extends BaseTest
{
	@BeforeEach
	void setUp() throws Exception
	{
		driver.get("https://www.amazon.com/");
	}

	@Test
	void screenshotOfEntireArea() throws IOException
	{
		Screenshot entirePage = new AShot()
				.shootingStrategy(ShootingStrategies.viewportPasting(1000)) // time between scrolls in milliseconds
				.takeScreenshot(driver);
		ImageIO.write(entirePage.getImage(), "png", new File("entire_area.png"));
	}
	
	@Test
	void screenshotOfWebElement() throws IOException
	{
		WebElement logo = driver.findElement(By.id("nav-logo-sprites"));
		File file = logo.getScreenshotAs(OutputType.FILE);
		FileHandler.copy(file, new File("web_element_logo.png"));
	}
	
}
