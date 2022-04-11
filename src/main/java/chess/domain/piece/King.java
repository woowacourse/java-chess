package chess.domain.piece;

import chess.domain.piece.movestrategy.KingMoveStrategy;

public final class King extends Piece {

    public King(final Color color) {
        super(Notation.KING, color, new KingMoveStrategy());
    }
}
