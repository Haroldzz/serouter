package life.freebao.devops.serouter.page.baidu;

import life.freebao.devops.serouter.page.AbstractBasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class SearchResult extends AbstractBasePage {
    @FindBy(id = "content_left")
    private List<WebElement> results;

    public Integer getResultCount(){
        return results.size();
    }

    public SearchResult(WebDriver driver, WebDriverWait wait) {
        super(driver, wait);
    }

    @Override
    public boolean isDisplayed() {
        return wait.until(webDriver -> !results.isEmpty());
            }
}
