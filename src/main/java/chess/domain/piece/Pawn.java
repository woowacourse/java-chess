package chess.domain.piece;

import chess.domain.board.Path;

public abstract class Pawn extends Piece {
    abstract boolean isBackward(Path path);

    private boolean moved;

    protected Pawn(Color color) {
        super(color);
        this.moved = false;
    }

    @Override
    public boolean canMove(Path path) {
        boolean checkMove = checkMovable(path);
        moved = true;
        return checkMove;
    }

    private boolean checkMovable(Path path) {
        if (isNotGeneralMove(path)) {
            return false;
        }
        if (path.containsDiagonalDirection()) {
            return path.isDistanceOf(1) && path.isEnemyAtTarget();
        }
        if (path.isDistanceOf(2)) {
            return path.isAllEmpty() && isFirstMove();
        }
        return path.isDistanceOf(1) && path.isAllEmpty();
    }

    private boolean isNotGeneralMove(Path path) {
        if (!path.hasCountOfDirection(1)) {
            return true;
        }
        return isBackward(path);
    }

    private boolean isFirstMove() {
        return !moved;
    }
}
