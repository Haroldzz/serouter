package life.freebao.devops.serouter.page.baidu;

import life.freebao.devops.serouter.page.AbstractBasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.remote.SessionId;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

@Component
public class BaiduSearchPage extends AbstractBasePage {
    private static final Logger log = LoggerFactory.getLogger(BaiduSearchPage.class);
    private final SearchComponent searchComponent;
    private final SearchResult searchResult;
    @Value("${application.webdriver.baidu.search.url}")
    private String url;

    public BaiduSearchPage(WebDriver driver, WebDriverWait wait, SearchComponent searchComponent, SearchResult searchResult) {
        super(driver, wait);
        this.searchComponent = searchComponent;
        this.searchResult = searchResult;
    }

    public SearchComponent getSearchComponent() {
        return searchComponent;
    }

    public SearchResult getSearchResult() {
        return searchResult;
    }

    public void getSearchPage(){
        log.info("Go to {}",url);
        driver.get(url);
    }

    @Override
    public boolean isDisplayed() {
        return searchComponent.isDisplayed();
    }

    public SessionId getSessionId(){
        return ((RemoteWebDriver) driver).getSessionId();
    }
    public void closeSession(){
        if (driver.getWindowHandles().size()>1) {
            driver.close();
        }
    }
    @Scheduled(cron = "${application.webdriver.baidu.search.cron:-}")
    public void baiduSearchPageTask(){
        log.info("Search task starts in session: "+getSessionId());
        getSearchPage();
        Assert.isTrue(isDisplayed(),"Search box is not displayed");
        getSearchComponent().search("time");
        Assert.isTrue(getSearchResult().isDisplayed(),"Search results are displayed");
        Assert.isTrue(getSearchResult().getResultCount() > 0,"Search results is empty");
        log.info("Search task ends in session: "+getSessionId());
        closeSession();
    }
}
