package utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;

import Report.MobileAutomationException;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.remote.MobileCapabilityType;
import io.appium.java_client.remote.MobilePlatform;
import org.openqa.selenium.remote.DesiredCapabilities;


public class DriverBase {

    private static final String appPackage = System.getenv("APP_PACKAGE_NAME");
    private static final String appActivity = System.getenv("APP_ACTIVITY_NAME");

    public static AppiumDriver getDriver() {
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("platformName", MobilePlatform.ANDROID);
        capabilities.setCapability(MobileCapabilityType.DEVICE_NAME, "Automation");
        capabilities.setCapability("udid", getDeviceId());
        capabilities.setCapability("appPackage", appPackage);
        capabilities.setCapability("appActivity", appActivity);
        capabilities.setCapability("noReset", true);
        capabilities.setCapability("automationName", "UiAutomator2");
        capabilities.setCapability("fullReset", false);
        capabilities.setCapability("autoWebView", true);
        capabilities.setCapability("setWebContentsDebuggingEnabled", true);
        capabilities.setCapability("autoAcceptAlerts", true);
        capabilities.setCapability("newCommandTimeout", 0);

        try {
            return new AndroidDriver<>(new URL("http://127.0.0.1:4723/wd/hub"), capabilities);
        } catch (MalformedURLException e) {
            throw new MobileAutomationException(e.getMessage());
        }

    }

    private static String getDeviceId(){
        String deviceId = null;
        try {
            Process process = Runtime.getRuntime().exec("adb devices");
            BufferedReader input = new BufferedReader(new InputStreamReader(process.getInputStream()));
            String line = null;
            while((line = input.readLine()) != null){
                String[] lineArr = line.split("\t");
                if(lineArr.length > 1 && lineArr[1].equalsIgnoreCase("device")) {
                    deviceId = lineArr[0];
                    break;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return deviceId;
    }



}
