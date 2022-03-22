package chess.domain.piece;

import chess.domain.Color;
import chess.domain.position.Position;

public class Pawn extends Piece {
    public Pawn(Position position, Color color) {
        super(position, color);
    }

    @Override
    protected String baseSignature() {
        return "p";
    }
}

