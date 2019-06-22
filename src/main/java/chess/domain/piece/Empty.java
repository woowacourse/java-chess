package chess.domain.piece;

import chess.domain.Spot;
import chess.domain.Team;

public class Empty extends Piece {
    private static Empty EMPTY;

    private Empty() {
        super(Team.EMPTY);
    }

    public static Empty getInstance() {
        if (EMPTY == null) {
            return new Empty();
        }
        return EMPTY;
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
