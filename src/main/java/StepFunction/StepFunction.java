package StepFunction;

import java.awt.RenderingHints.Key;
import java.sql.Date;
import java.util.Random;

import org.apache.xmlbeans.impl.xb.xsdschema.Public;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import ExcelUtils.ExcelUtils;
import RunnerClass.RunnerClass;
import net.bytebuddy.agent.builder.AgentBuilder.RedefinitionStrategy.DiscoveryStrategy.Explicit;

public class StepFunction {

	public static WebDriverWait wait = new WebDriverWait(RunnerClass.driver, 20);

	public static boolean Login_TW(WebDriver driver) throws Exception {

		Thread.sleep(10000);
		try {

			WebElement Region_Selection = driver
					.findElement(By.xpath("//*[contains(@class,\"tw-server-selection__text\")]"));

			wait.until(ExpectedConditions.elementToBeClickable(Region_Selection));

			if (driver.findElement(By.xpath("//*[contains(text(),\"North America\")]")).isDisplayed()) {

				driver.findElement(By.xpath("//*[contains(text(),\"North America\")]"));
				JavascriptExecutor executor = (JavascriptExecutor) driver;
				executor.executeScript("arguments[0].click();", Region_Selection);
				// Region_Selection.click();
				Thread.sleep(1000);
				WebElement Region_Asia = driver.findElement(By.xpath("//*[contains(text(),\"Asia\")]"));
				Region_Asia.click();

				ExcelUtils.setCellData("Pass", 1, 2, "RegressionTestCases", RunnerClass.filePath);
				System.out.println("Region Select");
				RunnerClass.Tflag=true;
			}

		} catch (Exception e) {
			RunnerClass.Tflag=false;
			System.out.println("something went wrong during Region Select");
			ExcelUtils.setCellData("Fail", 1, 2, "RegressionTestCases", RunnerClass.filePath);
			// TODO: handle exception
		}

		Thread.sleep(5000);

		try {

			WebElement login = driver.findElement(By.xpath("//*[contains(@name,\"email\")]"));

			WebElement Password = driver.findElement(By.xpath("//*[contains(@name,'password')]"));
			WebElement Log_In_button = driver.findElement(By.xpath("//*[contains(@type,'submit')]"));

			if (driver.findElement(By.xpath("//*[contains(@name,\"email\")]")).isDisplayed()) {

				login.sendKeys(RunnerClass.Username);

				Password.sendKeys(RunnerClass.Passwoord);

				Log_In_button.click();

				ExcelUtils.setCellData("Pass", 2, 2, "RegressionTestCases", RunnerClass.filePath);
				System.out.println("Login into TW");
				RunnerClass.Tflag=true;
			}

		} catch (Exception e) {
			RunnerClass.Tflag=false;
			System.out.println("Something went wrong during Login into TW");
			ExcelUtils.setCellData("Fail", 2, 2, "RegressionTestCases", RunnerClass.filePath);
			// TODO: handle exception
		}
		
		
		return RunnerClass.Tflag;

	}

	public static boolean Create_New_Project(WebDriver driver) throws Exception {

		Thread.sleep(5000);
		try {

			if (driver
					.findElement(By.xpath("//*[contains(@class,'tw-icon tw-icon-add --name_add tw-global-add__icon')]"))
					.isDisplayed()) {

				driver.findElement(
						By.xpath("//*[contains(@class,'tw-icon tw-icon-add --name_add tw-global-add__icon')]")).click();

				Thread.sleep(3000);

				driver.findElement(By.xpath("//div[contains(text(),'New Project')]")).click();

				Thread.sleep(5000);

				Random rnd = new Random();
				int number = 10 + rnd.nextInt(90);
				String Project_name = "Project-" + number;
				String Project_dessciption = "Project_dessciption-" + number;
				driver.findElement(By.xpath("//input[contains(@name,'project-name')]")).sendKeys(Project_name);

				driver.findElement(By.xpath("//input[contains(@name,\"description\")]")).sendKeys(Project_dessciption);

				driver.findElement(By.xpath("//span[contains(text(),\"Next: Choose a template\")]")).click();

				JavascriptExecutor executor = (JavascriptExecutor) driver;
				executor.executeScript("arguments[0].click();",
						driver.findElement(By.xpath("//div[contains(text(),\"Create Project\")]")));

				ExcelUtils.setCellData("Pass", 3, 2, "RegressionTestCases", RunnerClass.filePath);
				System.out.println("Create New Project into TW");
				RunnerClass.Tflag=true;
			}

		} catch (Exception e) {
			RunnerClass.Tflag=false;
			System.out.println("Something went wrong during Create New Project into TW");

			ExcelUtils.setCellData("Fail", 3, 2, "RegressionTestCases", RunnerClass.filePath);
			// TODO: handle exception
		}
		return RunnerClass.Tflag;

	}

	public static boolean Add_Task(WebDriver driver) throws Exception {

		Thread.sleep(5000);
		Random random = new Random();
		int number = 10 + random.nextInt(90);
		String TW_Assignment = "Assignment" + number;

		try {

			if (driver.findElement(By.xpath("//*[contains(@class,'tw-editable-textfield --textfield')]"))
					.isDisplayed()) {

				System.out.println("Project Created");

				Actions act = new Actions(driver);
				act.moveToElement(
						driver.findElement(By.xpath("//*[contains(@class,'tw-editable-textfield --textfield')]")))
						.build().perform();
				act.click().build().perform();
				act.sendKeys(TW_Assignment).build().perform();
				act.sendKeys(Keys.ENTER).build().perform();

			}
			ExcelUtils.setCellData("Pass", 4, 2, "RegressionTestCases", RunnerClass.filePath);
			System.out.println("Add New Task");
			RunnerClass.Tflag=true;

		} catch (Exception e) {
			System.out.println("Something went wrong during Add New Task into TW");
			RunnerClass.Tflag=false;
			ExcelUtils.setCellData("Fail", 4, 2, "RegressionTestCases", RunnerClass.filePath);
			// TODO: handle exception
		}

		String TW_Assignment_Sub_task = "TW__Sub_task" + number;

		try {
			if (driver.findElement(By.xpath("//*[contains(@class,'tw-search-box__input ax-search-field')]"))
					.isDisplayed()) {

				driver.findElement(By.xpath("//*[contains(@class,'tw-search-box__input ax-search-field')]")).click();

				Thread.sleep(2000);

				JavascriptExecutor executor = (JavascriptExecutor) driver;

				executor.executeScript("arguments[0].click();", driver.findElement(By.xpath(
						"//div[contains(@class,'tw-click-area tw-tasklist-header__add-icon ax-add-task-button')]")));

				Thread.sleep(2000);

				driver.findElement(By.xpath("//*[contains(@class,'ax-task-input-panel-textfield')]"))
						.sendKeys(TW_Assignment_Sub_task);

				executor.executeScript("arguments[0].click();", driver.findElement(By.xpath(
						"//button[contains(@class,'tw-button --size-28px --padding-normal --primary ax-create-task-button --narrow')]")));

				ExcelUtils.setCellData("Pass", 5, 2, "RegressionTestCases", RunnerClass.filePath);


				System.out.println("Add sub Task into TW");
			}
			RunnerClass.Tflag=true;

		} catch (Exception e) {
			RunnerClass.Tflag=false;
			System.out.println("Something went wrong during Add sub Task into TW");
			ExcelUtils.setCellData("Fail", 5, 2, "RegressionTestCases", RunnerClass.filePath);

			// TODO: handle exception
		}

		// driver.findElement(By.xpath("//button[contains(@class,'tw-button --size-28px
		// --padding-normal --primary ax-create-task-button --narrow')]")).click();

		Thread.sleep(2000);
		try {

			if (driver.findElement(By.xpath("//*[contains(@data-task-title," + TW_Assignment_Sub_task + ")]"))
					.isDisplayed()) {
				System.out.println("Sub Task Found in list - " + TW_Assignment_Sub_task);

				Actions act_subtask = new Actions(driver);

				act_subtask
						.moveToElement(
								driver.findElement(By.xpath("//div[contains(@class,'tw-task-header__checkbox')]")))
						.build().perform();
				act_subtask.click().build().perform();

				Actions act_checkbox = new Actions(driver);

				act_checkbox.moveToElement(driver.findElement(By.xpath(
						"//*[contains(@class,'tw-click-area tw-task-checkbox ax-task-checkbox --size-regular')]")))
						.build().perform();
				act_checkbox.click().build().perform();

				Actions act_title = new Actions(driver);

				act_title
						.moveToElement(driver.findElement(
								By.xpath("//*[contains(@class,'tw-task-header__title ax-task-header__title')]")))
						.build().perform();
				act_title.click().build().perform();
				ExcelUtils.setCellData("Pass", 6, 2, "RegressionTestCases", RunnerClass.filePath);
				System.out.println("Marked as comolete to the  sub Task into TW");
				RunnerClass.Tflag=true;
			}

		} catch (Exception e) {
			RunnerClass.Tflag=false;
			System.out.println("Something went wrong during Marked as comolete to the  sub Task into TW");
			ExcelUtils.setCellData("Fail", 6, 2, "RegressionTestCases", RunnerClass.filePath);

			// TODO: handle exception
		}

		try {
			if (driver.findElement(By.xpath("//div[contains(@class,\"tw-editable-panel-title__text\")]"))
					.isDisplayed()) {

				System.out.println("Task completed successfully====" + TW_Assignment_Sub_task);

				ExcelUtils.setCellData("Pass", 7, 2, "RegressionTestCases", RunnerClass.filePath);
				System.out.println("See the details of comoleted sub Task into TW");
				RunnerClass.Tflag=true;
			}
		} catch (Exception e) {
			RunnerClass.Tflag=false;
			System.out.println("Something went wrong during see the details of comoleted sub Task into TW");
			ExcelUtils.setCellData("Fail", 7, 2, "RegressionTestCases", RunnerClass.filePath);
			// TODO: handle exception
		}
		
		try {
			
		if(driver.findElement(By.xpath("//*[contains(@class,'tw-user-menu-button ax-user-menu')]")).isDisplayed()) {
			
			driver.findElement(By.xpath("//*[contains(@class,'tw-user-menu-button ax-user-menu')]")).click();
			
			Thread.sleep(2000);
			
			driver.findElement(By.xpath("//*[contains(text(),\"Sign Out\")]")).click();
			ExcelUtils.setCellData("Pass", 8, 2, "RegressionTestCases", RunnerClass.filePath);
			
			System.out.println("Logout from TW");
			RunnerClass.Tflag=true;
		}
			
		}catch (Exception e) {
			RunnerClass.Tflag=false;
			System.out.println("Something went wrong during Logout from TW");
			ExcelUtils.setCellData("Fail", 8, 2, "RegressionTestCases", RunnerClass.filePath);
			// TODO: handle exception
		}
		return RunnerClass.Tflag;
		

	}

}
