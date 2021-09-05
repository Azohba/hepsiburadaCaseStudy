package utils;


import com.thoughtworks.gauge.*;
import context.ConnectContextKeys;
import context.ContextKeys;
import io.appium.java_client.AppiumDriver;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.logging.Logger;

public class Driver {
    private static Driver instance;
    private  AppiumDriver driver;
    private static Logger log = Logger.getLogger(Driver.class.getName());

    public static synchronized Driver getInstance() {
        if (instance == null) {
            instance = new Driver();
        }
        return instance;
    }

    public AppiumDriver getDriver() {
        return driver;
    }

    @BeforeSuite
    public void initializeDriver() { getInstance().driver = DriverBase.getDriver();}


    @BeforeScenario
    public void beforeScenario(ExecutionContext executionContext){
       // driver.resetApp();
        //getInstance().driver.closeApp();
        //getInstance().driver.launchApp();
        ConnectContextKeys.initializeContext();
        ConnectContextKeys.addContext(ContextKeys.CASENAME, executionContext.getCurrentScenario().getName());
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-YYYY");
        ConnectContextKeys.addContext(ContextKeys.STARTTIME,sdf.format(Calendar.getInstance().getTime()));

    }


    @AfterScenario
    public void afterScenario(ExecutionContext executionContext){
        boolean isFailed = executionContext.getCurrentScenario().getIsFailing();
        if(executionContext.getCurrentScenario().getIsFailing()){{
                File scrFile = ((TakesScreenshot)getInstance().driver).getScreenshotAs(OutputType.FILE);
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                String filePath = "reports/Report-" + sdf.format(Calendar.getInstance().getTime()) + "/screenshots/" + ConnectContextKeys.getContextValue(ContextKeys.CASENAME) + ".png";
                try {
                    FileUtils.copyFile(scrFile, new File(filePath));
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

    }


    @BeforeStep
    public void beforeStep(ExecutionContext executionContext){
        ConnectContextKeys.addContext(ContextKeys.STEPNAME, executionContext.getCurrentStep().getText());
        log.info("ENTERING STEP : " + ConnectContextKeys.getContextValue(ContextKeys.STEPNAME));
    }

    @AfterSuite
    public void closeDriver(){
        getInstance().getDriver().quit();
    }


}
