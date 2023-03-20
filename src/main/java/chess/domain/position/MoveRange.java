package chess.domain.position;

import java.util.function.BiFunction;

public enum MoveRange {

    CROSS((diffRank, diffFile) -> diffFile * diffRank == 0),
    DIAGONAL((diffRank, diffFile) -> Math.abs(diffFile) == Math.abs(diffRank)),
    ONE_CIRCLE((diffRank, diffFile) -> Math.abs(diffFile) <= 1 && Math.abs(diffRank) <= 1),
    L_SHAPED((diffRank, diffFile) -> Math.abs(diffFile * diffRank) == 2),
    ONE_DIAGONAL_UP((diffRank, diffFile) -> Math.abs(diffRank) == 1 && diffFile == 1),
    ONE_DIAGONAL_DOWN((diffRank, diffFile) -> Math.abs(diffRank) == 1 && diffFile == -1),
    ONE_UP((diffRank, diffFile) -> diffRank == 0 && diffFile == 1),
    ONE_DOWN((diffRank, diffFile) -> diffRank == 0 && diffFile == -1),
    TWO_UP((diffRank, diffFile) -> diffRank == 0 && diffFile == 2),
    TWO_DOWN((diffRank, diffFile) -> diffRank == 0 && diffFile == -2);

    private final BiFunction<Integer, Integer, Boolean> isProperDirection;

    MoveRange(BiFunction<Integer, Integer, Boolean> isProperDirection) {
        this.isProperDirection = isProperDirection;
    }

    public boolean validate(Position source, Position destination) {
        int diffRank = destination.calculateRankDistance(source);
        int diffFile = destination.calculateFileDistance(source);
        return isProperDirection.apply(diffRank, diffFile);
    }
}
