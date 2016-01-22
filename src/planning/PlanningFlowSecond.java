package planning;

import org.openqa.selenium.WebDriver;

public class PlanningFlowSecond {
	static boolean result = true;
	public static boolean planningTestSuite_2( WebDriver driver) throws InterruptedException
	{
		
		System.out.println("----------------------------------Planning Test Suite 2------------------------");
		adminCanSeeOfferInAudioRowWithDateInPastAndNoProduct(driver);
		return result;
	}
	
	/*add an offer from the service page, with a meeting date in the past, with no products,
	 *and check that you see the offer in the audio row in the right column on the planning page*/
	private static void adminCanSeeOfferInAudioRowWithDateInPastAndNoProduct(WebDriver driver) {
		// TODO Auto-generated method stub
		System.out.println("add an offer from the service page, with a meeting date in the past, with no products, and check that you see the offer in the audio row in the right column on the planning page");
	}

}
