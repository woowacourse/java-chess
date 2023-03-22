package chess.piece.sliding;

import chess.board.Position;
import chess.piece.Direction;
import chess.piece.Side;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Queen extends SlidingPiece {

    private final List<Direction> directions;
    public Queen(final Position position, final Side side) {
        super(position, side);
        this.directions = initDirections();
    }

    private List<Direction> initDirections() {
        return new ArrayList<>(Arrays.asList(Direction.values()));
    }

    @Override
    public boolean isMovable(Position targetPosition) {
        final Direction direction = position.getDirectionTo(targetPosition);
        return directions.contains(direction);
    }

    @Override
    public Queen move(final Position positionToMove) {
        return new Queen(positionToMove, this.side);
    }

}
