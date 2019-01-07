import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class AboutPageTestClass {
	private WebDriver driver;
	private WebDriverWait wait;
	
	@Before
	public void setUp() throws Exception {
		String path = "/usr/local/bin/chromedriver";
		System.setProperty("webdriver.chrome.driver", path);
		ChromeOptions options = new ChromeOptions();
		options.addArguments("--start-maximized");
		driver = new ChromeDriver(options);
		wait = new WebDriverWait(driver, 20);
	}
	
	@Test
	public void goToAboutPage() throws InterruptedException {
		driver.get("https://flink-webshop.herokuapp.com/");
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//body[not(contains(@class,'fade-in'))]")));
		/* Make sure that the hyperlink works */
		driver.findElement(By.className("main_menu")).findElement(By.linkText("About")).click();
		assertEquals("https://flink-webshop.herokuapp.com/#about", driver.getCurrentUrl());
		Thread.sleep(2000);
		/* Make sure that the sidebar item is selected */
		String activeClass = driver.findElement(By.xpath("//*[@id=\"about-link\"]")).findElement(By.xpath("..")).getAttribute("class");
		assertEquals("sale-noti", activeClass);
	}
	
	@Test
	public void testHeaderDisplay() throws InterruptedException {
		driver.get("https://flink-webshop.herokuapp.com/#about");
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//body[not(contains(@class,'fade-in'))]")));	
		/* Assert page header and its background */
		WebElement header = driver.findElement(By.className("bg-title-page"));
		assertTrue(header.isDisplayed());
		assertEquals("url(\"images/about-us.jpg\");", header.getAttribute("style").split(" ")[1]);
		/* Assert page headline title */
		String headerTitle = driver.findElement(By.tagName("h2")).getText().trim();
		assertEquals("ABOUT", headerTitle);
		Thread.sleep(1000);
		
	}

	@After
	public void tearDown() throws Exception {
		driver.close();
	}
}
