import static org.junit.Assert.*;

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
import org.openqa.selenium.support.ui.WebDriverWait;

public class MainScreenTestClass {

	String path;
	WebDriver driver;
	String defaultUrl;
	WebDriverWait wait;
	
	@Before
	public void setUp() throws Exception {
		path = "/home/chern0/eclipse/java-2018-09/chromedriver";
		System.setProperty("webdriver.chrome.driver", path);
		
		ChromeOptions options = new ChromeOptions();
		
		options.addArguments("--start-maximized");
		
		driver = new ChromeDriver(options);
		
		wait = new WebDriverWait(driver, 20);
		
		defaultUrl = "https://flink-webshop.herokuapp.com/";
		
		System.out.println("Main screen test suite\n");
		
		driver.get("https://flink-webshop.herokuapp.com/");
		
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	}

	@Test
	public void FindOutMoreButtonTest() {
		System.out.println("General UI part testing");
		
		String finalUrl = "https://flink-webshop.herokuapp.com/#product";
		
		 try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 
		if(driver.findElement(By.linkText("FIND OUT MORE")).isDisplayed()) {
			driver.findElement(By.linkText("FIND OUT MORE")).click();
			
			assertEquals(finalUrl, driver.getCurrentUrl());
		}
	}
	
	@Test 
	public void PopUpTest() {
		System.out.println("All popups should be closeable by clicking OK or outside of it");
		
		driver.findElement(By.className("header-wrapicon1")).click();
		
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		assertTrue(driver.findElement(By.id("login-overlay")).isDisplayed());
		
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 
		driver.findElement(By.className("close")).click();
		
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		assertTrue(!driver.findElement(By.id("login-overlay")).isDisplayed());
		
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 
		driver.findElement(By.className("header-wrapicon1")).click();
		
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		assertTrue(driver.findElement(By.id("login-overlay")).isDisplayed());
			
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		
		driver.findElement(By.id("login-overlay")).click();
		
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		assertTrue(!driver.findElement(By.id("login-overlay")).isDisplayed());
		 
		
	}
	
	@Test
	public void CategoryLinksTest() {
		
		WebDriverWait wait = new WebDriverWait(driver, 20);
		
		driver.findElement(By.xpath("//*[@id=\"category-list\"]/div[1]/div/div/a")).click();
		
		try {
			Thread.sleep(500);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		assertEquals(defaultUrl + "#shop", driver.getCurrentUrl());
		
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("highlight")));
		
		assertEquals("Thermostat", driver.findElement(By.className("highlight")).getText());
		
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		driver.navigate().to(defaultUrl);
		
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		driver.findElement(By.xpath("//*[@id=\"category-list\"]/div[2]/div/div/a")).click();
		
		try {
			Thread.sleep(500);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		assertEquals(defaultUrl + "#shop", driver.getCurrentUrl());
		
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("highlight")));
		
		assertEquals("Smart Home", driver.findElement(By.className("highlight")).getText());

		
	}
	
	@Test 
	public void RegistrationPopupTest() {
		System.out.println("All popups should be closeable by clicking OK or outside of it");
		 
		driver.findElement(By.id("sign-up-banner-button")).click();
		
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		assertTrue(driver.findElement(By.id("login-overlay")).isDisplayed());
			
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		
		driver.findElement(By.id("login-overlay")).click();
		
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		assertTrue(!driver.findElement(By.id("login-overlay")).isDisplayed());
		 
		
	}
	
	@Test
	public void CountdownTest() {
		System.out.println("The 'Stay tuned' countdown should not display negative values");
		
		try {
		Thread.sleep(1000);
		} catch (InterruptedException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
		}

		String[] types = {"days", "hours", "minutes", "seconds"};
		
		for (String type : types) {
			int countdownValue = Integer.parseInt(driver.findElement(By.className(type)).getText());
						
			try {
				Thread.sleep(500);
				} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				}

			System.out.println(countdownValue);
			
			assertTrue(countdownValue >= 0);
		}
	}
	
	@Test
	public void HoverProductTest() {
				
		WebDriverWait wait = new WebDriverWait(driver, 60);
		
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
		
		assertTrue(!driver.findElement(By.className("swal-modal")).isDisplayed());
		
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		driver.findElement(By.className("header-wrapicon2")).click();
		
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
		assertTrue(driver.findElement(By.id("product_3")).isDisplayed());
		
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	@Test
	public void InstagramTest() {
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"instagram-container\"]/div[5]")));
		
		WebElement instagramCard = driver.findElement(By.xpath("//*[@id=\"instagram-container\"]/div[5]"));
		
		JavascriptExecutor js = (JavascriptExecutor) driver;
		
		js.executeScript("arguments[0].scrollIntoView();", instagramCard);
		
		Actions builder = new Actions(driver);
		
		Action moveToCard = builder.moveToElement(instagramCard).build();
		
		moveToCard.perform();
		
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		assertTrue(driver.findElement(By.xpath("//*[@id=\"instagram-container\"]/div[5]/a/span")).isDisplayed());
		assertTrue(driver.findElement(By.xpath("//*[@id=\"instagram-container\"]/div[5]/a/div")).isDisplayed());
		
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		String externalLink = driver.findElement(By.cssSelector("#instagram-container > div:nth-child(5) > a")).getAttribute("href");
		
		driver.get(externalLink);
		
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		assertEquals(driver.getCurrentUrl(), externalLink);
		
	}
	
	
	@After
	public void tearDown() {
		driver.close();
	}

}
