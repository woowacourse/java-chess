package chess.model.direction;

import static chess.model.Team.BLACK;

import chess.model.Team;
import chess.model.position.File;
import chess.model.position.Position;
import chess.model.position.Rank;
import java.util.List;

public enum Direction {

    EAST(0, 1),
    WEST(0, -1),
    SOUTH(1, 0),
    NORTH(-1, 0),
    NORTH_EAST(-1, 1),
    NORTH_WEST(-1, -1),
    SOUTH_EAST(1, 1),
    SOUTH_WEST(1, -1),

    EAST_TWICE_NORTH(-1, 2),
    EAST_TWICE_SOUTH(1, 2),
    WEST_TWICE_NORTH(-1, -2),
    WEST_TWICE_SOUTH(1, -2),
    SOUTH_TWICE_EAST(2, 1),
    SOUTH_TWICE_WEST(2, -1),
    NORTH_TWICE_EAST(-2, 1),
    NORTH_TWICE_WEST(-2, -1),
    ;

    private final int column;
    private final int row;

    Direction(int column, int row) {
        this.column = column;
        this.row = row;

    }

    public static List<Direction> ordinalDirection() {
        return List.of(EAST, WEST, SOUTH, NORTH, NORTH_EAST, NORTH_WEST, SOUTH_EAST, SOUTH_WEST);
    }

    public static List<Direction> knightDirection() {
        return List.of(EAST_TWICE_NORTH, EAST_TWICE_SOUTH, WEST_TWICE_NORTH, WEST_TWICE_SOUTH, SOUTH_TWICE_EAST,
                SOUTH_TWICE_WEST, NORTH_TWICE_EAST, NORTH_TWICE_WEST);
    }

    public static List<Direction> cardinalDirection() {
        return List.of(EAST, WEST, SOUTH, NORTH);
    }

    public static List<Direction> diagonalDirection() {
        return List.of(NORTH_EAST, NORTH_WEST, SOUTH_EAST, SOUTH_WEST);
    }

    public static List<Direction> pawnCatchDirection(Team team) {
        if (team == BLACK) {
            return List.of(SOUTH_EAST, SOUTH_WEST);
        }
        return List.of(NORTH_EAST, NORTH_WEST);
    }

    public static Direction pawnMoveDirection(Team team) {
        if (team == BLACK) {
            return SOUTH;
        }
        return NORTH;
    }

    public boolean MovableFrom(Rank rank, File file) {
        return rank.canAdd(column) && file.canAdd(row);
    }

    public Position createPositionFrom(Rank rank, File file) {
        return Position.of(rank.add(column), file.add(row));
    }
}
