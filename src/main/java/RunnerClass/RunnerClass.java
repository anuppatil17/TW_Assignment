/**
 * CHANGE PASSWORD METHOD
 * @author ANUP PATIL
 */
package RunnerClass;

import java.awt.Desktop;
import java.io.File;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.devtools.Log;
import org.testng.Assert;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;
import ExcelUtils.ExcelUtils;
import StepFunction.StepFunction;

public class RunnerClass {
	public static WebDriver driver;
	
    public static boolean Tflag=false;   
	public static String projectPath = System.getProperty("user.dir");// get current project directory
	public static String filePath = projectPath + "//src//main//java//DataSheet//TW_Assignment.xlsx";// excel file for testcases
	public static String Username ="anup.patil17@gmail.com";
	public static String Passwoord ="Anup@2838";
	

	/**
	 * 
	 * Read test data from Excel and assign to different variables in Runtime/
	 */
	@BeforeSuite
	public void AssignData() throws Exception {
		ExcelUtils.setExcelFile(filePath);
		int RowNum = ExcelUtils.getRowContains("SC_001", 0, "Scenario");

		String chromepath = System.getProperty("user.dir");
		System.setProperty("webdriver.chrome.driver", chromepath + "//chromedriver"); // for mac
		driver = new ChromeDriver();
		driver.get("https://enterprise.taskworld.com/");
		driver.manage().window().maximize();

	}

	/*
	 * Login to the application Create New Project -Create Task - Create Sub Task
	 * Marked As Complete Check details of application Logout
	 */

	@Test(description="Login to the application and Create new project")
	public void SC_01() throws Exception {
		try {
			Tflag=StepFunction.Login_TW(driver);

			Tflag=StepFunction.Create_New_Project(driver);

			Tflag=StepFunction.Add_Task(driver);

			if(Tflag==true) {
				ExcelUtils.setCellData("Pass", 1, 2, "Scenario", RunnerClass.filePath);
			}else {
				ExcelUtils.setCellData("Fail", 1, 2, "Scenario", RunnerClass.filePath);
			}
			
		} catch (Exception e) {
			ExcelUtils.setCellData("Fail", 1, 2, "Scenario", RunnerClass.filePath);
			System.out.println("Something went wrong........");
			// TODO: handle exception
		}

	}

	/**
	 * After Execution in @AfterSuite method will open the Excel FIle
	 */
	@AfterSuite
	public void close() {
		try {
			Desktop.getDesktop().open(new File(filePath));
			System.out.println("close");
		} catch (Exception e) {
			System.out.println("Exception to open excel file-" + filePath + e);
		}
	}
}
