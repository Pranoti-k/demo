package demo;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
/*Author Pranoti Kulkarni
 * *
 */
public class Facebook {

	@SuppressWarnings("deprecation")
	public static void main(String[] args) throws InterruptedException {
		// TODO Auto-generated method stub
		//launch mozilla browser 
		
		FirefoxProfile profile = new FirefoxProfile();
        profile.setPreference("permissions.default.desktop-notification", 1);
        DesiredCapabilities capabilities=DesiredCapabilities.firefox();
        capabilities.setCapability(FirefoxDriver.PROFILE, profile);
       
  
		System.setProperty("webdriver.gecko.driver","C:\\\\Users\\\\San\\\\Desktop\\\\Selenium\\\\geckodriver.exe");
		//Webdriver is interface and driver is object of firefoxdriver
         WebDriver driver=new FirefoxDriver();
         //fetching page 
         driver = new FirefoxDriver(capabilities);
         driver.get("https://en-gb.facebook.com/login/");
         driver.findElement(By.name("email")).sendKeys("pranotik2011@gmail.com");
         driver.findElement(By.name("pass")).sendKeys("Pranoti@691");
         driver.findElement(By.name("login")).click();
        // WebDriverWait wait = new WebDriverWait(driver, 30);
       //  wait.until(ExpectedConditions.elementToBeClickable(lastElementToLoad));
         // Thread.sleep(6000);
         driver.findElement(By.id("userNavigationLabel")).click();
      //   WebDriverWait wait = new WebDriverWait(driver, 10);

         //driver.wait(8000);;
       //  WebElement logOut = wait.until(ExpectedConditions.elementToBeClickable(By.linkText("Log Out")));
         //logOut.click();
        // driver.findElement(By.className("_w0d _w0d")).click();
         
         driver.manage().timeouts().implicitlyWait(20,TimeUnit.SECONDS);

        // driver.findElement(By.xpath("//span[@class='_54nh'][contains(.,'Log Out')]")).click();
        // driver.findElement(By.id("u_c_3")).submit();
driver.findElement(By.className("_54nc")).click();
System.out.println("Successfully logged out");
///html/body/div/div[4]/form/div[2]/div[1]/div[1]/div/div[2]/input
///html/body/div[1]/div[2]/div/div[1]/div/div/div/div[2]/div[1]/div[1]/div/a/span/span
//*[@id="u_0_d"]/div[1]/div[1]/div/a/span/span
//*[@id="u_0_d"]/div[1]/div[1]/div/a/span/span
    driver.close();     
	}
	

}
