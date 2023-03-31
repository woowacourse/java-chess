package chess.domain.direction;

import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public enum Direction {
    EAST(1, 0),
    WEST(-1, 0),
    SOUTH(0, -1),
    NORTH(0, 1),
    NORTH_EAST(1, 1),
    SOUTH_EAST(1, -1),
    SOUTH_WEST(-1, -1),
    NORTH_WEST(-1, 1),
    EAST_EAST_NORTH(2, 1),
    EAST_EAST_SOUTH(2, -1),
    SOUTH_SOUTH_EAST(1, -2),
    SOUTH_SOUTH_WEST(-1, -2),
    WEST_WEST_SOUTH(-2, -1),
    WEST_WEST_NORTH(-2, 1),
    NORTH_NORTH_WEST(-1, 2),
    NORTH_NORTH_EAST(1, 2),
    NORTH_NORTH(0, 2),
    SOUTH_SOUTH(0, -2);
    
    private final int columnDirection;
    private final int rowDirection;
    
    Direction(int columnDirection, int rowDirection) {
        this.columnDirection = columnDirection;
        this.rowDirection = rowDirection;
    }
    
    private static Set<Direction> ofStraight() {
        return Set.of(EAST, WEST, SOUTH, NORTH);
    }
    
    private static Set<Direction> ofDiagonal() {
        return Set.of(NORTH_EAST, SOUTH_EAST, SOUTH_WEST, NORTH_WEST);
    }
    
    public static Set<Direction> ofKing() {
        return Stream.concat(ofStraight().stream(), ofDiagonal().stream())
                .collect(Collectors.toUnmodifiableSet());
    }
    
    public static Set<Direction> ofKnight() {
        return Set.of(EAST_EAST_NORTH, EAST_EAST_SOUTH, SOUTH_SOUTH_EAST, SOUTH_SOUTH_WEST,
                WEST_WEST_SOUTH, WEST_WEST_NORTH, NORTH_NORTH_WEST, NORTH_NORTH_EAST);
    }
    
    public static Set<Direction> ofQueen() {
        return Stream.concat(ofStraight().stream(), ofDiagonal().stream())
                .collect(Collectors.toUnmodifiableSet());
    }
    
    public static Set<Direction> ofRook() {
        return ofStraight();
    }
    
    public static Set<Direction> ofBishop() {
        return ofDiagonal();
    }
    
    public static Set<Direction> ofBlackPawn() {
        return Set.of(SOUTH, SOUTH_EAST, SOUTH_WEST);
    }
    
    public static Set<Direction> ofWhitePawn() {
        return Set.of(NORTH, NORTH_EAST, NORTH_WEST);
    }
    
    public static Set<Direction> ofBlackStartPawn() {
        return Stream.concat(ofBlackPawn().stream(), Set.of(SOUTH_SOUTH).stream())
                .collect(Collectors.toUnmodifiableSet());
    }
    
    public static Set<Direction> ofWhiteStartPawn() {
        return Stream.concat(ofWhitePawn().stream(), Set.of(NORTH_NORTH).stream())
                .collect(Collectors.toUnmodifiableSet());
    }
    
    public boolean isAbsoluteRowDirectionTwo() {
        return this == SOUTH_SOUTH || this == NORTH_NORTH;
    }
    
    public boolean isDiagonal() {
        return ofDiagonal().contains(this);
    }
    
    public boolean isStraight() {
        return ofStraight().contains(this);
    }
    
    public int columnDirection() {
        return columnDirection;
    }
    
    public int rowDirection() {
        return rowDirection;
    }
}
