package chess2.domain2.board2.position;

import java.util.function.BiPredicate;

public enum Direction {

    UP((fileDiff, rankDiff) -> isUpward(rankDiff) && isVertical(fileDiff)),
    UP_RIGHT((fileDiff, rankDiff) -> isUpward(rankDiff) && isRight(fileDiff)),
    RIGHT((fileDiff, rankDiff) -> isHorizontal(rankDiff) && isRight(fileDiff)),
    DOWN_RIGHT((fileDiff, rankDiff) -> isDownward(rankDiff) && isRight(fileDiff)),
    DOWN((fileDiff, rankDiff) -> isDownward(rankDiff) && isVertical(fileDiff)),
    DOWN_LEFT((fileDiff, rankDiff) -> isDownward(rankDiff) && isLeft(fileDiff)),
    LEFT((fileDiff, rankDiff) -> isHorizontal(rankDiff) && isLeft(fileDiff)),
    UP_LEFT((fileDiff, rankDiff) -> isUpward(rankDiff) && isLeft(fileDiff));

    private final BiPredicate<Integer, Integer> directionChecker;

    Direction(BiPredicate<Integer, Integer> directionChecker) {
        this.directionChecker = directionChecker;
    }

    public boolean checkByPositionDifference(int fileDiff, int rankDiff) {
        return directionChecker.test(fileDiff, rankDiff);
    }

    private static boolean isUpward(Integer rankDiff) {
        return rankDiff > 0;
    }

    private static boolean isDownward(Integer rankDiff) {
        return rankDiff < 0;
    }

    private static boolean isHorizontal(Integer rankDiff) {
        return rankDiff == 0;
    }

    private static boolean isRight(Integer fileDiff) {
        return fileDiff > 0;
    }

    private static boolean isLeft(Integer fileDiff) {
        return fileDiff < 0;
    }

    private static boolean isVertical(Integer fileDiff) {
        return fileDiff == 0;
    }
}
