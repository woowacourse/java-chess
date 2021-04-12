package chess.domain;

public enum Status {
    OK(200),
    RESET_CONTENT(205),
    NO_CONTENT(204);

    private final int code;

    Status(int code) {
        this.code = code;
    }

    public int code() {
        return code;
    }
}
