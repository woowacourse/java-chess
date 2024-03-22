package chess.domain.piece;

import chess.domain.board.Path;

public class Knight extends Piece {
    public Knight(Color color) {
        super(color);
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
