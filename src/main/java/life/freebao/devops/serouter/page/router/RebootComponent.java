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
public class RebootComponent extends AbstractBasePage {
    private static final Logger log = LoggerFactory.getLogger(RebootComponent.class);
    @FindBy(id = "menu_action_restart_hint")
    private WebElement rebootButton;
    @FindBy(id = "confirm")
    private WebElement confirmButton;
    public RebootComponent(WebDriver driver, WebDriverWait wait) {
        super(driver, wait);
    }

    public void reboot() throws InterruptedException {
        log.info("Begin to reboot");
        rebootButton.click();
        TimeUnit.MILLISECONDS.sleep(5000L);
        confirmButton.click();
        TimeUnit.MILLISECONDS.sleep(5000L);
        log.info("End to reboot");
    }
    @Override
    public boolean isDisplayed(){
        return wait.until(webDriver -> rebootButton.isDisplayed());
    }

}
