package life.freebao.devops.serouter.page.router;

import life.freebao.devops.serouter.domain.Image;
import life.freebao.devops.serouter.page.AbstractBasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.devtools.DevTools;
import org.openqa.selenium.devtools.HasDevTools;
import org.openqa.selenium.devtools.v110.network.Network;
import org.openqa.selenium.devtools.v110.network.model.RequestId;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

import java.util.HashMap;
import java.util.Optional;
import java.util.concurrent.TimeUnit;

@Component
public class InformationPage extends AbstractBasePage {
    private static final Logger log = LoggerFactory.getLogger(InformationPage.class);
    private final InformationComponent informationComponent;
    private final InformationResult informationResult;
    private final LoginComponent loginComponent;
    private final LoginResult loginResult;
    @Value("${application.webdriver.router.information.url:http://127.0.0.1}")
    private String informationUrl;
    @Value("${application.webdriver.router.login.username:admin}")
    private String loginUsername;
    @Value("${application.webdriver.router.login.password:admin}")
    private String loginPassword;

    public InformationPage(WebDriver driver, WebDriverWait wait, InformationComponent informationComponent, InformationResult informationResult, LoginComponent loginComponent, LoginResult loginResult, Image image) {
        super(driver, wait);
        this.informationComponent = informationComponent;
        this.informationResult = informationResult;
        this.loginComponent = loginComponent;
        this.loginResult = loginResult;
    }

    public InformationComponent getInformationComponent() {
        return informationComponent;
    }

    public InformationResult getInformationResult() {
        return informationResult;
    }

    public LoginComponent getLoginComponent() {
        return loginComponent;
    }

    public LoginResult getLoginResult() {
        return loginResult;
    }

    @Override
    public boolean isDisplayed() {
        return informationComponent.isDisplayed();
    }

    public HashMap<String, String> getInformationPage() throws InterruptedException {
        // initial devtools
        DevTools devTools = ((HasDevTools) driver).getDevTools();
        devTools.createSession();
        devTools.send(Network.enable(Optional.empty(),Optional.empty(),Optional.empty()));
        HashMap<String, String> responseMap = new HashMap<>();
        devTools.addListener(Network.responseReceived(), responseReceived -> {
            try {
                String responseUrl = responseReceived.getResponse().getUrl();
                RequestId requestId = responseReceived.getRequestId();
                log.debug("Information page responseReceived = {}", responseMap);
                if (responseUrl.contains("gwinfo?get=part")) {
                    String responseBody = devTools.send(Network.getResponseBody(requestId)).getBody();
                    responseMap.put("text", responseBody);
                    log.debug("Information page responseReceived = {}", responseMap);
                }
            } catch (Exception e) {
                log.info("Incorrect response: " + responseReceived.getResponse().getUrl());
            }
        });
        // go to login url
        log.info("Go to url: {}", informationUrl);
        driver.get(informationUrl);
        // login
        loginComponent.login(loginUsername,loginPassword);
        Assert.isTrue(loginResult.isDisplayed(), "Login failed");
        // go to information page
        informationComponent.getInformationPage();
        Assert.isTrue(informationResult.isDisplayed(), "Go to information page failed");
        TimeUnit.MILLISECONDS.sleep(5000L);
        // get screenshot
        String imageEncode = informationComponent.getImageEncode();
        String imageDigest = informationComponent.getImageDigest(imageEncode);
        responseMap.put("imageEncode", imageEncode);
        responseMap.put("imageDigest", imageDigest);
        log.info("responseMap.size() = {}", responseMap.size());
        return responseMap;
    }
}
