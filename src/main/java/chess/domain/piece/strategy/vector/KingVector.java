package chess.domain.piece.strategy.vector;

import java.util.Arrays;

public enum KingVector {

    NORTH(0, 1),
    SOUTH(0, -1),
    WEST(-1, 0),
    EAST(1, 0),
    SOUTHEAST(1, -1),
    SOUTHWEST(-1, -1),
    NORTHEAST(1, 1),
    NORTHWEST(-1, 1),
    ;

    private final int unitFile;
    private final int unitRank;

    KingVector(final int unitFile, final int unitRank) {
        this.unitFile = unitFile;
        this.unitRank = unitRank;
    }

    public static boolean isExistMovableVector(final int distanceFile, final int distanceRank) {
        return Arrays.stream(KingVector.values())
                .anyMatch(kingVector -> kingVector.unitFile == distanceFile && kingVector.unitRank == distanceRank);
    }
}
