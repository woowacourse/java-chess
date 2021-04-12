package chess.dto;

public class PositionDTO {
    private final String from;
    private final String to;

    public PositionDTO(String from, String to) {
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
