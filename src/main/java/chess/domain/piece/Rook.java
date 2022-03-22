package chess.domain.piece;

import chess.domain.Color;
import chess.domain.position.Position;

public class Rook extends Piece {
    public Rook(Position position, Color color) {
        super(position, color);
    }

    @Override
    protected String baseSignature() {
        return "r";
    }
}
