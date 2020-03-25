package chess.domain.movingstrategy;

import chess.domain.Team;
import chess.domain.position.Position;
import chess.domain.position.Rank;

import java.util.function.Predicate;

public class PawnMovingStrategy implements Predicate<Variables> {
    @Override
    public boolean test(Variables variables) {
        // from 에서 to 로 갈 수 있으면 true return

        Position fromPosition = variables.getFromPosition();
        Position toPosition = variables.getToPosition();
        Team team = variables.getTeam();

        if (team == Team.BLACK) {
            if (fromPosition.isAt(Rank.SEVEN)) {
                if (fromPosition.increaseRank(-2) == toPosition
                        || fromPosition.increaseRank(-1) == toPosition) {
                    return true;
                }
                return false;
            }

            if (!fromPosition.isAt(Rank.SEVEN)) {
                if (fromPosition.increaseRank(-1) == toPosition) {
                    return true;
                }
                return false;
            }
        }

        // if team == Team.WHITE
        if (fromPosition.isAt(Rank.TWO)) {
            if (fromPosition.increaseRank(2) == toPosition
                    || fromPosition.increaseRank(1) == toPosition) {
                return true;
            }
            return false;
        }

        // if fromPosition.isAt(Rank.TWO)
        if (fromPosition.increaseRank(1) == toPosition) {
            return true;
        }
        return false;
    }
}
