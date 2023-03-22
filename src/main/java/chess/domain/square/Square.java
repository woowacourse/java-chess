package chess.domain.square;

import chess.domain.exception.SquareOutOfBoundException;

import java.util.HashMap;
import java.util.Map;

public final class Square {

    private static final int TOTAL_SQUARE_COUNT = 8 * 8;
    private static final Map<SquareKey, Square> cache = new HashMap<>(TOTAL_SQUARE_COUNT);

    private final File file;
    private final Rank rank;

    private Square(final File file, final Rank rank) {
        this.file = file;
        this.rank = rank;
    }

    public static Square of(final File file, final Rank rank) {
        final SquareKey key = new SquareKey(file, rank);
        if (cache.containsKey(key)) {
            return cache.get(key);
        }
        final Square square = new Square(file, rank);
        cache.put(key, square);
        return square;
    }

    public boolean isSameFile(final Square other) {
        return file == other.file;
    }

    public boolean isSameRank(final Square other) {
        return rank == other.rank;
    }

    public boolean isRankBiggerThan(final Square other) {
        return rank.isBiggerThan(other.rank);
    }

    public boolean isRankLowerThan(final Square other) {
        return rank.isLowerThan(other.rank);
    }

    public boolean isRankDifferenceOne(final Square other) {
        return rank.isDifferenceOne(other.rank);
    }

    public Square move(final Direction direction) {
        return move(direction.getFileDirection(), direction.getRankDirection());
    }

    public Square move(final int fileDifference, final int rankDifference) {
        try {
            File newFile = file.move(fileDifference);
            Rank newRank = rank.move(rankDifference);
            return Square.of(newFile, newRank);
        } catch (IllegalStateException e) {
            throw new SquareOutOfBoundException();
        }
    }

    public boolean isRankTwo() {
        return rank.equals(Rank.TWO);
    }

    public boolean isRankSeven() {
        return rank.equals(Rank.SEVEN);
    }

    @Override
    public String toString() {
        return "Square{" +
                "file=" + file +
                ", rank=" + rank +
                '}';
    }

    public int getFileDifference(final Square other) {
        return file.getDifference(other.file);
    }

    public int getRankDifference(final Square other) {
        return rank.getDifference(other.rank);
    }
}
