package chess.domain.piece;

import chess.domain.Spot;
import chess.domain.Team;

public class Blank extends Piece {
    private static Blank blank;

    private Blank() {
        super(Team.BLANK);
    }

    public static Blank getInstance() {
        if (blank == null) {
            return new Blank();
        }
        return blank;
    }

    @Override
    public boolean isMovable(Spot startSpot, Spot endSpot) {
        return false;
    }
}
