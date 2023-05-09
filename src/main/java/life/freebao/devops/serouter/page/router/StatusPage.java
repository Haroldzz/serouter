package life.freebao.devops.serouter.page.router;

import life.freebao.devops.serouter.domain.Image;
import life.freebao.devops.serouter.page.AbstractBasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

@Component
public class StatusPage extends AbstractBasePage {
    private static final Logger log = LoggerFactory.getLogger(StatusPage.class);
    private final StatusComponent statusComponent;
    private final StatusResult statusResult;
    private final LoginComponent loginComponent;
    private final LoginResult loginResult;
    @Value("${application.webdriver.router.status.url:http://127.0.0.1}")
    private String statusUrl;
    @Value("${application.webdriver.router.login.username:admin}")
    private String loginUsername;
    @Value("${application.webdriver.router.login.password:admin}")
    private String loginPassword;

    public StatusPage(WebDriver driver, WebDriverWait wait, StatusComponent statusComponent, StatusResult statusResult, LoginComponent loginComponent, LoginResult loginResult) {
        super(driver, wait);
        this.statusComponent = statusComponent;
        this.statusResult = statusResult;
        this.loginComponent = loginComponent;
        this.loginResult = loginResult;
    }

    public StatusComponent getStatusComponent() {
        return statusComponent;
    }
    public StatusResult getStatusResult() {
        return statusResult;
    }
    public LoginComponent getLoginComponent() {
        return loginComponent;
    }
    public LoginResult getLoginResult() {
        return loginResult;
    }

    @Override
    public boolean isDisplayed() {
        return statusComponent.isDisplayed();
    }

    public Image getStatusPage() throws InterruptedException {
        // go to login url
        log.info("Go to url: {}",statusUrl);
        driver.get(statusUrl);
        // login
        loginComponent.login(loginUsername,loginPassword);
        Assert.isTrue(loginResult.isDisplayed(), "Login failed");
        // go to status page
        statusComponent.getStatusPage();
        Assert.isTrue(statusResult.isDisplayed(), "Go to status page failed");
        // get screenshot
        String imageEncode = statusComponent.getImageEncode();
        String imageDigest = statusComponent.getImageDigest(imageEncode);
        Image.Builder builder = new Image.Builder();
        Image newImage = builder.base64(imageEncode).md5(imageDigest).build();
        log.debug("Status page = {}", newImage);
        return newImage;
    }
}

