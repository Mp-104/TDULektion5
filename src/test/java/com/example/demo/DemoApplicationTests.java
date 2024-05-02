package com.example.demo;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.Duration;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
class DemoApplicationTests {

	ChromeDriver driver;

	@BeforeAll
	public static void init () {
		WebDriverManager.chromedriver().setup();
	}

	@BeforeEach
	public void setUp () {
		driver = new ChromeDriver();

	}

	@AfterEach
	public void tearDown () {
		if (driver != null) {
			driver.quit();
		}
	}

//	@Test
//	void contextLoads() {
//	}

	@Test
	public void testWebsiteTitle () {
		driver.get("https://svtplay.se");
		assertEquals("SVT Play", driver.getTitle(), "Titeln stämmer med förväntat");
	}

	@Test
	public void testLogoIsPresent () {
		driver.get("https://google.se");
		WebElement logo = driver.findElement(By.cssSelector("img.lnXdpd"));
		boolean logoIsDisplayed = logo.isDisplayed();
		Assertions.assertTrue(logoIsDisplayed);
	}

	@Test
	public void slao () {
		driver.get("http://slao.se");
		assertEquals("Startsida – Svenska Skidanläggningar", driver.getTitle());
	}

	@Test
	public void hello () {
		driver.get("http://localhost:8080/hello");
		WebElement element = driver.findElement(By.tagName("h1"));

		assertEquals("Hello", element.getText());
	}

	@Test
	public void testParam () { //Need to change to @RestController instead of @Controller in ProductController.java
		driver.get("http://localhost:8080/myendpoint?name=berit");
		WebElement element = driver.findElement(By.tagName("body"));

		assertEquals("Hello berit", element.getText());
	}

	@Test
	public void testProducts () {
		driver.get("http://localhost:8080/products");
		WebElement element = driver.findElement(By.cssSelector("li"));

		WebElement element2 = driver.findElement(By.tagName("li"));

		assertEquals("ID: 1 Name: TV Samsung Price: 1000.0", element.getText());
	}

	@Test
	public void testProducts2 () {
		driver.get("http://localhost:8080/products");
		List<WebElement> products = driver.findElements(By.tagName("li"));

		System.out.println(products.get(1).getText());

		assertTrue(products.get(0).getText().contains("Samsung"), "Product name should be present");
		assertTrue(products.get(1).getText().contains("Joker"), "Product name should be present");
	}

	@Test
	public void testProductById () {
		driver.get("http://localhost:8080/products/1");
		WebElement element = driver.findElement(By.tagName("h1"));

		assertEquals("TV Samsung", element.getText());
	}

	@Test
	public void navExists () {
		driver.get("http://slao.se");
		WebElement element = driver.findElement(By.id("site-navigation"));

		assertTrue(element.isDisplayed());
	}

	@Test
	public void correctText () {
		driver.get("http://slao.se");
		List <WebElement> element = driver.findElements(By.tagName("li"));

		System.out.println(element.get(6).getText());
		assertEquals( "MEDLEM", element.get(6).getText());
		assertEquals( "OM SLAO", element.get(7).getText());
		assertEquals( "FAKTA", element.get(8).getText());
		assertEquals( "UTBILDNINGAR", element.get(9).getText());
		assertEquals( "HÅLLBARHET", element.get(10).getText());
	}

	@Test
	public void heroText () {
		driver.get("http://slao.se");
		WebElement element = driver.findElement(By.tagName("h1"));

		assertEquals("För roliga & trygga skidupplevelser", element.getText());
	}

	@Test
	public void testClick () {

		driver.get("http://slao.se");

		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

		List <WebElement> listOfLi = driver.findElements(By.tagName("li"));

		listOfLi.get(6).click();

		WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(By.tagName("h1")));

		System.out.println(element.getText());

		assertEquals("Medlemsnytta", element.getText());
	}

	@Test
	public void testClick2 () {

		driver.get("http://slao.se");

		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

		List <WebElement> listOfLi = driver.findElements(By.tagName("li"));

		listOfLi.get(7).click();

		WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(By.tagName("h1")));

		System.out.println(element.getText());

		assertEquals("SLAO samverkar för roliga och trygga skidupplevelser", element.getText());
	}

}
