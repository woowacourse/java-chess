package chess.domain.piece;

public abstract class Piece {
    private Position position;
    private final boolean isBlack;

    public Piece(boolean isBlack, char x, char y) {
        this.position = new Position(x, y);
        this.isBlack = isBlack;
    }

    public boolean isBlack() {
        return isBlack;
    }

    public Position getPosition() {
        return position;
    }

    public void move(char nextX, char nextY) {
        movable(nextX, nextY);
        this.position = new Position(nextX, nextY);
    }

    abstract void movable(char nextX, char nextY);

    abstract char getName();
}