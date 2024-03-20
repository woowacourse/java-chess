package chess.domain.piece;

import chess.domain.board.Path;

public class Rook extends Piece {
    public Rook(Color color) {
        super(color);
    }

    @Override
    boolean canMove(Path path) {
        if (path.hasPiecePathExcludedTarget()) {
            return false;
        }
        if (path.containsDiagonal()) {
            return false;
        }
        return path.canReach() && path.categoryNumOf(1);
    }
}
