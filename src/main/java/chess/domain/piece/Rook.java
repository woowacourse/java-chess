package chess.domain.piece;

import chess.domain.piece.strategy.RookMoveStrategy;

public class Rook extends RealPiece {
    private static final String ROOK_NAME = "Rook";

    public Rook(Color color) {
        super(color, ROOK_NAME, new RookMoveStrategy());
    }
}
