package Report;

import context.ConnectContextKeys;
import context.ContextKeys;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import utils.Driver;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.logging.Logger;

public class MobileAutomationException extends RuntimeException{

    private static Logger log = Logger.getLogger(MobileAutomationException.class.getName());

    public MobileAutomationException (String message){
        super(message);
        File scrFile = ((TakesScreenshot) Driver.getInstance().getDriver()).getScreenshotAs(OutputType.FILE);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-MM-YYYY");
        String filePath = "reports/Report-" + simpleDateFormat.format(Calendar.getInstance().getTime()) + "/screenshots/" + ConnectContextKeys.getContextValue(ContextKeys.CASENAME) + ".png";
        try{
            FileUtils.copyFile(scrFile, new File(filePath));
        }catch (IOException e){
            e.printStackTrace();
        }
            String description = "Mobile Automation failed at CaseName : "+ConnectContextKeys.getContextValue(ContextKeys.CASENAME)+ "STEP : " +ContextKeys.STEPNAME+ "  with exception : " +message;
        log.info(description);
        ConnectContextKeys.addContext(ContextKeys.EXCEPTION,description);
    }

}
