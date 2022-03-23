package chess.domain.piece;

import chess.domain.Color;
import chess.domain.Position;

public final class King extends Piece {
    private static final String NAME = "k";

    public King(Position position, Color color) {
        super(position, color, NAME);
    }
}
