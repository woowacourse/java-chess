package chess.domain.piece;

public abstract class Piece {
    private final Position position;

    public Piece(Position position) {
        this.position = position;
    }

    public abstract void move();

    public abstract boolean isMovable();
}
