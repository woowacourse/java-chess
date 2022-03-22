package chess.domain.piece;

import chess.domain.Color;
import chess.domain.position.Position;

public class Queen extends Piece {
    public Queen(Position position, Color color) {
        super(position, color);
    }

    @Override
    protected String baseSignature() {
        return "q";
    }
}
