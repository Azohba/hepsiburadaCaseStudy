package pages;

import com.google.api.Page;
import pageElements.Base;
import pageElements.Button;
import pageElements.PageElementModel;

import java.util.logging.Logger;

public class CategoryPage{
    private static Logger log = Logger.getLogger(CategoryPage.class.getName());
    private static CategoryPage instance;
    Base connectBasePage = Base.getInstance();

    public static CategoryPage getInstance(){
        if (instance==null)
            instance= new CategoryPage();
        return instance;
    }

    public void clickChoosenCategory(String xpath){
        log.info("ENTERING ABOUT clickChoosenCategory " );
        Button BTN_AnyCategory = new Button(PageElementModel.selectorNames.XPATH,"//*[@text='"+xpath+"']//..");
        connectBasePage.checkAnimationIsExist();
        BTN_AnyCategory.waitUntilVisibleAndClick();
    }
}
