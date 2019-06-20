package chess.domain.piece;

import chess.domain.Aliance;
import chess.domain.Position;

public class Knight extends Piece {
    private static final String NAME = "N";
    private static final double SCORE = 2.5;

    public Knight(Aliance aliance) {
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

