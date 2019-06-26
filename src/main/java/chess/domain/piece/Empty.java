package chess.domain.piece;

import chess.domain.Spot;
import chess.domain.Team;

public class Empty extends Piece {
    private static Empty EMPTY;

    public static Empty getInstance() {
        if (EMPTY == null) {
            EMPTY = new Empty();
        }
        return EMPTY;
    }

    private Empty() {
        super(Team.EMPTY);
    }

    @Override
    public boolean isMovable(Spot startSpot, Spot endSpot) {
        return false;
    }

    @Override
    public boolean isAttackable(Spot startSpot, Spot endSpot) {
        return false;
    }
}
