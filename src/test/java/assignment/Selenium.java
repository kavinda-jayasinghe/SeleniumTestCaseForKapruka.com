package assignment;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import org.testng.annotations.Test;



import io.github.bonigarcia.wdm.WebDriverManager;


public class Selenium {

	WebDriver driver;

	@BeforeMethod
	public void enterURL() {

		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();
		driver.manage().window().maximize();

		driver.manage().deleteAllCookies();
		driver.manage().timeouts().pageLoadTimeout(20, TimeUnit.SECONDS);
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		driver.get("https://www.kapruka.com/");
	}

	// ========================================TESTCASES===================================

	@Test(priority = 1)
	public void accountLoginTest() throws Exception {

		driver.findElement(By.xpath("//*[@id=\"topContent\"]/div[1]/div[2]/ul/li[1]/a")).click();
		driver.findElement(By.id("exampleInputEmail1")).sendKeys("thiliniwijekoonqun@gmail.com");
		driver.findElement(By.id("exampleInputPassword1")).click();
		driver.findElement(By.id("exampleInputPassword1")).sendKeys("Thilini@97");

		driver.findElement(By.xpath("/html/body/div[2]/div/div/div/div[1]/div/div[2]/div/div[1]/div[2]/form/input[3]")).click();
		String title = driver.getTitle();
		org.testng.Assert.assertEquals(title, "Your Account");

		Thread.sleep(3000);
	}

	@Test(priority = 2)
	public void InvalidPasswordTest() throws Exception {

		driver.findElement(By.xpath("//*[@id=\"topContent\"]/div[1]/div[2]/ul/li[1]/a")).click();
		driver.findElement(By.id("exampleInputEmail1")).sendKeys("thiliniwijekoonqun@gmail.com");
		driver.findElement(By.id("exampleInputPassword1")).click();
		driver.findElement(By.id("exampleInputPassword1")).sendKeys("1234");

		driver.findElement(By.xpath("/html/body/div[2]/div/div/div/div[1]/div/div[2]/div/div[1]/div[2]/form/input[3]")).click();
		String errorMsg=driver.findElement(By.xpath("html/body/div[2]/div/div/div/div[1]/div/div[2]/div/div[1]/div[1]/b/font")).getText();
		org.testng.Assert.assertEquals(errorMsg, "Your password is wrong. Please try again.");

		Thread.sleep(3000);
	}

 	@Test(priority = 3)
	public void SearchItemTest() throws Exception {

		driver.findElement(By.id("search_bar_id")).sendKeys("pizza");
		driver.findElement(By.id("search_btn")).click();
		String title = driver.getTitle();
		org.testng.Assert.assertEquals(title, "Kapruka.com | PIZZA in Kapruka | Price in Sri Lanka");
		Thread.sleep(2000);
	}

	@Test(priority = 4)
	public void AddToCartTest() throws Exception {
		driver.findElement(By.id("search_bar_id")).sendKeys("cake");
		driver.findElement(By.id("search_btn")).click();
		driver.findElement(By.id("pimg_cakeJAVA00195")).click();
		driver.findElement(By.id("addtocartbutton")).click();

		String title = driver.getTitle();
		org.testng.Assert.assertEquals(title, "Kapruka Shopping Cart");
		Thread.sleep(2000);
	}


	@Test(priority = 5)
	public void InvalidOrderStatus() throws Exception {
		driver.findElement(By.xpath("//*[@id=\"topContent\"]/div[1]/div[2]/ul/li[2]/a")).click();
		driver.findElement(By.xpath("\t/html/body/div[2]/div/div/div/div[1]/div/div[2]/div/form/table[2]/tbody/tr/td/div/input[1]")).click();
		driver.findElement(By.name("referenceID")).sendKeys("1234");
		driver.findElement(By.xpath("/html/body/div[2]/div/div/div/div[1]/div/div[2]/div/form/table[2]/tbody/tr/td/div/input[2]")).click();

		String errorMsg=driver.findElement(By.xpath("/html/body/div[2]/div/div/div/div[1]/div/div[2]/div/form/table[2]/tbody/tr/td/div/div/font")).getText();
		org.testng.Assert.assertEquals(errorMsg, "You have entered an invalid reference number");

		Thread.sleep(2000);
	}

	// ===========================================================post conditions

	@AfterMethod
	public void logout() {
		driver.quit();
	}

}
