package chess.domain.board;

import chess.domain.position.Position;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public final class MoveOrder {

    private final Set<Position> positions;
    private final Position from;
    private final Position to;
    private final Direction direction;

    public MoveOrder(final Set<Position> positions, final Position from, final Position to) {
        this.positions = positions;
        this.from = from;
        this.to = to;
        this.direction = Direction.of(from, to);
    }

    public boolean isEmptyDestination() {
        return !positions.contains(to);
    }

    public boolean isDiagonal() {
        return direction.isDiagonal();
    }

    public boolean isVertical() {
        return direction.isVertical();
    }

    public boolean hasPieceOnWay() {
        return getPositionsOnWay().stream()
                .anyMatch(this.positions::contains);
    }

    private List<Position> getPositionsOnWay() {
        final List<Position> positions = new ArrayList<>();
        var position = from.nextPosition(direction.getX(), direction.getY());
        while (!position.equals(to)) {
            positions.add(position);
            position = position.nextPosition(direction.getX(), direction.getY());
        }
        return positions;
    }

    public Direction getDirection() {
        return direction;
    }

    public Position getFromPosition() {
        return from;
    }

    public int getStepCount() {
        var position = from;
        var count = 0;
        while (!position.equals(to)) {
            position = position.nextPosition(direction.getX(), direction.getY());
            count++;
        }
        return count;
    }
}
