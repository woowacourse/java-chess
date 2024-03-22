package model.piece;

import model.Camp;
import model.position.Moving;
import model.position.Position;
import model.position.Row;

import java.util.Set;

public final class WhitePawn extends Pawn {

    public WhitePawn() {
        super(Camp.WHITE);
    }

    @Override
    public Set<Position> getMoveRoute(final Moving moving) {
        final Position currentPosition = moving.getCurrentPosition();
        final Position nextPosition = moving.getNextPosition();
        if (!canMovable(moving)) {
            throw new IllegalArgumentException("이동 불가");
        }
        if (Math.abs(nextPosition.getRowIndex() - currentPosition.getRowIndex()) == 1) {
            return Set.of();
        }
        return Set.of(new Position(currentPosition.getColumn(), Row.THIRD));
    }

    @Override
    protected boolean canMovable(final Moving moving) {
        if (moving.isNotMoved()) {
            return false;
        }
        final Position currentPosition = moving.getCurrentPosition();
        final Position nextPosition = moving.getNextPosition();
        final int dRow = currentPosition.getRowIndex() - nextPosition.getRowIndex();
        final int dColumn = currentPosition.getColumnIndex() - nextPosition.getColumnIndex();
        return isStraight(currentPosition, dColumn, dRow);
    }

    private boolean isStraight(final Position currentPosition, final int dColumn, final int dRow) {
        if (dColumn != 0) {
            return false;
        }
        if (Row.SECOND.getIndex() == currentPosition.getRowIndex() && dRow == 2) {
            return true;
        }
        return dRow == 1;
    }

    @Override
    public Set<Position> getAttackRoute(final Moving moving) {
        if (!canAttack(moving)) {
            throw new IllegalArgumentException("이동 불가");
        }
        return Set.of();
    }

    private boolean canAttack(final Moving moving) {
        if (moving.isNotMoved()) {
            return false;
        }
        final Position currentPosition = moving.getCurrentPosition();
        final Position nextPosition = moving.getNextPosition();
        final int dRow = currentPosition.getRowIndex() - nextPosition.getRowIndex();
        final int dColumn = currentPosition.getColumnIndex() - nextPosition.getColumnIndex();
        return isDiagonal(dColumn, dRow);
    }

    private boolean isDiagonal(final int dColumn, final int dRow) {
        if (Math.abs(dColumn) != 1) {
            return false;
        }
        return dRow == 1;
    }
}
