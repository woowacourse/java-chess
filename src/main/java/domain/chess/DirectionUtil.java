package domain.chess;

public class DirectionUtil {
    private DirectionUtil() {
        throw new UnsupportedOperationException("생성할 수 없습니다.");
    }

    public static Direction determineDirection(final Point standard, final Point comparison) {
        if (isVertical(standard, comparison)) {
            return verticalCase(standard, comparison);
        }
        if (isHorizontal(standard, comparison)) {
            return horizontalCase(standard, comparison);
        }
        if (isDiagonal(standard, comparison)) {
            return diagonalCase(standard, comparison);
        }
        throw new IllegalArgumentException(String.format("%s 에서 %s는 갈 수 없습니다", standard, comparison));
    }

    private static boolean isVertical(final Point standard, final Point comparison) {
        return standard.getFileIndex() == comparison.getFileIndex();
    }

    private static Direction verticalCase(final Point standard, final Point comparison) {
        if (isUp(standard, comparison)) {
            return Direction.UP;
        }
        return Direction.DOWN;
    }

    private static boolean isHorizontal(final Point standard, final Point comparison) {
        return standard.getRankIndex() == comparison.getRankIndex();
    }

    private static Direction horizontalCase(final Point standard, final Point comparison) {
        if (isLeft(standard, comparison)) {
            return Direction.LEFT;
        }
        return Direction.RIGHT;
    }

    private static boolean isDiagonal(final Point standard, final Point comparison) {
        return (Math.abs(standard.getFileIndex() - comparison.getFileIndex()))
                == (Math.abs(standard.getRankIndex() - comparison.getRankIndex()));
    }

    private static Direction diagonalCase(final Point standard, final Point comparison) {
        if (isUp(standard, comparison) && !isLeft(standard, comparison)) {
            return Direction.UP_RIGHT;
        }
        if (isUp(standard, comparison) && isLeft(standard, comparison)) {
            return Direction.UP_LEFT;
        }
        if (!isUp(standard, comparison) && isLeft(standard, comparison)) {
            return Direction.DOWN_LEFT;
        }
        return Direction.DOWN_RIGHT;
    }

    private static boolean isLeft(final Point standard, final Point comparison) {
        return standard.getFileIndex() > comparison.getFileIndex();
    }

    private static boolean isUp(final Point standard, final Point comparison) {
        return standard.getRankIndex() < comparison.getRankIndex();
    }
}
