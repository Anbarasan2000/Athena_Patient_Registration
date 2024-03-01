package login.org;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.time.Duration;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.Select;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import page.org.LoginPageObject;

public class AthenaLogin {

	public WebDriver driver;

	LoginPageObject loginpage = new LoginPageObject(driver);

	@Given("Open Chrome browser")
	public void Open_Chrome_browser() throws InterruptedException {

		//For Browsec VPN 
		ChromeOptions opt = new ChromeOptions();
		opt.addExtensions(new File("./Extensions/Browsec-VPN-Free-VPN-for-Chrome.crx"));

		driver = new ChromeDriver(opt);

		Thread.sleep(15000);
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(180));

		driver.get("https://preview.athenahealth.com/1959031/2/login.esp");
		driver.manage().window().maximize();
	}

	@When("Enters username {string} and password {string}")
	public void enters_username_and_password(String user, String pass) {
		
		//TO Login Valid Credentials
		LoginPageObject loginpage = new LoginPageObject(driver);

		loginpage.setUserName(user);
		loginpage.setPassWord(pass);
	}

	@Then("Click Login")
	public void Click_Login() {
		//Click the Login Button
		LoginPageObject loginpage = new LoginPageObject(driver);
		loginpage.clickLogin();

	}

	@Then("Department")
	public void Department() {
		//Click the Department Button
		LoginPageObject loginpage = new LoginPageObject(driver);
		loginpage.clickLogin();

	}

	@Then("AthenaOne PatientReg")
	public void AthenaOne_PatientReg() throws InterruptedException, IOException {
		//Read the Values excel file to Athena Reg
		File f = new File("D:\\DemoAthena.xlsx");
		FileInputStream file = new FileInputStream(f);
		XSSFWorkbook workbook = new XSSFWorkbook(file);
		XSSFSheet sheet = workbook.getSheet("Sheet1");
		int rowCount = sheet.getLastRowNum();
		sheet.getRow(1).getLastCellNum();
		
		//For Loop Used for performing a certain action with specific number of Times  
		for (int i = 3; i <= rowCount; i++) {
			XSSFRow celldata = sheet.getRow(i);

			//Using We can create a new Cell for Write a Note
			XSSFCell cell = sheet.getRow(i).createCell(10);
			Thread.sleep(2000);

			//its an Single I_frame so we can switch the frame
			driver.switchTo().frame(driver.findElement(By.xpath("//iframe[@id='Status']")));
			
			//Select a Department
			Select dept = new Select(driver.findElement(By.xpath("//select[@id='DEPARTMENTID']")));
			dept.selectByVisibleText(celldata.getCell(0).getStringCellValue());
			
			//Switch to main frame so we use default_content Method
			driver.switchTo().defaultContent();
			Thread.sleep(7000);
			driver.switchTo().frame(driver.findElement(By.xpath("//iframe[@id='GlobalNav']")));
			LoginPageObject loginpage = new LoginPageObject(driver);
			
			//To click Patient Button
			loginpage.patientButton();
			Thread.sleep(1000);
			driver.switchTo().defaultContent();
			
			//Click New Reg Patient
			driver.findElement(By.xpath("//*[@id=\"2cc1cab777a698baa62c605bd4fc30ec\"]")).click();
			System.out.println("Started the Patient Registration");


			//We are getting Multiple iframe so we can Switch frame using SwitchTO Method
			driver.switchTo().frame(driver.findElement(By.xpath("//iframe[@id='GlobalWrapper']")));
			driver.switchTo().frame(driver.findElement(By.xpath("//frame[@id='frameContent']")));
			driver.switchTo().frame(driver.findElement(By.xpath("//iframe[@id='frMain']")));
			
			//To Enter LastName
			WebElement lname = driver.findElement(By.xpath("//input[@name='LASTNAME']"));
			lname.sendKeys(celldata.getCell(2).getStringCellValue());

			driver.switchTo().defaultContent();
			driver.switchTo().frame(driver.findElement(By.xpath("//iframe[@id='GlobalWrapper']")));
			driver.switchTo().frame(driver.findElement(By.xpath("//frame[@id='frameContent']")));
			driver.switchTo().frame(driver.findElement(By.xpath("//iframe[@id='frMain']")));
			
			//To Enter FirstName
			WebElement fname = driver.findElement(By.xpath("//input[@name='FIRSTNAME']"));
			fname.sendKeys(celldata.getCell(1).getStringCellValue());

			driver.switchTo().defaultContent();
			driver.switchTo().frame(driver.findElement(By.xpath("//iframe[@id='GlobalWrapper']")));
			driver.switchTo().frame(driver.findElement(By.xpath("//frame[@id='frameContent']")));
			driver.switchTo().frame(driver.findElement(By.xpath("//iframe[@id='frMain']")));
			
			//To Select gender using Select Method
			Select sex = new Select(driver.findElement(By.xpath("//select[@name='SEX']")));
			sex.selectByValue(celldata.getCell(5).getStringCellValue());

			driver.switchTo().defaultContent();
			driver.switchTo().frame(driver.findElement(By.xpath("//iframe[@id='GlobalWrapper']")));
			driver.switchTo().frame(driver.findElement(By.xpath("//frame[@id='frameContent']")));
			driver.switchTo().frame(driver.findElement(By.xpath("//iframe[@id='frMain']")));
			
			//To Enter DOB 
			WebElement dob = driver.findElement(By.xpath("//input[@id='dob']"));
			dob.sendKeys(celldata.getCell(4).getStringCellValue());

			driver.switchTo().defaultContent();
			driver.switchTo().frame(driver.findElement(By.xpath("//iframe[@id='GlobalWrapper']")));
			driver.switchTo().frame(driver.findElement(By.xpath("//frame[@id='frameContent']")));
			driver.switchTo().frame(driver.findElement(By.xpath("//iframe[@id='frMain']")));
			
			//To Enter HomeNumber
			WebElement Hphone = driver.findElement(By.xpath("//input[@name='HOMEPHONE']"));
			Hphone.sendKeys(celldata.getCell(3).getStringCellValue());

			driver.switchTo().defaultContent();
			driver.switchTo().frame(driver.findElement(By.xpath("//iframe[@id='GlobalWrapper']")));
			driver.switchTo().frame(driver.findElement(By.xpath("//frame[@id='frameContent']")));
			driver.switchTo().frame(driver.findElement(By.xpath("//iframe[@id='frMain']")));
			
			//To Click Copy From HomeNumber
			WebElement mphone = driver.findElement(By.xpath("//a[normalize-space()='Copy from home']"));
			mphone.click();

			driver.switchTo().defaultContent();
			driver.switchTo().frame(driver.findElement(By.xpath("//iframe[@id='GlobalWrapper']")));
			driver.switchTo().frame(driver.findElement(By.xpath("//frame[@id='frameContent']")));
			driver.switchTo().frame(driver.findElement(By.xpath("//iframe[@id='frMain']")));
			
			//To click ConcentToCall
			WebElement ccall = driver.findElement(By.xpath("//input[@id='rb_CONSENTTOCALLFLAG_2']"));
			ccall.click();

			driver.switchTo().defaultContent();
			driver.switchTo().frame(driver.findElement(By.xpath("//iframe[@id='GlobalWrapper']")));
			driver.switchTo().frame(driver.findElement(By.xpath("//frame[@id='frameContent']")));
			driver.switchTo().frame(driver.findElement(By.xpath("//iframe[@id='frMain']")));
			
			//To Click ConcentToText
			WebElement ctext = driver.findElement(By.xpath("//input[@id='rb_CONSENTTOTEXTRADIO_2']"));
			ctext.click();

			driver.switchTo().defaultContent();
			driver.switchTo().frame(driver.findElement(By.xpath("//iframe[@id='GlobalWrapper']")));
			driver.switchTo().frame(driver.findElement(By.xpath("//frame[@id='frameContent']")));
			driver.switchTo().frame(driver.findElement(By.xpath("//iframe[@id='frMain']")));
			
			//To Enter Email 
			WebElement email = driver.findElement(By.xpath("//input[@name='EMAIL']"));
			email.sendKeys(celldata.getCell(6).getStringCellValue());

			driver.switchTo().defaultContent();
			driver.switchTo().frame(driver.findElement(By.xpath("//iframe[@id='GlobalWrapper']")));
			driver.switchTo().frame(driver.findElement(By.xpath("//frame[@id='frameContent']")));
			driver.switchTo().frame(driver.findElement(By.xpath("//iframe[@id='frMain']")));

			//Select MartialStatus Using Select Method
			Select sel = new Select(driver.findElement(By.xpath("//select[@name='MARITALSTATUSID']")));
			sel.selectByValue("S");
			driver.switchTo().defaultContent();
			driver.switchTo().frame(driver.findElement(By.xpath("//iframe[@id='GlobalWrapper']")));
			driver.switchTo().frame(driver.findElement(By.xpath("//frame[@id='frameContent']")));
			driver.switchTo().frame(driver.findElement(By.xpath("//iframe[@id='frMain']")));

			//Selct RelationShip Using Select Method
			Select sg = new Select(driver.findElement(By.xpath("//select[@id='RELATIONSHIPTOPATIENTID']")));
			sg.selectByIndex(2);

			driver.switchTo().defaultContent();
			driver.switchTo().frame(driver.findElement(By.xpath("//iframe[@id='GlobalWrapper']")));
			driver.switchTo().frame(driver.findElement(By.xpath("//frame[@id='frameContent']")));
			driver.switchTo().frame(driver.findElement(By.xpath("//iframe[@id='frMain']")));

			//To click CheckBox 
			WebElement pdec = driver.findElement(By.xpath("//input[@id='checkbox_yesno_UNCONFFAMILYSIZEDECLINEDYN']"));
			pdec.click();
			driver.switchTo().defaultContent();
			driver.switchTo().frame(driver.findElement(By.xpath("//iframe[@id='GlobalWrapper']")));
			driver.switchTo().frame(driver.findElement(By.xpath("//frame[@id='frameContent']")));
			driver.switchTo().frame(driver.findElement(By.xpath("//iframe[@id='frMain']")));

			////To click CheckBox 
			WebElement pdeclined = driver
					.findElement(By.xpath("//input[@id='checkbox_yesno_UNCONFIRMEDINCOMEDECLINEDYN']"));
			pdeclined.click();
			driver.switchTo().defaultContent();
			driver.switchTo().frame(driver.findElement(By.xpath("//iframe[@id='GlobalWrapper']")));
			driver.switchTo().frame(driver.findElement(By.xpath("//frame[@id='frameContent']")));
			driver.switchTo().frame(driver.findElement(By.xpath("//iframe[@id='frMain']")));

			//To click Save and Schedule Button
			WebElement ss = driver.findElement(By.xpath("//input[@id='RegistrationSaveScheduleButton']"));
			ss.click();

			Thread.sleep(2000);
			driver.switchTo().alert().accept();

			driver.switchTo().defaultContent();
			driver.switchTo().frame(driver.findElement(By.xpath("//iframe[@id='GlobalWrapper']")));
			driver.switchTo().frame(driver.findElement(By.xpath("//frame[@id='frameContent']")));
			driver.switchTo().frame(driver.findElement(By.xpath("//iframe[@id='frMain']")));
			driver.switchTo().frame(driver.findElement(By.xpath("//iframe[@name='frFindCalendar']")));
			
			//To click Date 
			driver.findElement(By.xpath("//*[@id=\"d_2024_02_25\"]")).click();

			driver.switchTo().defaultContent();
			driver.switchTo().frame(driver.findElement(By.xpath("//iframe[@id='GlobalWrapper']")));
			driver.switchTo().frame(driver.findElement(By.xpath("//frame[@id='frameContent']")));
			driver.switchTo().frame(driver.findElement(By.xpath("//iframe[@id='frMain']")));
			driver.switchTo().frame(driver.findElement(By.xpath("//iframe[@name='frFindMain']")));
			
			//To click Create a New Slot
			driver.findElement(By.xpath("//a[normalize-space()='Create new slot']")).click();

			driver.switchTo().defaultContent();
			driver.switchTo().frame(driver.findElement(By.xpath("//iframe[@id='GlobalWrapper']")));
			driver.switchTo().frame(driver.findElement(By.xpath("//frame[@id='frameContent']")));
			driver.switchTo().frame(driver.findElement(By.xpath("//iframe[@id='frMain']")));
			driver.switchTo().frame(driver.findElement(By.xpath("//iframe[@name='frFindMain']")));
			
			//To Select a Provider 
			Select pro = new Select(driver.findElement(By.xpath("//select[@name='STAFFUSERNAME']")));
			pro.selectByValue(celldata.getCell(7).getStringCellValue());

			driver.switchTo().defaultContent();
			driver.switchTo().frame(driver.findElement(By.xpath("//iframe[@id='GlobalWrapper']")));
			driver.switchTo().frame(driver.findElement(By.xpath("//frame[@id='frameContent']")));
			driver.switchTo().frame(driver.findElement(By.xpath("//iframe[@id='frMain']")));
			driver.switchTo().frame(driver.findElement(By.xpath("//iframe[@name='frFindMain']")));
			Thread.sleep(2000);
			
			//To Select a Appointment ID
			Select apt = new Select(driver.findElement(By.xpath("//select[@name='APPOINTMENTTYPEID']")));
			try {
				apt.selectByVisibleText(celldata.getCell(8).getStringCellValue());
			} catch (StaleElementReferenceException e) {
				apt = new Select(driver.findElement(By.xpath("//select[@name='APPOINTMENTTYPEID']")));
				apt.selectByVisibleText(celldata.getCell(8).getStringCellValue());

			}

			Thread.sleep(2000);
			driver.switchTo().defaultContent();
			driver.switchTo().frame(driver.findElement(By.xpath("//iframe[@id='GlobalWrapper']")));
			driver.switchTo().frame(driver.findElement(By.xpath("//frame[@id='frameContent']")));
			driver.switchTo().frame(driver.findElement(By.xpath("//iframe[@id='frMain']")));
			driver.switchTo().frame(driver.findElement(By.xpath("//iframe[@name='frFindMain']")));
			
			//To Enter Time
			WebElement time = driver.findElement(By.xpath("//input[@name='STARTTIME']"));
			time.sendKeys(celldata.getCell(9).getStringCellValue());

			driver.switchTo().defaultContent();
			driver.switchTo().frame(driver.findElement(By.xpath("//iframe[@id='GlobalWrapper']")));
			driver.switchTo().frame(driver.findElement(By.xpath("//frame[@id='frameContent']")));
			driver.switchTo().frame(driver.findElement(By.xpath("//iframe[@id='frMain']")));
			driver.switchTo().frame(driver.findElement(By.xpath("//iframe[@name='frFindMain']")));
			
			//To Click AddAppointment
			driver.findElement(By.xpath("//input[@id='ADDAPPOINTMENT']")).click();
			Thread.sleep(2000);
			
			
			driver.switchTo().defaultContent();
			driver.switchTo().frame(driver.findElement(By.xpath("//iframe[@id='GlobalWrapper']")));
			driver.switchTo().frame(driver.findElement(By.xpath("//frame[@id='frameContent']")));
			driver.switchTo().frame(driver.findElement(By.xpath("//iframe[@id='frMain']")));
			driver.switchTo().frame(driver.findElement(By.xpath("//iframe[@name='frFindMain']")));
			String text = driver.findElement(By.xpath("//tbody/tr[2]/td[1]")).getText();
			String aptcrt = text.toString();

			if (text.isEmpty()) {

				cell.setCellValue("Appointment Not Created");
			} else {
				cell.setCellValue(aptcrt);
			}

			//Using to Write a Values Webdriver to Excel
			FileOutputStream fos = new FileOutputStream(f);
			workbook.write(fos);

			System.out.println(aptcrt);
			System.out.println("===============================");

			driver.switchTo().defaultContent();
			driver.switchTo().frame(driver.findElement(By.xpath("//iframe[@id='GlobalNav']")));
			driver.findElement(By.xpath("//*[@id=\"globalnav\"]/div[1]/div[1]/span")).click();
			driver.switchTo().defaultContent();

		}

	}

}
