package chess.domain.piece;

import java.util.ArrayList;
import java.util.List;

import chess.domain.position.Position;

public class Rook extends Piece {

    private final List<Direction> directions;

    public Rook(final Position position, final Side side) {
        super(position, side);
        this.directions = initDirections();
    }

    @Override
    public boolean isMovable(Position targetPosition) {
        final Direction direction = position.getDirectionTo(targetPosition);
        return directions.contains(direction);
    }

    private List<Direction> initDirections() {
        return new ArrayList<>(
                List.of(Direction.UP, Direction.DOWN, Direction.LEFT, Direction.RIGHT)
        );
    }

    @Override
    public Piece move(Position positionToMove) {
        return new Rook(positionToMove, this.side);
    }

    @Override
    public boolean isPawn() {
        return false;
    }

    @Override
    public List<Position> getPaths(Position targetPosition) {
        List<Position> paths = new ArrayList<>();
        final Direction direction = position.getDirectionTo(targetPosition);
        final int moveCountBeforeArrivalPosition = position.getMoveCount(targetPosition, direction) - 1;
        Position nextPosition = this.position;
        for (int next = 0; next < moveCountBeforeArrivalPosition; next++) {
            nextPosition = nextPosition.getNextPosition(direction);
            paths.add(nextPosition);
        }
        return paths;
    }
}
