package chess.domain.piece;

import chess.domain.Color;
import chess.domain.position.Position;

public class King extends Piece {
    public King(Position position, Color color) {
        super(position, color);
    }

    @Override
    protected String baseSignature() {
        return "k";
    }
}
