package PageFactory;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import SeleniumHelpers.CustomWaits;

public class GmailHomePage {
	WebDriver driver;
	CustomWaits objWait = new CustomWaits();
	
	//Element list
	@FindBy(xpath = "//div[@class='T-I J-J5-Ji T-I-KE L3']")
	WebElement composeButton;
	
	@FindBy(xpath = "//div[@class='aYF']")
	WebElement headerNewMessage;
	
	@FindBy(name = "to")
	WebElement toNewMessage;
	
	@FindBy(name = "cc")
	WebElement ccNewMessage;
	
	@FindBy(name = "subjectbox")
	WebElement subjectNewMessage;
	
	@FindBy(xpath = "//div[@aria-label=\"Message Body\"]")
	WebElement mailBlockNewMessage;
	
	@FindBy(xpath = "//div[@data-tooltip= \"Send ‪(Ctrl-Enter)‬\"]")
	WebElement sendNewMessage;
	
	@FindBy(xpath = "//div[@data-tooltip = \"Sent\"]")
	WebElement sentMail;
	
	@FindBy(xpath = "//tr[@tabindex = \"-1\"]")
	WebElement[] sentEmailList;
	
	//Constructor
	public GmailHomePage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	//Getters and Setters
	public void clickGmailComposeButton() {
		objWait.waitTillTElementIsClickable(driver, composeButton);
		composeButton.click();
	}
	
	public Boolean isGmailComposeButtonPresent() {
		return composeButton.isDisplayed();
	}
	
	public Boolean isNewMessageOpen() {
		return headerNewMessage.isDisplayed();
	}
	
	public void setRecipients(String input) throws Exception {
		try {
			toNewMessage.click();
			toNewMessage.sendKeys(input);
			toNewMessage.sendKeys(Keys.TAB);
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
	
	public void setCC(String input) throws Exception {
		try {
			Actions act = new Actions(driver);
			act.sendKeys(Keys.CONTROL,Keys.SHIFT,"c");
			ccNewMessage.click();
			ccNewMessage.sendKeys(input);
			ccNewMessage.sendKeys(Keys.TAB);
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
	
	public void setSubject(String input) throws Exception {
		try {
			subjectNewMessage.sendKeys(input);;
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
	
	public void setMailBody(String input) throws Exception {
		try {
			mailBlockNewMessage.sendKeys(input);
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
	
	public void sendMessage() {
		Actions act = new Actions(driver);
		act.sendKeys(Keys.CONTROL,Keys.ENTER);
	}
	
	public void clickSentMailTab() {
		objWait.waitTillTElementIsClickable(driver, sentMail);
		sentMail.click();
	}
	
	public Boolean checkLastSentEmailFromList(String testSubject) {
		String xpathValue = "//tr[@tabindex = \"-1\"]//span[text() =\""+testSubject+"\"]";
		WebElement e = driver.findElement(By.xpath(xpathValue));
		e.click();
		return e.isDisplayed();
	}
}
