package chess.domain.piece;

import chess.domain.board.Path;

public class Bishop extends Piece {
    public Bishop(Color color) {
        super(color);
    }

    @Override
    public boolean canMove(Path path) {
        if (path.hasPiecePathExcludedTarget()) {
            return false;
        }
        if (path.containsOrthogonal()) {
            return false;
        }
        return path.hasNoAllyAtTarget() && path.categoryNumOf(1);
    }
}
