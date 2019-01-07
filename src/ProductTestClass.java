import static org.junit.Assert.*;

import java.util.concurrent.TimeUnit;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ProductTestClass {

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
	public void BreadCrumbTest() {
		driver.findElement(By.id("shop-link")).click();
		
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.linkText("FT-100")));
		
		driver.findElement(By.linkText("FT-100")).click();
		
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		assertEquals(defaultUrl + "#product", driver.getCurrentUrl());
		
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"product\"]/div[1]")));
		
		assertTrue(driver.findElement(By.xpath("//*[@id=\"product\"]/div[1]")).isDisplayed());
		
		driver.findElement(By.xpath("//*[@id=\"product\"]/div[1]/a[1]")).click();
		
		assertEquals(defaultUrl + "#shop", driver.getCurrentUrl());
		
		driver.navigate().back();
		
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"bread-category\"]")));
		
		driver.findElement(By.xpath("//*[@id=\"bread-category\"]")).click();
		
		assertEquals(defaultUrl + "#shop", driver.getCurrentUrl());
		
		driver.navigate().back();
	}
	
	@Test
	public void ImageAreaTest() {
		driver.findElement(By.id("shop-link")).click();
		
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.linkText("FT-100")));
		
		driver.findElement(By.linkText("FT-100")).click();
		
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		assertEquals(defaultUrl + "#product", driver.getCurrentUrl());
		
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"product\"]/div[2]/div/div[1]/div/div[1]/ul/li/div")));
		
		assertTrue(driver.findElement(By.xpath("//*[@id=\"product\"]/div[2]/div/div[1]/div/div[1]/ul/li/div")).isDisplayed());
		
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"product\"]/div[2]/div/div[1]/div/div[2]/div/div/div/div/img")));
		
		assertTrue(driver.findElement(By.xpath("//*[@id=\"product\"]/div[2]/div/div[1]/div/div[2]/div/div/div/div/img")).isDisplayed());
		
	}
	
	@Test
	public void DescriptionAreaTest() {
		driver.findElement(By.id("shop-link")).click();
		
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.linkText("FT-100")));
		
		driver.findElement(By.linkText("FT-100")).click();
		
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		assertEquals(defaultUrl + "#product", driver.getCurrentUrl());
		
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"product\"]/div[2]/div/div[1]/div/div[1]/ul/li/div")));
		
		assertTrue(driver.findElement(By.xpath("//*[@id=\"product\"]/div[2]/div/div[2]/h4")).isDisplayed());
		
		assertTrue(driver.findElement(By.xpath("//*[@id=\"product-price\"]")).isDisplayed());
		
		assertTrue(driver.findElement(By.xpath("//*[@id=\"product-tag\"]")).isDisplayed());
		
		assertTrue(driver.findElement(By.xpath("//*[@id=\"product\"]/div[2]/div/div[2]/div[1]/div/div/div[1]/input")).getAttribute("value").equals("1"));
		
		driver.findElement(By.xpath("//*[@id=\"product\"]/div[2]/div/div[2]/div[1]/div/div/div[1]/button[2]")).click();
		
		assertTrue(driver.findElement(By.xpath("//*[@id=\"product\"]/div[2]/div/div[2]/div[1]/div/div/div[1]/input")).getAttribute("value").equals("2"));
		
		driver.findElement(By.xpath("//*[@id=\"product\"]/div[2]/div/div[2]/div[1]/div/div/div[1]/button[1]")).click();
		
		driver.findElement(By.xpath("//*[@id=\"product\"]/div[2]/div/div[2]/div[1]/div/div/div[1]/button[1]")).click();
		
		driver.findElement(By.xpath("//*[@id=\"product\"]/div[2]/div/div[2]/div[1]/div/div/div[1]/button[1]")).click();
		
		assertTrue(driver.findElement(By.xpath("//*[@id=\"product\"]/div[2]/div/div[2]/div[1]/div/div/div[1]/input")).getAttribute("value").equals("1"));
		
		assertTrue(driver.findElement(By.xpath("//*[@id=\"add-product-to-cart\"]")).isDisplayed());
		
		driver.findElement(By.xpath("//*[@id=\"product\"]/div[2]/div/div[2]/div[1]/div/div/div[1]/input")).sendKeys("5");
		
		assertTrue(driver.findElement(By.xpath("//*[@id=\"product\"]/div[2]/div/div[2]/div[1]/div/div/div[1]/input")).getAttribute("value").equals("15"));
		
		driver.findElement(By.xpath("//*[@id=\"add-product-to-cart\"]")).click();
		
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("swal-modal")));
		
		driver.findElement(By.className("swal-button")).click();
		
		assertTrue(driver.findElement(By.xpath("//*[@id=\"product-category\"]/strong")).isDisplayed());
		
		assertTrue(driver.findElement(By.xpath("//*[@id=\"product\"]/div[2]/div/div[2]/div[3]/h5")).isDisplayed());
		
		assertTrue(driver.findElement(By.xpath("//*[@id=\"product\"]/div[2]/div/div[2]/div[4]/h5")).isDisplayed());
		
		assertTrue(driver.findElement(By.xpath("//*[@id=\"product\"]/div[2]/div/div[2]/div[5]/h5")).isDisplayed());
	}
	
	@After
	public void tearDown() throws Exception {
		driver.close();
	}

}
