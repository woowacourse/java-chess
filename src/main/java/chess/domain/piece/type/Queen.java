package chess.domain.piece.type;

import chess.domain.board.Position;
import chess.domain.piece.Direction;
import chess.domain.piece.Side;
import java.util.ArrayList;
import java.util.List;

public class Queen extends Piece {

    private static final int QUEEN_SCORE = 9;

    private final List<Direction> directions;

    public Queen(final Position position, final Side side) {
        super(position, side);
        this.directions = initDirections();
    }

    private List<Direction> initDirections() {
        List<Direction> directions = new ArrayList<>();
        directions.add(Direction.UP);
        directions.add(Direction.DOWN);
        directions.add(Direction.LEFT);
        directions.add(Direction.RIGHT);
        directions.add(Direction.UP_RIGHT);
        directions.add(Direction.UP_LEFT);
        directions.add(Direction.DOWN_RIGHT);
        directions.add(Direction.DOWN_LEFT);
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
        final int moveCountBeforeArrivalPosition = position.getMoveCount(targetPosition, direction) - 1;
        Position nextPosition = this.position;
        for (int next = 0; next < moveCountBeforeArrivalPosition; next++) {
            nextPosition = nextPosition.getNextPosition(direction);
            paths.add(nextPosition);
        }
        return paths;
    }

    @Override
    public Queen move(final Position positionToMove) {
        return new Queen(positionToMove, this.side);
    }

    @Override
    public boolean canPassThrough() {
        return false;
    }

    @Override
    public double getScore() {
        return QUEEN_SCORE;
    }
}
