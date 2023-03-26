package chess.direction;

import chess.domain.Position;
import chess.domain.piece.Piece;
import chess.domain.piece.Rook;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public enum Direction {
    TOP(0, 1),
    BOTTOM(0, -1),
    LEFT(-1, 0),
    RIGHT(1, 0),
    TOP_LEFT(-1, 1),
    TOP_RIGHT(1, 1),
    BOTTOM_LEFT(-1, -1),
    BOTTOM_RIGHT(1, -1),
    KNIGHT_TOP_LEFT(-1, 2),
    KNIGHT_TOP_RIGHT(1, 2),
    KNIGHT_LEFT_TOP(-2, 1),
    KNIGHT_LEFT_BOTTOM(-2, -1),
    KNIGHT_RIGHT_TOP(2, 1),
    KNIGHT_RIGHT_BOTTOM(2, -1),
    KNIGHT_BOTTOM_LEFT(-1, -2),
    KNIGHT_BOTTOM_RIGHT(1, -2);

    private static final List<Direction> BLACK_PAWN_DIRECTION = List.of(BOTTOM, BOTTOM_LEFT, BOTTOM_RIGHT);
    private static final List<Direction> WHITE_PAWN_DIRECTION = List.of(TOP, TOP_LEFT, TOP_RIGHT);
    private static final List<Direction> KNIGHT_DIRECTION = List.of(KNIGHT_TOP_LEFT, KNIGHT_TOP_RIGHT, KNIGHT_LEFT_TOP, KNIGHT_LEFT_BOTTOM,
            KNIGHT_RIGHT_TOP, KNIGHT_RIGHT_BOTTOM, KNIGHT_BOTTOM_LEFT, KNIGHT_BOTTOM_RIGHT);
    private static final List<Direction> UP_DOWN_LEFT_RIGHT_DIRECTION = List.of(TOP, BOTTOM, LEFT, RIGHT);
    private static final List<Direction> DIAGONAL_DIRECTION = List.of(TOP_LEFT, TOP_RIGHT, BOTTOM_LEFT, BOTTOM_RIGHT);
    
    private static final String NO_DIRECTION_ERROR_GUIDE_MESSAGE = "일치하는 Direction 값이 없습니다";

    private final int x;
    private final int y;

    Direction(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public static Direction findDirectionByGap(Position start, Position end, Piece piece) {
        return Arrays.stream(Direction.values())
                .filter(direction -> {
                    return piece.findDirection(direction, start, end, piece);
                })
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException(NO_DIRECTION_ERROR_GUIDE_MESSAGE));
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public static List<Direction> getDiagonalDirection() {
        return DIAGONAL_DIRECTION;
    }

    public static List<Direction> getKingDirection() {
        List<Direction> kingDirection = new ArrayList<>();
        kingDirection.addAll(UP_DOWN_LEFT_RIGHT_DIRECTION);
        kingDirection.addAll(DIAGONAL_DIRECTION);
        return kingDirection;
    }

    public static List<Direction> getKnightDirection() {
        return KNIGHT_DIRECTION;
    }

    public static List<Direction> getBlackPawnDirection() {
        return BLACK_PAWN_DIRECTION;
    }

    public static List<Direction> getQueenDirection() {
        List<Direction> queenDirection = new ArrayList<>();
        queenDirection.addAll(UP_DOWN_LEFT_RIGHT_DIRECTION);
        queenDirection.addAll(DIAGONAL_DIRECTION);
        return queenDirection;
    }

    public static List<Direction> getWhitePawnDirection() {
        return WHITE_PAWN_DIRECTION;
    }

    public static List<Direction> getRookDirection() {
        return UP_DOWN_LEFT_RIGHT_DIRECTION;
    }
}
