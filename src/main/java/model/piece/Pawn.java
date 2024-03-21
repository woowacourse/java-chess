package model.piece;

import java.util.Set;
import model.Camp;
import view.message.PieceType;
import model.position.Moving;
import model.position.Position;
import model.position.Row;

public class Pawn extends Piece {

    public Pawn(final Camp camp) {
        super(camp);
    }

    @Override
    public Set<Position> getMoveRoute(Moving moving) {
        Position currentPosition = moving.currentPosition();
        Position nextPosition = moving.nextPosition();
        if (!canMovable(moving)) {
            throw new IllegalArgumentException("이동 불가");
        }
        if (Math.abs(nextPosition.getRowIndex() - currentPosition.getRowIndex()) == 1) {
            return Set.of();
        }
        return getTwoStraightRoute(currentPosition);
    }

    private Set<Position> getTwoStraightRoute(Position currentPosition) {
        if (Camp.BLACK == camp) {
            return Set.of(new Position(currentPosition.getColumn(), Row.SIXTH));
        }
        return Set.of(new Position(currentPosition.getColumn(), Row.THIRD));
    }

    @Override
    protected boolean canMovable(Moving moving) {
        if (moving.isNotMoved()) {
            return false;
        }
        Position currentPosition = moving.currentPosition();
        Position nextPosition = moving.nextPosition();
        int dRow = currentPosition.getRowIndex() - nextPosition.getRowIndex();
        int dColumn = currentPosition.getColumnIndex() - nextPosition.getColumnIndex();
        return isStraight(currentPosition, dColumn, dRow);
    }

    private boolean isStraight(Position currentPosition, int dColumn, int dRow) {
        if (dColumn != 0) {
            return false;
        }
        if (Camp.BLACK == camp) {
            return isBlackTwoStraight(currentPosition, dRow);
        }
        if (Row.SECOND.getIndex() == currentPosition.getRowIndex() && dRow == 2) {
            return true;
        }
        return dRow == 1;
    }

    private boolean isBlackTwoStraight(Position currentPosition, int dRow) {
        if (Row.SEVENTH.getIndex() == currentPosition.getRowIndex() && dRow == -2) {
            return true;
        }
        return dRow == -1;
    }

    @Override
    public Set<Position> getAttackRoute(Moving moving) {
        if (!canAttack(moving)) {
            throw new IllegalArgumentException("이동 불가");
        }
        return Set.of();
    }

    private boolean canAttack(Moving moving) {
        if (moving.isNotMoved()) {
            return false;
        }
        Position currentPosition = moving.currentPosition();
        Position nextPosition = moving.nextPosition();
        int dRow = currentPosition.getRowIndex() - nextPosition.getRowIndex();
        int dColumn = currentPosition.getColumnIndex() - nextPosition.getColumnIndex();
        return isDiagonal(dColumn, dRow);
    }

    private boolean isDiagonal(int dColumn, int dRow) {
        if (Math.abs(dColumn) != 1) {
            return false;
        }
        if (Camp.BLACK == camp) {
            return dRow == -1;
        }
        return dRow == 1;
    }

    @Override
    public String toString() {
        return PieceType.from(this).getValue();
    }
}
