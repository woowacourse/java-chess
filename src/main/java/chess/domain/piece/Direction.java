package chess.domain.piece;

import chess.domain.position.Position;

import java.util.Arrays;
import java.util.function.BiPredicate;

public enum Direction {

    RIGHT(1, 0, (dx, dy) -> dx >= 0 && dy == 0),
    LEFT(-1, 0, (dx, dy) -> dx < 0 && dy == 0),
    UP(0, 1, (dx, dy) -> dx == 0 && dy >= 0),
    DOWN(0, -1, (dx, dy) -> dx == 0 && dy < 0),


    RIGHT_UP(1, 1, (dx, dy) -> isDiagonal(dx, dy) && dx > 0 && dy > 0),
    RIGHT_DOWN(1, -1, (dx, dy) -> isDiagonal(dx, dy) && dx > 0 && dy < 0),
    LEFT_UP(-1, 1, (dx, dy) -> isDiagonal(dx, dy) && dx < 0 && dy > 0),
    LEFT_DOWN(-1, -1, (dx, dy) -> isDiagonal(dx, dy) && dx < 0 && dy < 0),

    RIGHT_RIGHT_UP(2, 1, (dx, dy) -> isKnight(dx, dy) && dx == 2 && dy == 1),
    RIGHT_RIGHT_DOWN(2, -1, (dx, dy) -> isKnight(dx, dy) && dx == 2 && dy == -1),
    RIGHT_UP_UP(1, 2, (dx, dy) -> isKnight(dx, dy) && dx == 1 && dy == 2),
    RIGHT_DOWN_DOWN(1, -2, (dx, dy) -> isKnight(dx, dy) && dx == 1 && dy == -2),
    LEFT_LEFT_UP(-2, 1, (dx, dy) -> isKnight(dx, dy) && dx == -2 && dy == 1),
    LEFT_LEFT_DOWN(-2, -1, (dx, dy) -> isKnight(dx, dy) && dx == -2 && dy == -1),
    LEFT_UP_UP(-1, 2, (dx, dy) -> isKnight(dx, dy) && dx == -1 && dy == 2),
    LEFT_DOWN_DOWN(-1, -2, (dx, dy) -> isKnight(dx, dy) && dx == -1 && dy == -2);

    private final int dx;

    private final int dy;
    private final BiPredicate<Integer, Integer> isMatch;
    Direction(final int dx, final int dy, final BiPredicate<Integer, Integer> isMatch) {
        this.dx = dx;
        this.dy = dy;
        this.isMatch = isMatch;
    }

    private static boolean isDiagonal(final Integer dx, final Integer dy) {
        return Math.abs(dx) == Math.abs(dy);
    }

    private static boolean isKnight(final int dx, final int dy) {
        return Math.abs(dx * dy) == 2;
    }

    public static Direction findByPosition(final Position source, final Position target) {
        final int dx = diffFile(source, target);
        final int dy = diffRank(source, target);

        return Arrays.stream(Direction.values())
                .filter(direction -> direction.isMatch.test(dx, dy))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("이동할 수 없는 방향입니다"));
    }

    private static int diffFile(final Position source, final Position target) {
        return target.file() - source.file();
    }

    private static int diffRank(final Position source, final Position target) {
        return target.rank() - source.rank();
    }

    public int getDx() {
        return dx;
    }

    public int getDy() {
        return dy;
    }
}
