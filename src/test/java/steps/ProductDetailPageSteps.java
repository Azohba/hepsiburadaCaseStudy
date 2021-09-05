package steps;

import com.thoughtworks.gauge.Step;
import pages.ProducDetailPage;

public class ProductDetailPageSteps {

    ProducDetailPage connectProductDetailPage = new ProducDetailPage();

    @Step("Click product image and swipe")
    public void clickProductImgAndSwipe() {
        connectProductDetailPage.clickProductImgSwipeAndClose();

    }

    @Step("Add basket")
    public void addBasket() {
        connectProductDetailPage.getProductName();
        connectProductDetailPage.addBasket();
    }

    @Step("Go back")
    public void goBack() {
      connectProductDetailPage.goBack();
    }
}
