package chess.domain.piece;

import chess.domain.Color;
import chess.domain.Position;

public final class Queen extends Piece {

    private static final String NAME = "q";

    public Queen(Position position, Color color) {
        super(position, color, NAME);
    }
}
