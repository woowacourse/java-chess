package chess.domain.piece;

import chess.domain.board.Path;

public class King extends Piece {

    private static final int MAX_MOVE_DISTANCE = 1;

    protected King(Color color) {
        super(color);
    }

    @Override
    boolean canMove(Path path) {
        return path.isSizeOf(MAX_MOVE_DISTANCE) && path.canReach();
    }
}
