package domain.piece;

import domain.position.Direction;
import domain.position.Path;
import domain.position.Position;

import java.util.Set;

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

    protected boolean isMovableDirection(Position start, Position nextPosition) {
        Direction nextDirection = Direction.of(start, nextPosition);
        Set<Direction> PieceMovableDirections = getMovableDirections();
        return PieceMovableDirections.contains(nextDirection);
    }

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

    public Color getColor() {
        return color;
    }

    protected abstract Set<Direction> getMovableDirections();
}
