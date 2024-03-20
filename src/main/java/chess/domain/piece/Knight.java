package chess.domain.piece;

import chess.domain.board.Path;

public class Knight extends Piece {
    protected Knight(Color color) {
        super(color);
    }

    @Override
    boolean canMove(Path path) {
        return path.isSizeOf(2)
                && path.canReach()
                && path.containsDiagonal()
                && path.containsOrthogonal();
    }
}
