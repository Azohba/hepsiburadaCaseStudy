package pageElements;

import Report.MobileAutomationException;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.TouchAction;
import io.appium.java_client.touch.TapOptions;
import io.appium.java_client.touch.offset.PointOption;
import org.checkerframework.checker.units.qual.A;
import utils.Driver;

import java.util.logging.Logger;

public class Button extends PageElementModel {
    private static Logger log =Logger.getLogger(Button.class.getName());

    public  Button(selectorNames selectorName,String selectorValue) {
        super(selectorName,selectorValue);
    }

    public void click() {
        log.info("ABOUT CLICK BUTTON : " + getLoggingName());
        MobileElement me = getAnElement();
        try {
            me.click();
        } catch (Exception e) {
            String error = "COULD NOT CLICK BUTTON : " + getLoggingName();
            log.info(error);
            throw new MobileAutomationException(error);
        }

    }
    public void waitUntilVisibleAndClick(int... timeout){
        log.info("ABOUT CLICK BUTTON : " + getLoggingName());
        waitUntilVisible(timeout);
        click();

    }

 public void waitUntilClickableAndClick(int... timeout){
        log.info("ABOUT CLICK BUTTON : " + getLoggingName());
        isClickable();
        click();

    }



}
