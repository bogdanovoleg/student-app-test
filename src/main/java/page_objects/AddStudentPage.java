package page_objects;

import com.github.javafaker.Faker;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import utils.DriverManager;

import java.time.Duration;

import static org.openqa.selenium.support.ui.ExpectedConditions.elementToBeClickable;

public class AddStudentPage {
    private final WebDriver driver = DriverManager.getInstance();
    private final WebDriverWait webDriverWait;

    public WebElement getNameField() {
        return nameField;
    }

    public WebElement getNameFieldHelp() {
        return nameFieldHelp;
    }

    public WebElement getGenderDropDown() {
        return genderDropDown;
    }

    public WebElement getGenderFieldHelp() {
        return genderFieldHelp;
    }

    public WebElement getEmailField() {
        return emailField;
    }

    public WebElement getEmailFieldHelp() {
        return emailFieldHelp;
    }

    public WebElement getSubmitButton() {
        return submitButton;
    }

    public AddStudentPage() {
        this.webDriverWait = new WebDriverWait(driver, Duration.ofSeconds(5));
        PageFactory.initElements(driver, this);
    }

    @FindBy(how = How.ID, using = "name")
    WebElement nameField;
    @FindBy(how = How.ID, using = "name_help")
    WebElement nameFieldHelp;

    @FindBy(how = How.ID, using = "gender")
    WebElement genderDropDown;
    @FindBy(how = How.ID, using = "gender_help")
    WebElement genderFieldHelp;

    @FindBy(how = How.ID, using = "email")
    WebElement emailField;
    @FindBy(how = How.ID, using = "email_help")
    WebElement emailFieldHelp;

    @FindBy(how = How.XPATH, using = "//div[@class='ant-form-item-control-input-content']//button")
    WebElement submitButton;

    public void waitAndSetValueForNameField(String name) {
        webDriverWait.until(ExpectedConditions.visibilityOf(nameField));
        nameField.sendKeys(name);
    }

    public void waitAndSetGender(String gender) {
        genderDropDown.click();
        webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@title='" + gender.toUpperCase() + "']")));
        driver.findElement(By.xpath("//div[@title='" + gender.toUpperCase() + "']")).click();
    }

    public void waitAndSetValueForEmailField(String email) {
        webDriverWait.until(ExpectedConditions.visibilityOf(emailField));
        emailField.sendKeys(email);
    }

    public void clickOnSubmitButton() {
        webDriverWait.until(elementToBeClickable(submitButton));
        submitButton.click();
    }

    public String getText(WebElement webElement) {
        webDriverWait.until(ExpectedConditions.visibilityOf(webElement));
        return webElement.getText();
    }
}
