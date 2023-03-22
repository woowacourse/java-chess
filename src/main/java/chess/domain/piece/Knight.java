package chess.domain.piece;

import chess.domain.position.Position;

import java.util.Collections;
import java.util.List;

public class Knight extends Piece {

    private static final double L_MOVE_SLOPE1 = 0.5;
    private static final double L_MOVE_SLOPE2 = 2.0;

    public Knight(final Position position, final Side side) {
        super(position, side);
    }

    @Override
    public boolean isMovable(Position targetPosition) {
        final double slope = position.getSlope(targetPosition);

        return slope == L_MOVE_SLOPE1 || slope == L_MOVE_SLOPE2;
    }

    @Override
    public Piece move(Position positionToMove) {
        return new Knight(positionToMove, this.side);
    }

    @Override
    public boolean isNeedToCheckWhenDiagonalMove() {
        return false;
    }

    @Override
    public List<Position> getPaths(final Position targetPosition) {
        return Collections.emptyList();
    }
}
