package life.freebao.devops.serouter.domain;

import org.springframework.stereotype.Component;

@Component
public class Article {
    private String title;
    private String description;
    private String url;
    private String picurl;

    public Article() {}

    public Article(String title, String description, String url, String picurl) {
        this.title = title;
        this.description = description;
        this.url = url;
        this.picurl = picurl;
    }
    public Article(Article origin) {
        this.title = origin.title;
        this.description = origin.description;
        this.url = origin.url;
        this.picurl = origin.picurl;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getPicurl() {
        return picurl;
    }

    public void setPicurl(String picurl) {
        this.picurl = picurl;
    }

    @Override
    public String toString() {
        return "Article{" +
            "title='" + title + '\'' +
            ", description='" + description + '\'' +
            ", url='" + url + '\'' +
            ", picurl='" + picurl + '\'' +
            '}';
    }

    public static class Builder {
        private final Article target;

        public Builder() {
            this.target = new Article();
        }

        public Builder title(String title){
            target.title = title;
            return this;
        }

        public Builder description(String description) {
            target.description = description;
            return this;
        }

        public Builder url(String url){
            target.url = url;
            return this;
        }

        public Builder picurl(String picurl){
            target.picurl = picurl;
            return this;
        }

        public Article build(){
            return new Article(target);
        }
    }
}
