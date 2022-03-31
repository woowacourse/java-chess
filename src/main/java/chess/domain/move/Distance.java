package chess.domain.move;

import static chess.domain.move.MoveStrategy.NOT_MOVE;

import chess.domain.board.Position;

public class Distance {

    private final int horizon;
    private final int vertical;

    private Distance(final int horizon, final int vertical) {
        this.horizon = horizon;
        this.vertical = vertical;
    }

    public static Distance of(final Position source, final Position target) {
        return new Distance(source.subtractColumn(target), source.subtractRow(target));
    }

    public boolean isVerticalMovement() {
        return vertical != NOT_MOVE && horizon == NOT_MOVE;
    }

    public boolean isHorizontalMovement() {
        return horizon != NOT_MOVE && vertical == NOT_MOVE;
    }

    public boolean isPositiveDiagonal() {
        return isDiagonalMove() && vertical / horizon == 1;
    }

    public boolean isNegativeDiagonal() {
        return isDiagonalMove() && vertical / horizon == -1;
    }

    private boolean isDiagonalMove() {
        return vertical != NOT_MOVE && horizon != NOT_MOVE;
    }

    public int getVertical() {
        return vertical;
    }

    public int getHorizon() {
        return horizon;
    }
}
