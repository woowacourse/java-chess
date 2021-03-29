package chess.domain.piece;

import chess.domain.piece.strategy.KnightMoveStrategy;

public class Knight extends RealPiece {
    private static final String KNIGHT_WORD = "N";

    public Knight(Color color) {
        super(color, KNIGHT_WORD, new KnightMoveStrategy());
    }
}
