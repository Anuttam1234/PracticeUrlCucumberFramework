package stepDefinitions;

import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.github.bonigarcia.wdm.WebDriverManager;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;
import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class LoginStepDefinitions {
	WebDriver driver;
	
	
	public void moveScreenDown( int scrollAmount) {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollBy(0,"+scrollAmount+")", "");
		
	}
	
	public void clickDownKey() {
		Actions action = new Actions(driver);
		action.sendKeys(Keys.ARROW_DOWN).perform();
		
	}
	
	
	
	
	@Given("Practice Dashboard is Available")
	public void practice_dashboard_is_available() throws IOException, InterruptedException {
		FileInputStream fis = new FileInputStream(System.getProperty("user.dir")+"/src/test/resources/global.properties");
		Properties prop = new Properties();
		prop.load(fis);
			
//		System.setProperty("webdriver.edge.driver", System.getProperty("user.dir")+"//src/test//resources//WebDriver//msedgedriver.exe");
//		driver = new EdgeDriver();
		
		WebDriverManager.edgedriver().setup();
        driver = new EdgeDriver();
        driver.manage().window().maximize();
//        driver.get("https://www.google.com");
        driver.get("https://practice.expandtesting.com/");
        Thread.sleep(2000);
	    System.out.println("Dashboard available!");

	    for(int i = 0; i<5;i++) {
	    	clickDownKey();
	    }
	}
	@When("User Click on the {string} link")
	public void user_click_on_the_link(String string) {
		
		driver.findElement(By.xpath("//a[text()='"+string+"']")).click();
	}
	@When("Login Page Available")
	public void login_page_available() {
		List<WebElement> testLoginPageConfirmation = driver.findElements(By.xpath("//b[text()='Test Login Page']"));
		if(testLoginPageConfirmation.size()>0) {
			System.out.println("User reached Login page");
		}
	}
	@Then("Extract Username and Password from the Login Page")
	public void extract_username_and_password_from_the_login_page() throws InterruptedException {
		
		for(int i = 0; i<10;i++) {
	    	clickDownKey();
	    }
	    String username = driver.findElement(By.xpath("//li[text()='Username: ']/b")).getText();
	    String password = driver.findElement(By.xpath("//li[text()='Password: ']/b")).getText();
	    driver.findElement(By.xpath("//input[@name='username']")).sendKeys(username);
	    driver.findElement(By.xpath("//input[@name='password']")).sendKeys(password);
	    driver.findElement(By.xpath("//button[text()='Login']")).click();
	    Thread.sleep(500);
	    List<WebElement> loginConfirmation = driver.findElements(By.xpath("//b[text()='You logged into a secure area!']"));
		if(loginConfirmation.size()>0) {
			System.out.println("Login Successful");
		}
	    
	}
}
