package chess.domain.piece;

import java.util.List;

public enum PawnVector {

    NORTH(0, 1),
    TWO_NORTH(0, 2),
    NORTH_WEST(-1, 1),
    NORTH_EAST(1, 1),
    SOUTH(0, -1),
    TWO_SOUTH(0, -2),
    SOUTH_WEST(-1, -1),
    SOUTH_EAST(1, -1);

    private final int unitFile;
    private final int unitRank;

    PawnVector(final int unitFile, final int unitRank) {
        this.unitFile = unitFile;
        this.unitRank = unitRank;
    }

    public static List<PawnVector> getVectorsByColor(final Color color) {
        if (color.isBlack()) {
            return List.of(SOUTH, TWO_SOUTH, SOUTH_WEST, SOUTH_EAST);
        }
        return List.of(NORTH, TWO_NORTH, NORTH_WEST, NORTH_EAST);
    }

    public boolean isMovable(final int distanceFile, final int distanceRank) {
        return unitFile == distanceFile && unitRank == distanceRank;
    }
}
