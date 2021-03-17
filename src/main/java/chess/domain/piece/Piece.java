package chess.domain.piece;

public abstract class Piece {
    private Position position;
    private final boolean isBlack;

    public Piece(final boolean isBlack, final char x, final char y) {
        this.position = new Position(x, y);
        this.isBlack = isBlack;
    }

    public boolean isBlack() {
        return isBlack;
    }

    public Position getPosition() {
        return position;
    }

    public void move(final char nextX, final char nextY) {
        movable(nextX, nextY);
        Position newPosition = new Position(nextX, nextY);
        validatePositionMoved(newPosition);
        this.position = newPosition;
    }

    private void validatePositionMoved(final Position other) { // 물어보기 역할?
        if (this.position.equals(other)) {
            throw new IllegalArgumentException("체스 말은 무조건 움직여야 합니다.");
        }
    }

    abstract void movable(final char nextX, final char nextY);

    abstract char getName();
}