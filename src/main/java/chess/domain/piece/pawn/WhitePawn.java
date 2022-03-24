package chess.domain.piece.pawn;

import chess.domain.piece.strategy.WhitePawnMovableStrategy;

public final class WhitePawn extends AbstractWhitePawn {

    public WhitePawn() {
        super(new WhitePawnMovableStrategy());
    }
}
