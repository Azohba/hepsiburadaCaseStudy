package pageElements;


import Report.MobileAutomationException;
import io.appium.java_client.MobileElement;
import io.appium.java_client.TouchAction;
import io.appium.java_client.touch.WaitOptions;
import io.appium.java_client.touch.offset.PointOption;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import utils.Driver;

import java.time.Duration;
import java.util.logging.Logger;


public abstract class PageElementModel {

    private String xPath;
    private String id;
    private String name;
    private String className;
    private String loggingName;

    public enum selectorNames {XPATH, ID, NAME, CLASS_NAME}

    private static final Logger log = Logger.getLogger(PageElementModel.class.getName());

    public PageElementModel(selectorNames selectorName, String selectorValue) {
        switch (selectorName) {
            case ID:
                id = selectorValue;
                loggingName = "ID: " + selectorValue;
                break;
            case NAME:
                name = selectorValue;
                loggingName = "NAME: " + selectorValue;
                break;
            case XPATH:
                xPath = selectorValue;
                loggingName = "XPATH: " + selectorValue;
                break;
            case CLASS_NAME:
                className = selectorValue;
                loggingName = "CLASS: " + selectorValue;
                break;
            default:
                className = id;
                break;
        }
    }

    private String getxPath() {
        return xPath;
    }

    public void setxPat(String xPath) {
        this.xPath = xPath;
    }


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    private String getName() {
        return name;
    }

    private String getClassName() {
        return className;
    }

    protected String getLoggingName() {
        return loggingName;
    }

    private MobileElement getMobileElement() throws WebDriverException {
        WebElement we;
        if (getId() != null) {
            we = Driver.getInstance().getDriver().findElement(By.id(getId()));
        } else if (getName() != null) {
            we = Driver.getInstance().getDriver().findElement(By.name(getName()));
        } else if (getxPath() != null) {
            we = Driver.getInstance().getDriver().findElement(By.xpath(getxPath()));
        } else if (getClassName() != null) {
            we = Driver.getInstance().getDriver().findElement(By.className(getClassName()));
        } else {
            throw new MobileAutomationException("NO PARAMETERS FOUND");
        }
        return (MobileElement) we;
    }


    public MobileElement getAnElement() {
        MobileElement me;
        try {
            me = getMobileElement();
        } catch (NoSuchElementException e) {
            String error = "ELEMENT NOT FOUND: " + getLoggingName();
            log.info(error);
            throw new MobileAutomationException(error);
        }
        return me;
    }




    public MobileElement getAttribute(){
        MobileElement me;
        try {
            me = getAttribute();
        }catch (NoSuchElementException e) {
            String error = "ELEMENT NOT FOUND: " + getLoggingName();
            log.info(error);
            throw new MobileAutomationException(error);
        }
        return me;
    }



    public boolean isExist() {
        boolean isDisplayed = false;
        try {
            if (getMobileElement() != null) {
                isDisplayed = true;
            }
        } catch (NoSuchElementException e) {
            return isDisplayed;
        }
        return isDisplayed;
    }

    public boolean isVisible() {
        WebDriverWait wait = new WebDriverWait(Driver.getInstance().getDriver(), 10);
        try {
            if (getxPath() != null)
                wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(getxPath())));
            else if (getId() != null)
                wait.until(ExpectedConditions.visibilityOfElementLocated(By.id(getId())));
            else if (getName() != null)
                wait.until(ExpectedConditions.visibilityOfElementLocated(By.name(getName())));
            else if (getClassName() != null)
                wait.until(ExpectedConditions.visibilityOfElementLocated(By.className(getClassName())));
            return true;
        } catch (TimeoutException e) {
            return false;
        }
    }

    public void waitFor(int... timeOut) {
        int timeOutI = 2;
        if (timeOut.length != 0) {
            timeOutI = timeOut[0];
        }
        try {
            Thread.sleep(timeOutI * 1000L);
        } catch (InterruptedException e1) {
            e1.printStackTrace();
        }
    }

    public boolean isClickable() {
        ExpectedConditions.elementToBeClickable(getMobileElement());
        return true;
    }


    public void waitUntilVisible(int... timeOut) {
        int timeOutI = 30;
        if (timeOut.length != 0) {
            timeOutI = timeOut[0];
        }
        WebDriverWait wait = new WebDriverWait(Driver.getInstance().getDriver(), 3);
        boolean isElementFound = false;
        int retryCount = timeOutI;
        while (!isElementFound && retryCount > 0) {
            log.info("RETRY: " + retryCount);
            try {
                wait.until(ExpectedConditions.visibilityOf(getMobileElement()));
                isElementFound = true;
            } catch (WebDriverException e) {
                retryCount--;
                if (retryCount < 10)
                    swipeDown();
                try {
                    Thread.sleep(1000L);
                } catch (InterruptedException e1) {
                    throw new MobileAutomationException(e1.getMessage());
                }
            }
        }
        if (!isElementFound && retryCount == 0) {
            throw new MobileAutomationException("Could not find element in expected time");
        }
    }


    public void swipeDown() {
        Dimension size = Driver.getInstance().getDriver().manage().window().getSize();
        int anchor = (int) (size.width * 0.5);
        int startPoint = (int) (size.height * 0.8);
        PointOption pointOption = new PointOption();
        pointOption.withCoordinates(anchor, startPoint);
        WaitOptions waitOptions = new WaitOptions();
        waitOptions.withDuration(Duration.ofMillis(500));
        PointOption pointOptionTo = new PointOption();
        pointOptionTo.withCoordinates(anchor, startPoint / 2);
        try {
            new TouchAction(Driver.getInstance().getDriver()).press(pointOption).waitAction(waitOptions).moveTo(pointOptionTo).release().perform();
        } catch (Exception e) {
            log.info("UNABLE TO SWIPE");
        }
    }

    public void swipeRight(){
        Dimension size = Driver.getInstance().getDriver().manage().window().getSize();
        int anchor = (int) (size.width * 0.9);
        MobileElement me = getMobileElement();
        Point meCenter = me.getCenter();
        PointOption pointOption= new PointOption();
        pointOption.withCoordinates(anchor,meCenter.y);
        WaitOptions waitOptions = new WaitOptions();
        waitOptions.withDuration(Duration.ofMillis(500));
        PointOption pointOption1 = new PointOption();
        pointOption1.withCoordinates(anchor/2,meCenter.y);
        try {
            new TouchAction(Driver.getInstance().getDriver()).press(pointOption).waitAction(waitOptions).moveTo(pointOption1).release().perform();
        }catch (Exception e){
            log.info("UNABLE TO SWIPE");
        }
    }




    public void swipeDownUntilElement() {
        int count = 8;
        Dimension size = Driver.getInstance().getDriver().manage().window().getSize();
        int anchor = (int) (size.width * 0.5);
        int startPoint = (int) (size.height * 0.8);
        PointOption pointOption = new PointOption();
        pointOption.withCoordinates(anchor, startPoint);
        WaitOptions waitOptions = new WaitOptions();
        waitOptions.withDuration(Duration.ofMillis(500));
        PointOption pointOptionTo = new PointOption();
        pointOptionTo.withCoordinates(anchor, (int) (size.height * 0.5));
        MobileElement me;
        while (count > 0) {
            try {
                me = getMobileElement();
                count = 0;
            } catch (NoSuchElementException e) {
                new TouchAction(Driver.getInstance().getDriver()).press(pointOption).waitAction(waitOptions).moveTo(pointOptionTo).release().perform();
            }
            count--;
        }
    }




}
