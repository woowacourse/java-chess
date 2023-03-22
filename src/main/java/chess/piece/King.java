package chess.piece;

import chess.board.Position;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class King extends NonSlidingPiece {

    private final List<Direction> directions;

    public King(final Position position, final Side side) {
        super(position, side);
        this.directions = initDirections();
    }

    private List<Direction> initDirections() {
        return new ArrayList<>(Arrays.asList(Direction.values()));
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
