package planning;

import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;


public class Planning {

	public static void main(String[] args) throws InterruptedException {
		// TODO Auto-generated method stub
		//live start
				/*ArrayList<String> cliArgsCap = new ArrayList<String>();
				cliArgsCap.add("--web-security=false");
				cliArgsCap.add("--ssl-protocol=any");
				cliArgsCap.add("--ignore-ssl-errors=true");
				DesiredCapabilities caps = DesiredCapabilities.phantomjs();
				caps.setCapability(PhantomJSDriverService.PHANTOMJS_CLI_ARGS, cliArgsCap );
				caps.setCapability(PhantomJSDriverService.PHANTOMJS_EXECUTABLE_PATH_PROPERTY, "/var/www/phantomjs/phantomjs-1.9.8-linux-x86_64/bin/phantomjs");
				WebDriver driver = new PhantomJSDriver(Configure(cliArgsCap), caps);
				driver.get("http://ci.codexa.fr");
				System.out.println(driver.getCurrentUrl());
				Dimension d = new Dimension(1024,768);
				driver.manage().window().setSize(d);
				Snippet.screenshot(driver);
				System.out.println("selenium test starts");*/
				//live end
				//local start
				/*ArrayList<String> cliArgsCap = new ArrayList<String>();
				cliArgsCap.add("--web-security=false");
				cliArgsCap.add("--ssl-protocol=any");
				cliArgsCap.add("--ignore-ssl-errors=true");
				DesiredCapabilities caps = DesiredCapabilities.phantomjs();
				caps.setCapability(PhantomJSDriverService.PHANTOMJS_CLI_ARGS, cliArgsCap );
				caps.setCapability(PhantomJSDriverService.PHANTOMJS_EXECUTABLE_PATH_PROPERTY, "/usr/lib/phantomjs/phantomjs");
				WebDriver driver = new PhantomJSDriver(Configure_local(cliArgsCap), caps);*/
				
				WebDriver driver = new FirefoxDriver();
				/*String Username="codexa";
				String Password="codexa15";
				driver.get("https://" + Username + ":" + Password + "@" + "staging.codexa.fr");*/
				driver.get("https://ci.codexa.fr");
				Dimension d = new Dimension(1024,768);
				driver.manage().window().setSize(d);
				AdminLogin(driver);
				//local end
				
				boolean check = planningFlow.planningTestCase(driver);
				if(check!=true)
				{
					System.out.println("Planning test case fail.");
					driver.findElement(By.xpath("Planning test case fail."));
				}
	}
	
	
	static void AdminLogin(WebDriver driver) {
		// TODO Auto-generated method stub
		driver.findElement(By.xpath(".//*[@id='username']")).clear();
		driver.findElement(By.xpath(".//*[@id='username']")).sendKeys("admin@codexa.fr");
		driver.findElement(By.xpath(".//*[@id='password']")).clear();
		driver.findElement(By.xpath(".//*[@id='password']")).sendKeys("Abs0lut47$");
		driver.findElement(By.cssSelector("body > div > div > div > div > form > fieldset > div.clearfix > div > div:nth-child(2) > button")).click();
		WebElement myprofile = null;
		try
		{
			myprofile = driver.findElement(By.xpath("//*[@id='navbar-container']/div[2]/ul/li/ul/li/a"));
			if(myprofile!=null)
			{			}
		}
		catch(NoSuchElementException e)
		{
			System.out.println(e);
		}
	}

}
