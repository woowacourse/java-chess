package chess.domain.piece;

import chess.domain.Color;
import chess.domain.position.Position;

public class Bishop extends Piece {
    public Bishop(Position position, Color color) {
        super(position, color);
    }

    @Override
    protected String baseSignature() {
        return "b";
    }
}
