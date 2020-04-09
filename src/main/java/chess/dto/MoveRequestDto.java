package chess.dto;

public class MoveRequestDto {
    private String from;
    private String to;

    public MoveRequestDto(final String from, final String to) {
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

