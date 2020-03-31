package chess.domain;

public class MoveDto {
    private String from;
    private String to;

    public MoveDto(final String from, final String to) {
        this.from = from;
        this.to = to;
    }

    public String getFrom() {
        return from;
    }

    public String getTo() {
        return to;
    }
}

