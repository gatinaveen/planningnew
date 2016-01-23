package planning;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class PlanningFlowSecond {
	static boolean result = true;
	public static boolean planningTestSuite_2( WebDriver driver) throws InterruptedException
	{
		
		System.out.println("----------------------------------Planning Test Suite 2------------------------");
		//adminCanSeeOfferInAudioRowWithDateInPastAndNoProduct(driver);
		adminCanSeeOfferInAudioRowWithDateInFutureAndNoProduct(driver);
		return result;
	}
	
	/*add an offer from the service page, with a meeting date in the future
	 *(choose a date in 2017 for example), with no products, and check that you
	 *see the offer in the audio row in the right column on the planning page*/
	private static void adminCanSeeOfferInAudioRowWithDateInFutureAndNoProduct(WebDriver driver) {
		// TODO Auto-generated method stub
		System.out.println("add an offer from the service page, with a meeting date in the future (choose a date in 2017 for example), with no products, and check that you see the offer in the audio row in the right column on the planning page");
		driver.get("https://ci.codexa.fr/planning/");
	    driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
	    driver.findElement(By.xpath("//html//body//div[2]//div[2]//div[2]//div//div//div[1]//div[2]//span[2]//font//font")).click();
	    driver.findElements(By.className("day"));
	    String NextWeekDate = findDateAndMonthOfNextWeek();
		
		driver.get("https://ci.codexa.fr/clients");
	    driver.findElement(By.linkText("Liste des clients")).click();
	    driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	    driver.findElement(By.xpath("//table[@id='DataTables_Table_0']/tbody/tr/td[6]/a/i")).click();
	    driver.findElement(By.xpath("//td[3]/a/i")).click();
	    String oldwindow = driver.getWindowHandle();
	    driver.findElement(By.xpath("//td[4]/a[3]/i")).click();
	    Set<String> windows = new HashSet<String>();
		windows= driver.getWindowHandles();
		for(Iterator <String> it=windows.iterator(); it.hasNext();)
		{
			String s = it.next();
			if(!it.equals(oldwindow))
			{
				driver.switchTo().window(s);
				System.out.println();
			}
			
		}
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.findElement(By.xpath("//*[@id='codexa_bundle_offerbundle_offer_otherNatureOfTheMeeting']")).sendKeys("next year date meeting");
	    driver.findElement(By.xpath(".//*[@id='codexa_bundle_offerbundle_offer_meetingDate']")).clear();
	    driver.findElement(By.xpath(".//*[@id='codexa_bundle_offerbundle_offer_meetingDate']")).sendKeys(NextWeekDate);
	    driver.findElement(By.id("codexa_bundle_offerbundle_offer_submit")).click();
	    driver.get("https://ci.codexa.fr/planning/");
	    driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
	    driver.findElement(By.xpath("//html//body//div[2]//div[2]//div[2]//div//div//div[1]//div[2]//span[2]//font//font")).click();
	    driver.findElement(By.xpath("//html//body//div[3]//div[1]//table//thead//tr[1]//th[2]")).click();
	    driver.findElement(By.xpath("/html/body/div[3]/div[2]/table/thead/tr/th[3]")).click();
	    if(isTextPresent("next year date meeting",driver))
	    {
	    	System.out.println("pass");
	    }
	    else
	    {
	    	System.out.println("fail");
	    }
		
	}

	/*add an offer from the service page, with a meeting date in the past, with no products,
	 *and check that you see the offer in the audio row in the right column on the planning page*/
	private static void adminCanSeeOfferInAudioRowWithDateInPastAndNoProduct(WebDriver driver) {
		// TODO Auto-generated method stub
		System.out.println("add an offer from the service page, with a meeting date in the past, with no products, and check that you see the offer in the audio row in the right column on the planning page");
		String currentdate=findDateOfCurrentWeekWednesday();
		driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
		driver.get("https://ci.codexa.fr/clients");
	    driver.findElement(By.linkText("Liste des clients")).click();
	    driver.findElement(By.xpath("//table[@id='DataTables_Table_0']/tbody/tr/td[6]/a/i")).click();
	    driver.findElement(By.xpath("//td[3]/a/i")).click();
	    String oldwindow = driver.getWindowHandle();
	    driver.findElement(By.xpath("//td[4]/a[3]/i")).click();
	    Set<String> windows = new HashSet<String>();
		windows= driver.getWindowHandles();
		for(Iterator <String> it=windows.iterator(); it.hasNext();)
		{
			String s = it.next();
			if(!it.equals(oldwindow))
			{
				driver.switchTo().window(s);
			}
			
		}
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.findElement(By.xpath("//*[@id='codexa_bundle_offerbundle_offer_otherNatureOfTheMeeting']")).sendKeys("current date meeting");
	    driver.findElement(By.xpath(".//*[@id='codexa_bundle_offerbundle_offer_meetingDate']")).clear();
	    driver.findElement(By.xpath(".//*[@id='codexa_bundle_offerbundle_offer_meetingDate']")).sendKeys(currentdate);
	    driver.findElement(By.id("codexa_bundle_offerbundle_offer_submit")).click();
	    driver.get("https://ci.codexa.fr/planning/");
	    if(isTextPresent("current date meeting",driver))
	    {
	    	System.out.println("pass");
	    }
	    else
	    {
	    	System.out.println("fail");
	    }
	    
	}
	
	
	
	
	public static String findDateOfCurrentWeekWednesday() {
		// TODO Auto-generated method stub
		String date=null;
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yy");
		Calendar c = Calendar.getInstance();
		c.setFirstDayOfWeek(Calendar.WEDNESDAY);
		c.setTime(new Date());
		int today = c.get(Calendar.DAY_OF_WEEK);
		c.add(Calendar.DAY_OF_WEEK, -today+Calendar.WEDNESDAY);
		Date d = c.getTime();
		date=sdf.format(d);
		//System.out.println(date);
		return date;
	}
	
	
	private static String findDateAndMonthOfNextWeek() {
		// TODO Auto-generated method stub
		Date date = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yy");
		Calendar c = Calendar.getInstance();
	    c.setTime(date);
	    int i = c.get(Calendar.DAY_OF_WEEK) - c.getFirstDayOfWeek();
	    c.add(Calendar.DATE, -i + 7);
	    Date start = c.getTime();
	    c.setTime(start);
	    c.add(Calendar.DATE, 3);
	    Date dateneed = c.getTime();
	    String d=sdf.format(dateneed);
	    
	return d;
	}
	
	protected static boolean isTextPresent(String text,WebDriver driver)
	{
	    try{
	        boolean b = driver.getPageSource().contains(text);
	        return b;
	    }
	    catch(Exception e){
	        return false;
	    }

     }

}
