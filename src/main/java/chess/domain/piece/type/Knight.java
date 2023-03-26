package chess.domain.piece.type;

import chess.domain.board.Position;
import chess.domain.piece.Direction;
import chess.domain.piece.Side;
import java.util.ArrayList;
import java.util.List;

public class Knight extends Piece {

    private static final int FORWARD_MOVING_COUNT = 2;
    private static final double KNIGHT_SCORE = 2.5;

    private final List<Direction> directions;

    public Knight(final Position position, final Side side) {
        super(position, side);
        directions = initDirections();
    }

    private List<Direction> initDirections() {
        List<Direction> directions = new ArrayList<>();
        directions.add(Direction.UP_UP_RIGHT);
        directions.add(Direction.UP_UP_LEFT);
        directions.add(Direction.RIGHT_RIGHT_UP);
        directions.add(Direction.RIGHT_RIGHT_DOWN);
        directions.add(Direction.DOWN_DOWN_RIGHT);
        directions.add(Direction.DOWN_DOWN_LEFT);
        directions.add(Direction.LEFT_LEFT_UP);
        directions.add(Direction.LEFT_LEFT_DOWN);
        return directions;
    }

    @Override
    public boolean isMovable(Position targetPosition) {
        final Direction direction = position.getDirectionTo(targetPosition);
        return directions.contains(direction);
    }

    @Override
    public List<Position> getPaths(Position targetPosition) {
        List<Position> paths = new ArrayList<>();
        final Direction direction = position.getDirectionTo(targetPosition);
        final Direction directionBeforeMoveSideways = direction.getDirectionBeforeMoveSideways();
        Position nextPosition = this.position;
        for (int next = 0; next < FORWARD_MOVING_COUNT; next++) {
            nextPosition = nextPosition.getNextPosition(directionBeforeMoveSideways);
            paths.add(nextPosition);
        }
        return paths;
    }

    @Override
    public Knight move(final Position positionToMove) {
        return new Knight(positionToMove, this.side);
    }

    @Override
    public boolean canPassThrough() {
        return true;
    }

    @Override
    public double getScore() {
        return KNIGHT_SCORE;
    }
}
