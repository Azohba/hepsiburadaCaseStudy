package pages;

import Report.MobileAutomationException;
import context.ContextKeys;
import pageElements.Label;
import pageElements.PageElementModel;

import java.util.logging.Logger;

public class BasketPage {


    private static Logger log = Logger.getLogger(BasketPage.class.getName());


    public void isProductAdded(){
        Label LBL_ProductName = new Label(PageElementModel.selectorNames.XPATH,"//*[@content-desc='"+ ContextKeys.PRODUCTNAME +"']");
        log.info("ENTERING ABOUT isProductAdded ");
        try {
            LBL_ProductName.isExist();
            log.info("PRODUCT ADDED TO BASKET");
        }catch (Exception e){
            log.info("PRODUCT NOT ADDED TO BASKET");
        }

    }


}
