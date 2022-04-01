package chess.domain.piece;

import chess.domain.board.Direction;
import chess.domain.piece.movestrategy.DefaultMoveStrategy;

public final class Queen extends Piece {

    public Queen(final Color color) {
        super(Notation.QUEEN, color, new DefaultMoveStrategy(Direction.EVERY_DIRECTION));
    }
}
