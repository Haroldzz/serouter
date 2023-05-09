package life.freebao.devops.serouter.domain;

import org.springframework.stereotype.Component;

@Component
public class Markdown {
    private String content;

    public Markdown() {
    }

    public Markdown(String content) {
        this.content = content;
    }
    public Markdown(Markdown origin) {
        this.content = origin.content;
    }


    public String getContent() {
        return content;
    }

    public void setContent(Markdown origin) {
        this.content = origin.content;
    }

    @Override
    public String toString() {
        return "Markdown{" +
            "content='" + content + '\'' +
            '}';
    }

    public static class Builder {
        private final Markdown target;

        public Builder() {
            this.target = new Markdown();
        }

        public Builder content(String content){
            target.content = content;
            return this;
        }
        public Markdown build() {
            return new Markdown(target);
        }
    }
}
