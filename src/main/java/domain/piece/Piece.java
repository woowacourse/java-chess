package domain.piece;

import domain.position.Path;
import domain.position.Position;

public abstract class Piece {

    private final PieceName name;
    private final Color color;

    public Piece(PieceName name, Color color) {
        this.name = name;
        this.color = color;
    }

    public boolean isBlack() {
        return color == Color.BLACK;
    }

    public boolean isWhite() {
        return color == Color.WHITE;
    }

    public abstract boolean isMovablePath(Position start, Path path);

    protected abstract boolean isMovableDirection(Position start, Position nextPosition);

    protected abstract boolean isMovableDistance(int distance);

    public abstract boolean isPawn();

    public abstract boolean isKing();

    public String getName() {
        if (isBlack()) {
            return name.getBlack();
        }
        return name.getWhite();
    }

    public double getScore() {
        return name.getScore();
    }
}
