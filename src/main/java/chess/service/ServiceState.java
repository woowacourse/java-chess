package chess.service;

public enum ServiceState {
    INIT("index.html"),
    EXECUTE("chess.html"),
    TERMINATE("result.html");

    private final String url;

    ServiceState(String url) {
        this.url = url;
    }

    public String getUrl() {
        return url;
    }
}
