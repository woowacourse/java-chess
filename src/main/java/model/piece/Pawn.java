package model.piece;

import constant.ErrorCode;
import exception.InvalidMovingException;
import java.util.Set;
import model.Camp;
import model.position.Moving;
import model.position.Position;

public abstract class Pawn extends Piece {

    protected Pawn(final Camp camp) {
        super(camp);
    }

    public static Pawn create(Camp camp) {
        if (camp == Camp.BLACK) {
            return new BlackPawn();
        }
        return new WhitePawn();
    }

    protected abstract boolean isDiagonal(final int differenceRank, final int differenceFile);

    protected abstract boolean isStraight(final Position currentPosition,
                                          final int differenceRank,
                                          final int differenceFile);

    protected abstract Set<Position> twoMovedRoute(final Position currentPosition);

    protected boolean canAttack(final Moving moving) {
        if (moving.isNotMoved()) {
            return true;
        }
        final Position currentPosition = moving.getCurrentPosition();
        final Position nextPosition = moving.getNextPosition();

        final int differenceRank = currentPosition.getRankIndex() - nextPosition.getRankIndex();
        final int differenceFile = currentPosition.getFileIndex() - nextPosition.getFileIndex();

        return !isDiagonal(differenceRank, differenceFile);
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

    @Override
    public Set<Position> getMoveRoute(final Moving moving) {
        if (!canMovable(moving)) {
            throw new InvalidMovingException(ErrorCode.INVALID_PAWN_MOVEMENT);
        }
        final Position currentPosition = moving.getCurrentPosition();
        final Position nextPosition = moving.getNextPosition();

        if (Math.abs(nextPosition.getRankIndex() - currentPosition.getRankIndex()) == 1) {
            return Set.of();
        }
        return twoMovedRoute(currentPosition);
    }

    @Override
    public Set<Position> getAttackRoute(final Moving moving) {
        if (canAttack(moving)) {
            throw new InvalidMovingException(ErrorCode.INVALID_PAWN_MOVEMENT);
        }
        return Set.of();
    }
}
