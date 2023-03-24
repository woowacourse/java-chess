package chess.domain.piece;

import chess.domain.board.File;
import chess.domain.board.Rank;

import java.util.function.BiFunction;

public enum DirectionVector {

    NORTH(0, 1, (x, y) -> x == 0 && y > 0),
    SOUTH(0, -1, (x, y) -> x == 0 && y < 0),
    WEST(-1, 0, (x, y) -> x < 0 && y == 0),
    EAST(1, 0, (x, y) -> x > 0 && y == 0),
    SOUTHEAST(1, -1, (x, y) -> x > 0 && y < 0 && Math.abs(x) == Math.abs(y)),
    SOUTHWEST(-1, -1, (x, y) -> x < 0 && y < 0 && Math.abs(x) == Math.abs(y)),
    NORTHEAST(1, 1, (x, y) -> x > 0 && y > 0 && Math.abs(x) == Math.abs(y)),
    NORTHWEST(-1, 1, (x, y) -> x < 0 && y > 0 && Math.abs(x) == Math.abs(y));

    private final int x;
    private final int y;
    private final BiFunction<Integer, Integer, Boolean> isCorrectMovingDirection;

    DirectionVector(final int x, final int y, final BiFunction<Integer, Integer, Boolean> isCorrectMovingDirection) {
        this.x = x;
        this.y = y;
        this.isCorrectMovingDirection = isCorrectMovingDirection;
    }

    public boolean isOnMyWay(final int distanceX, final int distanceY) {
        return isCorrectMovingDirection.apply(distanceX, distanceY);
    }

    public File next(final File file) {
        if (x == 1) {
            return file.next();
        }
        if (x == -1) {
            return file.prev();
        }
        return file;
    }

    public Rank next(final Rank rank) {
        if (y == 1) {
            return rank.next();
        }
        if (y == -1) {
            return rank.prev();
        }
        return rank;
    }
}
