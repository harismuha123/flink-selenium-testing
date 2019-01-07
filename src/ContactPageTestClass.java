import static org.junit.Assert.*;

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

public class ContactPageTestClass {
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
	public void goToContactPage() throws InterruptedException {
		driver.get("https://flink-webshop.herokuapp.com/");
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//body[not(contains(@class,'fade-in'))]")));
		/* Make sure that the hyperlink works */
		driver.findElement(By.className("main_menu")).findElement(By.linkText("Contact")).click();
		assertEquals("https://flink-webshop.herokuapp.com/#contact", driver.getCurrentUrl());
		Thread.sleep(2000);
		/* Make sure that the sidebar item is selected */
		String activeClass = driver.findElement(By.xpath("//*[@id=\"contact-link\"]")).findElement(By.xpath("..")).getAttribute("class");
		assertEquals("sale-noti", activeClass);
		/* Go back to the home page */
		driver.navigate().back();
		assertEquals("https://flink-webshop.herokuapp.com/#landing", driver.getCurrentUrl());
	}
	
	@Test
	public void testHeaderDisplay() throws InterruptedException {
		driver.get("https://flink-webshop.herokuapp.com/#contact");
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//body[not(contains(@class,'fade-in'))]")));	
		/* Assert page header and its background */
		WebElement header = driver.findElement(By.className("bg-title-page"));
		assertTrue(header.isDisplayed());
		assertEquals("url(\"images/flink-background-contact.jpg\");", header.getAttribute("style").split(" ")[1]);
		/* Assert page headline title */
		String headerTitle = driver.findElement(By.tagName("h2")).getText().trim();
		assertEquals("CONTACT", headerTitle);
		Thread.sleep(1000);
	}
	
	@Test
	public void testContactMap() throws InterruptedException {
		driver.get("https://flink-webshop.herokuapp.com/#contact");
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//body[not(contains(@class,'fade-in'))]")));	
		Thread.sleep(2000);
		driver.findElement(By.className("dismissButton")).click();
		/* Assert that the marker image exists */
		String xpath = "//*[@id=\"google_map\"]/div/div/div[1]/div[3]/div/div[3]/div";
		assertEquals("	", driver.findElement(By.xpath(xpath)).findElement(By.tagName("img")).getAttribute("src"));
		/* Assert that the popup appears correctly */
		driver.findElement(By.xpath(xpath)).click();
		Thread.sleep(2000);
		assertTrue(driver.findElement(By.className("infobox")).isDisplayed());
		/* Assert that the popup can be closed */
		driver.findElement(By.xpath("//button[@title='Close']")).click();
		assertFalse(driver.findElements(By.className("infobox")).size() != 0);
		Thread.sleep(2000);
	}
	
	@Test
	public void testContactFormPositive() throws InterruptedException {
		driver.get("https://flink-webshop.herokuapp.com/#contact");
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//body[not(contains(@class,'fade-in'))]")));	
		/* Assert the contact form text is displayed */
		assertEquals("Send us a message", driver.findElement(By.xpath("//form[@class='leave-comment']/h4")).getText());
		/* Assert that a message can be sent */
		driver.findElement(By.id("full-name")).sendKeys("Aldin Kovacevic");
		driver.findElement(By.id("phone-number")).sendKeys("0605532242");
		driver.findElement(By.id("email-address")).sendKeys("aldin@tribeos.io");
		driver.findElement(By.id("message-area")).sendKeys("Testing message sending...");
		Thread.sleep(2000);
		driver.findElement(By.id("send-contact-form")).click();
		wait.until(ExpectedConditions.presenceOfElementLocated(By.className("swal-modal")));
		assertEquals("Message sent!", driver.findElement(By.className("swal-title")).getText());
		assertEquals("Thank you for contacting us. We will get back to you soon.", driver.findElement(By.className("swal-text")).getText());
		Thread.sleep(2000);
	}
	
	@Test
	public void testContactFormNegative() throws InterruptedException {
		driver.get("https://flink-webshop.herokuapp.com/#contact");
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//body[not(contains(@class,'fade-in'))]")));	
		/* Assert that a message will not be sent, due to missing data */
		Thread.sleep(2000);
		driver.findElement(By.id("send-contact-form")).click();
		wait.until(ExpectedConditions.presenceOfElementLocated(By.className("swal-modal")));
		assertEquals("Sending failed!", driver.findElement(By.className("swal-title")).getText());
		assertEquals("You did not provide appropriate contact information.", driver.findElement(By.className("swal-text")).getText());
		Thread.sleep(2000);
	}

	@After
	public void tearDown() throws Exception {
		driver.close();
	}

}
