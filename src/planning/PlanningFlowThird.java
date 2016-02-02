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
import org.openqa.selenium.support.ui.Select;

public class PlanningFlowThird {
	static boolean result = true;
	static List<String> oldwindows = new ArrayList<String>();
	static List<WebElement> elementlist = new ArrayList<WebElement>();
	static List<WebElement> pancilicon = new ArrayList<WebElement>();
	public static boolean planningTestSuite_3( WebDriver driver) throws InterruptedException
	{
		
		System.out.println("----------------------------------Planning Test Suite 3------------------------");
		adminCanSeeOfferInAudioRowWithAudioProduct(driver);
		adminCanSeeTwoOfferInAudioProduct(driver);
		adminCanDeleteAudioProductAndSeeQuestionMark(driver);
		adminCanNotSeePancilIocnAfterDeletingAllReductionProduct(driver);
		adminCanSeeClientNameInBlueOnPlanningPage(driver);
		adminCanNotSeeClientNameInBlueOnPlanningPage(driver);
		return result;
	}
	
	
	/*check that if you erase the value in the field "Date et heure de réception
	 *des fichiers audio" in the redaction product, the client name is not 
	 *displayed any more in blue.*/
	private static void adminCanNotSeeClientNameInBlueOnPlanningPage(WebDriver driver) {
		// TODO Auto-generated method stub
		System.out.println("check that if you erase the value in the field 'Date et heure de réception des fichiers audio' in the redaction product, the client name is not displayed any more in blue.");
		driver.navigate().back();
		System.out.println(driver.getCurrentUrl());
		
	}

	/*check that if you input a value in the field "Date et heure de réception
	 *des fichiers audio" on the redaction product (format = 20/01/16 09:00), 
	 *you see the client's name in the offer box in blue. For this ticket the 
	 *meeting needs to be < or = to the current date as a date in the future
	 *can't be inputed in the field "Date et heure de réception des fichiers
	 *audio"*/
	private static void adminCanSeeClientNameInBlueOnPlanningPage(WebDriver driver) throws InterruptedException {
		// TODO Auto-generated method stub
		System.out.println("check that if you input a value in the field 'Date et heure de réception des fichiers audio' on the redaction product (format = 20/01/16 09:00), you see the client's name in the offer box in blue. For this ticket the meeting needs to be < or = to the current date as a date in the future can't be inputed in the field 'Date et heure de réception des fichiers audio'");
		String offerdate = findDateAndMonthOfLastWeek();
		String audiodate = findDateOfCurrentWeekMonday();
		audiodate=audiodate+" 09:00";
		System.out.println("add an offer with an audio product and check that you see the offer in the audio row in the right column");
		driver.findElement(By.cssSelector("span.menu-text.tooltipshow")).click();
		driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
		driver.findElement(By.linkText("Liste des clients")).click();
	    driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	    driver.findElement(By.xpath("//table[@id='DataTables_Table_0']/tbody/tr/td[6]/a[1]/i")).click();
	    driver.findElement(By.xpath("//html//body//div[2]//div[2]//div[2]//div//table[2]//tbody//tr[2]//td[3]//a[1]//i")).click();
	    String old =driver.getWindowHandle();
	    driver.findElement(By.xpath("//html//body//div[2]//div[2]//div[2]//div//table[2]//tbody//tr[2]//td[4]//a[3]//i")).click();
	    Set<String> windows = new HashSet<String>();
		windows= driver.getWindowHandles();
		for(Iterator <String> it=windows.iterator(); it.hasNext();)
		{
			String s = it.next();
			if(!s.equals(old))
			{
				driver.switchTo().window(s);
			}
			
		}
		oldwindows.add(old);
		driver.manage().timeouts().implicitlyWait(60,TimeUnit.SECONDS);
	    driver.findElement(By.id("codexa_bundle_offerbundle_offer_meetingDate")).clear();
	    driver.findElement(By.id("codexa_bundle_offerbundle_offer_meetingDate")).sendKeys(offerdate);
	    driver.findElement(By.id("codexa_bundle_offerbundle_offer_otherNatureOfTheMeeting")).clear();
	    driver.findElement(By.id("codexa_bundle_offerbundle_offer_otherNatureOfTheMeeting")).sendKeys("Blue Client Name");
	    driver.findElement(By.id("codexa_bundle_offerbundle_offer_submit")).click();
	    new Select(driver.findElement(By.xpath(".//*[@id='codexa_bundle_offerbundle_offerproducttemplateselection_productTemplate']"))).selectByVisibleText("CRO");
	    driver.findElement(By.id("codexa_bundle_offerbundle_offerproducttemplateselection_submit")).click();
	    driver.findElement(By.cssSelector("html body.navbar-fixed.skin-1 div.main-container div.main-container-inner div.main-content div.page-content div#current-form.well form#redaction.form-horizontal.form-horizontal div#supplementary-data div.row div.col-sm-6 div#audio-reception-date div.form-group div.col-sm-8 input#codexa_bundle_offerbundle_redaction_audioReceptionDate.form-control")).clear();
	    driver.findElement(By.cssSelector("html body.navbar-fixed.skin-1 div.main-container div.main-container-inner div.main-content div.page-content div#current-form.well form#redaction.form-horizontal.form-horizontal div#supplementary-data div.row div.col-sm-6 div#audio-reception-date div.form-group div.col-sm-8 input#codexa_bundle_offerbundle_redaction_audioReceptionDate.form-control")).sendKeys(audiodate);
	    driver.findElement(By.id("codexa_bundle_offerbundle_redaction_submit")).click();
	    driver.get("https://ci.codexa.fr/planning/");
	    String s =driver.findElement(By.className("client_section_bgcolor_blue")).getText();
	    if(s.contains("Blue Client Name"))
	    {
	    	System.out.println("pass");
	    }
	    else
	    {
	    	System.out.println("fail");
	    }
	}

	/*check that if you delete the remaining redaction product in the offer,
	 *you don't see any more the pencil icon, the clock icon and the redactor's box*/
	private static void adminCanNotSeePancilIocnAfterDeletingAllReductionProduct(WebDriver driver) {
		// TODO Auto-generated method stub
		System.out.println("check that if you delete the remaining redaction product in the offer, you don't see any more the pencil icon, the clock icon and the redactor's box");
		driver.get("https://ci.codexa.fr/planning/");
		elementlist=driver.findElements(By.className("ordBgcolor"));
		int previoussize = elementlist.size();
		String old =driver.getWindowHandle();
		for(String d:oldwindows) {
            driver.switchTo().window(d).close();
            driver.switchTo().window(old);
        }
		old =driver.getWindowHandle();
		driver.findElement(By.linkText("Client MS1")).click();
		Set<String> windows = new HashSet<String>();
		windows= driver.getWindowHandles();
		for(Iterator <String> it=windows.iterator(); it.hasNext();)
		{
			String s = it.next();
			if(!s.equals(old))
			{
				driver.switchTo().window(s);
			}
			
		}
		oldwindows.add(old);
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.findElement(By.linkText("Rédaction")).click();
		driver.findElement(By.xpath(".//*[@id='accordion']/div/div[1]/h4/a[2]")).click();
		driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
		driver.findElement(By.cssSelector("html body.navbar-fixed.skin-1.modal-open div.main-container div#deleteModal.modal.fade.in form#modal-form div.modal-dialog div.modal-content div.modal-footer button#bh-delete.btn.btn-xs.btn-primary")).click();
		driver.get("https://ci.codexa.fr/planning/");
		elementlist=driver.findElements(By.className("ordBgcolor"));
		int elementsize = elementlist.size();
		if(elementsize<previoussize)
		{
			if(elementsize==0)
			{
				pancilicon=driver.findElements(By.className("pencilicon"));
				if(pancilicon.isEmpty())
				{
					System.out.println("pass");
				}
				else
				{
					System.out.println("fail");
				}
				
			}
			
		}
		else
		{
			System.out.println("fail");
		}
	}


	/*check that if you delete the second redaction product in the offer,
	 *the second redaction box disappears*/
	private static void adminCanDeleteAudioProductAndSeeQuestionMark(WebDriver driver) {
		// TODO Auto-generated method stub
		System.out.println("check that if you delete the second redaction product in the offer, the second redaction box disappears");
		elementlist=driver.findElements(By.className("ordBgcolor"));
		int previoussize = elementlist.size();
		String old =driver.getWindowHandle();
		for(String d:oldwindows) {
            driver.switchTo().window(d).close();
            driver.switchTo().window(old);
        }
		oldwindows.clear();
		old =driver.getWindowHandle();
		driver.findElement(By.linkText("Client MS1")).click();
		Set<String> windows = new HashSet<String>();
		windows= driver.getWindowHandles();
		for(Iterator <String> it=windows.iterator(); it.hasNext();)
		{
			String s = it.next();
			if(!s.equals(old))
			{
				driver.switchTo().window(s);
			}
			
		}
		oldwindows.add(old);
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.findElement(By.xpath("//html//body//div[2]//div[2]//div[2]//div[2]//div[3]//div//div//ul//li[3]//a")).click();
		driver.findElement(By.xpath(".//*[@id='accordion']/div[2]/div[1]/h4/a[2]")).click();
		driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
		driver.findElement(By.cssSelector("html body.navbar-fixed.skin-1.modal-open div.main-container div#deleteModal.modal.fade.in form#modal-form div.modal-dialog div.modal-content div.modal-footer button#bh-delete.btn.btn-xs.btn-primary")).click();
		driver.get("https://ci.codexa.fr/planning/");
		elementlist=driver.findElements(By.className("ordBgcolor"));
		int elementsize = elementlist.size();
		if(elementsize<previoussize)
		{
			System.out.println("pass");
		}
		else
		{
			System.out.println("fail");
		}
	}

	
	
	
	/*check that if you add a second redaction product in the offer you see a
	 *second redactor's box in the offer box*/
	private static void adminCanSeeTwoOfferInAudioProduct(WebDriver driver) {
		// TODO Auto-generated method stub
		System.out.println("check that if you add a second redaction product in the offer you see a second redactor's box in the offer box");
		driver.navigate().refresh();
		elementlist=driver.findElements(By.className("ordBgcolor"));
		int previoussize = elementlist.size();
		String old =driver.getWindowHandle();
		for(String d:oldwindows) {
            driver.switchTo().window(d).close();
            driver.switchTo().window(old);
        }
		oldwindows.clear();
		old =driver.getWindowHandle();
		driver.findElement(By.linkText("Client MS1")).click();
		Set<String> windows = new HashSet<String>();
		windows= driver.getWindowHandles();
		for(Iterator <String> it=windows.iterator(); it.hasNext();)
		{
			String s = it.next();
			if(!s.equals(old))
			{
				driver.switchTo().window(s);
			}
			
		}
		oldwindows.add(old);
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		new Select(driver.findElement(By.xpath(".//*[@id='codexa_bundle_offerbundle_offerproducttemplateselection_productTemplate']"))).selectByVisibleText("CRO");
		driver.findElement(By.id("codexa_bundle_offerbundle_offerproducttemplateselection_submit")).click();
	    driver.findElement(By.id("codexa_bundle_offerbundle_redaction_submit")).click();
	    driver.get("https://ci.codexa.fr/planning/");
		elementlist=driver.findElements(By.className("ordBgcolor"));
		int elementsize = elementlist.size();
		if(elementsize>previoussize)
		{
			System.out.println("pass");
		}
		else
		{
			System.out.println("fail");
		}
	}
	
	
	/*add an offer with an audio product and check that you see the offer in the
	 *audio row in the right column*/
	
	/*check that you see the pencil icon in the offer box*/
	
	/*check that you see a redactor's box with a question mark in the offer box*/
	
	/*check that you see the clock icon in the offer box*/
	private static void adminCanSeeOfferInAudioRowWithAudioProduct(WebDriver driver) {
		// TODO Auto-generated method stub
		String offerdate = findDateOfCurrentWeekWednesday();
		System.out.println("add an offer with an audio product and check that you see the offer in the audio row in the right column");
		driver.findElement(By.cssSelector("span.menu-text.tooltipshow")).click();
		driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
		driver.findElement(By.linkText("Liste des clients")).click();
	    driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	    driver.findElement(By.xpath("//table[@id='DataTables_Table_0']/tbody/tr/td[6]/a[1]/i")).click();
	    driver.findElement(By.xpath("//html//body//div[2]//div[2]//div[2]//div//table[2]//tbody//tr[2]//td[3]//a[1]//i")).click();
	    String old =driver.getWindowHandle();
	    driver.findElement(By.xpath("//html//body//div[2]//div[2]//div[2]//div//table[2]//tbody//tr[2]//td[4]//a[3]//i")).click();
	    Set<String> windows = new HashSet<String>();
		windows= driver.getWindowHandles();
		for(Iterator <String> it=windows.iterator(); it.hasNext();)
		{
			String s = it.next();
			if(!s.equals(old))
			{
				driver.switchTo().window(s);
			}
			
		}
		oldwindows.add(old);
		driver.manage().timeouts().implicitlyWait(60,TimeUnit.SECONDS);
	    driver.findElement(By.id("codexa_bundle_offerbundle_offer_meetingDate")).clear();
	    driver.findElement(By.id("codexa_bundle_offerbundle_offer_meetingDate")).sendKeys(offerdate);
	    driver.findElement(By.id("codexa_bundle_offerbundle_offer_otherNatureOfTheMeeting")).clear();
	    driver.findElement(By.id("codexa_bundle_offerbundle_offer_otherNatureOfTheMeeting")).sendKeys("Audio offer");
	    driver.findElement(By.id("codexa_bundle_offerbundle_offer_submit")).click();
	    new Select(driver.findElement(By.xpath(".//*[@id='codexa_bundle_offerbundle_offerproducttemplateselection_productTemplate']"))).selectByVisibleText("CRO");
	    driver.findElement(By.id("codexa_bundle_offerbundle_offerproducttemplateselection_submit")).click();
	    driver.findElement(By.id("codexa_bundle_offerbundle_redaction_submit")).click();
	    driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
	    driver.get("https://ci.codexa.fr/planning/");
	    driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
		String classname=driver.findElement(By.xpath("//div//table//tbody//tr[1]//td[3]//div//div[1]//div[1]//div[2]")).getAttribute("class");
		if(isTextPresent("Audio offer",driver))
	    {
			if(classname.contains("client_meeting_section"))
			{
				System.out.println("pass");
			}
	    	
	    }
	    else
	    {
	    	System.out.println("fail");
	    }
		String pancilicon = driver.findElement(By.xpath("//html//body//div[2]//div[2]//div[2]//div//div//div[2]//div[2]//table//tbody//tr[1]//td[3]//div//div[1]//div[2]//div")).getAttribute("class");
		System.out.println("check that you see the pencil icon in the offer box");
		if(pancilicon.equals("pencilicon"))
		{
			System.out.println("pass");
		}
		else
		{
			System.out.println("fail");
		}
		
		String Questionmark = driver.findElement(By.xpath(".//*[@id='offersCalander']/div[2]/table/tbody/tr[1]/td[3]/div/div[1]/div[2]/div/div[2]/ul/li")).getText();
		System.out.println("check that you see a redactor's box with a question mark in the offer box");
		if(Questionmark.contains("?"))
		{
			System.out.println("pass");
		}
		else
		{
			System.out.println("fail");
		}
		
		String Clockicon = driver.findElement(By.xpath(".//*[@id='offersCalander']/div[2]/table/tbody/tr[1]/td[3]/div/div[1]/div[1]/div[1]/i")).getAttribute("class");
		System.out.println("check that you see the clock icon in the offer box");
		if(Clockicon.contains("fa fa-clock-o"))
		{
			System.out.println("pass");
		}
		else
		{
			System.out.println("fail");
		}
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
	
	
	
	public static String findDateOfCurrentWeekMonday() {
		// TODO Auto-generated method stub
		String date=null;
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yy");
		Calendar c = Calendar.getInstance();
		c.setFirstDayOfWeek(Calendar.MONDAY);
		c.setTime(new Date());
		int today = c.get(Calendar.DAY_OF_WEEK);
		c.add(Calendar.DAY_OF_WEEK, -today+Calendar.MONDAY);
		Date d = c.getTime();
		date=sdf.format(d);
		//System.out.println(date);
		return date;
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
