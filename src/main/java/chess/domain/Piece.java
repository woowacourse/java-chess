package chess.domain;

public class Piece {
    private final PieceType type;
    private final Color color;

    public Piece(final PieceType type, final Color color) {
        this.type = type;
        this.color = color;
    }

    public PieceType getType() {
        return type;
    }

    public Color getColor() {
        return this.color;
    }
}
