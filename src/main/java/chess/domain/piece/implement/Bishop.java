package chess.domain.piece.implement;

import chess.domain.board.Path;
import chess.domain.piece.Color;
import chess.domain.piece.Piece;
import chess.domain.piece.PieceType;

public class Bishop extends Piece {
    public Bishop(Color color) {
        super(color, PieceType.BISHOP);
    }

    @Override
    public boolean canMove(Path path) {
        if (path.hasPiecePathExcludedTarget()) {
            return false;
        }
        if (path.containsOrthogonalDirection()) {
            return false;
        }
        return path.isNotAllyAtTarget() && path.hasCountOfDirection(1);
    }

    @Override
    public void move() {
    }
}
