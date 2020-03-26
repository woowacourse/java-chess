package domain.pieces;

import domain.move.Direction;
import domain.point.Column;
import domain.point.Point;
import domain.point.Row;
import domain.team.Team;
import java.util.Map;

public class Queen extends Piece {
    private static final String INITIAL = "Q";

    public Queen(Team team) {
        super(INITIAL, team);
    }

    @Override
    public boolean isMovable(Map<Point, Piece> pieces, Point from, Point to) {
        for (Direction direction : Direction.getAllDirection()) {
            if (direction.isMovableUnlimited(from.getRowDistance(to), from.getColumnDistance(to))  && !isSameTeamToTarget(pieces, to)) {
                int row = from.getRowIndex() + direction.getRow();
                int column = from.getColumnIndex() + direction.getColumn();
                while (row < to.getRowIndex() && column < to.getColumnIndex()) {
                    if (pieces.get(new Point(Row.findRowType(row), Column.findColumnType(column))).team != Team.NONE) {
                        return false;
                    }
                    row += direction.getRow();
                    column += direction.getColumn();
                }
                return true;
            }
        }
        return false;
    }
}
