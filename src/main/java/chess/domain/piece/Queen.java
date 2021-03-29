package chess.domain.piece;

import chess.domain.piece.strategy.QueenMoveStrategy;

public class Queen extends RealPiece {
    private static final String QUEEN_WORD = "Q";

    public Queen(Color color) {
        super(color, QUEEN_WORD, new QueenMoveStrategy());
    }
}
