package PageFactory;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import SeleniumHelpers.CustomWaits;
public class GmailLoginPage {
	
	WebDriver driver;
	CustomWaits objWait = new CustomWaits();
	
	@FindBy(id = "identifierId")
	WebElement gmailUserName;
	
	@FindBy(id = "identifierNext")
	WebElement gmailUserNameNextButton;
	
	@FindBy(name = "password")
	WebElement gmailPassword;
	
	@FindBy(id = "passwordNext")
	WebElement gmailPassWordNextButton;
	
	@FindBy(xpath = "//*[@id=\"view_container\"]//*[contains(text(), \"valid email\")]")
	WebElement wrongEmailMessage;
	
	@FindBy(xpath = "//*[@id=\"password\"]//*[contains(text(), \"Wrong password\")]")
	WebElement wrongPasswordMessage;
	
	
	//Constructor
	public GmailLoginPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	//Getter - Setters
	public void setGmailUsername(String usr) throws Exception {
		try {
			gmailUserName.sendKeys(usr);
		}
		catch (StaleElementReferenceException e) {
			PageFactory.initElements(driver, this);
		}
		catch (WebDriverException w_e) {
			throw(w_e);
		}
		catch (Exception e) {
			throw(e);
		}
	}
	
	public void setGmailPassword(String pwd) throws Exception {
		try {
			gmailPassword.sendKeys(pwd);			
		}
		catch (StaleElementReferenceException e) {
			PageFactory.initElements(driver, this);
		}
		catch (WebDriverException w_e) {
			throw(w_e);
		}
		catch (Exception e) {
			throw(e);
		}
	}
	
	public String getGmailUsername() {
		return gmailUserName.getText();
	}
	
	public String getGmailPassword() {
		return gmailPassword.getText();
	}
	
	public Boolean isWrongEmailMessagePresent() {
		return wrongEmailMessage.isDisplayed();
	}
	
	public Boolean isWrongPasswordMessagePresent() {
		return wrongPasswordMessage.isDisplayed();
	}
	
	public String getWrongEmailMessage() {
		return wrongEmailMessage.getText();
	}
	
	public String getWrongPasswordMessage() {
		return wrongPasswordMessage.getText();
	}
	
	public void clickNextAfterUsername() {
		objWait.waitTillTElementIsClickable(driver, gmailUserNameNextButton);
		gmailUserNameNextButton.click();
	}
	
	public void clickNextAfterPassword() {
		objWait.waitTillTElementIsClickable(driver, gmailPassWordNextButton);
		gmailPassWordNextButton.click();
	}
	
	public WebElement getPasswordElement() {
		return this.gmailPassword;
	}
}
