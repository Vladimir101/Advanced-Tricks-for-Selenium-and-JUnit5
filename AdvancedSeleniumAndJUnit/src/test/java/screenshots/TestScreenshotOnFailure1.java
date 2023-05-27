package screenshots;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

import common.BaseTest1;

class TestScreenshotOnFailure1 extends BaseTest1
{
	@Test
	void somethingWrongWithAmazon()
	{
		driver.get("https://www.amazon.com/");
		fail("Amazon");
	}
	
	@Test
	void somethingWrongWithTarget()
	{
		driver.get("https://www.target.com/");
		fail("Target");
	}
}
