package chess.domain.piece;

import chess.domain.Color;

public class Bishop extends Piece {
    public Bishop(Color color) {
        super(color);
    }

    @Override
    protected String baseSignature() {
        return "b";
    }
}
