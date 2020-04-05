package domain.pieces;

import domain.move.Direction;
import domain.point.Column;
import domain.point.MovePoint;
import domain.point.Point;
import domain.point.Row;
import domain.team.Team;
import java.util.Map;

public class Roles {

    public static boolean isMovableLimitedCase(Direction direction, MovePoint movePoint) {
        return direction
            .isMovableLimited(movePoint.getRowDistance(), movePoint.getColumnDistance());
    }

    public static boolean isMovableUnlimitedCase(Direction direction, Map<Point, Piece> pieces,
        MovePoint movePoint) {
        if (direction
            .isMovableUnlimited(movePoint.getRowDistance(), movePoint.getColumnDistance())) {
            return isNotObstacle(direction, pieces, movePoint);
        }
        return false;
    }

    private static boolean isNotObstacle(Direction direction, Map<Point, Piece> pieces,
        MovePoint movePoint) {
        int row = movePoint.getFromRowIndex() + direction.getRow();
        int column = movePoint.getFromColumnIndex() + direction.getColumn();
        boolean isNotObstacle = true;
        while (movePoint.canMovePoint(row, column) && isNotObstacle) {
            isNotObstacle = isNotObstaclePoint(pieces, row, column);
            row += direction.getRow();
            column += direction.getColumn();
        }
        return isNotObstacle;
    }

    private static boolean isNotObstaclePoint(Map<Point, Piece> pieces, int row, int column) {
        Point point = Point.of(Row.findRowType(row), Column.findColumnType(column));
        return pieces.get(point).isNoneTeam();
    }

    public static boolean isLinearOrDiagonalWhiteTeam(Direction direction, Map<Point, Piece> pieces,
        Point to) {
        if (direction != Direction.TOP && pieces.get(to).team == Team.BLACK) {
            return true;
        }
        return direction == Direction.TOP && pieces.get(to).team == Team.NONE;
    }

    public static boolean isLinearOrDiagonalBlackTeam(Direction direction, Map<Point, Piece> pieces,
        Point to) {
        if (direction != Direction.DOWN && pieces.get(to).team == Team.WHITE) {
            return true;
        }
        return direction == Direction.DOWN && pieces.get(to).team == Team.NONE;
    }
}
