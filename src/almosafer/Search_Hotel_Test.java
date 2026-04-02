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
import org.testng.Assert;

public class Search_Hotel_Test {
	
	WebDriver driver = new EdgeDriver();
	String url = "https://www.almosafer.com/ar/hotels-home";

	@BeforeTest
	public void setup() {

		driver.get(url);
		driver.manage().window().maximize();
		driver.findElement(By.id("mui-5")).click();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));

	}
	
	
	@Test(enabled = true, priority = 1)
	public void check_url() throws InterruptedException {
		Thread.sleep(1000);
		driver.navigate().to(url);
		System.out.println("Current URL: " + driver.getCurrentUrl());
		Thread.sleep(1000);
		driver.findElement(By.cssSelector("div[data-testid='change_language']")).click();;
	}

	@Test(enabled=true,priority=2)
	public void Search_for_hotel() {
		WebElement location =driver.findElement(By.id("DesktopSearchWidget_Destination_InputField_Test_Id"));
		WebElement checkin_date =driver.findElement(By.id("DesktopSearchWidget_Dates_Check_In_InputField_Test_Id"));
		WebElement Room_info =driver.findElement(By.id("mui-1"));

		
		//Actions
		location.sendKeys("dubai");
		List<WebElement> Locations = driver.findElements(By.cssSelector(".__ds__comp.undefined.MuiBox-root.alm-desktop-160pu9s"));
		Locations.get(0).click();
		
		
		checkin_date.click();
		do {
			
			driver.findElement(By.cssSelector(".__ds__comp.undefined.MuiBox-root.alm-desktop-nphujf")).click();	
		
		} while(!driver.getPageSource().contains("February 2027"));
		
		WebElement day =driver.findElement(By.xpath("//div[@aria-label='Tue Feb 02 2027']"));
		day.click();
		
		
		Room_info.click();
		List<WebElement> rooms = driver.findElements(By.cssSelector(".__ds__comp.undefined.MuiBox-root.alm-desktop-16wwg55"));
		rooms.get(1).click();
		
		WebElement Search_Button =driver.findElement(By.id("mui-2"));
		Search_Button.click();
		List<WebElement> suggestions = driver.findElements(By.xpath("//div[@data-testid=\"hotel_card_hotelName\"]"));
		for(int i=0;i<5;i++) {
			System.out.println(suggestions.get(i).getText());
		}

		//Assertions
		Assert.assertEquals(driver.getPageSource().contains("Dubai"), true);

		}
	
	
	@AfterTest
	public void complete_testing() {

		driver.quit();	}
	}

	