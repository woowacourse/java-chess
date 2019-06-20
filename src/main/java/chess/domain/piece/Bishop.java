package chess.domain.piece;

import chess.domain.Aliance;
import chess.domain.Position;

public class Bishop extends Piece {
    private static final String NAME = "B";
    private static final double SCORE = 3;

    public Bishop(Aliance aliance) {
        super(aliance);
    }

    @Override
    public Position isPossibleMove() {
        return null;
    }

    @Override
    public String toString() {
        if (aliance == Aliance.BLACK) {
            return NAME;
        }
        return NAME.toLowerCase();
    }
}

