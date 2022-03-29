package chess.strategy;

@FunctionalInterface
public interface DirectionChecker {

    boolean checkCorrectDirection(int fileDiff, int rankDiff);
}
