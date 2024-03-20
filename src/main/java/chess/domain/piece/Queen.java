package chess.domain.piece;

import chess.domain.board.Path;

public class Queen extends Piece {
    public Queen(Color color) {
        super(color);
    }

    @Override
    public boolean canMove(Path path) {
        if (path.hasPiecePathExcludedTarget()) {
            return false;
        }
        return path.canReach() && path.categoryNumOf(1);
    }
}

