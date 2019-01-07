import static org.junit.Assert.*;

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
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class CartTestClass {

	String path;
	WebDriver driver;
	String defaultUrl;
	WebDriverWait wait;
	
	@Before
	public void setUp() throws Exception {
		path = "/usr/local/bin/chromedriver";
		System.setProperty("webdriver.chrome.driver", path);
		
		ChromeOptions options = new ChromeOptions();
		
		options.addArguments("--start-maximized");
		
		driver = new ChromeDriver(options);
		
		wait = new WebDriverWait(driver, 60);
		
		defaultUrl = "https://flink-webshop.herokuapp.com/";
		
		System.out.println("Main screen test suite\n");
		
		driver.get("https://flink-webshop.herokuapp.com/");
		
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	}

	@Test
	public void EmptyCartTest() {
		driver.findElement(By.id("cart-link")).click();
		
		assertEquals(defaultUrl + "#cart", driver.getCurrentUrl());
		
		assertTrue(driver.findElement(By.xpath("//*[@id=\"cart\"]/section[2]/div/div[1]/div/table/tr/td[1]/p")).isDisplayed());
		
		assertTrue(driver.findElement(By.xpath("//*[@id=\"cart-subtotals\"]")).getText().equals("0.00 BAM"));
		assertTrue(driver.findElement(By.xpath("//*[@id=\"total-price\"]")).getText().equals("0.00 BAM"));
		
		assertTrue(driver.findElement(By.xpath("//*[@id=\"cart\"]/section[2]/div/div[1]/div/table/tr/td[2]/div/button")).isDisplayed());
		driver.findElement(By.xpath("//*[@id=\"cart\"]/section[2]/div/div[1]/div/table/tr/td[2]/div/button")).click();
		
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		assertEquals(defaultUrl + "#shop", driver.getCurrentUrl());
		
	}
	
	@Test
	public void NotEmptyCartTest() {
		
		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//*[@id=\\\"landing\\\"]/section[3]/div/div[2]/div/div/div/div[7]/div/div[1]/div")));
		
		WebElement item = driver.findElement(By.xpath("//*[@id=\"landing\"]/section[3]/div/div[2]/div/div/div/div[7]/div/div[1]/div"));
		
		JavascriptExecutor js = (JavascriptExecutor) driver;
		
		js.executeScript("arguments[0].scrollIntoView();", item);
		
		Actions builder = new Actions(driver);
		
		Action hoverOverItem = builder.moveToElement(item).pause(1000).build();
		
		hoverOverItem.perform();
		
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
				
		driver.findElement(By.xpath("//*[@id=\"landing\"]/section[3]/div/div[2]/div/div/div/div[7]/div/div[1]/div/div/button")).click();
		
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		assertTrue(driver.findElement(By.className("swal-modal")).isDisplayed());
		
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		driver.findElement(By.className("swal-button")).click();
		
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		driver.findElement(By.id("cart-link")).click();
		
		assertEquals(defaultUrl + "#cart", driver.getCurrentUrl());
		
		for (int i = 1; i < 5; i++) {
			assertTrue(driver.findElement(By.className("column-"+i)).isDisplayed());
		}
		
		assertTrue(driver.findElement(By.className("table-row")).isDisplayed());
		
		assertTrue(driver.findElement(By.xpath("//*[@id=\"p-quantity\"]")).getAttribute("value").equals("1"));
		
		driver.findElement(By.xpath("//*[@id=\"3\"]/td[4]/div/button[2]")).click();
		
		driver.findElement(By.xpath("//*[@id=\"3\"]/td[4]/div/button[2]")).click();
		
		assertTrue(driver.findElement(By.xpath("//*[@id=\"p-quantity\"]")).getAttribute("value").equals("3"));
		
		driver.findElement(By.xpath("//*[@id=\"3\"]/td[4]/div/button[1]")).click();
		
		driver.findElement(By.xpath("//*[@id=\"3\"]/td[4]/div/button[1]")).click();
		
		assertTrue(driver.findElement(By.xpath("//*[@id=\"p-quantity\"]")).getAttribute("value").equals("1"));
		
		driver.findElement(By.xpath("//*[@id=\"p-quantity\"]")).sendKeys("5");
		
		assertTrue(driver.findElement(By.xpath("//*[@id=\"p-quantity\"]")).getAttribute("value").equals("15"));
		
		assertTrue(driver.findElement(By.xpath("//*[@id=\"cart-subtotals\"]")).getText().equals("90.00 BAM"));
		
		assertTrue(driver.findElement(By.xpath("//*[@id=\"total-price\"]")).getText().equals("90.00 BAM"));
		
		driver.findElement(By.xpath("//*[@id=\"3\"]/td[4]/div/button[2]")).click();
		
		driver.findElement(By.xpath("//*[@id=\"update-cart-button\"]")).click();
		
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("swal-modal")));
		
		assertTrue(driver.findElement(By.className("swal-text")).getText().equals("has been successfully updated!"));
		
		driver.findElement(By.className("swal-button")).click();
		
		assertTrue(driver.findElement(By.xpath("//*[@id=\"cart-subtotals\"]")).getText().equals("1440.00 BAM"));
		
	}
	
	@Test
	public void CouponTest() {
		
		driver.findElement(By.id("cart-link")).click();
		
		assertEquals(defaultUrl + "#cart", driver.getCurrentUrl());
		
		/*
		 * Validan: 2GV1bKhA2
			Expired: uADp6ZiO
			A za invalid, lupi bilo kakav string.*/
		
		driver.findElement(By.id("enter-coupon")).sendKeys("2GV1bKhA2");
		
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		driver.findElement(By.id("apply-coupon-button")).click();
		
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("swal-modal")));
				
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		driver.findElement(By.className("swal-button")).click();
		
		assertTrue(driver.findElement(By.className("swal-title")).getText().equals("Invalid operation."));
		
		driver.navigate().to(defaultUrl);
		
		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//*[@id=\\\"landing\\\"]/section[3]/div/div[2]/div/div/div/div[7]/div/div[1]/div")));
		
		WebElement item = driver.findElement(By.xpath("//*[@id=\"landing\"]/section[3]/div/div[2]/div/div/div/div[7]/div/div[1]/div"));
		
		JavascriptExecutor js = (JavascriptExecutor) driver;
		
		js.executeScript("arguments[0].scrollIntoView();", item);
		
		Actions builder = new Actions(driver);
		
		Action hoverOverItem = builder.moveToElement(item).pause(1000).build();
		
		hoverOverItem.perform();
		
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
				
		driver.findElement(By.xpath("//*[@id=\"landing\"]/section[3]/div/div[2]/div/div/div/div[7]/div/div[1]/div/div/button")).click();
		
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		assertTrue(driver.findElement(By.className("swal-modal")).isDisplayed());
		
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		driver.findElement(By.className("swal-button")).click();
		
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		driver.findElement(By.id("cart-link")).click();
		
		assertEquals(defaultUrl + "#cart", driver.getCurrentUrl());
		
		/*
		 * Validan: 2GV1bKhA2
			Expired: uADp6ZiO
			A za invalid, lupi bilo kakav string.*/
		
		driver.findElement(By.id("enter-coupon")).sendKeys("2GV1bKhA2");
		
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		driver.findElement(By.id("apply-coupon-button")).click();
		
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("swal-modal")));
				
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		driver.findElement(By.className("swal-button")).click();
		
		assertTrue(driver.findElement(By.id("enter-coupon")).getAttribute("placeholder").equals("Coupon has been applied."));
		
		assertTrue(driver.findElement(By.id("apply-coupon-button")).getText().equals("5% DISCOUNT"));
		
	}
	
	@Test
	public void StackDiscountTest() {
		
		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//*[@id=\"landing\"]/section[3]/div/div[2]/div/div/div/div[7]/div/div[1]/div")));
		
		WebElement item = driver.findElement(By.xpath("//*[@id=\"landing\"]/section[3]/div/div[2]/div/div/div/div[7]/div/div[1]/div"));
		
		JavascriptExecutor js = (JavascriptExecutor) driver;
		
		js.executeScript("arguments[0].scrollIntoView();", item);
		
		Actions builder = new Actions(driver);
		
		Action hoverOverItem = builder.moveToElement(item).pause(1000).build();
		
		hoverOverItem.perform();
		
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
				
		driver.findElement(By.xpath("//*[@id=\"landing\"]/section[3]/div/div[2]/div/div/div/div[7]/div/div[1]/div/div/button")).click();
		
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		assertTrue(driver.findElement(By.className("swal-modal")).isDisplayed());
		
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		driver.findElement(By.className("swal-button")).click();
		
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		driver.findElement(By.id("cart-link")).click();
		
		assertEquals(defaultUrl + "#cart", driver.getCurrentUrl());
		
		assertTrue(driver.findElement(By.id("cart-subtotals")).getText().equals("90.00 BAM"));
		assertTrue(driver.findElement(By.id("total-price")).getText().equals("90.00 BAM"));
		
		driver.findElement(By.xpath("//*[@id=\"3\"]/td[4]/div/button[2]")).click();
		
		driver.findElement(By.id("update-cart-button")).click();
		
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		assertTrue(driver.findElement(By.className("swal-modal")).isDisplayed());
		
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		driver.findElement(By.className("swal-button")).click();
		
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		assertTrue(driver.findElement(By.id("cart-subtotals")).getText().equals("180.00 BAM"));
		
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("header-wrapicon1")));
		
		WebElement login = driver.findElement(By.className("header-wrapicon1"));
		
		login.click();
		
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
				
		driver.findElement(By.xpath("//*[@id=\"email\"]")).sendKeys("harismuha123@gmail.com");
		
		try {
			Thread.sleep(500);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		driver.findElement(By.xpath("//*[@id=\"password\"]")).sendKeys("majmunica1");
		
		try {
			Thread.sleep(500);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		driver.findElement(By.xpath("//*[@id=\"loginForm\"]/button")).click();
		
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("swal-modal")));
		
		try {
			Thread.sleep(500);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		assertTrue(driver.findElement(By.className("swal-modal")).isDisplayed());
		
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		driver.findElement(By.className("swal-button")).click();		
		
		driver.navigate().to(defaultUrl + "#cart");
		
		assertTrue(driver.findElement(By.id("cart-subtotals")).getText().equals("171.00 BAM"));
		assertTrue(driver.findElement(By.id("total-price")).getText().equals("171.00 BAM"));
		
		
		
	}
	
	@Test
	public void ShippingFeeTest() {
		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//*[@id=\"landing\"]/section[3]/div/div[2]/div/div/div/div[7]/div/div[1]/div")));
		
		WebElement item = driver.findElement(By.xpath("//*[@id=\"landing\"]/section[3]/div/div[2]/div/div/div/div[7]/div/div[1]/div"));
		
		JavascriptExecutor js = (JavascriptExecutor) driver;
		
		js.executeScript("arguments[0].scrollIntoView();", item);
		
		Actions builder = new Actions(driver);
		
		Action hoverOverItem = builder.moveToElement(item).pause(1000).build();
		
		hoverOverItem.perform();
		
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
				
		driver.findElement(By.xpath("//*[@id=\"landing\"]/section[3]/div/div[2]/div/div/div/div[7]/div/div[1]/div/div/button")).click();
		
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		assertTrue(driver.findElement(By.className("swal-modal")).isDisplayed());
		
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		driver.findElement(By.className("swal-button")).click();
		
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		driver.findElement(By.id("cart-link")).click();
		
		assertEquals(defaultUrl + "#cart", driver.getCurrentUrl());
		
		assertTrue(driver.findElement(By.xpath("//*[@id=\"country-fee\"]")).getText().equals("0.00 BAM"));
		
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		Select countries = new Select(driver.findElement(By.id("country-selection")));
		
		List<WebElement> listOfCountries = countries.getOptions();
		
		countries.selectByIndex(0);
		
		driver.findElement(By.id("update-totals")).click();
		
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		assertTrue(driver.findElement(By.className("swal-modal")).isDisplayed());
		
		assertTrue(driver.findElement(By.className("swal-title")).getText().equals("Incorrect selection!"));
		
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		driver.findElement(By.className("swal-button")).click();
		
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		countries.selectByIndex(2);
		
		assertFalse(driver.findElement(By.xpath("//*[@id=\"country-fee\"]")).getText().equals("0.00 BAM"));
		
		driver.findElement(By.id("update-totals")).click();
		
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		assertTrue(driver.findElement(By.className("swal-modal")).isDisplayed());
		
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		driver.findElement(By.className("swal-button")).click();
		
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		assertTrue(driver.findElement(By.xpath("//*[@id=\"total-price\"]")).getText().equals("105.00 BAM"));
		
	}
	
	@Test
	public void ProceedToCheckoutTest() {
		
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id=\"landing\"]/section[3]/div/div[2]/div/div/div/div[7]/div/div[1]/div")));
		
		WebElement item = driver.findElement(By.xpath("//*[@id=\"landing\"]/section[3]/div/div[2]/div/div/div/div[7]/div/div[1]/div"));
		
		JavascriptExecutor js = (JavascriptExecutor) driver;
		
		js.executeScript("arguments[0].scrollIntoView();", item);
		
		Actions builder = new Actions(driver);
		
		Action hoverOverItem = builder.moveToElement(item).pause(1000).build();
		
		hoverOverItem.perform();
		
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
				
		driver.findElement(By.xpath("//*[@id=\"landing\"]/section[3]/div/div[2]/div/div/div/div[7]/div/div[1]/div/div/button")).click();
		
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		assertTrue(driver.findElement(By.className("swal-modal")).isDisplayed());
		
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		driver.findElement(By.className("swal-button")).click();
		
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		driver.findElement(By.id("cart-link")).click();
		
		assertEquals(defaultUrl + "#cart", driver.getCurrentUrl());
		
		for (int i = 1; i < 5; i++) {
			assertTrue(driver.findElement(By.className("column-"+i)).isDisplayed());
		}
		
		assertTrue(driver.findElement(By.className("table-row")).isDisplayed());
		
		assertTrue(driver.findElement(By.xpath("//*[@id=\"p-quantity\"]")).getAttribute("value").equals("1"));
		
		driver.findElement(By.xpath("//*[@id=\"3\"]/td[4]/div/button[2]")).click();
		
		driver.findElement(By.xpath("//*[@id=\"3\"]/td[4]/div/button[2]")).click();
		
		assertTrue(driver.findElement(By.xpath("//*[@id=\"p-quantity\"]")).getAttribute("value").equals("3"));
		
		driver.findElement(By.xpath("//*[@id=\"3\"]/td[4]/div/button[1]")).click();
		
		driver.findElement(By.xpath("//*[@id=\"3\"]/td[4]/div/button[1]")).click();
		
		assertTrue(driver.findElement(By.xpath("//*[@id=\"p-quantity\"]")).getAttribute("value").equals("1"));
		
		driver.findElement(By.xpath("//*[@id=\"p-quantity\"]")).sendKeys("5");
		
		assertTrue(driver.findElement(By.xpath("//*[@id=\"p-quantity\"]")).getAttribute("value").equals("15"));
		
		assertTrue(driver.findElement(By.xpath("//*[@id=\"cart-subtotals\"]")).getText().equals("90.00 BAM"));
		
		assertTrue(driver.findElement(By.xpath("//*[@id=\"total-price\"]")).getText().equals("90.00 BAM"));
		
		driver.findElement(By.xpath("//*[@id=\"3\"]/td[4]/div/button[2]")).click();
		
		driver.findElement(By.xpath("//*[@id=\"update-cart-button\"]")).click();
		
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("swal-modal")));
		
		assertTrue(driver.findElement(By.className("swal-text")).getText().equals("has been successfully updated!"));
		
		driver.findElement(By.className("swal-button")).click();
		
		assertTrue(driver.findElement(By.xpath("//*[@id=\"cart-subtotals\"]")).getText().equals("1440.00 BAM"));
			
		Select countries = new Select(driver.findElement(By.id("country-selection")));
		
		List<WebElement> listOfCountries = countries.getOptions();
		
		countries.selectByIndex(0);
		
		driver.findElement(By.id("update-totals")).click();
		
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		assertTrue(driver.findElement(By.className("swal-modal")).isDisplayed());
		
		assertTrue(driver.findElement(By.className("swal-title")).getText().equals("Incorrect selection!"));
		
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		driver.findElement(By.className("swal-button")).click();
		
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		countries.selectByIndex(2);
		
		assertFalse(driver.findElement(By.xpath("//*[@id=\"country-fee\"]")).getText().equals("0.00 BAM"));
		
		driver.findElement(By.id("update-totals")).click();
		
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		assertTrue(driver.findElement(By.className("swal-modal")).isDisplayed());
		
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		driver.findElement(By.className("swal-button")).click();
		
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		driver.findElement(By.xpath("//*[@id=\"proceed-to-checkout\"]")).click();
		
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		assertTrue(driver.findElement(By.id("checkout-overlay")).isDisplayed());
		
		assertTrue(driver.findElement(By.className("list-unstyled")).isDisplayed());
		
		driver.findElement(By.xpath("//*[@id=\"payment-redirect\"]")).click();
		
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		assertTrue(driver.findElement(By.className("swal-modal")).isDisplayed());
		
		assertTrue(driver.findElement(By.className("swal-title")).getText().equals("Invalid checkout data!"));
		
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		driver.findElement(By.className("swal-button")).click();
		
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		assertTrue(driver.findElement(By.xpath("//*[@id=\"name-li\"]")).getText().equals("not provided"));
		
		driver.findElement(By.xpath("//*[@id=\"update-buyer-details\"]")).click();
		
		driver.findElement(By.id("save-buyer-details")).click();
		
		assertTrue(driver.findElement(By.xpath("//*[@id=\"buyername-error\"]")).isDisplayed());
		
		String[] fields = {"name", "phone", "address"};
		
		for (String field : fields) {
			assertTrue(driver.findElement(By.id("buyer"+field)).isDisplayed());
			
			driver.findElement(By.id("buyer"+field)).sendKeys("hmuhibic");
		}
		
		driver.findElement(By.id("buyerzipcode")).sendKeys("71240");
		
		driver.findElement(By.id("save-buyer-details")).click();
		
		driver.findElement(By.xpath("//*[@id=\"payment-redirect\"]")).click();
		
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("swal-modal")));
		
		assertTrue(driver.findElement(By.className("swal-modal")).isDisplayed());
		
		assertTrue(driver.findElement(By.className("swal-title")).getText().equals("Successful order!"));
		
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		driver.findElement(By.className("swal-button")).click();
		
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		assertEquals(defaultUrl + "#landing", driver.getCurrentUrl());
		
		driver.findElement(By.className("header-wrapicon2")).click();
		
		assertTrue(driver.findElement(By.xpath("/html/body/header/div[1]/div[1]/div[1]/div/div/ul/p")).isDisplayed());
		
	}
	
	
	
	@After
	public void tearDown() throws Exception {
		driver.close();
	}

}
