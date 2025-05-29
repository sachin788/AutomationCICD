package rahulshettyacademy.tests;

import io.github.bonigarcia.wdm.WebDriverManager;
import junit.framework.Assert;
import rahulshettyacademy.pageobjects.LandingPage;

import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class StandAloneTest {
	public static void main(String[] arg) throws InterruptedException {
		String productName= "ZARA COAT 3";
		WebDriverManager.chromedriver().setup();
//		ChromeOptions options= new ChromeOptions();
//		options.addArguments("user-data-dir=C:/Users/SACHIN/AppData/Local/Google/Chrome/User Data");
		WebDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.get("https://rahulshettyacademy.com/client/");
		LandingPage landingPage= new LandingPage(driver);
		driver.findElement(By.id("userEmail")).sendKeys("lzzy7068@gmail.com");
		driver.findElement(By.id("userPassword")).sendKeys("Asdfgh@1234");
		driver.findElement(By.id("login")).click();
		WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(5));
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".mb-3")));
		List<WebElement> products=driver.findElements(By.cssSelector(".mb-3"));
		WebElement prod=products.stream().filter(product->
		product.findElement(By.cssSelector("b")).getText().equals(productName)).findFirst().orElse(null);
		prod.findElement(By.cssSelector(".card-body button:last-of-type")).click();
		
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#toast-container")));
		wait.until(ExpectedConditions.invisibilityOf(driver.findElement(By.cssSelector(".ng-animating"))));
		Thread.sleep(2000);
		driver.findElement(By.cssSelector("[routerlink*='cart']")).click();
		List <WebElement> cartProducts=driver.findElements(By.cssSelector(".cartSection h3"));
		Boolean match=cartProducts.stream().anyMatch(cartProduct ->cartProduct.getText().equals(productName));
		Assert.assertTrue(match);
		driver.findElement(By.cssSelector(".totalRow button")).click();
		Actions a = new Actions(driver);
		a.sendKeys(driver.findElement(By.cssSelector("input[placeholder*='Select']")), "India").build().perform();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".ta-results")));
		driver.findElement(By.xpath("(//button[contains(@class,'ta-item')])[2]")).click();
		driver.findElement(By.xpath("//a[text()='Place Order ']")).click();
		String confrmMessage= driver.findElement(By.cssSelector(".hero-primary")).getText();
		Assert.assertTrue(confrmMessage.equalsIgnoreCase("THANKYOU FOR THE ORDER."));
//		driver.findElement(By.cssSelector("input[placeholder*='Select']")).sendKeys("ind");
//		Thread.sleep(3000);
//		List<WebElement> optionss=driver.findElements(By.cssSelector("section[class*='list-group'] button"));
//		for(WebElement option :optionss) {
//			if(option.getText().equalsIgnoreCase("India")){
//				option.click();
//				break;
//			}
//		}
//		driver.findElement(By.xpath("//a[text()='Place Order ']")).click();
//		System.out.println(driver.findElement(By.cssSelector("label[class*='star']")).getText());
		
		driver.close();
		
	}
}
