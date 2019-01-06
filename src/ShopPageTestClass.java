import static org.junit.Assert.*;

import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ShopPageTestClass {
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
	public void testGoToShopPage() throws InterruptedException {
		driver.get("https://flink-webshop.herokuapp.com/");
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//body[not(contains(@class,'fade-in'))]")));
		/* Make sure that the hyperlink works */
		driver.findElement(By.className("main_menu")).findElement(By.linkText("Shop")).click();
		assertEquals("https://flink-webshop.herokuapp.com/#shop", driver.getCurrentUrl());
		Thread.sleep(2000);
		/* Make sure that the sidebar item is selected */
		String activeClass = driver.findElement(By.xpath("//*[@id=\"shop-link\"]")).findElement(By.xpath("..")).getAttribute("class");
		assertEquals("sale-noti", activeClass);
	}
	
	@Test
	public void testHeaderDisplay() throws InterruptedException {
		driver.get("https://flink-webshop.herokuapp.com/#shop");
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//body[not(contains(@class,'fade-in'))]")));	
		/* Assert page header and its background */
		WebElement header = driver.findElement(By.className("bg-title-page"));
		assertTrue(header.isDisplayed());
		assertEquals("url(\"images/shop-background.jpg\");", header.getAttribute("style").split(" ")[1]);
		/* Assert page headline title */
		String headerTitle = driver.findElement(By.tagName("h2")).getText().trim();
		assertEquals("SHOP", headerTitle);
		Thread.sleep(1000);
		
	}
	
	@Test
	public void testProductCategories() throws InterruptedException {
		driver.get("https://flink-webshop.herokuapp.com/#shop");
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//body[not(contains(@class,'fade-in'))]")));
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id=\"shop\"]/section[2]/div/div/div[1]/div/ul/li[1]")));
		/* Assert presence of all menu items and listing of associated items */
		// Thermostat category
		String xpath = "//*[@id=\"shop\"]/section[2]/div/div/div[1]/div/ul/li[2]/span";
		driver.findElement(By.xpath(xpath)).click();
		wait.until(ExpectedConditions.textToBePresentInElementLocated(By.id("result-count"), "Showing 4 results"));
		Thread.sleep(2000);
		assertEquals("Showing 4 results.", driver.findElement(By.id("result-count")).getText());
		assertEquals(4, driver.findElements(By.className("block2")).size());
		assertEquals("highlight", driver.findElement(By.xpath(xpath)).getAttribute("class").split(" ")[2]);
		// Smart Home category
		xpath = "//*[@id=\"shop\"]/section[2]/div/div/div[1]/div/ul/li[3]/span";
		driver.findElement(By.xpath(xpath)).click();
		wait.until(ExpectedConditions.textToBePresentInElementLocated(By.id("result-count"), "Showing 1 result"));
		Thread.sleep(2000);
		assertEquals("Showing 1 result.", driver.findElement(By.id("result-count")).getText());
		assertEquals(1, driver.findElements(By.className("block2")).size());
		assertEquals("highlight", driver.findElement(By.xpath(xpath)).getAttribute("class").split(" ")[2]);
		// All categories
		xpath = "//*[@id=\"shop\"]/section[2]/div/div/div[1]/div/ul/li[1]/span";
		driver.findElement(By.xpath(xpath)).click();
		wait.until(ExpectedConditions.textToBePresentInElementLocated(By.id("result-count"), "Showing 5 results"));
		assertEquals(5, driver.findElements(By.className("block2")).size());
		Thread.sleep(2000);
		assertEquals("Showing 5 results.", driver.findElement(By.id("result-count")).getText());
		assertEquals("highlight", driver.findElement(By.xpath(xpath)).getAttribute("class").split(" ")[2]);
	}
	
	@Test
	public void testProductFilters() throws InterruptedException {
		driver.get("https://flink-webshop.herokuapp.com/#shop");
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//body[not(contains(@class,'fade-in'))]")));
		wait.until(ExpectedConditions.textToBePresentInElementLocated(By.id("result-count"), "Showing 5 results"));
		/* Assert price ranges on the slider */
		List<WebElement> sliderEnds = driver.findElements(By.xpath("//div[@aria-valuetext]"));
		assertEquals("0.00", sliderEnds.get(0).getAttribute("aria-valuetext"));
		assertEquals("200.00", sliderEnds.get(1).getAttribute("aria-valuetext"));
		Thread.sleep(2000);
		/* Assert that the slider works */
		Actions builder = new Actions(driver);
		Action moveSlider = builder.moveToElement(sliderEnds.get(0))
								   .clickAndHold().moveByOffset(100, 0).release()
								   .moveToElement(sliderEnds.get(1))
								   .clickAndHold().moveByOffset(-100, 0).release()
								   .build();
		moveSlider.perform();
		assertEquals("84", driver.findElement(By.id("value-lower")).getText());
		assertEquals("116", driver.findElement(By.id("value-upper")).getText());
		Thread.sleep(2000);
		/* Assert that items get filtered and displayed appropriately */
		driver.findElement(By.id("filter-price")).click();
		wait.until(ExpectedConditions.textToBePresentInElementLocated(By.id("result-count"), "Showing 2 results."));
		assertEquals(2, driver.findElements(By.className("block2")).size());
		Thread.sleep(2000);
	}
	
	@Test
	public void testIndividualProductDetails() throws InterruptedException {
		driver.get("https://flink-webshop.herokuapp.com/#shop");
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//body[not(contains(@class,'fade-in'))]")));
		wait.until(ExpectedConditions.textToBePresentInElementLocated(By.id("result-count"), "Showing 5 results"));
		/* Assert all product properties */
		WebElement productBlock = driver.findElement(By.xpath("//*[@id=\"filtered-product-list\"]/div[1]/div"));
		String productImg = productBlock.findElement(By.tagName("img")).getAttribute("src");
		String productName = productBlock.findElement(By.className("block2-name")).getText();
		String productPrice = productBlock.findElement(By.className("block2-price")).getText();
		assertEquals("http://media2.govtech.com/images/940*704/nest+thermostat.jpg", productImg);
		assertEquals("FT-100", productName);
		assertEquals("50.00 BAM", productPrice);
		Thread.sleep(2000);
	}
	
	@Test
	public void testIndividualProductLink() throws InterruptedException {
		driver.get("https://flink-webshop.herokuapp.com/#shop");
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//body[not(contains(@class,'fade-in'))]")));
		wait.until(ExpectedConditions.textToBePresentInElementLocated(By.id("result-count"), "Showing 5 results"));
		/* Assert that product link works */
		WebElement productBlock = driver.findElement(By.xpath("//*[@id=\"filtered-product-list\"]/div[1]/div"));
		productBlock.findElement(By.className("block2-name")).click();
		wait.until(ExpectedConditions.presenceOfElementLocated(By.className("product-detail-name")));
		/* Assert current location */
		assertEquals("https://flink-webshop.herokuapp.com/#product", driver.getCurrentUrl());
		assertEquals("sale-noti", driver.findElement(By.xpath("//*[@id=\"product-link\"]")).findElement(By.xpath("..")).getAttribute("class"));
		assertEquals("FT-100", driver.findElement(By.id("bread-product")).getText());
		assertEquals("FT-100", driver.findElement(By.className("product-detail-name")).getText());
		Thread.sleep(2000);
	}
	
	@Test
	public void testIndividualProductToCart() throws InterruptedException {
		driver.get("https://flink-webshop.herokuapp.com/#shop");
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//body[not(contains(@class,'fade-in'))]")));
		wait.until(ExpectedConditions.textToBePresentInElementLocated(By.id("result-count"), "Showing 5 results"));
		/* Assert that product is properly added to the cart */
		WebElement productBlock = driver.findElement(By.xpath("//*[@id=\"filtered-product-list\"]/div[1]/div"));
		Actions builder = new Actions(driver);
		builder.moveToElement(productBlock).pause(1000).perform();
		productBlock.findElement(By.tagName("button")).click();
		Thread.sleep(2000);
		assertEquals("FT-100", driver.findElement(By.className("swal-title")).getText());
		assertEquals("has been added to cart!", driver.findElement(By.className("swal-text")).getText());
		driver.findElement(By.className("swal-button")).click();
		Thread.sleep(2000);
		assertEquals("1", driver.findElement(By.id("cart-notification")).getText());
		Thread.sleep(2000);
	}
	
	@After
	public void tearDown() throws Exception {
		driver.close();
	}
}
