package piece;

import model.Camp;
import point.Position;
import point.Row;

public class Pawn extends Piece {

    public Pawn(final Camp camp) {
        super(camp);
    }

    @Override
    public boolean canMovable(Position currentPosition, Position nextPosition) {
        int dRow = currentPosition.getRow() - nextPosition.getRow();
        int dColumn = currentPosition.getColumn() - nextPosition.getColumn();

        if (dColumn != 0) {
            return false;
        }

        if (Camp.BLACK == camp) {
            if (Row.SEVENTH.getIndex() == currentPosition.getRow() && (dRow == -1 || dRow == -2)) {
                return true;
            }
            return dRow == -1;
        }

        if (Row.SECOND.getIndex() == currentPosition.getRow() && (dRow == 1 || dRow == 2)) {
            return true;
        }
        return dRow == 1;
    }

    @Override
    public void move(Position targetPosition) {

    }

    @Override
    public String toString() {
        if (camp == Camp.WHITE) {
            return "p";
        }
        return "P";
    }
}
