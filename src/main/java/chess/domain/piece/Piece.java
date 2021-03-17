package chess.domain.piece;

public abstract class Piece {
    private final String notation;

    public Piece(String notation) {
        this.notation = notation;
    }

    public String getNotation() {
        return notation;
    }
}
