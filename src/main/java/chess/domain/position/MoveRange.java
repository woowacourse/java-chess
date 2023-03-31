package chess.domain.position;

import java.util.function.BiFunction;

public enum MoveRange {

    CROSS((diffFile, diffRank) -> diffRank * diffFile == 0),
    DIAGONAL((diffFile, diffRank) -> Math.abs(diffRank) == Math.abs(diffFile)),
    ONE_CIRCLE((diffFile, diffRank) -> Math.abs(diffRank) <= 1 && Math.abs(diffFile) <= 1),
    L_SHAPED((diffFile, diffRank) -> Math.abs(diffRank * diffFile) == 2),
    ONE_DIAGONAL_UP((diffFile, diffRank) -> Math.abs(diffFile) == 1 && diffRank == 1),
    ONE_DIAGONAL_DOWN((diffFile, diffRank) -> Math.abs(diffFile) == 1 && diffRank == -1),
    ONE_UP((diffFile, diffRank) -> diffFile == 0 && diffRank == 1),
    ONE_DOWN((diffFile, diffRank) -> diffFile == 0 && diffRank == -1),
    TWO_UP((diffFile, diffRank) -> diffFile == 0 && diffRank == 2),
    TWO_DOWN((diffFile, diffRank) -> diffFile == 0 && diffRank == -2);

    private final BiFunction<Integer, Integer, Boolean> isProperDirection;

    MoveRange(BiFunction<Integer, Integer, Boolean> isProperDirection) {
        this.isProperDirection = isProperDirection;
    }

    public boolean validate(Position source, Position destination) {
        int diffFile = destination.calculateFileDistance(source);
        int diffRank = destination.calculateRankDistance(source);
        return isProperDirection.apply(diffFile, diffRank);
    }
}
