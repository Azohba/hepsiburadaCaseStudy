package pages;

import pageElements.Base;
import pageElements.Button;
import pageElements.PageElementModel;

import java.util.Random;
import java.util.logging.Logger;

public class CategoryDetailPage {
    private static Logger log = Logger.getLogger(CategoryDetailPage.class.getName());
    private static  Button BTN_OpenProductDetail = new Button(PageElementModel.selectorNames.XPATH,"(//*[@resource-id='com.pozitron.hepsiburada:id/view_product'])[1]");
    Base connectBasePage = Base.getInstance();

    public void opendProductDetail(){
        log.info("ENTERING ABOUT opendProductDetail");
        BTN_OpenProductDetail.swipeDownUntilElement();
        connectBasePage.checkAnimationIsExist();
        BTN_OpenProductDetail.waitUntilVisibleAndClick();
    }


}
