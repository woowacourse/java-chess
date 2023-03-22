package chess.domain.piece;

import chess.domain.game.Position;

import java.util.function.BiFunction;

public enum MoveStrategy {

    ROOK((fileDiff, rankDiff) -> fileDiff == 0 || rankDiff == 0),
    BISHOP((fileDiff, rankDiff) -> Math.abs(fileDiff) == Math.abs(rankDiff)),
    KNIGHT((fileDiff, rankDiff) -> Math.abs(fileDiff * rankDiff) == 2),
    KING((fileDiff, rankDiff) -> Math.abs(fileDiff) <= 1 && Math.abs(rankDiff) <= 1),
    QUEEN((fileDiff, rankDiff) -> ROOK.moveStrategy.apply(fileDiff, rankDiff)
            || BISHOP.moveStrategy.apply(fileDiff, rankDiff)),
    BLACK_PAWN_FIRST((fileDiff, rankDiff) -> fileDiff == 0 && rankDiff == -2),
    BLACK_PAWN_NORMAL((fileDiff, rankDiff) -> fileDiff == 0 && rankDiff == -1),
    BLACK_PAWN_ENEMY((fileDiff, rankDiff) -> Math.abs(fileDiff) == 1 && rankDiff == -1),
    WHITE_PAWN_FIRST((fileDiff, rankDiff) -> fileDiff == 0 && rankDiff == 2),
    WHITE_PAWN_NORMAL((fileDiff, rankDiff) -> fileDiff == 0 && rankDiff == 1),
    WHITE_PAWN_ENEMY((fileDiff, rankDiff) -> Math.abs(fileDiff) == 1 && rankDiff == 1);

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
