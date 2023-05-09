package life.freebao.devops.serouter.domain;

import org.springframework.stereotype.Component;

@Component
public class Image {
    private String base64;
    private String md5;

    public Image() {
    }

    public Image(Image origin) {
        this.base64 = origin.base64;
        this.md5 = origin.md5;
    }

    public String getBase64() {
        return base64;
    }

    public void setBase64(String base64) {
        this.base64 = base64;
    }

    public String getMd5() {
        return md5;
    }

    public void setMd5(String md5) {
        this.md5 = md5;
    }

    @Override
    public String toString() {
        return "Image{" +
            "base64='" + base64 + '\'' +
            ", md5='" + md5 + '\'' +
            '}';
    }

    public static class Builder {
        private final Image target;

        public Builder() {
            this.target = new Image();
        }

        public Builder base64(String base64){
            target.base64 = base64;
            return this;
        }

        public Builder md5(String md5) {
            target.md5 = md5;
            return this;
        }

        public Image build() {
            return new Image(target);
        }
    }
}
