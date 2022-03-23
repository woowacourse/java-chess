package chess.model;

public abstract class Piece {

    private final Color color;

    public Piece(final Color color) {
        this.color = color;
    }

    public abstract boolean movable();

    public boolean isBlack() {
        return color.isBlack();
    }
}
