package chess.domain.piece;

import chess.domain.board.Route;

public abstract class Pawn extends Piece {

    public static final String PAWN_NAME = "P";

    abstract boolean isBackward(Route route);

    private boolean moved = false;

    protected Pawn(Color color) {
        super(color, PAWN_NAME);
    }

    @Override
    public boolean canMove(Route route) {
        boolean movable = checkMovable(route);
        checkMoved(movable);
        return movable;
    }

    private boolean checkMovable(Route route) {
        if (isNotGeneralMove(route)) {
            return false;
        }
        if (route.containsDiagonal()) {
            return route.isSizeOf(1) && route.isTargetHasEnemy();
        }
        if (route.isSizeOf(2)) {
            return route.isAllEmpty() && isFirstMove();
        }
        return route.isSizeOf(1) && route.isAllEmpty();
    }

    private void checkMoved(boolean movable) {
        if (movable) {
            moved = true;
        }
    }

    private boolean isNotGeneralMove(Route route) {
        if (!route.categoryNumOf(1)) {
            return true;
        }
        return isBackward(route);
    }

    private boolean isFirstMove() {
        return !moved;
    }
}
