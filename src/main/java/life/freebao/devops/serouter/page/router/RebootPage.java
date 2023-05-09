package life.freebao.devops.serouter.page.router;

import life.freebao.devops.serouter.page.AbstractBasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.remote.SessionId;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

@Component
public class RebootPage extends AbstractBasePage {
    private static final Logger log = LoggerFactory.getLogger(RebootPage.class);
    private final RebootComponent rebootComponent;
    private final RebootResult rebootResult;
    private final LoginComponent loginComponent;
    private final LoginResult loginResult;
    @Value("${application.webdriver.router.reboot.url:http://127.0.0.1}")
    private String rebootUrl;
    @Value("${application.webdriver.router.login.username:admin}")
    private String loginUsername;
    @Value("${application.webdriver.router.login.password:admin}")
    private String loginPassword;
    public RebootPage(WebDriver driver, WebDriverWait wait, RebootComponent rebootComponent, RebootResult rebootResult, LoginComponent loginComponent, LoginResult loginResult) {
        super(driver, wait);
        this.rebootComponent = rebootComponent;
        this.rebootResult = rebootResult;
        this.loginComponent = loginComponent;
        this.loginResult = loginResult;
    }

    public RebootComponent getRebootComponent() {
        return rebootComponent;
    }

    public RebootResult getRebootResult() {
        return rebootResult;
    }

    public LoginComponent getLoginComponent() {
        return loginComponent;
    }

    public LoginResult getLoginResult() {
        return loginResult;
    }

    @Override
    public boolean isDisplayed() {
        return rebootComponent.isDisplayed();
    }

    public SessionId getSessionId(){
        return ((RemoteWebDriver) driver).getSessionId();
    }
    public void closeSession(){ if (driver.getWindowHandles().size()>1) driver.close(); }
    public void rebootRouter() throws InterruptedException {
        // go to login url
        log.info("Go to url: {}",rebootUrl);
        driver.get(rebootUrl);
        // login
        loginComponent.login(loginUsername,loginPassword);
        Assert.isTrue(loginResult.isDisplayed(), "Login failed");
        // reboot
        rebootComponent.reboot();
        log.info("Restarting task ends in session: " + getSessionId());
        closeSession();
    }
}
