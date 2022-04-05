package chess.dto;

public class MoveRequestDto {
    private final String from;
    private final String to;

    public MoveRequestDto(String from, String to) {
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
