package model.piece;

import java.util.Set;
import model.Camp;
import model.position.Moving;
import model.position.Position;
import model.position.Row;

public final class BlackPawn extends Pawn {

    private static final int BLACK_PAWN_ONE_STRAIGHT = 1;

    public BlackPawn() {
        super(Camp.BLACK);
    }

    @Override
    public Set<Position> getMoveRoute(final Moving moving) {
        final Position currentPosition = moving.getCurrentPosition();
        final Position nextPosition = moving.getNextPosition();
        if (!canMovable(moving)) {
            throw new IllegalArgumentException("해당 기물이 이동할 수 없는 위치입니다.");
        }
        if (Math.abs(nextPosition.getRowIndex() - currentPosition.getRowIndex()) == BLACK_PAWN_ONE_STRAIGHT) {
            return Set.of();
        }
        return Set.of(new Position(currentPosition.getColumn(), Row.SIXTH));
    }

    @Override
    protected boolean canMovable(final Moving moving) {
        if (moving.isNotMoved()) {
            return false;
        }
        final Position currentPosition = moving.getCurrentPosition();
        final Position nextPosition = moving.getNextPosition();
        return isPawnStraight(currentPosition, nextPosition);
    }

    private boolean isPawnStraight(final Position currentPosition, final Position nextPosition) {
        final int dRow = nextPosition.getRowIndex() - currentPosition.getRowIndex();
        final int dColumn = nextPosition.getColumnIndex() - currentPosition.getColumnIndex();
        if (dColumn != 0) {
            return false;
        }
        if (Row.SEVENTH.getIndex() == currentPosition.getRowIndex() && dRow == 2) {
            return true;
        }
        return dRow == 1;
    }

    @Override
    public Set<Position> getAttackRoute(final Moving moving) {
        if (!canAttack(moving)) {
            throw new IllegalArgumentException("해당 기물이 이동할 수 없는 위치입니다.");
        }
        return Set.of();
    }

    private boolean canAttack(final Moving moving) {
        if (moving.isNotMoved()) {
            return false;
        }
        final Position currentPosition = moving.getCurrentPosition();
        final Position nextPosition = moving.getNextPosition();
        return isPawnDiagonal(currentPosition, nextPosition);
    }

    private boolean isPawnDiagonal(final Position currentPosition, final Position nextPosition) {
        final int dRow = nextPosition.getRowIndex() - currentPosition.getRowIndex();
        final int dColumn = nextPosition.getColumnIndex() - currentPosition.getColumnIndex();
        if (Math.abs(dColumn) != 1) {
            return false;
        }
        return dRow == 1;
    }
}
