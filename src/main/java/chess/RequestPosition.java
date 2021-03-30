package chess;

public class RequestPosition {
    private final String from;
    private final String to;

    public RequestPosition(String from, String to) {
        this.from = from;
        this.to = to;
    }

    public String from() {
        return from;
    }

    public String to() {
        return to;
    }
}
