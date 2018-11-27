package example;		

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import java.util.concurrent.TimeUnit;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.*;	
import PageFactory.GmailLoginPage;
import PageFactory.GmailHomePage;
import SeleniumHelpers.CustomWaits;
import SeleniumHelpers.PropertyFileReader;

public class GmailLoginTest{		
    private WebDriver driver;
    GmailLoginPage objLogin;
    GmailHomePage objHome;
    
    PropertyFileReader objProperties = new PropertyFileReader();
    CustomWaits objWait = new CustomWaits();

    String usernameStr = objProperties.getString("username");
    String passwordStr = objProperties.getString("password");
    String wrongUser = objProperties.getString("wrongUser");
    String wrongPass = objProperties.getString("wrongPass");
    String wrongEmailMessageCheck = objProperties.getString("wrongEmailMessageCheck");
    String wrongPasswordCheck = objProperties.getString("wrongPasswordCheck");;
    
	@BeforeMethod
	public void setup() {
		System.setProperty("webdriver.chrome.driver", "C:\\selenium\\chromedriver.exe");
	    driver = new ChromeDriver();
	    driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.get(objProperties.getString("url"));
	    objLogin = new GmailLoginPage(driver);
	    objHome = new GmailHomePage(driver);
	}

	@Test		
	public void testLoginSuccessful() throws Exception {	
		objLogin.setGmailUsername(usernameStr);
		objLogin.clickNextAfterUsername();
		objLogin.setGmailPassword(passwordStr);
		objLogin.clickNextAfterPassword();
		objWait.waitTillTitleContains(driver, "Inbox");
		assertTrue(objHome.isGmailComposeButtonPresent());
		System.out.println("Login Successful - Test completed");
	}	
	
	@Test				
	public void testLoginUnsuccessfulWrongUserName() throws Exception {	
		objLogin.setGmailUsername(wrongUser);
		objLogin.clickNextAfterUsername();
		assertEquals(objLogin.getWrongEmailMessage(), wrongEmailMessageCheck);
		System.out.println("Login unsuccessful(wrong username) - Test completed");
	}
	@Test				
	public void testLoginUnsuccessfulWrongPassword() throws Exception {	
		objLogin.setGmailUsername(usernameStr);
		objLogin.clickNextAfterUsername();
		objLogin.setGmailPassword(wrongPass);
		objLogin.clickNextAfterPassword();
		assertEquals(objLogin.getWrongPasswordMessage(), wrongPasswordCheck);
		System.out.println("Login unsuccessful(wrong password) - Test completed");
	}
	@AfterMethod
	public void teardown() {
		driver.close();
		driver.quit();			
	}		
}	
