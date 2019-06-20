package chess.domain.piece;

import chess.domain.Aliance;
import chess.domain.Position;

public class Pawn extends Piece {
    private static final String NAME = "P";
    private static final double SCORE = 1;

    public Pawn(Aliance aliance) {
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

