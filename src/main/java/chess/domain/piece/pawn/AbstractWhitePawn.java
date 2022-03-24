package chess.domain.piece.pawn;

import chess.domain.Color;
import chess.domain.piece.strategy.PawnMovableStrategy;

public abstract class AbstractWhitePawn extends Pawn {

    private static final Color WHITE_PAWN_COLOR = Color.WHITE;

    public AbstractWhitePawn(PawnMovableStrategy pawnMovableStrategy) {
        super(WHITE_PAWN_COLOR, pawnMovableStrategy);
    }
}
