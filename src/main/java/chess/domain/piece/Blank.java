package chess.domain.piece;

import chess.domain.piece.strategy.CannotMoveStrategy;

public class Blank extends Piece {
    private static final String BLANK_NOTATION = ".";

    public Blank() {
        super(BLANK_NOTATION, new CannotMoveStrategy());
    }
}
