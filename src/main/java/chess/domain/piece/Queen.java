package chess.domain.piece;

import chess.domain.piece.property.Color;
import chess.domain.piece.state.StartedQueen;

public final class Queen extends Piece {

    private static final String NAME = "q";

    public Queen(Color color) {
        super(color, NAME, new StartedQueen());
    }
}
