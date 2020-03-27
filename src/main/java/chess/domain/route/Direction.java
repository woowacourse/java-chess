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
    NORTHEAST(1, 1),
    EAST(1, 0),
    SOUTHEAST(1, -1),
    SOUTH(0, -1),
    SOUTHWEST(-1, -1),
    WEST(-1, 0),
    NORTHWEST(-1, 1),

    NNE(1, 2),
    NNW(-1, 2),
    SSE(1, -2),
    SSW(-1, -2),
    EEN(2, 1),
    EES(2, -1),
    WWN(-2, 1),
    WWS(-2, -1);

    private static final Map<PieceType, Function<Team, List<Direction>>> DIRECTIONS_CACHE = new HashMap<>();

    static {
        DIRECTIONS_CACHE.put(PieceType.PAWN, Direction::PawnDirectionOf);
        DIRECTIONS_CACHE.put(PieceType.KNIGHT, team -> knightDirection());
        DIRECTIONS_CACHE.put(PieceType.BISHOP, team -> diagonalDirection());
        DIRECTIONS_CACHE.put(PieceType.ROOK, team -> linearDirection());
        DIRECTIONS_CACHE.put(PieceType.QUEEN, team -> everyDirection());
        DIRECTIONS_CACHE.put(PieceType.KING, team -> everyDirection());
    }

    private int xDegree;
    private int yDegree;

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
        return Arrays.asList(NORTHEAST, SOUTHEAST, SOUTHWEST, NORTHWEST);
    }

    private static List<Direction> everyDirection() {
        return Arrays.asList(NORTH, EAST, SOUTH, WEST, NORTHEAST, SOUTHEAST, SOUTHWEST, NORTHWEST);
    }

    private static List<Direction> knightDirection() {
        return Arrays.asList(NNE, NNW, SSE, SSW, EEN, EES, WWN, WWS);
    }

    private static List<Direction> PawnDirectionOf(Team team) {
        if (team.isBlack()) {
            return blackPawnDirection();
        }

        return whitePawnDirection();
    }

    private static List<Direction> whitePawnDirection() {
        return Arrays.asList(NORTH, NORTHEAST, NORTHWEST);
    }

    private static List<Direction> blackPawnDirection() {
        return Arrays.asList(SOUTH, SOUTHEAST, SOUTHWEST);
    }
}
