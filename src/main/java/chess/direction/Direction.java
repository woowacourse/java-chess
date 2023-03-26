package chess.direction;

import chess.domain.Position;
import chess.domain.piece.Knight;
import chess.domain.piece.Piece;

import java.util.Arrays;

import static chess.domain.piece.Pawn.isDiagonal;

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
}
