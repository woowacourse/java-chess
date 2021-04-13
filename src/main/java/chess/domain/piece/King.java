package chess.domain.piece;

import chess.domain.piece.strategy.KingMoveStrategy;

public class King extends RealPiece {
    private static final String KING_NAME = "King";

    public King(Color color) {
        super(color, KING_NAME, new KingMoveStrategy());
    }
}
