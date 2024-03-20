package chess.domain.piece;

import chess.domain.board.Path;

//TODO 색깔 기본으로 넘기기
public class BlackPawn extends Piece {

    private boolean moved = false;

    //
    public BlackPawn(Color color) {
        super(color);
    }

    @Override
    public boolean canMove(Path path) {
        boolean checkMove = checkMovable(path);
        moved = true;
        return checkMove;
    }

    private boolean checkMovable(Path path) {
        if (!path.categoryNumOf(1)) {
            return false;
        }
        if (path.isUpside()) {
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

    private boolean isFirstMove() {
        return !moved;
    }
}
