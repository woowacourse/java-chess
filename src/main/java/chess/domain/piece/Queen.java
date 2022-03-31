package chess.domain.piece;

import chess.domain.piece.property.Color;
import chess.domain.piece.state.started.StartedQueen;

public final class Queen extends Piece {
    private static final String NAME = "q";
    private static final double SCORE = 9;

    public Queen(Color color) {
        super(color, NAME, SCORE, new StartedQueen());
    }

    @Override
    public boolean isKing() {
        return false;
    }
}
