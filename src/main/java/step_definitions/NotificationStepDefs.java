package step_definitions;

import io.cucumber.java.en.Then;
import org.testng.Assert;
import page_objects.Notifications;

import static org.testng.Assert.assertEquals;

public class NotificationStepDefs {
    Notifications notifications = new Notifications();
    @Then("user is created")
    public void userIsCreated() {
        assertEquals(notifications.getText(notifications.getNotificationMessageElement()), "Student successfully added");
    }
}
