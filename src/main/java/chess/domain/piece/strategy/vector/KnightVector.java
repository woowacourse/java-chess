package chess.domain.piece.strategy.vector;

import java.util.Arrays;

public enum KnightVector {

    TWO_NORTH_ONE_WEST(-1, 2),
    TWO_NORTH_ONE_EAST(1, 2),
    TWO_EAST_ONE_NORTH(2, 1),
    TWO_EAST_ONE_SOUTH(2, -1),
    TWO_SOUTH_ONE_EAST(1, -2),
    TWO_SOUTH_ONE_WEST(-1, -2),
    TWO_WEST_ONE_NORTH(-2, 1),
    TWO_WEST_ONE_SOUTH(-2, -1),
    ;

    private final int unitFile;
    private final int unitRank;

    KnightVector(final int unitFile, final int unitRank) {
        this.unitFile = unitFile;
        this.unitRank = unitRank;
    }

    public static boolean isExistMovableVector(final int distanceFile, final int distanceRank) {
        return Arrays.stream(KnightVector.values())
                .anyMatch(
                        knightVector -> knightVector.unitFile == distanceFile && knightVector.unitRank == distanceRank);
    }
}
