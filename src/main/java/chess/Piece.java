package chess;

public class Piece {
    private final PieceType type;
    private final Color color;

    public Piece(final PieceType type, final Color color) {
        this.type = type;
        this.color = color;
    }
}
