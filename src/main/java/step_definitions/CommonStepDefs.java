package step_definitions;

import io.cucumber.java.en.Given;
import utils.ConfigHelper;
import utils.DriverManager;

import static utils.ConfigHelper.getConfig;
import static utils.DriverManager.getInstance;

public class CommonStepDefs {
    @Given("user is on the app page")
    public void user_is_on_the_app_page() {
        getInstance().get(getConfig().getString("student.app.hostname"));
    }
}

