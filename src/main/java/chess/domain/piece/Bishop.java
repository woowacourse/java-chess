package chess.domain.piece;

import chess.domain.piece.attribute.Color;
import chess.domain.piece.attribute.Notation;
import chess.domain.piece.attribute.Score;
import chess.domain.piece.strategy.BishopMoveStrategy;

public class Bishop extends Piece {
    private static final Score BISHOP_SCORE = new Score(3);
    private static final Notation BISHOP_NOTATION = new Notation("B");

    public Bishop(Color color) {
        super(color, BISHOP_NOTATION, new BishopMoveStrategy(), BISHOP_SCORE);
    }
}
