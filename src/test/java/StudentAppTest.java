import com.github.javafaker.Faker;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import page_objects.AddStudentPage;
import page_objects.AllStudentsPage;
import page_objects.Notifications;

import java.lang.reflect.Method;
import java.time.Duration;

import static constants.AllConstants.GenderConstants.MALE;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;
import static utils.ConfigHelper.getConfig;
import static utils.DriverManager.*;

public class StudentAppTest {
    private WebDriverWait driverWait;
    private AllStudentsPage allStudentsPage;
    private AddStudentPage addStudentPage;
    private Notifications notifications;
    private final Faker dataFaker = new Faker();

    @BeforeMethod(alwaysRun = true)
    public void initialize(Method method) {
        testName = (method.getName());
        driverWait = new WebDriverWait(getInstance(), Duration.ofSeconds(10));
        getInstance().get(getConfig().getString("student.app.hostname"));
        allStudentsPage = new AllStudentsPage();
        addStudentPage = new AddStudentPage();
        notifications = new Notifications();
    }

    @AfterMethod(alwaysRun = true)
    public void tearDown(ITestResult result) {
        if (!getConfig().getBoolean("student.app.run.locally")) markRemoteTest(result);
        closeDriver();
    }

    @Test(description = "Add student, check successful and error messages")
    public void openStudentApp() {
        allStudentsPage.waitAndClickOnAddStudentButton();

        String name = dataFaker.name().firstName();
        addStudentPage.waitAndSetValueForNameField(name);
        String email = dataFaker.internet().emailAddress();
        addStudentPage.waitAndSetValueForEmailField(email);
        addStudentPage.waitAndSetGender(MALE);
        addStudentPage.clickOnSubmitButton();

        assertEquals(notifications.getText(notifications.getNotificationMessageElement()), "Student successfully added");
        assertEquals(notifications.getText(notifications.getNotificationDescriptionElement()), name + " was added to the system");

        notifications.getPopUpCloseButton().click();
        assertTrue(driverWait.until(ExpectedConditions.invisibilityOf(notifications.getPopUpCloseButton())));

        allStudentsPage.waitAndClickOnAddStudentButton();
        addStudentPage.clickOnSubmitButton();
        Assert.assertEquals(notifications.getText(notifications.getNotificationMessageElement()), "There was an issue");
        Assert.assertEquals(notifications.getText(notifications.getNotificationDescriptionElement()), "Email " + email + " taken [400] [Bad Request]");

    }

    @Test()
    public void checkTitle() {
        assertEquals(getInstance().getTitle(), "acodemy - React App");
    }


    @Test(description = "Check that add student fields are mandatory")
    public void addStudentFieldValidation() {
        allStudentsPage.waitAndClickOnAddStudentButton();

        addStudentPage.clickOnSubmitButton();
        Assert.assertEquals(addStudentPage.getText(addStudentPage.getNameFieldHelp()), "Please enter student name");
        Assert.assertEquals(addStudentPage.getText(addStudentPage.getEmailFieldHelp()), "Please enter student email");
        Assert.assertEquals(addStudentPage.getText(addStudentPage.getGenderFieldHelp()), "Please select a gender");
    }

    @Test(description = "Add student and delete the same student")
    public void addAndDeleteStudent() {
        allStudentsPage.waitAndClickOnAddStudentButton();

        String name = dataFaker.name().firstName();
        addStudentPage.waitAndSetValueForNameField(name);
        String email = dataFaker.internet().emailAddress();
        addStudentPage.waitAndSetValueForEmailField(email);
        addStudentPage.waitAndSetGender(MALE);
        addStudentPage.clickOnSubmitButton();

        assertEquals(notifications.getText(notifications.getNotificationMessageElement()), "Student successfully added");
        assertEquals(notifications.getText(notifications.getNotificationDescriptionElement()), name + " was added to the system");

        notifications.waitAndClickOnPopUpCloseButton();

    }
}