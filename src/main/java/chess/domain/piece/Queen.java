package chess.domain.piece;

import chess.domain.Color;

public class Queen extends Piece {
    public Queen(Color color) {
        super(color);
    }

    @Override
    protected String baseSignature() {
        return "q";
    }
}
