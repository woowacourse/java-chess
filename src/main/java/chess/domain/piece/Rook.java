package chess.domain.piece;

import chess.domain.Aliance;
import chess.domain.Position;

public class Rook extends Piece {
    private static final String NAME = "R";
    private static final double SCORE = 5;

    public Rook(Aliance aliance) {
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

