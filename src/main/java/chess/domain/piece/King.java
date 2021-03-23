package chess.domain.piece;

import chess.domain.piece.attribute.Color;
import chess.domain.piece.attribute.Notation;
import chess.domain.piece.attribute.Score;
import chess.domain.piece.strategy.KingMoveStrategy;

public class King extends Piece {
    private static final Score ZERO_SCORE = new Score(0);
    private static final Notation KING_WORD = new Notation("K");

    public King(Color color) {
        super(color, KING_WORD, new KingMoveStrategy(), ZERO_SCORE);
    }
}
