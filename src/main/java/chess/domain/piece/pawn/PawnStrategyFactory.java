package chess.domain.piece.pawn;

import chess.domain.piece.property.Color;

public final class PawnStrategyFactory {

    private PawnStrategyFactory() {
    }

    public static PawnMoveStrategy from(final Color color) {
        if (color == Color.WHITE) {
            return new WhitePawnMoveStrategy();
        }
        return new BlackPawnMoveStrategy();
    }
}
