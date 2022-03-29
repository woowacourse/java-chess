package chess.domain.board.position;

import chess.strategy.DirectionChecker;

public enum Direction {

    UP((eastWardDiff, northWardDiff) -> isUpward(northWardDiff) && isVertical(eastWardDiff)),
    UP_RIGHT((eastWardDiff, northWardDiff) -> isUpward(northWardDiff) && isRight(eastWardDiff)),
    RIGHT((eastWardDiff, northWardDiff) -> isHorizontal(northWardDiff) && isRight(eastWardDiff)),
    DOWN_RIGHT((eastWardDiff, northWardDiff) -> isDownward(northWardDiff) && isRight(eastWardDiff)),
    DOWN((eastWardDiff, northWardDiff) -> isDownward(northWardDiff) && isVertical(eastWardDiff)),
    DOWN_LEFT((eastWardDiff, northWardDiff) -> isDownward(northWardDiff) && isLeft(eastWardDiff)),
    LEFT((eastWardDiff, northWardDiff) -> isHorizontal(northWardDiff) && isLeft(eastWardDiff)),
    UP_LEFT((eastWardDiff, northWardDiff) -> isUpward(northWardDiff) && isLeft(eastWardDiff));

    private final DirectionChecker directionChecker;

    Direction(DirectionChecker directionChecker) {
        this.directionChecker = directionChecker;
    }

    public boolean checkByPositionDifference(int eastWardDiff, int northWardDiff) {
        return directionChecker.checkCorrectDirection(eastWardDiff, northWardDiff);
    }

    private static boolean isUpward(Integer northWardDiff) {
        return northWardDiff > 0;
    }

    private static boolean isDownward(Integer northWardDiff) {
        return northWardDiff < 0;
    }

    private static boolean isHorizontal(Integer northWardDiff) {
        return northWardDiff == 0;
    }

    private static boolean isRight(Integer eastWardDiff) {
        return eastWardDiff > 0;
    }

    private static boolean isLeft(Integer eastWardDiff) {
        return eastWardDiff < 0;
    }

    private static boolean isVertical(Integer eastWardDiff) {
        return eastWardDiff == 0;
    }
}
