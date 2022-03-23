package chess.piece;

public abstract class Piece {

    protected final Color color;

    protected Piece(Color color) {
        this.color = color;
    }

    public abstract PieceType getType();

    public Color getColor() {
        return color;
    }
}
