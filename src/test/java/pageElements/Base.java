package pageElements;

import io.appium.java_client.MobileElement;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import utils.Driver;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Logger;

public class Base {
    private static Base instance;
    private static Logger log = Logger.getLogger(Base.class.getName());

    private static Button BTN_CloseAnimation = new Button(PageElementModel.selectorNames.ID,"com.pozitron.hepsiburada:id/com_appboy_inappmessage_modal_close_button");


    public static Base getInstance(){
        if (instance==null)
            instance=new Base();
        return instance;
    }

    public void check(){

        List<WebElement> img_list =  Driver.getInstance().getDriver().findElements(By.xpath("//android.widget.ImageView"));
        log.info("+++++++++++++++++++++++++++++++++++++++The page under has : " + img_list.size() +" images");
        for (int i = 0; i<=img_list.size();i++){
            if ( img_list.get(i).isDisplayed())
                log.info("IMG IS LOAD");
            else log.info("IMG IS NOT LOAD");
        }
        }

        public void checkAnimationIsExist(){
        if (BTN_CloseAnimation.isExist())
            BTN_CloseAnimation.click();
        }


    }

