package chess.domain.piece;

import chess.domain.board.Path;

public class King extends Piece {

    private static final int MAX_MOVE_DISTANCE = 1;

    public King(Color color) {
        super(color);
    }

    @Override
    public boolean canMove(Path path) {
        return path.isDistanceOf(MAX_MOVE_DISTANCE) && path.isNotAllyAtTarget();
    }
}
