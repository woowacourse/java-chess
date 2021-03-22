package chess.domain.piece;

import chess.domain.piece.attribute.Color;
import chess.domain.piece.attribute.Notation;
import chess.domain.piece.strategy.KingMoveStrategy;

public class King extends RealPiece {
    private static final Notation KING_WORD = new Notation("K");

    public King(Color color) {
        super(color, KING_WORD, new KingMoveStrategy());
    }
}
