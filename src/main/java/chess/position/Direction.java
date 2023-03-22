package chess.position;

import java.util.Arrays;
import java.util.List;

public enum Direction {
    EAST(Position.initPosition(1, 0)),
    NORTH(Position.initPosition(0, 1)),
    NORTH_NORTH(Position.initPosition(0, 2)),
    WEST(Position.initPosition(-1, 0)),
    SOUTH(Position.initPosition(0, -1)),
    SOUTH_SOUTH(Position.initPosition(0, -2)),
    EAST_NORTH(Position.initPosition(1, 1)),
    WEST_NORTH(Position.initPosition(-1, 1)),
    EAST_SOUTH(Position.initPosition(1, -1)),
    WEST_SOUTH(Position.initPosition(-1, -1)),
    KNIGHT_EAST_NORTH(Position.initPosition(2, 1)),
    KNIGHT_NORTH_EAST(Position.initPosition(1, 2)),
    KNIGHT_NORTH_WEST(Position.initPosition(-1, 2)),
    KNIGHT_WEST_NORTH(Position.initPosition(-2, 1)),
    KNIGHT_WEST_SOUTH(Position.initPosition(-2, -1)),
    KNIGHT_SOUTH_WEST(Position.initPosition(-1, -2)),
    KNIGHT_SOUTH_EAST(Position.initPosition(1, -2)),
    KNIGHT_EAST_SOUTH(Position.initPosition(2, -1));

    private final Position direction;


    Direction(Position direction) {
        this.direction = direction;
    }

    public static final List<Position> WHITE_PAWN_MOVE_DIRECTION = List.of(NORTH.direction);

    public static final List<Position> BLACK_PAWN_MOVE_DIRECTION = List.of(SOUTH.direction);

    public static final List<Position> WHITE_PAWN_FIRST_MOVE_DIRECTION = List.of(NORTH_NORTH.direction);

    public static final List<Position> BLACK_PAWN_FIRST_MOVE_DIRECTION = List.of(SOUTH_SOUTH.direction);

    public static final List<Position> WHITE_PAWN_CATCH_DIRECTION
            = Arrays.asList(EAST_NORTH.direction, WEST_NORTH.direction);

    public static final List<Position> BLACK_PAWN_CATCH_DIRECTION
            = Arrays.asList(EAST_SOUTH.direction, WEST_SOUTH.direction);

    public static final List<Position> CROSS_DIRECTION
            = Arrays.asList(EAST.direction, NORTH.direction, WEST.direction, SOUTH.direction);

    public static final List<Position> DIAGONAL_DIRECTION
            = Arrays.asList(EAST_NORTH.direction, WEST_NORTH.direction, EAST_SOUTH.direction, WEST_SOUTH.direction);

    public static final List<Position> KNIGHT_DIRECTION = Arrays.asList(
            KNIGHT_EAST_NORTH.direction, KNIGHT_NORTH_EAST.direction,
            KNIGHT_NORTH_WEST.direction, KNIGHT_WEST_NORTH.direction,
            KNIGHT_WEST_SOUTH.direction, KNIGHT_SOUTH_WEST.direction,
            KNIGHT_SOUTH_EAST.direction, KNIGHT_EAST_SOUTH.direction);

}
