package chess.domain.piece;

import chess.domain.board.Path;

public class Bishop extends Piece {
    protected Bishop(Color color) {
        super(color);
    }

    @Override
    boolean canMove(Path path) {
        if (path.hasPiecePathExcludedTarget()) {
            return false;
        }
        if (path.containsOrthogonal()) {
            return false;
        }
        return path.canReach() && path.categoryNumOf(1);
    }
}
