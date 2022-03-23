package chess.dto;

public class MovePositionCommandDto {

    private final String from;
    private final String to;

    public MovePositionCommandDto(String from, String to) {
        this.from = from;
        this.to = to;
    }

    public String source() {
        return from;
    }

    public String target() {
        return to;
    }
}
