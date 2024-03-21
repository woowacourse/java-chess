package util;

import domain.piece.attribute.point.Direction;
import domain.piece.attribute.point.Point;


public class DirectionUtil {
    private DirectionUtil() {
        throw new UnsupportedOperationException("생성할 수 없습니다.");
    }

    public static Direction determineDirection(final Point standard, final Point comparison) {
        if (isUp(standard, comparison)) {
            return Direction.UP;
        }
        if (isDown(standard, comparison)) {
            return Direction.DOWN;
        }
        if (isLeft(standard, comparison)) {
            return Direction.LEFT;
        }
        if (isRight(standard, comparison)) {
            return Direction.RIGHT;
        }
        if (isUpRight(standard, comparison)) {
            return Direction.UP_RIGHT;
        }
        if (isUpLeft(standard, comparison)) {
            return Direction.UP_LEFT;
        }
        if (isDownRight(standard, comparison)) {
            return Direction.DOWN_RIGHT;
        }
        if (isDownLeft(standard, comparison)) {
            return Direction.DOWN_LEFT;
        }

        throw new IllegalArgumentException();
    }

    private static boolean isUp(final Point standard, final Point comparison) {
        return standard.getFileIndex() == comparison.getFileIndex()
                && standard.getRankIndex() < comparison.getRankIndex();
    }

    private static boolean isDown(final Point standard, final Point comparison) {
        return standard.getFileIndex() == comparison.getFileIndex()
                && standard.getRankIndex() > comparison.getRankIndex();
    }

    private static boolean isLeft(final Point standard, final Point comparison) {
        return standard.getFileIndex() > comparison.getFileIndex()
                && standard.getRankIndex() == comparison.getRankIndex();
    }

    private static boolean isRight(final Point standard, final Point comparison) {
        return standard.getFileIndex() < comparison.getFileIndex()
                && standard.getRankIndex() == comparison.getRankIndex();
    }

    private static boolean isUpRight(final Point standard, final Point comparison) {
        if (!isDiagonal(standard, comparison)) {
            return false;
        }
        return standard.getFileIndex() < comparison.getFileIndex()
                && standard.getRankIndex() < comparison.getRankIndex();
    }

    private static boolean isUpLeft(final Point standard, final Point comparison) {
        if (!isDiagonal(standard, comparison)) {
            return false;
        }
        return standard.getFileIndex() > comparison.getFileIndex()
                && standard.getRankIndex() < comparison.getRankIndex();
    }

    private static boolean isDownRight(final Point standard, final Point comparison) {
        if (!isDiagonal(standard, comparison)) {
            return false;
        }
        return standard.getFileIndex() < comparison.getFileIndex()
                && standard.getRankIndex() > comparison.getRankIndex();
    }

    private static boolean isDownLeft(final Point standard, final Point comparison) {
        if (!isDiagonal(standard, comparison)) {
            return false;
        }
        return standard.getFileIndex() > comparison.getFileIndex()
                && standard.getRankIndex() > comparison.getRankIndex();
    }

    private static boolean isDiagonal(final Point standard, final Point comparison) {
        return (Math.abs(standard.getFileIndex() - comparison.getFileIndex()))
                == (Math.abs(standard.getRankIndex() - comparison.getRankIndex()));
    }

}
