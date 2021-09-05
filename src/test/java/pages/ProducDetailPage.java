package pages;

import com.google.api.Page;
import com.thoughtworks.gauge.Step;
import context.ConnectContextKeys;
import context.ContextKeys;
import io.appium.java_client.TouchAction;
import io.appium.java_client.touch.offset.PointOption;
import pageElements.Button;
import pageElements.Label;
import pageElements.PageElementModel;
import utils.Driver;

import java.util.logging.Logger;

public class ProducDetailPage {
    private static Logger log = Logger.getLogger(ProducDetailPage.class.getName());
    private static Button BTN_AddBasket = new Button(PageElementModel.selectorNames.ID, "com.pozitron.hepsiburada:id/product_detail_add_to_cart");
    private static Button BTN_ProductImg = new Button(PageElementModel.selectorNames.ID, "com.pozitron.hepsiburada:id/productImage");
    private static Button BTN_BackButton = new Button(PageElementModel.selectorNames.ID, "com.pozitron.hepsiburada:id/leftIcon");
    private static Button BTN_GoBasket = new Button(PageElementModel.selectorNames.ID, "com.pozitron.hepsiburada:id/goBasketTxt");
    private static Label LBL_ProductName = new Label(PageElementModel.selectorNames.ID,"com.pozitron.hepsiburada:id/productName");

    public void addBasket() {
        log.info("ENTERING ABOUT addBasket ");
        BTN_AddBasket.waitUntilVisibleAndClick();
        BTN_AddBasket.waitFor(2);
        if (BTN_GoBasket.isExist())
            BTN_GoBasket.swipeDown();
    }

    public void clickProductImgSwipeAndClose() {
        log.info("ENTERING ABOUT  : clickProductImgAndSwipe ");
        BTN_ProductImg.waitFor(5);
        TouchAction touchAction=new TouchAction(Driver.getInstance().getDriver());
        touchAction.tap(PointOption.point(401,920)).perform();
       // BTN_ProductImg.waitUntilClickableAndClick();
        BTN_ProductImg.waitFor(3);
        BTN_ProductImg.swipeRight();
        BTN_BackButton.waitUntilVisibleAndClick();
    }
    public void goBack(){
        BTN_BackButton.waitUntilVisibleAndClick();

    }

    public void getProductName(){
        String productName = LBL_ProductName.getElementText();
        ConnectContextKeys.addContext(ContextKeys.PRODUCTNAME, productName);

    }


}
