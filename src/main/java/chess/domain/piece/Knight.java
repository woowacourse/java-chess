package chess.domain.piece;

import chess.domain.Color;

public class Knight extends Piece {
    public Knight(Color color) {
        super(color);
    }

    @Override
    protected String baseSignature() {
        return "n";
    }
}
