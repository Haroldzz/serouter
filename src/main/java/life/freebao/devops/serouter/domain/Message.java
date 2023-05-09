package life.freebao.devops.serouter.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import life.freebao.devops.serouter.domain.enumeration.MessageType;
import org.springframework.stereotype.Component;

@Component
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Message {
    private MessageType msgtype;
    private Text text;
    private Markdown markdown;
    private Image image;
    private News news;
    private File file;

    public Message() {
    }

    public Message(MessageType msgtype, Text text, Markdown markdown, Image image, News news, File file) {
        this.msgtype = msgtype;
        this.text = text;
        this.markdown = markdown;
        this.image = image;
        this.news = news;
        this.file = file;
    }

    public Message(Message origin) {
        this.msgtype = origin.msgtype;
        this.text = origin.text;
        this.markdown = origin.markdown;
        this.image = origin.image;
        this.news = origin.news;
        this.file = origin.file;
    }

    public MessageType getMsgtype() {
        return msgtype;
    }

    public void setMsgtype(MessageType msgtype) {
        this.msgtype = msgtype;
    }

    public Text getText() {
        return text;
    }

    public void setText(Text text) {
        this.text = text;
    }

    public Markdown getMarkdown() {
        return markdown;
    }

    public void setMarkdown(Markdown markdown) {
        this.markdown = markdown;
    }

    public Image getImage() {
        return image;
    }

    public void setImage(Image image) {
        this.image = image;
    }

    public News getNews() {
        return news;
    }

    public void setNews(News news) {
        this.news = news;
    }

    public File getFile() {
        return file;
    }

    public void setFile(File file) {
        this.file = file;
    }

    @Override
    public String toString() {
        return "Message{" +
            "messageType=" + msgtype +
            ", text=" + text +
            ", markdown=" + markdown +
            ", image=" + image +
            ", news=" + news +
            ", file=" + file +
            '}';
    }

    public static class Builder {
        private final Message target;

        public Builder() {
            this.target = new Message();
        }

        public Builder messageType(MessageType messageType){
            target.msgtype = messageType;
            return this;
        }
        public Builder text(Text text){
            target.text = text;
            return this;
        }

        public Builder markdown(Markdown markdown){
            target.markdown = markdown;
            return this;
        }

        public Builder image(Image image){
            target.image = image;
            return this;
        }

        public Builder news(News news){
            target.news = news;
            return this;
        }

        public Builder file(File file){
            target.file = file;
            return this;
        }

        public Message build(){
            return new Message(target);
        }
    }
}
