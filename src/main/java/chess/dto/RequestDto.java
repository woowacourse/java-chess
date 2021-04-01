package chess.dto;

public class RequestDto {
    private final String from;
    private final String to;

    public RequestDto(String from, String to) {
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
