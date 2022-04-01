package chess.domain.piece;

import chess.domain.piece.movestrategy.KnightMoveStrategy;

public final class Knight extends Piece {

    public Knight(final Color color) {
        super(Notation.KNIGHT, color, new KnightMoveStrategy());
    }
}
