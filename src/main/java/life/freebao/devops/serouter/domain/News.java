package life.freebao.devops.serouter.domain;

import org.springframework.stereotype.Component;

import java.util.List;
@Component
public class News {
    private List<Article> articles;

    public News() {
    }

    public News(List<Article> articles) {
        this.articles = articles;
    }
    public News(News origin) {
        this.articles = origin.articles;
    }
    public List<Article> getArticles() {
        return articles;
    }

    public void setArticles(List<Article> articles) {
        this.articles = articles;
    }

    public void setArticles(News origin) {
        this.articles = origin.articles;
    }

    @Override
    public String toString() {
        return "News{" +
            "articles=" + articles +
            '}';
    }

    public static class Builder{
        private final News target;

        public Builder() {
            this.target = new News();
        }

        public Builder articles(List<Article> articles){
            target.articles = articles;
            return this;
        }

        public News build(){
            return new News(target);
        }
    }
}
