package life.freebao.devops.serouter.page.router;

import life.freebao.devops.serouter.page.AbstractBasePage;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.stereotype.Component;
import org.springframework.util.DigestUtils;

import java.io.IOException;
import java.util.Base64;
import java.util.concurrent.TimeUnit;

@Component
public class StatusComponent extends AbstractBasePage {
    private static final Logger log = LoggerFactory.getLogger(StatusComponent.class);
    @FindBy(id = "first_menu_setting")
    private WebElement settingPage;
    @FindBy(id = "sub_second_menu_setting_gateway_status")
    private WebElement statusPage;
    @Value("${application.webdriver.screenshot.path:/tmp/}")
    private String imagePath;

    public StatusComponent(WebDriver driver, WebDriverWait wait) {
        super(driver, wait);
    }
    public String getImageEncode(){
        return ((TakesScreenshot) driver).getScreenshotAs(OutputType.BASE64);
    }
    public String getImageDigest(String imageEncode){
        byte[] imageDecode = Base64.getDecoder().decode(imageEncode);
        String imageDigest = DigestUtils.md5DigestAsHex(imageDecode);
        String path = imagePath.endsWith("/") ? imagePath : (imagePath + '/');
        String filePath = path + imageDigest + ".png";
        try {
            new FileSystemResource(filePath).getOutputStream().write(imageDecode);
        } catch (IOException e) {
            log.info("{} saved failed with message: {}",filePath,e.getMessage());
        }
        return imageDigest;
    }
    public void getStatusPage() throws InterruptedException {
        settingPage.click();
        TimeUnit.MILLISECONDS.sleep(1000L);
        statusPage.click();
        TimeUnit.MILLISECONDS.sleep(5000L);
    }
    @Override
    public boolean isDisplayed() {
        return wait.until(webDriver -> settingPage.isDisplayed());
    }
}
