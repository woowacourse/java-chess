package chess.piece;

import chess.board.Position;
import java.util.ArrayList;
import java.util.List;

public class Rook extends SlidingPiece {

    private final List<Direction> directions;

    public Rook(final Position position, final Side side) {
        super(position, side);
        this.directions = initDirections();
    }

    private List<Direction> initDirections() {
        final List<Direction> directions = new ArrayList<>();
        directions.add(Direction.UP);
        directions.add(Direction.DOWN);
        directions.add(Direction.LEFT);
        directions.add(Direction.RIGHT);
        return directions;
    }

    @Override
    public boolean isMovable(Position targetPosition) {
        final Direction direction = position.getDirectionTo(targetPosition);
        return directions.contains(direction);
    }

    @Override
    public Rook move(final Position positionToMove) {
        return new Rook(positionToMove, this.side);
    }

}
