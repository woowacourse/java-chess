package chess.domain.piece;

import chess.domain.piece.strategy.RookMoveStrategy;

public class Rook extends RealPiece {
    private static final String ROOK_WORD = "R";

    public Rook(Color color) {
        super(color, ROOK_WORD, new RookMoveStrategy());
    }
}
