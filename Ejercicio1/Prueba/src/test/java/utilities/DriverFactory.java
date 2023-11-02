package utilities;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class DriverFactory {
	public static WebDriver open(String browser) {
		if (browser.equals("chrome")) {
			WebDriverManager.chromedriver().setup();
			return new ChromeDriver();
		} else if (browser.equals("firefox")) {
			WebDriverManager.firefoxdriver().setup();
			return new FirefoxDriver();
	    } else {
	        throw new IllegalArgumentException("Navegador no compatible: " + browser);
	    }
	}
}