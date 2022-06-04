package assignment1;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Question2 {
	static WebDriver tool;
	public static void main(String[]args) throws InterruptedException {
		WebDriverManager.chromedriver().setup();
		ChromeOptions chromeops = new ChromeOptions();
		chromeops.addArguments("--start-maximized");
		tool = new ChromeDriver(chromeops);
		tool.get("https://jt-dev.azurewebsites.net/#/SignUp");	
		
		Thread.sleep(4000);
		// Validate that the dropdown has "English" and "Dutch" language
		tool.findElement(By.xpath("(//i[@class='caret pull-right'])[2]")).click();
		List<WebElement> languages = tool.findElements(By.xpath("//ul//li//div//a[@class='ui-select-choices-row-inner']"));
			for (WebElement language:languages)
				if (language.getText().equals("English"))
					System.out.println("Validated English language from drop down");
				else if (language.getText().equals("Dutch"))
					System.out.println("Validated Dutch language from drop down");
			
		tool.manage().timeouts().implicitlyWait(15,TimeUnit.SECONDS);
		
		// Filling name
		tool.findElement(By.id("name")).sendKeys("Santosh Tukaram Basabire");
		
		// Filling Organization name
		tool.findElement(By.id("orgName")).sendKeys("Santosh Tukaram Basabire");
		
		// Filling Email ID
		tool.findElement(By.id("singUpEmail")).sendKeys("xyzvb1234@gmail.com");
		
		// Clicking on terms and conditions checkbox
		tool.findElement(By.xpath("//span[@class='black-color ng-binding']")).click();
		
		// Clicking on get started
		tool.findElement(By.xpath("//button[text()='Get Started']")).click();
		
		// Validating Email delivery Message
		WebDriverWait wait = new WebDriverWait(tool,15);
		WebElement checkDeliverymsg = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div[@class='alert alert-danger alert-custom']//span")));
		if (checkDeliverymsg.getText().equals("A welcome email has been sent. Please check your email."))
			System.out.println("Email delivery message is validated");
		
		Thread.sleep(15000);
		tool.close();		
		
		// validating email received
		WebDriverManager.chromedriver().setup();
		tool = new ChromeDriver();
		tool.manage().window().maximize();
		tool.get("https://accounts.google.com/signin/v2/identifier?hl=en-GB&continue=https%3A%2F%2Fmail.google.com&service=mail&ec=GAlAFw&flowName=GlifWebSignIn&flowEntry=AddSession\"");
		tool.findElement(By.id("identifierId")).sendKeys("xyzvb1234@gmail.com");
		tool.findElement(By.xpath("//span[text()='Next']")).click();
		tool.manage().timeouts().implicitlyWait(15,TimeUnit.SECONDS);
		tool.findElement(By.xpath("//input[@autocomplete='current-password']")).sendKeys("Santosh@123");
		tool.findElement(By.xpath("//span[text()='Next']")).click();		
		
		tool.manage().timeouts().implicitlyWait(15,TimeUnit.SECONDS);
		WebElement text = tool.findElement(By.xpath("//span[@name='JabaTalks Developme.']"));
		if (text.getText().equals("JabaTalks Developme."))
			System.out.println("Email is verified");
		
		Thread.sleep(3000);
		tool.close();
		
		
			
	}
}
