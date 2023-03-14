package chess.model.piece;

public class Piece {

    private final Color color;
    private final Type type;

    public Piece(final Color color, final Type type) {
        this.color = color;
        this.type = type;
    }

    public Type getType() {
        return type;
    }
}
