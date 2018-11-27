package SeleniumHelpers;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class DriverHelper {
	
	public static WebDriver getDriver(String str) {
		if(str.toLowerCase() == "chrome") {
			System.setProperty("webdriver.chrome.driver", "C:\\selenium\\chromedriver.exe");
			return new ChromeDriver();
		}
		else if(str.toLowerCase() == "firefox") {
	        System.setProperty("webdriver.firefox.marionette", "C:\\selenium\\geckodriver.exe");
			return new FirefoxDriver();
		}
		return null;
	}
}
