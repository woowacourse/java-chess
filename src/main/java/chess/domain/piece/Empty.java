package chess.domain.piece;

import chess.domain.Spot;
import chess.domain.Team;

public class Empty extends Piece {
    private Empty() {
        super(Team.EMPTY);
    }

    public static Empty getInstance() {
        return LazyHolder.INSTANCE;
    }

    private static class LazyHolder {
        private static Empty INSTANCE = new Empty();
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
