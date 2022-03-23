package chess.domain.piece;

import chess.domain.Color;
import chess.domain.Position;

public final class Pawn extends Piece {
    private static final String NAME = "p";

    public Pawn(Position position, Color color) {
        super(position, color, NAME);
    }
}
