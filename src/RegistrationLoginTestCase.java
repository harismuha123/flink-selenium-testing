import static org.junit.Assert.*;

import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class RegistrationLoginTestCase {

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
		
		wait = new WebDriverWait(driver, 60);
		
		defaultUrl = "https://flink-webshop.herokuapp.com/";
		
		System.out.println("Main screen test suite\n");
		
		driver.get("https://flink-webshop.herokuapp.com/");
		
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	}

	@Test 
	public void LoginTest() {
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("header-wrapicon1")));
		
		WebElement login = driver.findElement(By.className("header-wrapicon1"));
		
		login.click();
		
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		assertTrue(driver.findElement(By.className("modal-content")).isDisplayed());
		
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
		
		
		
	}
	
	@Test
	public void LoginDataListTest() {
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("header-wrapicon1")));
		
		WebElement login = driver.findElement(By.className("header-wrapicon1"));
		
		login.click();
		
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		assertTrue(driver.findElement(By.className("modal-content")).isDisplayed());
		
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
		
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("header-wrapicon1")));
		
		login = driver.findElement(By.className("header-wrapicon1"));
		
		login.click();
		
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		assertTrue(driver.findElement(By.className("list-unstyled")).isDisplayed());
		
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		assertTrue(driver.findElement(By.id("log-out-button")).isDisplayed());
		
		driver.findElement(By.id("log-out-button")).click();
	}
	
	@Test
	public void LoginFeaturesTest() {
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("header-wrapicon1")));
		
		WebElement login = driver.findElement(By.className("header-wrapicon1"));
		
		login.click();
		
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		assertTrue(driver.findElement(By.className("modal-content")).isDisplayed());
		
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
		
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("header-wrapicon1")));
		
		login = driver.findElement(By.className("header-wrapicon1"));
		
		login.click();
		
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		assertEquals("FEATURES", driver.findElement(By.xpath("//*[@id=\"login-modal-body\"]/div/div[2]/p[1]/span")).getText().replaceAll("\\p{Z}",""));
		
		assertTrue(driver.findElement(By.xpath("//*[@id=\"subscribe-button\"]")).isDisplayed());
		
	}

	@Test
	public void LoginSubscribeTest() {
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("header-wrapicon1")));
		
		WebElement login = driver.findElement(By.className("header-wrapicon1"));
		
		login.click();
		
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		assertTrue(driver.findElement(By.className("modal-content")).isDisplayed());
		
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
		
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("header-wrapicon1")));
		
		login = driver.findElement(By.className("header-wrapicon1"));
		
		login.click();
		
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		driver.findElement(By.xpath("//*[@id=\"subscribe-button\"]")).click();
		
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("swal-modal")));
		
		assertTrue(driver.findElement(By.className("swal-modal")).isDisplayed());
		
		driver.findElement(By.className("swal-button")).click();
		
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		assertTrue(driver.findElement(By.xpath("//*[@id=\"subscription-link\"]/span")).getAttribute("class").toString().equals("fa fa-check highlight"));
		
		driver.findElement(By.xpath("//*[@id=\"subscribe-button\"]")).click();
		
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("swal-modal")));
		
		driver.findElement(By.className("swal-button")).click();
		
		assertTrue(driver.findElement(By.xpath("//*[@id=\"subscription-link\"]/span")).getAttribute("class").toString().equals("fa fa-times highlight"));
		
	}
	
	@Test
	public void LogoutSidebarTest() {
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("header-wrapicon1")));
		
		WebElement login = driver.findElement(By.className("header-wrapicon1"));
		
		login.click();
		
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		assertTrue(driver.findElement(By.className("modal-content")).isDisplayed());
		
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
		
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"logout\"]")));
	
		assertTrue(driver.findElement(By.xpath("//*[@id=\"logout\"]")).isDisplayed());
		
		driver.findElement(By.xpath("//*[@id=\"logout\"]")).click();
		
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		assertTrue(!driver.findElement(By.xpath("//*[@id=\"logout\"]")).isDisplayed());
		
	}
	
	
	@Test
	public void SignUpTest() {
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("header-wrapicon1")));
		
		driver.findElement(By.className("header-wrapicon1")).click();
		
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		driver.findElement(By.id("sign-up-redirect")).click();
		
		try {
			Thread.sleep(500);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		driver.findElement(By.id("go-back-button")).click();
		
		try {
			Thread.sleep(500);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		driver.findElement(By.id("sign-up-redirect")).click();
		
		try {
			Thread.sleep(500);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		driver.findElement(By.id("sign-up-button")).click();
		
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		assertTrue(driver.findElement(By.xpath("//*[@id=\"username-error\"]")).isDisplayed());
		
		try {
			Thread.sleep(500);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		Select countries = new Select(driver.findElement(By.xpath("//*[@id=\"country-list\"]")));
		
		List<WebElement> listOfCountries = countries.getOptions();
		int index = 0;
		
		for (WebElement webElement : listOfCountries) {
			countries.selectByIndex(index);
			try {
				Thread.sleep(500);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			assertTrue(webElement.isSelected());
			index++;
		}
		
		countries.selectByIndex(0);
		
		String[] inputFields = {"username", "realname",  "password", "rePassword", "address"};
		String[] numericalFields = {"phone", "zipcode"};
		String emailField = "email";
		
		for (String field : inputFields) {
			WebElement input = driver.findElement(By.id(field));
			assertTrue(input.isDisplayed());
			
			input.sendKeys("hmuhibic");
		}
		
		for (String field : numericalFields) {
			WebElement input = driver.findElement(By.id(field));
			assertTrue(input.isDisplayed());
			
			input.sendKeys("666666");
		}
		
		Random rand = new Random(System.currentTimeMillis());
		
		driver.findElement(By.id(emailField)).sendKeys("haris" + rand.nextInt(10000) +"@stu.ibu.edu.ba");
		
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		driver.findElement(By.id("sign-up-button")).click();
		
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("swal-modal")));
		
		assertTrue(driver.findElement(By.className("swal-modal")).isDisplayed());
		
		driver.findElement(By.className("swal-button")).click();
		
	}

	@After
	public void tearDown() throws Exception {
		driver.close();
	}

}
