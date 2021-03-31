package chess;

public class Request {
    private final String from;
    private final String to;

    public Request(String from, String to) {
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
