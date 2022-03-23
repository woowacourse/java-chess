package chess.domain.piece;

import chess.domain.Color;
import chess.domain.Position;

public final class Bishop extends Piece {
    private static final String NAME = "b";

    public Bishop(Position position, Color color) {
        super(position, color, NAME);
    }
}
