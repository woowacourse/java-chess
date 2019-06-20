package chess.domain.piece;

import chess.domain.Aliance;
import chess.domain.Position;

public class Queen extends Piece {
    private static final String NAME = "Q";
    private static final double SCORE = 9;

    public Queen(Aliance aliance) {
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

