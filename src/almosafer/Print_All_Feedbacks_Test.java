package almosafer;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.edge.EdgeDriver;

import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import org.openqa.selenium.JavascriptExecutor;

public class Print_All_Feedbacks_Test {

	WebDriver driver = new EdgeDriver();
	String url = "https://www.almosafer.com/ar/hotel/details/atg/%D9%81%D9%86%D8%AF%D9%82-%D9%83%D8%B1%D8%A7%D9%88%D9%86-%D8%B1%D9%88%D8%B2-%D8%A7%D9%84%D8%B5%D8%AD%D8%A7%D9%81%D8%A9-1798809?lang=ar";

	@BeforeTest
	public void setup() {

		driver.get(url);
		driver.manage().window().maximize();
		driver.findElement(By.cssSelector(".MuiButtonBase-root.MuiButton-root.jss4.MuiButton-contained")).click();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));

	}

	@Test(enabled = true, priority = 1)
	public void check_url() throws InterruptedException {
		Thread.sleep(1000);
		driver.navigate().to(url);
		System.out.println("Current URL: " + driver.getCurrentUrl());
		Thread.sleep(1000);
	}

	@Test(enabled = true, priority = 2)
	public void scroll() throws InterruptedException {

		WebElement point_to_scroll = driver.findElement(By.xpath("//p[contains(@class,'MuiTypography-root') and text()='مرافق']"));
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].scrollIntoView(true);", point_to_scroll);
		Thread.sleep(1000);

	}

	@Test(enabled = true, priority = 3)
	public void print_all_the_feedbacks() throws InterruptedException {

		List<WebElement> read_all = driver.findElements(By.xpath("//button[contains(.,'اقرأ') and contains(.,'التقييمات')]"));
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].click();", read_all.get(0));
		
		Thread.sleep(2000);
	    WebElement modal = driver.findElement(By.id("reviews-modal-content"));

		while (true) {
			try {
				WebElement moreBtn = modal.findElement(By.xpath(".//div[contains(.,'تحميل المزيد')]"));
				js.executeScript("arguments[0].scrollIntoView(true);",moreBtn);
				
				if(moreBtn.isDisplayed()) {
					moreBtn.click();
					Thread.sleep(1000);	} 
				
			    List<WebElement> feedbacks = modal.findElements(By.cssSelector(".__ds__comp.undefined.MuiBox-root.muirtl-1bpuj9s"));
		    	System.out.println(feedbacks.size());
		    	for(int i = 0 ; i < feedbacks.size() ; i++) {
		    		System.out.println(feedbacks.get(i).getText()); }
			} catch (Exception e) {
				break;		
				}
}

}

	@AfterTest
	public void complete_testing() {

		driver.quit();	}
}
