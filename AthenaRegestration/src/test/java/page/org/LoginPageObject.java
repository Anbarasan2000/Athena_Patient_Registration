package page.org;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPageObject {

	public WebDriver ldriver;

	public LoginPageObject(WebDriver rdriver) {

		ldriver = rdriver;
		PageFactory.initElements(rdriver, this);
	}

	@FindBy(id = "USERNAME")
	@CacheLookup
	WebElement userName;

	@FindBy(id = "PASSWORD")
	@CacheLookup
	WebElement passWord;

	@FindBy(id = "loginbutton")
	@CacheLookup
	WebElement loginButton;

	@FindBy(id = "patientsmenucomponent")
	@CacheLookup
	WebElement patient;

	@FindBy(xpath = "//*[@id=\\\"2cc1cab777a698baa62c605bd4fc30ec\\\"]")
	@CacheLookup
	WebElement newPatientReg;

	public void setUserName(String uName) {
		userName.sendKeys(uName);

	}

	public void setPassWord(String pWord) {
		passWord.sendKeys(pWord);
	}

	public void clickLogin() {
		loginButton.click();
	}

	public void patientButton() {
		patient.click();
	}

	public void regButton() {
		newPatientReg.click();
	}
}
