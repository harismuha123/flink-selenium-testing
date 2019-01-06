import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class FooterTestClass {
	private WebDriver driver;
	private WebDriverWait wait;

	@Before
	public void setUp() throws Exception {
		String path = "/home/chern0/eclipse/java-2018-09/chromedriver";
		System.setProperty("webdriver.chrome.driver", path);
		ChromeOptions options = new ChromeOptions();
		options.addArguments("--start-maximized");
		driver = new ChromeDriver(options);
		wait = new WebDriverWait(driver, 20);
		
		/* Get the default single-page application */
		driver.get("https://flink-webshop.herokuapp.com/");
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//body[not(contains(@class,'fade-in'))]")));
		// driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	}
	
	@Test
	public void testFooterColumns() throws Exception {
		/* Test existence of footer columns */
		WebElement footerColumn1 = driver.findElement(By.xpath("/html/body/div[1]/footer/div[1]/div[1]"));
		assertEquals("GET IN TOUCH", footerColumn1.findElement(By.tagName("h4")).getText().trim());
		WebElement footerColumn2 = driver.findElement(By.xpath("/html/body/div[1]/footer/div[1]/div[2]"));
		assertEquals("LINKS", footerColumn2.findElement(By.tagName("h4")).getText().trim());
		WebElement footerColumn3 = driver.findElement(By.xpath("/html/body/div[1]/footer/div[1]/div[3]"));
		assertEquals("OUR APPS", footerColumn3.findElement(By.tagName("h4")).getText().trim());
		Thread.sleep(2000);
	}
	
	@Test
	public void testSocialMediaLinks() throws Exception {
		WebElement footer = driver.findElement(By.tagName("footer"));
		/* Test Facebook link */
		JavascriptExecutor js = (JavascriptExecutor)driver;
		js.executeScript("document.getElementsByTagName('footer')[0].getElementsByTagName('a')[0].setAttribute('target','_self');");
		Thread.sleep(1000);
		footer.findElement(By.className("fa-facebook")).click();
		Thread.sleep(1000);
		assertEquals("https://www.facebook.com/Flink-1528897597158926", driver.getCurrentUrl());
		driver.navigate().back();
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//body[not(contains(@class,'fade-in'))]")));
		/* Test Instagram link */
		footer = driver.findElement(By.tagName("footer"));
		js.executeScript("document.getElementsByTagName('footer')[0].getElementsByTagName('a')[1].setAttribute('target','_self');");
		Thread.sleep(1000);
		footer.findElement(By.className("fa-instagram")).click();
		Thread.sleep(1000);
		assertEquals("https://www.instagram.com/flink_home/", driver.getCurrentUrl());
		driver.navigate().back();
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//body[not(contains(@class,'fade-in'))]")));
		/* Test LinkedIn link */
		footer = driver.findElement(By.tagName("footer"));
		js.executeScript("document.getElementsByTagName('footer')[0].getElementsByTagName('a')[2].setAttribute('target','_self');");
		Thread.sleep(1000);
		footer.findElement(By.className("fa-linkedin")).click();
		Thread.sleep(1000);
		assertEquals("https://www.linkedin.com/company/bsmart", driver.getCurrentUrl());
		driver.navigate().back();
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//body[not(contains(@class,'fade-in'))]")));
	}
	
	@Test
	public void testQuickLinks() throws Exception {
		driver.findElement(By.linkText("Shop".trim())).click();
		assertEquals("https://flink-webshop.herokuapp.com/#shop", driver.getCurrentUrl());
		Thread.sleep(1000);
		driver.findElement(By.linkText("About Us".trim())).click();
		assertEquals("https://flink-webshop.herokuapp.com/#about", driver.getCurrentUrl());
		Thread.sleep(1000);
		driver.findElement(By.linkText("Contact Us".trim())).click();
		assertEquals("https://flink-webshop.herokuapp.com/#contact", driver.getCurrentUrl());
		Thread.sleep(1000);
	}
	
	@Test
	public void testAppLinks() throws Exception {
		WebElement footer = driver.findElement(By.tagName("footer"));
		/* Test Android application link */
		JavascriptExecutor js = (JavascriptExecutor)driver;
		js.executeScript("document.getElementsByTagName('footer')[0].getElementsByTagName('a')[6].setAttribute('target','_self');");
		Thread.sleep(1000);
		footer.findElement(By.className("fa-android")).click();
		Thread.sleep(1000);
		assertEquals("https://play.google.com/store/apps/details?id=enver.flinkhomev1", driver.getCurrentUrl());
		driver.navigate().back();
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//body[not(contains(@class,'fade-in'))]")));
		/* Test iOS application link */
		footer = driver.findElement(By.tagName("footer"));
		js.executeScript("document.getElementsByTagName('footer')[0].getElementsByTagName('a')[7].setAttribute('target','_self');");
		Thread.sleep(1000);
		footer.findElement(By.className("fa-apple")).click();
		Thread.sleep(1000);
		assertEquals("https://itunes.apple.com/au/app/flink-home/id1353775850?mt=8", driver.getCurrentUrl());
		driver.navigate().back();
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//body[not(contains(@class,'fade-in'))]")));

	}

	@After
	public void tearDown() throws Exception {
		driver.close();
	}

}
