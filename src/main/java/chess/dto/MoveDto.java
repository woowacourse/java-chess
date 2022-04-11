package chess.dto;

public final class MoveDto {

    private final String from;
    private final String to;

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
