package chess.domain.piece;

import chess.domain.Color;
import chess.domain.Position;

public final class Rook extends Piece {
    private static final String NAME = "r";

    public Rook(Position position, Color color) {
        super(position, color, NAME);
    }
}
