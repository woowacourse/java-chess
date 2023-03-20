package chess.domain;

import java.util.function.BiFunction;

public enum MoveStrategy {

    ROOK((fileDiff, rankDiff) -> fileDiff == 0 || rankDiff == 0),
    BISHOP((fileDiff, rankDiff) -> Math.abs(fileDiff) == Math.abs(rankDiff)),
    KNIGHT((fileDiff, rankDiff) -> Math.abs(fileDiff * rankDiff) == 2),
    KING((fileDiff, rankDiff) -> Math.abs(fileDiff) <= 1 && Math.abs(rankDiff) <= 1),
    QUEEN((fileDiff, rankDiff) -> (fileDiff == 0 || rankDiff == 0)
            || (Math.abs(fileDiff) == Math.abs(rankDiff))),
    BLACK_PAWN_FIRST((fileDiff, rankDiff) -> fileDiff == 0 && rankDiff >= -2 && rankDiff < 0),
    BLACK_PAWN_CROSS((fileDiff, rankDiff) -> rankDiff == -1 && Math.abs(fileDiff) == 1),
    BLACK_PAWN_STRAIGHT((fileDiff, rankDiff) -> rankDiff == -1 && fileDiff == 0),
    WHITE_PAWN_FIRST((fileDiff, rankDiff) -> fileDiff == 0 && rankDiff <= 2 && rankDiff > 0),
    WHITE_PAWN_CROSS((fileDiff, rankDiff) -> rankDiff == 1 && Math.abs(fileDiff) == 1),
    WHITE_PAWN_STRAIGHT((fileDiff, rankDiff) -> rankDiff == 1 && fileDiff == 0);

    private final BiFunction<Integer, Integer, Boolean> moveStrategy;

    MoveStrategy(BiFunction<Integer, Integer, Boolean> moveStrategy) {
        this.moveStrategy = moveStrategy;
    }

    public boolean isMovable(Position source, Position target) {
        int fileDiff = source.fileDiff(target);
        int rankDiff = source.rankDiff(target);
        return moveStrategy.apply(fileDiff, rankDiff);
    }
}
