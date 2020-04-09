package chess.dao;

public enum ServerInfo {
    SERVER("localhost:13306"),
    DATABASE("chess"),
    OPTION("?useSSL=false&serverTimezone=UTC"),
    USER_NAME("root"),
    PASSWORD("root");

    private final String info;

    ServerInfo(String info) {
        this.info = info;
    }

    public String getInfo() {
        return info;
    }
}
