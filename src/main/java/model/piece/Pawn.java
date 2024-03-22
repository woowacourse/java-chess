package model.piece;

import constant.ErrorCode;
import exception.InvalidMovingException;
import java.util.Set;
import model.Camp;
import model.position.Moving;
import model.position.Position;
import model.position.Rank;
import view.message.PieceType;

public class Pawn extends Piece {

    public Pawn(final Camp camp) {
        super(camp);
    }

    @Override
    public Set<Position> getMoveRoute(final Moving moving) {
        if (!canMovable(moving)) {
            throw new InvalidMovingException(ErrorCode.INVALID_MOVEMENT_RULE);
        }
        final Position currentPosition = moving.getCurrentPosition();
        final Position nextPosition = moving.getNextPosition();

        if (Math.abs(nextPosition.getRankIndex() - currentPosition.getRankIndex()) == 1) {
            return Set.of();
        }
        return getTwoStraightRoute(currentPosition);
    }

    private Set<Position> getTwoStraightRoute(final Position currentPosition) {
        if (Camp.BLACK == camp) {
            return Set.of(new Position(currentPosition.getFile(), Rank.SIX));
        }
        return Set.of(new Position(currentPosition.getFile(), Rank.THREE));
    }

    @Override
    protected boolean canMovable(final Moving moving) {
        if (moving.isNotMoved()) {
            return false;
        }
        final Position currentPosition = moving.getCurrentPosition();
        final Position nextPosition = moving.getNextPosition();

        final int differenceRank = currentPosition.getRankIndex() - nextPosition.getRankIndex();
        final int differenceFile = currentPosition.getFileIndex() - nextPosition.getFileIndex();

        return isStraight(currentPosition, differenceRank, differenceFile);
    }

    private boolean isStraight(final Position currentPosition, final int differenceRank, final int differenceFile) {
        if (differenceFile != 0) {
            return false;
        }
        if (Camp.BLACK == camp) {
            return isBlackTwoStraight(currentPosition, differenceRank);
        }
        if (Rank.TWO.getIndex() == currentPosition.getRankIndex() && differenceRank == 2) {
            return true;
        }
        return differenceRank == 1;
    }

    private boolean isBlackTwoStraight(final Position currentPosition, final int differenceRank) {
        if (Rank.SEVEN.getIndex() == currentPosition.getRankIndex() && differenceRank == -2) {
            return true;
        }
        return differenceRank == -1;
    }

    @Override
    public Set<Position> getAttackRoute(final Moving moving) {
        if (!canAttack(moving)) {
            throw new InvalidMovingException(ErrorCode.INVALID_MOVEMENT_RULE);
        }
        return Set.of();
    }

    private boolean canAttack(final Moving moving) {
        if (moving.isNotMoved()) {
            return false;
        }
        final Position currentPosition = moving.getCurrentPosition();
        final Position nextPosition = moving.getNextPosition();

        final int differenceRank = currentPosition.getRankIndex() - nextPosition.getRankIndex();
        final int differenceFile = currentPosition.getFileIndex() - nextPosition.getFileIndex();

        return isDiagonal(differenceRank, differenceFile);
    }

    private boolean isDiagonal(final int differenceRank, final int differenceFile) {
        if (Math.abs(differenceFile) != 1) {
            return false;
        }
        if (Camp.BLACK == camp) {
            return differenceRank == -1;
        }
        return differenceRank == 1;
    }

    @Override
    public String toString() {
        return PieceType.from(this).getValue();
    }
}
