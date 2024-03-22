package chess.piece;

import chess.position.Direction;
import chess.position.Position;
import java.util.List;
import java.util.stream.Stream;

public abstract class Piece {

    protected final Color color;
    private final List<Direction> directions;

    protected Piece(Color color, List<Direction> directions) {
        this.color = color;
        this.directions = directions;
    }

    public boolean isMovable(Position source, Position destination) {
        Direction direction = Direction.calculateBetween(source, destination);
        return matchesDirection(direction) &&
                isReachable(source, destination, direction);
    }

    protected boolean matchesDirection(Direction direction) {
        return directions.contains(direction);
    }

    protected boolean isReachable(Position source, Position destination, Direction direction) {
        int distance = (int) Stream.iterate(source,
                        position -> position.isNotEquals(destination),
                        direction::nextPosition)
                .count();
        return distance <= getMaxUnitMove();
    }

    protected abstract int getMaxUnitMove();

    public boolean isAttackable(Position source, Position destination) {
        return isMovable(source, destination);
    }

    public boolean isInitPawn() {
        return false;
    }

    public boolean hasSameColorWith(Piece piece) {
        return color == piece.color;
    }

    public boolean hasDifferentColorWith(Piece piece) {
        return !hasSameColorWith(piece);
    }

    public boolean hasColorOf(Color color) {
        return this.color == color;
    }

    public Color getColor() {
        return color;
    }
}
