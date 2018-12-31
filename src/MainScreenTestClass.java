import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class MainScreenTestClass {

	String path;
	WebDriver driver;
	
	@Before
	public void setUp() throws Exception {
		path = "/home/chern0/eclipse/java-2018-09/chromedriver";
		System.setProperty("webdriver.chrome.driver", path);
		
		ChromeOptions options = new ChromeOptions();
		
		options.addArguments("--start-maximized");
		
		driver = new ChromeDriver(options);
		
		System.out.println("Main screen test suite\n");
		
		driver.get("https://flink-webshop.herokuapp.com/");
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
	
	@After
	public void tearDown() {
		driver.close();
	}

}
