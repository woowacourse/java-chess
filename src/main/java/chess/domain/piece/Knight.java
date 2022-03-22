package chess.domain.piece;

import chess.domain.Color;
import chess.domain.position.Position;

public class Knight extends Piece {
    public Knight(Position position, Color color) {
        super(position, color);
    }

    @Override
    protected String baseSignature() {
        return "n";
    }
}
