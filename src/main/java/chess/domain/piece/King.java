package chess.domain.piece;

import chess.domain.Aliance;
import chess.domain.Position;

public class King extends Piece {
    private static final String NAME = "K";
    private static final double SCORE = 0;

    public King(Aliance aliance) {
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
