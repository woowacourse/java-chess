package chess.domain.piece.strategy.vector;

import chess.domain.board.File;
import chess.domain.board.Rank;
import java.util.function.BiFunction;

public enum DirectionVector {

    NORTH(0, 1, (file, rank) -> file == 0 && rank > 0),
    SOUTH(0, -1, (file, rank) -> file == 0 && rank < 0),
    WEST(-1, 0, (file, rank) -> file < 0 && rank == 0),
    EAST(1, 0, (file, rank) -> file > 0 && rank == 0),
    SOUTHEAST(1, -1, (file, rank) -> file > 0 && rank < 0 && Math.abs(file) == Math.abs(rank)),
    SOUTHWEST(-1, -1, (file, rank) -> file < 0 && rank < 0 && Math.abs(file) == Math.abs(rank)),
    NORTHEAST(1, 1, (file, rank) -> file > 0 && rank > 0 && Math.abs(file) == Math.abs(rank)),
    NORTHWEST(-1, 1, (file, rank) -> file < 0 && rank > 0 && Math.abs(file) == Math.abs(rank)),
    ;

    private final int unitFile;
    private final int unitRank;
    private final BiFunction<Integer, Integer, Boolean> way;

    DirectionVector(final int x, final int unitRank, final BiFunction<Integer, Integer, Boolean> way) {
        this.unitFile = x;
        this.unitRank = unitRank;
        this.way = way;
    }

    public boolean isOnMyWay(final int distanceFile, final int distanceRank) {
        return way.apply(distanceFile, distanceRank);
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
