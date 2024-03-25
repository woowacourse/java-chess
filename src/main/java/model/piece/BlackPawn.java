package model.piece;

import java.util.Set;
import model.Camp;
import model.position.Position;
import model.position.Rank;

public class BlackPawn extends Pawn {

    protected BlackPawn(final Camp camp) {
        super(camp);
    }

    @Override
    protected Set<Position> twoMovedRoute(final Position currentPosition) {
        return Set.of(new Position(currentPosition.getFile(), Rank.SIX));
    }

    @Override
    protected boolean isStraight(final Position currentPosition, final int differenceRank, final int differenceFile) {
        if (differenceFile != 0) {
            return false;
        }
        if (Rank.SEVEN.getIndex() == currentPosition.getRankIndex() && differenceRank == -2) {
            return true;
        }
        return differenceRank == -1;
    }

    @Override
    protected boolean isDiagonal(final int differenceRank, final int differenceFile) {
        if (Math.abs(differenceFile) != 1) {
            return false;
        }
        return differenceRank == -1;
    }
}
