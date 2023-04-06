package step_definitions;
import com.github.javafaker.Faker;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import page_objects.AddStudentPage;

import static constants.AllConstants.GenderConstants.MALE;

public class StudentModalStepDefs {
    AddStudentPage addStudentPage = new AddStudentPage();
    private final Faker faker = new Faker();
    @Given("user enters name in name field")
    public void user_enters_in_name_field() {
        addStudentPage.waitAndSetValueForNameField(faker.princessBride().character());
    }
    @Given("user enters email in email field")
    public void user_enters_in_email_field() {
        addStudentPage.waitAndSetValueForEmailField(faker.internet().emailAddress());
    }
    @Given("user enters {string} in gender field")
    public void user_enters_in_gender_field(String gender) {
        addStudentPage.waitAndSetGender(gender);
    }
    @When("user click on submit button")
    public void user_click_on_submit_button() {
        addStudentPage.clickOnSubmitButton();
    }
}
