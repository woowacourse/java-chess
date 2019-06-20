package chess.domain.piece;

import chess.domain.Spot;
import chess.domain.Team;

public class Blank extends Piece {
    public Blank() {
        super(Team.BLANK);
    }

    @Override
    public boolean isMovable(Spot startSpot, Spot endSpot) {
        return false;
    }
}
