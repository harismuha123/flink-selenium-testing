import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class SidebarTestClass {

	String path;
	WebDriver driver;
	
	@Before
	public void setUp() throws Exception {
		path = "/home/chern0/eclipse/java-2018-09/chromedriver";
		System.setProperty("webdriver.chrome.driver", path);
		
		ChromeOptions options = new ChromeOptions();
		
		options.addArguments("--start-maximized");
		
		driver = new ChromeDriver(options);
		
		System.out.println("Sidebar test suite\n");
		
		driver.get("https://flink-webshop.herokuapp.com/");
	}

	@Test()
	public void LogoTest() {
		System.out.println("The logo should lead to the default home page.\n");
		
		String finalUrl = "https://flink-webshop.herokuapp.com/#landing";
		
		driver.findElement(By.id("shop-link")).click();
		
		 try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		driver.findElement(By.className("logo3")).click();
		
		 try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		String currentUrl = driver.getCurrentUrl();
		
		assertEquals("The logo should lead to the default home page.", finalUrl, currentUrl);
	}
	
	@Test
	public void ProfilePopupTest() {
		System.out.println("The 'Profile' icon opens 'Profile' popup");
		
		driver.findElement(By.className("header-wrapicon1")).click();
		
		 try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		assertTrue(driver.findElement(By.id("login-overlay")).isDisplayed());
		
	}
	
	@Test
	public void CartPopupTest() {
		System.out.println("The 'Cart' icon opens 'Cart' popup");
		
		driver.findElement(By.className("header-wrapicon2")).click();
		
		 try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		assertTrue(driver.findElement(By.className("header-cart")).isDisplayed());
		
	}
	
	@Test
	public void SidebarLinksTest() {
		System.out.println("All sidebar items lead to their respective pages");
		
		String[] links = {"landing", "shop", "cart", "about", "contact"};
		
		
		for (String link : links) {
			String finalUrl = "https://flink-webshop.herokuapp.com/#" +  link;
			driver.findElement(By.id(link+"-link")).click();
			
			try {
				Thread.sleep(500);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			assertEquals(finalUrl, driver.getCurrentUrl());
		}
		
	}
	
	@Test
	public void SocialMediaLinksTest() {
		System.out.println("Facebook, Instagram and LinkedIn links should lead to corresponding\n" + 
				"pages");
		
		String[] sites = {"facebook", "instagram", "linkedin"};
		
		for (String site : sites) {
			String link =  driver.findElement(By.className("fa-" + site)).getAttribute("href");
			
			String facebookLink = "https://www.facebook.com/Flink-1528897597158926";
			String instagramLink = "https://www.instagram.com/flink_home";
			String linkedinLink = "https://www.linkedin.com/company/bsmart";
			
			try {
				Thread.sleep(500);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			switch(site) {
				case "facebook":
					assertEquals(facebookLink, link);
					break;
				case "instagram":
					assertEquals(instagramLink, link);
					break;
				case "linkedin":
					assertEquals(linkedinLink, link);
					break;
				default:
					break;
			}
		}
	}
	
	
	@After
	public void tearDown() {
		driver.close();
	}

}
