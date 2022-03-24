package chess.domain.piece;

import chess.domain.Color;
import chess.domain.piece.strategy.QueenMovableStrategy;

public final class Queen extends Piece {

    private static final String QUEEN_NAME = "Q";

    public Queen(Color color) {
        super(color, QUEEN_NAME, new QueenMovableStrategy());
    }
}
