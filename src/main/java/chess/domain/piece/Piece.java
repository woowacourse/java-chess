package chess.domain.piece;

public abstract class Piece {

    private final Color color;
    protected Type type;

    public Piece(Color color) {
        this.color = color;
    }

    public boolean isSameColor(Piece piece) {
        return this.color == piece.color;
    }

    public String getName() {
        return type.nameByColor(color);
    }
}
