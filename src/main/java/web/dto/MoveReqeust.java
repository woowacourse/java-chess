package web.dto;

public class MoveReqeust {

    private final String from;
    private final String to;

    public MoveReqeust(final String from, final String to) {
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
