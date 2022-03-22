package chess.domain.piece;

import chess.domain.Color;

public class Rook extends Piece {
    public Rook(Color color) {
        super(color);
    }

    @Override
    protected String baseSignature() {
        return "r";
    }
}
