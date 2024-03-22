package domain.piece;

public abstract class AbstractPiece implements Piece {
    private final Color color;

    protected AbstractPiece(Color color) {
        this.color = color;
    }

    @Override
    public Color color() {
        return color;
    }
}
