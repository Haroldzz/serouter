package life.freebao.devops.serouter.domain;

import com.fasterxml.jackson.annotation.JsonAlias;
import org.springframework.stereotype.Component;

import java.util.List;
@Component
public class Text {
    private String content;
    @JsonAlias("mentioned_list")
    private List<String> mentionedList;
    @JsonAlias("mentioned_mobile_list")
    private List<String> mentionedMobileList;

    public Text() {
    }

    public Text(Text origin) {
        this.content = origin.content;
        this.mentionedList = origin.mentionedList;
        this.mentionedMobileList = origin.mentionedMobileList;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public List<String> getMentionedList() {
        return mentionedList;
    }

    public void setMentionedList(List<String> mentionedList) {
        this.mentionedList = mentionedList;
    }

    public List<String> getMentionedMobileList() {
        return mentionedMobileList;
    }

    public void setMentionedMobileList(List<String> mentionedMobileList) {
        this.mentionedMobileList = mentionedMobileList;
    }

    @Override
    public String toString() {
        return "Text{" +
            "content='" + content + '\'' +
            ", mentionedList=" + mentionedList +
            ", mentionedMobileList=" + mentionedMobileList +
            '}';
    }

    public static class Builder {
        private final Text target;

        public Builder() {
            this.target = new Text();
        }
        public Builder content(String content){
            target.content = content;
            return this;
        }

        public Builder mentionedList(List<String> mentionedList){
            target.mentionedList = mentionedList;
            return this;
        }

        public Builder mentionedMobileList(List<String> mentionedMobileList){
            target.mentionedMobileList = mentionedMobileList;
            return this;
        }

        public Text build(){
            return new Text(target);
        }
    }
}
