package planning;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class planningFlow {
	static boolean result = true;
	public static boolean planningTestCase( WebDriver driver) throws InterruptedException
	{
		adminCanSeePlanningPageAndAllColumns(driver);
		adminCanSeePreviousWeekOnClickOfLeftArrow(driver);
		adminCanSeeNextWeekOnClickOfRightArrow(driver);
		adminCanSeecorrespondingWeekOnClickOfsemaineButton(driver);
		adminCanSeeSamePageOfCalendarAfterRefresh(driver);
		return result;
	}
	
	/*given I display a week which is not the current week, when I click on the refresh icon,
	 *I still see the week I have selected.*/
	private static void adminCanSeeSamePageOfCalendarAfterRefresh(WebDriver driver) {
		// TODO Auto-generated method stub
		System.out.println("given I display a week which is not the current week, when I click on the refresh icon, I still see the week I have selected.");
		driver.findElement(By.xpath("//div[@id='sidebar']/ul/li[6]/a/span")).click();
	    driver.findElement(By.xpath("//span[2]/font/font")).click();
	    String m =driver.findElement(By.cssSelector("body > div.datepicker.datepicker-dropdown.dropdown-menu.datepicker-orient-left.datepicker-orient-top > div.datepicker-days > table > thead > tr:nth-child(1) > th.datepicker-switch")).getText();
	    if(m.contains("January"))
		{
			m="janvier";
		}
		else if(m.contains("February"))
		{
			m="février"; 
		}
		else if(m.contains("March"))
		{
			m="mars"; 
		}
		else if(m.contains("April"))
		{
			m="avril"; 
		}
		else if(m.contains("May"))
		{
			m="mai"; 
		}
		else if(m.contains("June"))
		{
			m="juin"; 
		}
		else if(m.contains("July"))
		{
			m="juillet"; 
		}
		else if(m.contains("August"))
		{
			m="août"; 
		}
		else if(m.contains("September"))
		{
			m="septembre"; 
		}
		else if(m.contains("October"))
		{
			m="octobre"; 
		}
		else if(m.contains("November"))
		{
			m="novembre"; 
		}
		else if(m.contains("December"))
		{
			m="décembre"; 
		}
	    String s =driver.findElement(By.cssSelector("body > div.datepicker.datepicker-dropdown.dropdown-menu.datepicker-orient-left.datepicker-orient-top > div.datepicker-days > table > tbody > tr:nth-child(3) > td:nth-child(5)")).getText();
	    driver.findElement(By.cssSelector("body > div.datepicker.datepicker-dropdown.dropdown-menu.datepicker-orient-left.datepicker-orient-top > div.datepicker-days > table > tbody > tr:nth-child(3) > td:nth-child(5)")).click();
		String p = driver.findElement(By.cssSelector("#offersCalander > div.offersCalander > table > thead > tr:nth-child(1) > th:nth-child(4) > div")).getText();
		driver.findElement(By.cssSelector("#update-offers > i")).click();
		String q = driver.findElement(By.cssSelector("#offersCalander > div.offersCalander > table > thead > tr:nth-child(1) > th:nth-child(4) > div")).getText();
		if(p.contains(m))
		{
			if(p.equals(q))
			{
				System.out.println("pass");
			}
			else
			{
				System.out.println("fail");
				result=false;
			}
		}
		
	}
	/*when I click on "semaine", I can select a date and see the corresponding week*/
	private static void adminCanSeecorrespondingWeekOnClickOfsemaineButton(WebDriver driver) {
		// TODO Auto-generated method stub
		System.out.println("when I click on 'semaine', I can select a date and see the corresponding week");
		driver.findElement(By.xpath("//div[@id='sidebar']/ul/li[6]/a/span")).click();
	    driver.findElement(By.xpath("//span[2]/font/font")).click();
	    String m =driver.findElement(By.cssSelector("body > div.datepicker.datepicker-dropdown.dropdown-menu.datepicker-orient-left.datepicker-orient-top > div.datepicker-days > table > thead > tr:nth-child(1) > th.datepicker-switch")).getText();
	    if(m.contains("January"))
		{
			m="janvier";
		}
		else if(m.contains("February"))
		{
			m="février"; 
		}
		else if(m.contains("March"))
		{
			m="mars"; 
		}
		else if(m.contains("April"))
		{
			m="avril"; 
		}
		else if(m.contains("May"))
		{
			m="mai"; 
		}
		else if(m.contains("June"))
		{
			m="juin"; 
		}
		else if(m.contains("July"))
		{
			m="juillet"; 
		}
		else if(m.contains("August"))
		{
			m="août"; 
		}
		else if(m.contains("September"))
		{
			m="septembre"; 
		}
		else if(m.contains("October"))
		{
			m="octobre"; 
		}
		else if(m.contains("November"))
		{
			m="novembre"; 
		}
		else if(m.contains("December"))
		{
			m="décembre"; 
		}
	    String s =driver.findElement(By.cssSelector("body > div.datepicker.datepicker-dropdown.dropdown-menu.datepicker-orient-left.datepicker-orient-top > div.datepicker-days > table > tbody > tr:nth-child(3) > td:nth-child(5)")).getText();
	    driver.findElement(By.cssSelector("body > div.datepicker.datepicker-dropdown.dropdown-menu.datepicker-orient-left.datepicker-orient-top > div.datepicker-days > table > tbody > tr:nth-child(3) > td:nth-child(5)")).click();
		String p = driver.findElement(By.cssSelector("#offersCalander > div.offersCalander > table > thead > tr:nth-child(1) > th:nth-child(4) > div")).getText();
		if(p.contains(m))
		{
			if(isTextPresent(s,driver))
			{
				System.out.println("pass");
			}
			else
			{
				System.out.println("fail");
				result=false;
			}
		}
		
	}

	/*when I click on the right arrow I see next week*/
	private static void adminCanSeeNextWeekOnClickOfRightArrow(WebDriver driver) {
		// TODO Auto-generated method stub
		System.out.println("when I click on the right arrow I see next week");
		String []d = findDateAndMonthOfNextWeek();
		String nextWeekWednesday = "mercredi"+" "+d[0];
		driver.findElement(By.xpath("//div[@id='sidebar']/ul/li[6]/a/span")).click();
		if(isTextPresent("A finaliser",driver))
		{
			driver.findElement(By.xpath("/html/body/div[2]/div[2]/div[2]/div/div/div[1]/div[2]/a[2]/span/span/font/font")).click();
			WebDriverWait wait = new WebDriverWait(driver, 15); 
			wait.until(ExpectedConditions.textToBePresentInElementLocated(By.xpath("//*[@id='offersCalander']/div[2]/table/thead/tr[1]/th[3]/div"), nextWeekWednesday)); 
	        
			
			if(isTextPresent(nextWeekWednesday,driver))
	        {
	        	System.out.println("pass");
	        	
	        }
	        else
	        {
	        	System.out.println("fail");
	        	result= false;
	        }
		}
		
		
	}

	

	/*when I click on the left arrow I see the previous week*/
	private static void adminCanSeePreviousWeekOnClickOfLeftArrow(WebDriver driver) throws InterruptedException {
		// TODO Auto-generated method stub
		System.out.println("when I click on the left arrow I see the previous week");
		String []d = findDateAndMonthOfLastWeek();
		String lastWeekWednesday = "mercredi"+" "+d[0];
		driver.findElement(By.xpath("//div[@id='sidebar']/ul/li[6]/a/span")).click();
		if(isTextPresent("A finaliser",driver))
		{
			driver.findElement(By.xpath("/html/body/div[2]/div[2]/div[2]/div/div/div[1]/div[2]/a[1]/span/span/font/font")).click();
			WebDriverWait wait = new WebDriverWait(driver, 15); 
			wait.until(ExpectedConditions.textToBePresentInElementLocated(By.xpath("//*[@id='offersCalander']/div[2]/table/thead/tr[1]/th[3]/div"), lastWeekWednesday)); 
	        
			
			if(isTextPresent(lastWeekWednesday,driver))
	        {
	        	System.out.println("pass");
	        	
	        }
	        else
	        {
	        	System.out.println("fail");
	        	result= false;
	        }
		}
		
		
	}

	

	/*when I click on the planning menu I see a page with a column "à finaliser"
	 *and 5 columns corresponding to the 5 days of the current week*/
	private static void adminCanSeePlanningPageAndAllColumns(WebDriver driver) {
		// TODO Auto-generated method stubA finaliser
		System.out.println("when I click on the planning menu I see a page with a column 'à finaliser' and 5 columns corresponding to the 5 days of the current week");
		driver.findElement(By.xpath("//div[@id='sidebar']/ul/li[6]/a/span")).click();
		if(isTextPresent("A finaliser",driver))
		{
			
		}
		else
		{
			System.out.println("fail");
			result=false;
		}
		if(isTextPresent("lundi",driver))
		{
			
		}
		else
		{
			System.out.println("fail");
			result=false;
		}
		if(isTextPresent("mardi",driver))
		{
			
		}
		else
		{
			System.out.println("fail");
			result=false;
		}
		if(isTextPresent("mercredi",driver))
		{
			
		}
		else
		{
			System.out.println("fail");
			result=false;
		}
		if(isTextPresent("jeudi",driver))
		{
			
		}
		else
		{
			System.out.println("fail");
			result=false;
		}
		if(isTextPresent("vendredi",driver))
		{
			
		}
		else 
		{
			System.out.println("fail");
			result=false;
		}
		
		String []d = findCurrentDateAndMonth();
		String currentWeekWednesday = "mercredi"+" "+d[0];
		if(isTextPresent(currentWeekWednesday,driver))
		{
			
		}
		else
		{
			System.out.println("fail");
			result=false;
		}
		if(result==true)
		{
			System.out.println("pass");
		}
	}
	
	
	
	private static String[] findCurrentDateAndMonth() {
		// TODO Auto-generated method stub
		String s = null;
		s= findDateOfCurrentWeekWednesday();
		String date [] = new String[3];
		date = s.split("-");
		if(date[1].contains("January"))
		{
			date[1]="Janvier";
		}
		else if(date[1].contains("February"))
		{
			date[1]="Février"; 
		}
		else if(date[1].contains("March"))
		{
			date[1]="Mars"; 
		}
		else if(date[1].contains("April"))
		{
			date[1]="Avril"; 
		}
		else if(date[1].contains("May"))
		{
			date[1]="Mai"; 
		}
		else if(date[1].contains("June"))
		{
			date[1]="Juin"; 
		}
		else if(date[1].contains("July"))
		{
			date[1]="Juillet"; 
		}
		else if(date[1].contains("August"))
		{
			date[1]="Août"; 
		}
		else if(date[1].contains("September"))
		{
			date[1]="Septembre"; 
		}
		else if(date[1].contains("October"))
		{
			date[1]="Octobre"; 
		}
		else if(date[1].contains("November"))
		{
			date[1]="Novembre"; 
		}
		else if(date[1].contains("December"))
		{
			date[1]="Décembre"; 
		}
		return date;
		
	}

	private static String findDateOfCurrentWeekWednesday() {
		// TODO Auto-generated method stub
		String date=null;
		SimpleDateFormat sdf = new SimpleDateFormat("dd-MMMM-yy");
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
	
	
	private static String[] findDateAndMonthOfLastWeek() {
		// TODO Auto-generated method stub
		    Date date = new Date();
		    SimpleDateFormat sdf = new SimpleDateFormat("dd-MMMM-yy");
		    Calendar c = Calendar.getInstance();
		    c.setTime(date);
		    int i = c.get(Calendar.DAY_OF_WEEK) - c.getFirstDayOfWeek();
		    c.add(Calendar.DATE, -i - 7);
		    Date start = c.getTime();
		    c.setTime(start);
		    c.add(Calendar.DATE, 3);
		    Date dateneed = c.getTime();
		    String d=sdf.format(dateneed);
		    String splitdate [] = new String[3];
			splitdate = d.split("-");
		return splitdate;
	}
	
	private static String[] findDateAndMonthOfNextWeek() {
		// TODO Auto-generated method stub
		Date date = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("dd-MMMM-yy");
		Calendar c = Calendar.getInstance();
	    c.setTime(date);
	    int i = c.get(Calendar.DAY_OF_WEEK) - c.getFirstDayOfWeek();
	    c.add(Calendar.DATE, -i + 7);
	    Date start = c.getTime();
	    c.setTime(start);
	    c.add(Calendar.DATE, 3);
	    Date dateneed = c.getTime();
	    String d=sdf.format(dateneed);
	    String splitdate [] = new String[3];
		splitdate = d.split("-");
	return splitdate;
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
