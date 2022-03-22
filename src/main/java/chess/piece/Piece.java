package chess.piece;

public abstract class Piece {

    private final Color color;

    public Piece(Color color) {
        this.color = color;
    }

    public final boolean isSameType(Class<? extends Piece> type) {
        return this.getClass() == type;
    }

    public final boolean isSameColor(Color color) {
        return this.color == color;
    }
}
