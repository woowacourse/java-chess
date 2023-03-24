package chess.domain.piece;

import chess.domain.board.File;
import chess.domain.board.Rank;

import java.util.function.BiFunction;

public enum DirectionVector {

    NORTH(0, 1, (distanceFile, distanceRank) -> distanceFile == 0 && distanceRank > 0),
    SOUTH(0, -1, (distanceFile, distanceRank) -> distanceFile == 0 && distanceRank < 0),
    WEST(-1, 0, (distanceFile, distanceRank) -> distanceFile < 0 && distanceRank == 0),
    EAST(1, 0, (distanceFile, distanceRank) -> distanceFile > 0 && distanceRank == 0),
    SOUTHEAST(1, -1, (distanceFile, distanceRank) -> distanceFile > 0 && distanceRank < 0 && Math.abs(distanceFile) == Math.abs(distanceRank)),
    SOUTHWEST(-1, -1, (distanceFile, distanceRank) -> distanceFile < 0 && distanceRank < 0 && Math.abs(distanceFile) == Math.abs(distanceRank)),
    NORTHEAST(1, 1, (distanceFile, distanceRank) -> distanceFile > 0 && distanceRank > 0 && Math.abs(distanceFile) == Math.abs(distanceRank)),
    NORTHWEST(-1, 1, (distanceFile, distanceRank) -> distanceFile < 0 && distanceRank > 0 && Math.abs(distanceFile) == Math.abs(distanceRank));

    private final int unitFile;
    private final int unitRank;
    private final BiFunction<Integer, Integer, Boolean> isCorrectMovingDirection;

    DirectionVector(final int unitFile, final int unitRank, final BiFunction<Integer, Integer, Boolean> isCorrectMovingDirection) {
        this.unitFile = unitFile;
        this.unitRank = unitRank;
        this.isCorrectMovingDirection = isCorrectMovingDirection;
    }

    public boolean isOnMyWay(final int distanceFile, final int distanceRank) {
        return isCorrectMovingDirection.apply(distanceFile, distanceRank);
    }

    public File next(final File file) {
        if (unitFile == 1) {
            return file.next();
        }
        if (unitFile == -1) {
            return file.prev();
        }
        return file;
    }

    public Rank next(final Rank rank) {
        if (unitRank == 1) {
            return rank.next();
        }
        if (unitRank == -1) {
            return rank.prev();
        }
        return rank;
    }
}
