package pages;


import pageElements.Base;
import pageElements.Button;
import pageElements.PageElementModel;


import java.util.logging.Logger;


public class HomePage  {


    private static HomePage instance;
    private static Logger log = Logger.getLogger(HomePage.class.getName());
    public static HomePage getInstance(){
        if (instance==null)
            instance=new HomePage();
        return instance;
    }

    Base connectBase = Base.getInstance();

    private static Button BTN_userIcon = new Button(PageElementModel.selectorNames.ID,"com.pozitron.hepsiburada:id/account_icon");


    public void clickUserIcon(){
        log.info("ENTERING clickUserIcon");
       connectBase.checkAnimationIsExist();
        BTN_userIcon.waitUntilVisibleAndClick();
    }

    public void clickNavigationBar(String navigation){
        log.info("ENTERING clickNavigationBar");
        Button BTN_navBar = new Button(PageElementModel.selectorNames.XPATH,"//*[@content-desc='"+navigation+"']");
        connectBase.checkAnimationIsExist();
        BTN_navBar.waitUntilVisibleAndClick();
        //log.info("ENTERING ABOUT checkImageIsLoaded");
       // connectBase.checkImageIsLoaded();

    }


}
