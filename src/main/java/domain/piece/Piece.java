package domain.piece;

import domain.piece.move.Situation;
import domain.piece.move.Coordinate;

public abstract class Piece {

    public boolean isReachableByRule(
            final Coordinate start,
            final Coordinate end,
            final Situation situation
    ) {
        validateIsNotSameColor(situation);
        return isReachableByRuleWhenNoEnemy(start, end);
    }

    protected abstract boolean isReachableByRuleWhenNoEnemy(
            final Coordinate start,
            final Coordinate end
    );

    private void validateIsNotSameColor(final Situation situation) {
        if (situation.meetColleague()) {
            throw new IllegalArgumentException("[ERROR] 같은 팀이 존재하는 곳으로 이동할 수 없습니다.");
        }
    }

    public boolean canJump() {
        return false;
    }

    public boolean isPawn() {
        return false;
    }

    public boolean isKing() {
        return false;
    }
}
