package pageElements;


import Report.MobileAutomationException;
import io.appium.java_client.MobileElement;

import java.util.logging.Logger;

public class TextBox extends PageElementModel{
    private static Logger log = Logger.getLogger(TextBox.class.getName());
    public TextBox(selectorNames selectorName, String selectorValue) {
        super(selectorName, selectorValue);
    }

    public void type(String inputText){
        log.info("ABOUT TO TEXTBOX" + getLoggingName());
        MobileElement me = getAnElement();
        try {
            me.sendKeys(inputText);
        }catch (Exception e){
            String error = "COULD NOT TYPE TEXTBOX: " + getLoggingName() + "TEXT IS : " + inputText;
            log.info(error);
            throw new MobileAutomationException(error);
        }
    }

    public void waitUntilVisibleAndType(String text){
        log.info("ABOUT TO TEXTBOX" + getLoggingName());
        waitUntilVisible();
        type(text);
    }
}
