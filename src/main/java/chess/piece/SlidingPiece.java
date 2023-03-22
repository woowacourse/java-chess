package chess.piece;

import chess.board.Position;
import java.util.ArrayList;
import java.util.List;

public abstract class SlidingPiece extends Piece {

    public SlidingPiece(final Position position, final Side side) {
        super(position, side);
    }

    @Override
    public boolean isMovable(final Position targetPosition) {
        return false;
    }

    @Override
    public Piece move(final Position positionToMove) {
        return null;
    }

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
