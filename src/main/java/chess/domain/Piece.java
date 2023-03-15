package chess.domain;

public abstract class Piece {

    private final Color color;
    private final PieceType type;

    protected Piece(final Color color, final PieceType type) {
        this.color = color;
        this.type = type;
    }

    public Color color() {
        return color;
    }

    public PieceType type() {
        return type;
    }
}
