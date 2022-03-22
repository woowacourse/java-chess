package chess.domain.piece;

import chess.domain.Color;

public class Pawn extends Piece {
    public Pawn(Color color) {
        super(color);
    }

    @Override
    protected String baseSignature() {
        return "p";
    }
}

