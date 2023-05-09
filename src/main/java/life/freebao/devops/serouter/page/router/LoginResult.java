package life.freebao.devops.serouter.page.router;

import life.freebao.devops.serouter.page.AbstractBasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.stereotype.Component;

@Component
public class LoginResult extends AbstractBasePage {
    @FindBy(id = "menu_action_logout_hint")
    private WebElement logoutButton;

    public LoginResult(WebDriver driver, WebDriverWait wait) {
        super(driver, wait);
    }

    @Override
    public boolean isDisplayed() {
        return wait.until(webDriver -> logoutButton.isDisplayed());
    }
}
