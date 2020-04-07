package chess.dao;

public enum ServerInfo {
    SERVER("localhost:13306"),
    DATABASE("chess"),
    OPTION("?useSSL=false&serverTimezone=UTC"),
    USER_NAME("root"),
    PASSWORD("root");

    String data;

    ServerInfo(String data) {
        this.data = data;
    }

    public String getData() {
        return data;
    }
}
