package example;		

import static org.testng.Assert.assertTrue;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.*;	
import PageFactory.GmailLoginPage;
import SeleniumHelpers.CustomWaits;
import SeleniumHelpers.PropertyFileReader;
import PageFactory.GmailHomePage;

public class GmailSendMailTest{		
    private WebDriver driver;
    GmailLoginPage objLogin;
    GmailHomePage objHome;
    CustomWaits objWait = new CustomWaits();
    PropertyFileReader objProperties = new PropertyFileReader();


    String usernameStr = objProperties.getString("username");
    String passwordStr = objProperties.getString("password");
    String sendTo = objProperties.getString("sendTo");
    String testSubject = objProperties.getString("testSubject");
    String testBody = objProperties.getString("testBody");
    
	@BeforeMethod
	public void setup() {
		System.setProperty("webdriver.chrome.driver", "C:\\selenium\\chromedriver.exe");
	    driver = new ChromeDriver();
	    driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	    objLogin = new GmailLoginPage(driver);
	    objHome = new GmailHomePage(driver);

		driver.get(objProperties.getString("url"));  
	}
	
	@Test		
	public void testSendMail() throws Exception {	
		objLogin.setGmailUsername(usernameStr);
		objLogin.clickNextAfterUsername();
		objLogin.setGmailPassword(passwordStr);
		objLogin.clickNextAfterPassword();
		objWait.waitTillTitleContains(driver, "Inbox");
		assertTrue(objHome.isGmailComposeButtonPresent());
		objHome.clickGmailComposeButton();
		assertTrue(objHome.isNewMessageOpen());
		objHome.setRecipients(sendTo);
		objHome.setSubject(testSubject);
		objHome.setMailBody("Test Mail Body");
		objHome.sendMessage();
		System.out.println("Sent Mail Test Compelte");
	}	
	
	@AfterMethod
	public void teardown() {
		driver.close();
		driver.quit();			
	}		
}	
