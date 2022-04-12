package chess.dto;

public class PositionDto {
    private final String position;
    private final String piece;

    private PositionDto(String position, String piece) {
        this.position = position;
        this.piece = piece;
    }

    public static PositionDto of(String position, String piece) {
        return new PositionDto(position, piece);
    }

    public String getPosition() {
        return position;
    }

    public String getPiece() {
        return piece;
    }
}
