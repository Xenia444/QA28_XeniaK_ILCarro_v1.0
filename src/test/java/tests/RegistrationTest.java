package tests;

import application.MyDataProvider;
import models.User;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class RegistrationTest extends TestBase {

    @BeforeMethod(alwaysRun = true)
    public  void precondition() {
        if(!app.userHelper().isLogged()) {
            app.userHelper().logOut();
        }
    }

    @Test(groups = {"web"})
    public void registrationPositiveTest() {
        int i = (int) ((System.currentTimeMillis()/1000)%3600);

        User user = new User().withName("Lion")
                .withLastName("King2")
                .withEmail("pass" + i + "@gmail.com")
                .withPassword("Pa$$" + i + "gmailc0m");

        logger.info(("Registration: \nName - " + user.getName() + "\nLast Name - " + user.getLastName()
                + "\nEmail - " + user.getEmail() + "\nPassword - " + user.getPassword()));
        app.userHelper().clickOnSignUp();
        app.userHelper().fillRegistrationForm(user);
        app.userHelper().clickOnCheckbox();
        app.userHelper().clickOnSubmit();

        app.userHelper().waitTheRegistrationInSuccess();
        String registrationInSuccess = app.userHelper().takeTheText();
        app.userHelper().clickOkButton();
        Assert.assertTrue(registrationInSuccess.contains("success"));
        logger.info("Test passed");
    }

    @Test(groups = {"web"}, dataProvider = "registrationCSV", dataProviderClass = MyDataProvider.class)
    public void registrationPositiveTestCSV(User user) {
        logger.info(("Registration: \nName - " + user.getName() + "\nLast Name - " + user.getLastName()
                + "\nEmail - " + user.getEmail() + "\nPassword - " + user.getPassword()));
        app.userHelper().clickOnSignUp();
        app.userHelper().fillRegistrationForm(user);
        app.userHelper().clickOnCheckbox();
        app.userHelper().clickOnSubmit();

        app.userHelper().waitTheRegistrationInSuccess();
        String registrationInSuccess = app.userHelper().takeTheText();
        app.userHelper().clickOkButton();
        Assert.assertTrue(registrationInSuccess.contains("success"));
        logger.info("Test passed");
    }

    @Test(dataProvider = "validDataRegistrationDPClass", dataProviderClass = MyDataProvider.class)
    public void registrationPositiveTestFromDPClass(String name, String lastName, String email, String password) {
        User user = new User().withName(name)
                .withLastName(lastName)
                .withEmail(email)
                .withPassword(password);

        logger.info(("Registration: \nName - " + user.getName() + "\nLast Name - " + user.getLastName()
                + "\nEmail - " + user.getEmail() + "\nPassword - " + user.getPassword()));
        app.userHelper().clickOnSignUp();
        app.userHelper().fillRegistrationForm(user);
        app.userHelper().clickOnCheckbox();
        app.userHelper().clickOnSubmit();

        app.userHelper().waitTheRegistrationInSuccess();
        String registrationInSuccess = app.userHelper().takeTheText();
        app.userHelper().clickOkButton();
        Assert.assertTrue(registrationInSuccess.contains("success"));
        logger.info("Test passed");
    }

}
