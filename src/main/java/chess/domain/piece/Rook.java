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
        if (path.containsDiagonalDirection()) {
            return false;
        }
        return path.isNotAllyAtTarget() && path.hasCountOfDirection(1);
    }

    @Override
    public void move() {
    }
}
