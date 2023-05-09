package life.freebao.devops.serouter.domain;

import com.fasterxml.jackson.annotation.JsonAlias;
import org.springframework.stereotype.Component;

@Component
public class File {
    @JsonAlias("media_id")
    private String mediaId;

    public String getMediaId() {
        return mediaId;
    }

    public void setMediaId(String mediaId) {
        this.mediaId = mediaId;
    }

    public File() {
    }

    public File(String mediaId) {
        this.mediaId = mediaId;
    }

    public File(File origin) {
        this.mediaId = origin.mediaId;
    }

    @Override
    public String toString() {
        return "File{" +
            "mediaId='" + mediaId + '\'' +
            '}';
    }

    public static class Builder {
        private final File target;

        public Builder() {
            this.target = new File();
        }

        public Builder mediaId(String mediaId){
            target.mediaId = mediaId;
            return this;
        }

        public File build(){
            return new File(target);
        }
    }
}
