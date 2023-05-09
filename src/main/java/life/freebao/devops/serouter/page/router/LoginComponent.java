package life.freebao.devops.serouter.page.router;

import life.freebao.devops.serouter.page.AbstractBasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

@Component
public class LoginComponent extends AbstractBasePage {
    private static final Logger log = LoggerFactory.getLogger(LoginComponent.class);
    @FindBy(id = "login_username")
    private WebElement loginUsernameBox;
    @FindBy(id = "login_password")
    private WebElement loginPasswordBox;
    @FindBy(xpath = "//*[@id=\"login_form\"]/button")
    private WebElement loginButton;
    @FindBy(id = "menu_action_logout_hint")
    private WebElement logoutButton;
    @FindBy(id = "confirm")
    private WebElement confirmButton;
    public LoginComponent(WebDriver driver, WebDriverWait wait) {
        super(driver, wait);
    }

    public void login(String username, String password){
        log.info("Begin to login \"{}\"",username);
        loginUsernameBox.sendKeys(username);
        loginPasswordBox.sendKeys(password);
        loginButton.click();
        try {
            Thread.sleep(5000L);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        log.info("End to login \"{}\"",username);
    }
    public void logout() throws InterruptedException {
        log.info("Begin to logout");
        logoutButton.click();
        TimeUnit.MILLISECONDS.sleep(10000L);
        confirmButton.click();
        log.info("End to logout");
    }

    @Override
    public boolean isDisplayed(){
        return wait.until(webDriver -> loginButton.isDisplayed());
    }

}
