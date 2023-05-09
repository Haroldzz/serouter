package life.freebao.devops.serouter.domain.enumeration;

public enum MessageType {
    text("text"),
    markdown("markdown"),
    image("image"),
    news("news"),
    file("file");
    private final String type;
    public String getType() {
        return type;
    }
    MessageType(String type) {
        this.type = type;
    }
}
