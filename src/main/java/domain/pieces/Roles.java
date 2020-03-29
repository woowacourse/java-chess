package domain.pieces;

import domain.move.Direction;
import domain.point.Column;
import domain.point.Point;
import domain.point.Row;
import domain.team.Team;
import java.util.Map;

public class Roles {
    public static boolean isMovableLimitedCase(Direction direction, Map<Point, Piece> pieces, Point from, Point to) {
        return direction.isMovableLimited(from.getRowDistance(to), from.getColumnDistance(to));
    }

    public static boolean isMovableUnlimitedCase(Direction direction, Map<Point, Piece> pieces, Point from, Point to) {
        if (direction.isMovableUnlimited(from.getRowDistance(to), from.getColumnDistance(to))) {
            return isNotObstacle(direction, pieces, from, to);
        }
        return false;
    }

    private static boolean isNotObstacle(Direction direction, Map<Point, Piece> pieces, Point from, Point to) {
        int row = from.getRowIndex() + direction.getRow();
        int column = from.getColumnIndex() + direction.getColumn();
        while (row < to.getRowIndex() && column < to.getColumnIndex()) {
            if (pieces.get(Point.of(Row.findRowType(row), Column.findColumnType(column))).team != Team.NONE) {
                return false;
            }
            row += direction.getRow();
            column += direction.getColumn();
        }
        return true;
    }

    public static boolean isLinearOrDiagonalWhiteTeam(Direction direction, Map<Point, Piece> pieces, Point to) {
        if (direction != Direction.TOP && pieces.get(to).team == Team.BLACK) {
            return true;
        }
        return direction == Direction.TOP && pieces.get(to).team == Team.NONE;
    }

    public static boolean isLinearOrDiagonalBlackTeam(Direction direction, Map<Point, Piece> pieces, Point to) {
        if (direction != Direction.DOWN && pieces.get(to).team == Team.WHITE) {
            return true;
        }
        return direction == Direction.DOWN && pieces.get(to).team == Team.NONE;
    }
}
