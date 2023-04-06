package page_objects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import utils.DriverManager;

import java.time.Duration;

public class Notifications {
    private final WebDriverWait webDriverWait;
    public Notifications() {
        WebDriver driver = DriverManager.getInstance();
        webDriverWait = new WebDriverWait(driver, Duration.ofSeconds(10));
        PageFactory.initElements(driver, this);
    }

    public WebElement getNotificationMessageElement() {
        return notificationMessageElement;
    }

    public WebElement getNotificationDescriptionElement() {
        return notificationDescriptionElement;
    }

    public WebElement getPopUpCloseButton() {
        return popUpCloseButton;
    }

    @FindBy(how = How.CLASS_NAME, using = "ant-notification-notice-message")
    WebElement notificationMessageElement;

    @FindBy(how = How.CLASS_NAME, using = "ant-notification-notice-description")
    WebElement notificationDescriptionElement;

    @FindBy(how = How.CLASS_NAME, using = "ant-notification-notice-close")
    WebElement popUpCloseButton;


    public String getText(WebElement notification) {
        webDriverWait.until(ExpectedConditions.visibilityOf(notification));
        return notification.getText();
    }
}
