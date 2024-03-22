package model.piece;

import java.util.Set;
import model.Camp;
import model.position.Moving;
import model.position.Position;
import model.position.Row;

public final class WhitePawn extends Pawn {

    public WhitePawn() {
        super(Camp.WHITE);
    }

    @Override
    public Set<Position> getMoveRoute(final Moving moving) {
        final Position currentPosition = moving.getCurrentPosition();
        final Position nextPosition = moving.getNextPosition();
        if (!canMovable(moving)) {
            throw new IllegalArgumentException("해당 기물이 이동할 수 없는 위치입니다.");
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
        return isPawnStraight(currentPosition, nextPosition);
    }

    private boolean isPawnStraight(Position currentPosition, Position nextPosition) {
        final int dRow = currentPosition.getRowIndex() - nextPosition.getRowIndex();
        final int dColumn = currentPosition.getColumnIndex() - nextPosition.getColumnIndex();
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

    private static boolean isPawnDiagonal(Position currentPosition, Position nextPosition) {
        final int dRow = currentPosition.getRowIndex() - nextPosition.getRowIndex();
        final int dColumn = currentPosition.getColumnIndex() - nextPosition.getColumnIndex();
        if (Math.abs(dColumn) != 1) {
            return false;
        }
        return dRow == 1;
    }
}
