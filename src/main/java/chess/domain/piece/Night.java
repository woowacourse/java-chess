package chess.domain.piece;

import chess.domain.Color;
import chess.domain.Position;

public final class Night extends Piece {
    private static final String NAME = "n";

    public Night(Position position, Color color) {
        super(position, color, NAME);
    }
}
