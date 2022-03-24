package chess.domain.piece;

import chess.domain.Color;
import chess.domain.piece.strategy.KingMovableStrategy;

public final class King extends Piece {

    private static final String KING_NAME = "K";

    public King(Color color) {
        super(color, KING_NAME, new KingMovableStrategy());
    }
}
