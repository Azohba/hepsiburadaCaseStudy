package pages;

import pageElements.Button;
import pageElements.PageElementModel;
import pageElements.TextBox;

import java.util.logging.Logger;

public class LoginPage {
    private static Logger log = Logger.getLogger(LoginPage.class.getName());
    private static LoginPage instance;

    private static TextBox TXTUserName = new TextBox(PageElementModel.selectorNames.XPATH,"//*[@resource-id='txtUserName']");
    private static TextBox TXTPassword = new TextBox(PageElementModel.selectorNames.XPATH,"//android.widget.EditText[@resource-id='txtPassword']");
    private static Button BTNLogin = new Button(PageElementModel.selectorNames.XPATH,"//android.widget.Button[@resource-id='btnLogin']");


    public static LoginPage getInstance(){
        if (instance==null)
            instance=new LoginPage();
        return instance;
    }

    public void loginWithEmail(String username, String pass){
        log.info("ENTERING loginWithEmail");
        TXTUserName.waitUntilVisibleAndType(username);
        TXTPassword.type(pass);
        BTNLogin.click();
    }
}
