package chess.domain.piece;

import chess.domain.board.Path;

public class Rook extends Piece {
    public Rook(Color color) {
        super(color);
    }

    @Override
    public boolean canMove(Path path) {
        if (path.hasPiecePathExcludedTarget()) {
            return false;
        }
        if (path.containsDiagonal()) {
            return false;
        }
        return path.hasNoAllyAtTarget() && path.categoryNumOf(1);
    }
}
