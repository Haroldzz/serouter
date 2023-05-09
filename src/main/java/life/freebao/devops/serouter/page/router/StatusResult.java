package life.freebao.devops.serouter.page.router;

import life.freebao.devops.serouter.page.AbstractBasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.stereotype.Component;

@Component
public class StatusResult extends AbstractBasePage {
    @FindBy(id = "first_menu_setting") //systime first_menu_setting //*[@id="systime"]
    private WebElement uptime;
    public StatusResult(WebDriver driver, WebDriverWait wait) {
        super(driver, wait);
    }
    @Override
    public boolean isDisplayed() { return wait.until(webDriver -> uptime.isDisplayed()); }
}
