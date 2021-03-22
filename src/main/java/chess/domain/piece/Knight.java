package chess.domain.piece;

import chess.domain.piece.attribute.Color;
import chess.domain.piece.attribute.Notation;
import chess.domain.piece.strategy.KnightMoveStrategy;

public class Knight extends RealPiece {
    private static final Notation KNIGHT_NOTATION = new Notation("K");

    public Knight(Color color) {
        super(color, KNIGHT_NOTATION, new KnightMoveStrategy());
    }
}
