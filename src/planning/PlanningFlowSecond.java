package planning;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class PlanningFlowSecond {
	static boolean result = true;
	static List<String> oldwindows = new ArrayList<String>();
	public static boolean planningTestSuite_2( WebDriver driver) throws InterruptedException
	{
		
		System.out.println("----------------------------------Planning Test Suite 2------------------------");
		adminCanSeeOfferInAudioRowWithDateInPastAndNoProduct(driver);
		adminCanSeeOfferInAudioRowWithDateInFutureAndNoProduct(driver);
		adminCanSeeShortNameOfClientAndMeetingDateOfOffer(driver);
		adminCanSeeMeetingTypeInOfferBoxOnPlanningPage(driver);
		adminCanSeeOfferPageOnClickOfClientShortNameInNewTab(driver);
		adminCanEditDateOfOfferThenShowInCorrespondingDate(driver);
		adminCanDeleteOfferAndItShouldNotShowOnPlanningPage(driver);
		
		return result;
	}
	
	
	/*check that if you delete the offer you don't see it any more in the planning page*/
	private static void adminCanDeleteOfferAndItShouldNotShowOnPlanningPage(WebDriver driver) throws InterruptedException {
		// TODO Auto-generated method stub
		System.out.println("check that if you delete the offer you don't see it any more in the planning page");
		String old =driver.getWindowHandle();
		for(String d:oldwindows) {
            driver.switchTo().window(d).close();
            driver.switchTo().window(old);
        }
		driver.get("https://ci.codexa.fr/planning/");
		driver.findElement(By.xpath("//html//body//div[2]//div[2]//div[2]//div//div//div[1]//div[2]//a[1]//span//span//font//font")).click();
		while(!isTextPresent("date of last week meeting",driver))
		{
			
		}
		old =driver.getWindowHandle();
		driver.findElement(By.linkText("Client MS1")).click();
		Set<String> windows = new HashSet<String>();
		windows= driver.getWindowHandles();
		for(Iterator <String> it=windows.iterator(); it.hasNext();)
		{
			String s = it.next();
			if(!it.equals(old))
			{
				driver.switchTo().window(s);
			}
			
		}
		driver.findElement(By.linkText("Supprimer")).click();
		WebDriverWait wait = new WebDriverWait(driver, 10);
		driver.findElement(By.xpath("//html//body//div[2]//div[2]//div[2]//div[2]//div[1]//a[3]")).click();
		WebElement element = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(".//*[@id='bh-delete']")));
		element.click();
		driver.get("https://ci.codexa.fr/planning/");
		driver.findElement(By.xpath("//html//body//div[2]//div[2]//div[2]//div//div//div[1]//div[2]//a[1]//span//span//font//font")).click();
		Thread.sleep(5000);
		if(!isTextPresent("date of last week meeting",driver))
		{
			System.out.println("Pass");
		}
		else
		{
			System.out.println("fail");
		}
		
	}

	/*check that if you change the meeting date on the informations générales
	 *tab of the offer, the corresponding offer can be seen in the right column
	 *on the planning page*/
	private static void adminCanEditDateOfOfferThenShowInCorrespondingDate(WebDriver driver) throws InterruptedException {
		// TODO Auto-generated method stub
		System.out.println("check that if you change the meeting date on the informations générales tab of the offer, the corresponding offer can be seen in the right column on the planning page");
		String newdate = findDateAndMonthOfNextWeek();
		driver.get("https://ci.codexa.fr/offer/offer/3/show");
		driver.findElement(By.xpath("//html//body//div[2]//div[2]//div[2]//div[2]//div[1]//a[4]")).click();
		driver.findElement(By.xpath(".//*[@id='codexa_bundle_offerbundle_offer_meetingDate']")).clear();
		driver.findElement(By.xpath(".//*[@id='codexa_bundle_offerbundle_offer_meetingDate']")).sendKeys(newdate);
		driver.findElement(By.xpath(".//*[@id='codexa_bundle_offerbundle_offer_submit']")).click();
		driver.get("https://ci.codexa.fr/planning/");
		driver.navigate().refresh();
		if(!isTextPresent("CE",driver))
		{
			driver.findElement(By.xpath("//html//body//div[2]//div[2]//div[2]//div//div//div[1]//div[2]//a[2]//span//span//font//font")).click();
			while(!isTextPresent("CE",driver))
			{
				
			}
			if(isTextPresent("CE",driver))
			{
				System.out.println("pass");
			}
			else
			{
				System.out.println("fail");
			}
		}
		
	}


	/*check that when you click on the client's name in the offer box in the 
	 *planning page, you see the corresponding offer page*/
	private static void adminCanSeeOfferPageOnClickOfClientShortNameInNewTab(WebDriver driver) throws InterruptedException {
		// TODO Auto-generated method stub
		System.out.println("check that when you click on the client's name in the offer box in the planning page, you see the corresponding offer page");
		String current = driver.getWindowHandle();
		for(String d:oldwindows) {
            driver.switchTo().window(d).close();
            driver.switchTo().window(current);
        }
		driver.get("https://ci.codexa.fr/planning/");
	    driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
	    driver.findElement(By.xpath("//html//body//div[2]//div[2]//div[2]//div//div//div[1]//div[2]//a[2]//span//span//font//font")).click();
	    while(!isTextPresent("next week date meeting",driver))
	    {
	    	
	    }
		String oldwindow = driver.getWindowHandle();
		driver.findElement(By.xpath(".//*[@id='offersCalander']/div[2]/table/tbody/tr[1]/td[3]/div/div[1]/div/div[2]/a")).click();
	    Set<String> windows = new HashSet<String>();
		windows= driver.getWindowHandles();
		for(Iterator <String> it=windows.iterator(); it.hasNext();)
		{
			String s = it.next();
			if(!it.equals(oldwindow))
			{
				driver.switchTo().window(s);
				current=s;
			}
			
		}
		Thread.sleep(10000);
		oldwindows.clear();
		oldwindows.add(oldwindow);
		if(isTextPresent("Date de la prestation",driver) && isTextPresent("next week date meeting",driver))
		{
			System.out.println("pass");
		}
		else
		{
			System.out.println("fail");
		}
	}

	/*check that you see the client's short name in the offer box (field "Nom du client" on the client's page https://ci.codexa.fr/client/client/1/show)*/
	private static void adminCanSeeMeetingTypeInOfferBoxOnPlanningPage(WebDriver driver) throws InterruptedException {
		// TODO Auto-generated method stub
		System.out.println("check that you see the client's short name in the offer box");
		String currentweekdate = findDateOfCurrentWeekWednesday();
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
		oldwindows.add(oldwindow);
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	    driver.findElement(By.xpath(".//*[@id='codexa_bundle_offerbundle_offer_meetingDate']")).sendKeys(currentweekdate);
	    driver.findElement(By.id("codexa_bundle_offerbundle_offer_submit")).click();
	    driver.get("https://ci.codexa.fr/planning/");
	    driver.findElement(By.xpath("//html//body//div[2]//div[2]//div[2]//div//div//div[1]//div[2]//a[1]//span//span//font//font")).click();
	    while(!isTextPresent("Client MS1",driver))
	    {
	    	
	    }
	    if(isTextPresent("CE",driver))
	    {
	    	System.out.println("pass");
	    }
	    else
	    {
	    	System.out.println("fail");
	    }
	}


	/*check that you see the client's short name in the offer box (field "Nom du client" on the client's page*/
	/*check that if you add a value in the field "Nature des réunions
	 *(si autres)" on the informations générales tab of the offer, you see in
	 *the offer box the the value in this field instead of the value of the
	 *field "Nature des réunions"*/
	private static void adminCanSeeShortNameOfClientAndMeetingDateOfOffer(WebDriver driver) {
		// TODO Auto-generated method stub
		System.out.println("check that you see the client's short name in the offer box (field 'Nom du client' on the client's page)");
		driver.get("https://ci.codexa.fr/planning/");
		driver.findElement(By.xpath("//html//body//div[2]//div[2]//div[2]//div//div//div[1]//div[2]//a[2]//span//span//font//font")).click();
	    while(!isTextPresent("Client MS1",driver))
	    {
	    	
	    }
	    if(isTextPresent("Client MS1",driver))
	    {
	    	System.out.println("pass");
	    }
	    else
	    {
	    	System.out.println("fail");
	    }
		System.out.println("check that if you add a value in the field 'Nature des réunions (si autres)' on the informations générales tab of the offer, you see in the offer box the the value in this field instead of the value of the field 'Nature des réunions'");
		if(isTextPresent("next week date meeting",driver))
	    {
	    	System.out.println("pass");
	    }
	    else
	    {
	    	System.out.println("fail");
	    }
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
				
			}
			
		}
		oldwindows.add(oldwindow);
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.findElement(By.xpath("//*[@id='codexa_bundle_offerbundle_offer_otherNatureOfTheMeeting']")).sendKeys("next week date meeting");
	    driver.findElement(By.xpath(".//*[@id='codexa_bundle_offerbundle_offer_meetingDate']")).clear();
	    driver.findElement(By.xpath(".//*[@id='codexa_bundle_offerbundle_offer_meetingDate']")).sendKeys(NextWeekDate);
	    driver.findElement(By.id("codexa_bundle_offerbundle_offer_submit")).click();
	    driver.get("https://ci.codexa.fr/planning/");
	    driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
	    driver.findElement(By.xpath("//html//body//div[2]//div[2]//div[2]//div//div//div[1]//div[2]//a[2]//span//span//font//font")).click();
	    while(!isTextPresent("next week date meeting",driver))
	    {
	    	
	    }
	    if(isTextPresent("next week date meeting",driver))
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
		String dateoflastweek=findDateAndMonthOfLastWeek();
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
		oldwindows.add(oldwindow);
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.findElement(By.xpath("//*[@id='codexa_bundle_offerbundle_offer_otherNatureOfTheMeeting']")).sendKeys("date of last week meeting");
	    driver.findElement(By.xpath(".//*[@id='codexa_bundle_offerbundle_offer_meetingDate']")).clear();
	    driver.findElement(By.xpath(".//*[@id='codexa_bundle_offerbundle_offer_meetingDate']")).sendKeys(dateoflastweek);
	    driver.findElement(By.id("codexa_bundle_offerbundle_offer_submit")).click();
	    driver.get("https://ci.codexa.fr/planning/");
	    driver.findElement(By.xpath("//html//body//div[2]//div[2]//div[2]//div//div//div[1]//div[2]//a[1]//span//span//font//font")).click();
	    while(!isTextPresent("date of last week meeting",driver))
	    {
	    	
	    }
	    if(isTextPresent("date of last week meeting",driver))
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
	
	private static String findDateAndMonthOfLastWeek() {
		// TODO Auto-generated method stub
		    Date date = new Date();
		    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yy");
		    Calendar c = Calendar.getInstance();
		    c.setTime(date);
		    int i = c.get(Calendar.DAY_OF_WEEK) - c.getFirstDayOfWeek();
		    c.add(Calendar.DATE, -i - 7);
		    Date start = c.getTime();
		    c.setTime(start);
		    c.add(Calendar.DATE, 3);
		    Date dateneed = c.getTime();
		    String d=sdf.format(dateneed);
		return d;
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
