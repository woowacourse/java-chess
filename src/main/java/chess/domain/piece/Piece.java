package chess.domain.piece;

import chess.domain.position.Direction;
import chess.domain.position.Position;
import java.util.List;
import java.util.stream.Stream;

public abstract class Piece {

    private final Color color;
    private final List<Direction> directions;

    public Piece(Color color, List<Direction> directions) {
        this.color = color;
        this.directions = directions;
    }

    public abstract int getMaxUnitMove();

    public boolean isMovable(Position source, Position destination) {
        Direction direction = Direction.calculateBetween(source, destination);
        return matchesDirection(direction) &&
                isReachable(source, destination, direction);
    }

    private boolean matchesDirection(Direction direction) {
        return directions.contains(direction);
    }

    private boolean isReachable(Position source, Position destination, Direction direction) {
        int distance = (int) Stream.iterate(source,
                        position -> position.isNotEquals(destination),
                        direction::nextPosition)
                .count();
        return distance <= getMaxUnitMove();
    }

    public boolean isAttackable(Position source, Position destination) {
        return isMovable(source, destination);
    }

    public boolean isInitPawn() {
        return false;
    }

    public final boolean hasSameColorWith(Piece piece) {
        return this.color == piece.getColor();
    }

    public final boolean hasDifferentColorWith(Piece piece) {
        return !hasSameColorWith(piece);
    }

    public final boolean hasColorOf(Color color) {
        return this.color == color;
    }

    public final Color getColor() {
        return color;
    }
}
