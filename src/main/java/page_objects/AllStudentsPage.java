package page_objects;

import lombok.Getter;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;
import utils.DriverManager;

import java.time.Duration;

import static org.openqa.selenium.support.ui.ExpectedConditions.elementToBeClickable;

public class AllStudentsPage {
    private final WebDriverWait webDriverWait;
    public AllStudentsPage() {
        WebDriver driver = DriverManager.getInstance();
        this.webDriverWait = new WebDriverWait(driver, Duration.ofSeconds(5));
        PageFactory.initElements(driver, this);
    }
    @Getter @FindBy(how = How.XPATH, using = "//div[@class='ant-table-title']//button")
    WebElement addStudentButton;
    @Getter @FindBy(how = How.XPATH, using = "//button[@class='ant-pagination-item-link']//span[@aria-label='right']")
    WebElement nextPageButton;
    public void waitAndClickOnAddStudentButton() {
        webDriverWait.until(elementToBeClickable(addStudentButton));
        addStudentButton.click();
    }
    public void waitAndClickOnDeleteStudentButton(String email){

    }
}
