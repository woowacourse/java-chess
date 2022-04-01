package chess.domain.piece;

import chess.domain.board.Direction;
import chess.domain.piece.movestrategy.DefaultMoveStrategy;

public final class Rook extends Piece {

    public Rook(final Color color) {
        super(Notation.ROOK, color, new DefaultMoveStrategy(Direction.LINEAR_DIRECTION));
    }
}
