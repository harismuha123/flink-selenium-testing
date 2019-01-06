import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.google.common.collect.ImmutableBiMap.Builder;

public class ShoppingCartPopupTestClass {
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
		
		/* Get the default single-page application */
		driver.get("https://flink-webshop.herokuapp.com/");
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//body[not(contains(@class,'fade-in'))]")));
	}
	
	@Test
	public void testEmptyCart() throws InterruptedException {
		driver.findElement(By.xpath("//div[@title='Cart']")).click();
		assertEquals("0", driver.findElement(By.id("cart-notification")).getText());
		Thread.sleep(2000);
		/* Empty cart text */
		String emptyText = driver.findElement(By.xpath("/html/body/header/div[1]/div[1]/div[1]/div/div/ul/p")).getText();
		assertEquals("Your cart is currently empty.", emptyText);
		/* Empty cart total */
		String emptyTotal = driver.findElement(By.className("header-cart-total")).getText();
		assertEquals("Totals: 0.00 BAM", emptyTotal);
	}
	
	@Test
	public void testCartWithItems() throws InterruptedException {
		String xpath = "//*[@id=\"landing\"]/section[3]/div/div[2]/div/div/div/div[7]/div/div[1]/div/div";
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(xpath)));
		Actions builder = new Actions(driver);
		/* Add two items to the cart */
		Action addToCart = builder.moveToElement(driver.findElement(By.xpath(xpath))).pause(1000).click().build();
		addToCart.perform();
		Thread.sleep(2000);
		driver.findElement(By.className("swal-button")).click();
		addToCart.perform();
		Thread.sleep(2000);
		driver.findElement(By.className("swal-button")).click();
		/* Open the cart */
		driver.findElement(By.xpath("//div[@title='Cart']")).click();
		Thread.sleep(2000);
		/* Assert all item attributes */
		assertEquals("2", driver.findElement(By.id("cart-notification")).getText());
		String img = driver.findElement(By.xpath("//div[@class='header-cart-item-img']/img")).getAttribute("src");
		String itemName = driver.findElement(By.className("header-cart-item-name")).getText();
		String info = driver.findElement(By.className("header-cart-item-info")).getText();
		String totalPrice = driver.findElement(By.className("header-cart-total")).getText();
		assertEquals("http://media2.govtech.com/images/940*704/nest+thermostat.jpg", img);
		assertEquals("FT-200", itemName);
		assertEquals("2 x 90.00 BAM", info);
		assertEquals("Totals: 180.00 BAM", totalPrice);
		/* Remove one item from the cart an re-assert */
		driver.findElement(By.xpath("//div[@class='header-cart-item-img']")).click();
		info = driver.findElement(By.className("header-cart-item-info")).getText();
		totalPrice = driver.findElement(By.className("header-cart-total")).getText();
		Thread.sleep(2000);
		assertEquals("1 x 90.00 BAM", info);
		assertEquals("Totals: 90.00 BAM", totalPrice);
		/* Clear the cart and re-assert */
		driver.findElement(By.xpath("//div[@class='header-cart-item-img']")).click();
		Thread.sleep(2000);
		String emptyText = driver.findElement(By.xpath("/html/body/header/div[1]/div[1]/div[1]/div/div/ul/p")).getText();
		String emptyTotal = driver.findElement(By.className("header-cart-total")).getText();
		assertEquals("Your cart is currently empty.", emptyText);
		assertEquals("Totals: 0.00 BAM", emptyTotal);
	}
	
	@Test
	public void testCartItemLink() throws InterruptedException {
		String xpath = "//*[@id=\"landing\"]/section[3]/div/div[2]/div/div/div/div[7]/div/div[1]/div/div";
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(xpath)));
		Actions builder = new Actions(driver);
		/* Add two items to the cart */
		Action addToCart = builder.moveToElement(driver.findElement(By.xpath(xpath))).pause(1000).click().build();
		addToCart.perform();
		Thread.sleep(2000);
		driver.findElement(By.className("swal-button")).click();
		driver.findElement(By.xpath("//div[@title='Cart']")).click();
		Thread.sleep(2000);
		/* Go to product page from cart */
		driver.findElement(By.className("header-cart-item-name")).click();
		wait.until(ExpectedConditions.presenceOfElementLocated(By.className("product-detail-name")));
		Thread.sleep(2000);
		/* Assert current location */
		assertEquals("https://flink-webshop.herokuapp.com/#product", driver.getCurrentUrl());
		assertEquals("sale-noti", driver.findElement(By.xpath("//*[@id=\"product-link\"]")).findElement(By.xpath("..")).getAttribute("class"));
		assertEquals("FT-200", driver.findElement(By.id("bread-product")).getText());
		assertEquals("FT-200", driver.findElement(By.className("product-detail-name")).getText());
	}
	
	@Test
	public void testProceedToCartFromPopup() throws InterruptedException {
		driver.findElement(By.xpath("//div[@title='Cart']")).click();
		Thread.sleep(1000);
		driver.findElement(By.className("popup-cart-button")).click();
		Thread.sleep(2000);
		/* Assert re-direction to 'Cart' page */ 
		wait.until(ExpectedConditions.presenceOfElementLocated(By.className("bg-title-page")));
		assertEquals("https://flink-webshop.herokuapp.com/#cart", driver.getCurrentUrl());
		assertEquals("sale-noti", driver.findElement(By.xpath("//*[@id=\"cart-link\"]")).findElement(By.xpath("..")).getAttribute("class"));
	}

	@After
	public void tearDown() throws Exception {
		driver.close();
	}
}
