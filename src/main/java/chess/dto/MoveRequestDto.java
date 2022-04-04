package chess.dto;

public class MoveRequestDto {
    private final String piece;
    private final String from;
    private final String to;

    public MoveRequestDto(String piece, String from, String to) {
        this.piece = piece;
        this.from = from;
        this.to = to;
    }

    public String getPiece() {
        return piece;
    }

    public String getFrom() {
        return from;
    }

    public String getTo() {
        return to;
    }
}
