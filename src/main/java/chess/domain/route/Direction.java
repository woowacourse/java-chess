package chess.domain.route;

import chess.domain.Team;
import chess.domain.piece.PieceType;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;

public enum Direction {
    NORTH(0, 1),
    NORTH_EAST(1, 1),
    EAST(1, 0),
    SOUTH_EAST(1, -1),
    SOUTH(0, -1),
    SOUTH_WEST(-1, -1),
    WEST(-1, 0),
    NORTH_WEST(-1, 1),

    NORTH_NORTH_EAST(1, 2),
    NORTH_NORTH_WEST(-1, 2),
    SOUTH_SOUTH_EAST(1, -2),
    SOUTH_SOUTH_WEST(-1, -2),
    EAST_EAST_NORTH(2, 1),
    EAST_EAST_SOUTH(2, -1),
    WEST_WEST_NORTH(-2, 1),
    WEST_WEST_SOUTH(-2, -1);

    private static final Map<PieceType, Function<Team, List<Direction>>> DIRECTIONS_CACHE = new HashMap<>();

    static {
        DIRECTIONS_CACHE.put(PieceType.PAWN, Direction::PawnDirectionOf);
        DIRECTIONS_CACHE.put(PieceType.KNIGHT, team -> knightDirection());
        DIRECTIONS_CACHE.put(PieceType.BISHOP, team -> diagonalDirection());
        DIRECTIONS_CACHE.put(PieceType.ROOK, team -> linearDirection());
        DIRECTIONS_CACHE.put(PieceType.QUEEN, team -> everyDirection());
        DIRECTIONS_CACHE.put(PieceType.KING, team -> everyDirection());
    }

    private final int xDegree;
    private final int yDegree;

    Direction(int xDegree, int yDegree) {
        this.xDegree = xDegree;
        this.yDegree = yDegree;
    }

    public int getXDegree() {
        return xDegree;
    }

    public int getYDegree() {
        return yDegree;
    }

    public static List<Direction> directionsFor(PieceType pieceType, Team team) {
        return DIRECTIONS_CACHE.get(pieceType).apply(team);
    }

    private static List<Direction> linearDirection() {
        return Arrays.asList(NORTH, EAST, SOUTH, WEST);
    }

    private static List<Direction> diagonalDirection() {
        return Arrays.asList(NORTH_EAST, SOUTH_EAST, SOUTH_WEST, NORTH_WEST);
    }

    private static List<Direction> everyDirection() {
        return Arrays.asList(NORTH, EAST, SOUTH, WEST, NORTH_EAST, SOUTH_EAST, SOUTH_WEST, NORTH_WEST);
    }

    private static List<Direction> knightDirection() {
        return Arrays.asList(NORTH_NORTH_EAST, NORTH_NORTH_WEST, SOUTH_SOUTH_EAST, SOUTH_SOUTH_WEST, EAST_EAST_NORTH, EAST_EAST_SOUTH, WEST_WEST_NORTH, WEST_WEST_SOUTH);
    }

    private static List<Direction> PawnDirectionOf(Team team) {
        if (team.isBlack()) {
            return blackPawnDirection();
        }

        return whitePawnDirection();
    }

    private static List<Direction> whitePawnDirection() {
        return Arrays.asList(NORTH, NORTH_EAST, NORTH_WEST);
    }

    private static List<Direction> blackPawnDirection() {
        return Arrays.asList(SOUTH, SOUTH_EAST, SOUTH_WEST);
    }
}
