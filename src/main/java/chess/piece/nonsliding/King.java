package chess.piece.nonsliding;

import chess.board.Position;
import chess.piece.Direction;
import chess.piece.Side;
import java.util.ArrayList;
import java.util.List;

public class King extends NonSlidingPiece {

    private final List<Direction> directions;

    public King(final Position position, final Side side) {
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
        final boolean isPossibleDirection = directions.contains(direction);
        final boolean isPossibleDistance = position.getMoveCount(targetPosition, direction) == 1;
        return isPossibleDistance && isPossibleDirection;
    }

    @Override
    public King move(final Position positionToMove) {
        return new King(positionToMove, this.side);
    }

}
