package chess.domain.piece.pawn;

import chess.domain.Color;
import chess.domain.piece.Piece;
import chess.domain.piece.strategy.PawnMovableStrategy;
import chess.domain.piece.strategy.WhitePawnFirstMovableStrategy;

public abstract class Pawn extends Piece {

    private static final String PAWN_NAME = "P";
    private static final double PAWN_SCORE = 1;

    public Pawn(Color color) {
        super(color, PAWN_NAME, new WhitePawnFirstMovableStrategy());
    }

    public Pawn(Color color, PawnMovableStrategy pawnMovableStrategy) {
        super(color, PAWN_NAME, pawnMovableStrategy);
    }

    @Override
    public final double score() {
        return PAWN_SCORE;
    }

    @Override
    public final boolean isPawn() {
        return true;
    }
}
