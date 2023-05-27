package cookies;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.*;
import org.openqa.selenium.Cookie;

import common.BaseTestCookies;

class LoginWithCookies extends BaseTestCookies
{	
	@BeforeEach
	void setUp()
	{
		driver.get("https://the-internet.herokuapp.com/login");
	}

	@Test
	void loginWithCookies() throws InterruptedException
	{
// !!! Delete any existing cookies to ensure a clean session
		driver.manage().deleteAllCookies();
		for (Cookie cookie : cookies)
		{
			System.out.println((cookie.getName() + " = " 
					+ cookie.getValue()));
// Add the stored cookies to the WebDriver instance
			driver.manage().addCookie(cookie);
		}
// Navigate to the desired page
		driver.get("https://the-internet.herokuapp.com/secure");
		
		Thread.sleep(3000);
	}
}
