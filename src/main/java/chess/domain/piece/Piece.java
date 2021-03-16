package chess.domain.piece;

public abstract class Piece {
    private final Position position;
    private boolean isBlack;

    public Piece(boolean isBlack, char horizontal, char vertical) {
        this.position = new Position(horizontal, vertical);
    }

    abstract void move();

    abstract boolean isMovable();

    abstract char getName();
}