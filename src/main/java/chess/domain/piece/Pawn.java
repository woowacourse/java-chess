package chess.domain.piece;

import chess.domain.piece.strategy.PawnMoveStrategy;

public class Pawn extends RealPiece {
    private static final String PAWN_NAME = "Pawn";

    public Pawn(Color color) {
        super(color, PAWN_NAME, new PawnMoveStrategy(color));
    }
}
