package steps;

import com.thoughtworks.gauge.Step;
import pages.BasketPage;
import pages.ProducDetailPage;

public class BasketSteps {

    BasketPage connectBasketPage = new BasketPage();

    @Step("Check basket product")
    public void checkBasket() {
        connectBasketPage.isProductAdded();
    }
}
