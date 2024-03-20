package model.piece;

import java.util.Set;
import model.Camp;
import model.position.Position;
import model.position.Row;

public class Pawn extends Piece {

    public Pawn(final Camp camp) {
        super(camp);
    }

    @Override
    public Set<Position> getRoute(Position currentPosition, Position nextPosition) {
        if (canMovable(currentPosition, nextPosition)) {
            if (Math.abs(nextPosition.getRowIndex() - currentPosition.getRowIndex()) == 1) {
                return Set.of();
            }
            if (Camp.BLACK == camp) {
                return Set.of(new Position(Row.SIXTH, currentPosition.getColumn()));
            }
            return Set.of(new Position(Row.THIRD, currentPosition.getColumn()));
        }
        throw new IllegalArgumentException("이동 불가");
    }

    @Override
    protected boolean canMovable(Position currentPosition, Position nextPosition) {
        if (currentPosition.equals(nextPosition)) {
            return false;
        }
        int dRow = currentPosition.getRowIndex() - nextPosition.getRowIndex();
        int dColumn = currentPosition.getColumnIndex() - nextPosition.getColumnIndex();

        if (dColumn != 0) {
            return false;
        }

        if (Camp.BLACK == camp) {
            if (Row.SEVENTH.getIndex() == currentPosition.getRowIndex() && (dRow == -1 || dRow == -2)) {
                return true;
            }
            return dRow == -1;
        }

        if (Row.SECOND.getIndex() == currentPosition.getRowIndex() && (dRow == 1 || dRow == 2)) {
            return true;
        }
        return dRow == 1;
    }

    @Override
    public String toString() {
        if (camp == Camp.WHITE) {
            return "p";
        }
        return "P";
    }
}
