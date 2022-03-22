package chess.domain.piece;

import chess.domain.Color;

public class King extends Piece {
    public King(Color color) {
        super(color);
    }

    @Override
    protected String baseSignature() {
        return "k";
    }
}
