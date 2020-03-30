package chess.domain.route;

import chess.domain.Team;
import chess.domain.piece.PieceType;

import java.util.Arrays;
import java.util.List;
import java.util.function.Function;

import static chess.domain.route.Direction.*;

public enum Directions {
    WHITE_PAWN_DIRECTIONS(PieceType.PAWN, team -> pawnDirection(team)),
    KNIGHT_DIRECTIONS(PieceType.KNIGHT, team -> knightDirection()),
    BISHOP_DIRECTIONS(PieceType.BISHOP, team -> diagonalDirection()),
    ROOK_DIRECTIONS(PieceType.ROOK, team -> linearDirection()),
    QUEEN_DIRECTIONS(PieceType.QUEEN, team -> everyDirection()),
    KING_DIRECTIONS(PieceType.KING, team -> everyDirection());

    private final PieceType pieceType;
    private final Function<Team, List<Direction>> conditionByTeam;

    Directions(PieceType pieceType, Function<Team, List<Direction>> directions) {
        this.pieceType = pieceType;
        this.conditionByTeam = directions;
    }

    public static List<Direction> of(PieceType pieceType, Team team) {
        return Arrays.stream(values())
                .filter(directions -> directions.pieceType == pieceType)
                .map(directions -> directions.conditionByTeam.apply(team))
                .findFirst()
                .orElseThrow(() -> new AssertionError("해당하는 Directions가 존재하지 않습니다."));
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

    private static List<Direction> pawnDirection(Team team) {
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