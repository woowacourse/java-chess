package domain.piece;

public abstract class Piece {

    private final Color color;

    protected Piece(Color color) {
        this.color = color;
    }

    public boolean isWhite() {
        return color == Color.WHITE;
    }
}
