package chess.domain.piece;

import chess.domain.piece.strategy.QueenMoveStrategy;

public class Queen extends RealPiece {
    private static final String QUEEN_NAME = "Queen";

    public Queen(Color color) {
        super(color, QUEEN_NAME, new QueenMoveStrategy());
    }
}
