package chess.domain.piece;

import chess.domain.piece.strategy.PawnMoveStrategy;

public class Pawn extends RealPiece {
    private static final String PAWN_WORD = "P";

    public Pawn(Color color) {
        super(color, PAWN_WORD, new PawnMoveStrategy(color));
    }
}
