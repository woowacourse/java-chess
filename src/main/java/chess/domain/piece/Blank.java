package chess.domain.piece;

import chess.domain.piece.strategy.CannotMoveStrategy;

public class Blank extends Piece {
    private static final String BLANK_NOTATION = ".";

    public Blank() {
        super(BLANK_NOTATION, new CannotMoveStrategy());
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Blank) {
            return true;
        }
        return false;
    }
}
