package chess.domain.piece;

import chess.domain.board.Path;

public abstract class Pawn extends Piece {
    abstract boolean isBackward(Path path);

    private boolean moved = false;

    protected Pawn(Color color) {
        super(color);
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
        if (path.containsDiagonal()) {
            return path.isSizeOf(1) && path.isTargetHasEnemy();
        }
        if (path.isSizeOf(2)) {
            return path.isAllEmpty() && isFirstMove();
        }
        return path.isSizeOf(1) && path.isAllEmpty();
    }

    private boolean isNotGeneralMove(Path path) {
        if (!path.categoryNumOf(1)) {
            return true;
        }
        return isBackward(path);
    }

    private boolean isFirstMove() {
        return !moved;
    }
}
