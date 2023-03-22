package chess.piece;

import chess.board.Position;
import java.util.ArrayList;
import java.util.List;

public class Bishop extends SlidingPiece {

    private final List<Direction> directions;

    public Bishop(final Position position, final Side side) {
        super(position, side);
        this.directions = initDirections();
    }

    private List<Direction> initDirections() {
        final List<Direction> directions = new ArrayList<>();
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
    public Bishop move(final Position positionToMove) {
        return new Bishop(positionToMove, this.side);
    }

}
