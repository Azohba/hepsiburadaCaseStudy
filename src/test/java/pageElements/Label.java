package pageElements;

import io.appium.java_client.MobileElement;

import java.util.logging.Logger;

public class Label extends PageElementModel {
    private static final Logger log = Logger.getLogger(Label.class.getName());

    public Label (selectorNames selectorName,String selectorValue){super(selectorName,selectorValue);}



    public String getElementText(){
        log.info("GETTIN AN ELEMENTS TEXT: " + getLoggingName());
        String elementText;
        MobileElement me = getAnElement();
        elementText = me.getText();
        return elementText;
    }


}
