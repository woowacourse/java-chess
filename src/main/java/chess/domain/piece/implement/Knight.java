package chess.domain.piece.implement;

import chess.domain.board.Path;
import chess.domain.piece.Color;
import chess.domain.piece.Piece;
import chess.domain.piece.PieceType;

public class Knight extends Piece {
    public Knight(Color color) {
        super(color, PieceType.KNIGHT);
    }

    @Override
    public boolean canMove(Path path) {
        return path.isDistanceOf(2)
                && path.isNotAllyAtTarget()
                && path.containsDiagonalDirection()
                && path.containsOrthogonalDirection();
    }

    @Override
    public void move() {
    }
}
