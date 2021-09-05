package steps;

import com.thoughtworks.gauge.Step;
import pageElements.Label;
import pageElements.PageElementModel;
import pages.HomePage;
import pages.LoginPage;
import pages.ProfilePage;


public class LoginSteps extends ProfilePage {
    private static HomePage connectHomePage = HomePage.getInstance();
    private static ProfilePage connectProfilePage = ProfilePage.getInstance();
    private static LoginPage connectLoginPage = LoginPage.getInstance();


    @Step("Click user icon")
    public void clickUserIcon() {
        connectHomePage.clickUserIcon();
    }

    @Step("Click login button")
    public void clickLoginButton() {
        connectProfilePage.clickGirisYapBTN();
    }

    @Step("Type user <username> and pass <pass> info and click login button")
    public void loginHB(String user, String pass) {
        connectLoginPage.loginWithEmail(user, pass);
    }


    @Step("Check error message <>")
    public void checkErrorMessage(String error) {
        connectProfilePage.checkErrorMessage(error);
    }

    @Step("Click submit button")
    public void clickSubmit() {
        connectProfilePage.clickAlertOkButton();
    }

    @Step("Logout")
    public void logout() {
        connectProfilePage.logout();
    }
}
