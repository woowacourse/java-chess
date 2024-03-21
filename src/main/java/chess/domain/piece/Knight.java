package chess.domain.piece;

import chess.domain.board.Path;

public class Knight extends Piece {
    public Knight(Color color) {
        super(color);
    }

    @Override
    public boolean canMove(Path path) {
        return path.isSizeOf(2)
                && path.hasNoAllyAtTarget()
                && path.containsDiagonal()
                && path.containsOrthogonal();
    }
}
