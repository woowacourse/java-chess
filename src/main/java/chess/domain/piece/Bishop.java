package chess.domain.piece;

import chess.domain.board.Direction;
import chess.domain.piece.movestrategy.DefaultMoveStrategy;

public final class Bishop extends Piece {

    public Bishop(final Color color) {
        super(Notation.BISHOP, color, new DefaultMoveStrategy(Direction.DIAGONAL_DIRECTION));
    }
}
