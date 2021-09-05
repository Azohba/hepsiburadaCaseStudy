package pages;

import io.appium.java_client.MobileElement;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import pageElements.Button;
import pageElements.Label;
import pageElements.PageElementModel;
import utils.Driver;

public class ProfilePage {

    private static ProfilePage instance;
    private static Log log =  LogFactory.getLog(ProfilePage.class);
    private static Button BTNAlertOk = new Button(PageElementModel.selectorNames.ID,"android:id/button1");
    private static Button BTNLogin = new Button(PageElementModel.selectorNames.XPATH,"//*[@text='Giriş yap']");
    private static Button BTN_Logout = new Button(PageElementModel.selectorNames.XPATH,"//android.widget.Button[@resource-id='com.pozitron.hepsiburada:id/button']");


    public static ProfilePage getInstance(){
        if (instance==null)
            instance=new ProfilePage();
        return instance;
    }


    public void clickGirisYapBTN(){
        log.info("ENTERING LOGIN PAGE");
//        if (!BTNLogin.isExist()){
//            BTN_Logout.swipeDownUntilElement();
//            BTN_Logout.click();
//        }
        BTNLogin.waitUntilVisibleAndClick();
    }

    public void checkErrorMessage(String error){
        log.info("ENTERING checkLoginAlert");
        Label LBLErrorMessages = new Label(PageElementModel.selectorNames.XPATH,"//*[contains(@text,'"+ error +"')]");
        LBLErrorMessages.waitFor(4);
        String elementText = LBLErrorMessages.getElementText();
        log.info(elementText);
        try {
            LBLErrorMessages.waitUntilVisible();
            LBLErrorMessages.equals(error);
        }catch (Exception e){
            log.info("ERROR MESSAGES DOES NOT EQUAL ACTUAL RESULT");
        }

    }
    public void clickAlertOkButton(){
        log.info("ENTERING clickAlertOKButton");
        BTNAlertOk.click();
    }

      public void logout(){
        log.info("ENTERING logout");
        BTN_Logout.swipeDownUntilElement();
          BTN_Logout.waitUntilVisibleAndClick();

    }


}
