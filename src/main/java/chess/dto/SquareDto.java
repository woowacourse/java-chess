package chess.dto;

public class SquareDto {

    private final String position;
    private final String piece;

    public SquareDto(final String position, final String piece) {
        this.position = position;
        this.piece = piece;
    }

    public String getPosition() {
        return this.position;
    }

    public String getPiece() {
        return this.piece;
    }
}
