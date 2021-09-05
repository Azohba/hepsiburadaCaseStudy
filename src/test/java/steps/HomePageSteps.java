package steps;

import com.thoughtworks.gauge.Step;
import pages.HomePage;

public class HomePageSteps {
    HomePage connectHomePage = HomePage.getInstance();

    @Step("Click <> navigation bar")
    public void clickNavBar(String navigation) {
        connectHomePage.clickNavigationBar(navigation);
    }
}
