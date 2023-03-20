package chess.domain.square;

import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;

public final class Square {

    private static final int TOTAL_SQUARE_COUNT = 8 * 8;

    private static final Map<String, Square> cache = new ConcurrentHashMap<>(TOTAL_SQUARE_COUNT);
    private final File file;
    private final Rank rank;

    private Square(final File file, final Rank rank) {
        this.file = file;
        this.rank = rank;
    }

    public static Square of(final File file, final Rank rank) {
        final String key = String.valueOf(List.of(file.getValue(), rank.getValue()));
        return cache.computeIfAbsent(key, ignored -> new Square(file, rank));
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
            // TODO: 리팩터링 필요
            throw new IllegalArgumentException("잘못된 좌표값입니다.");
        }
    }

    public boolean isRankTwo() {
        return rank == Rank.TWO;
    }

    public boolean isRankSeven() {
        return rank == Rank.SEVEN;
    }

    public int getFileDifference(final Square other) {
        return file.getDifference(other.file);
    }

    public int getRankDifference(final Square other) {
        return rank.getDifference(other.rank);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Square square = (Square) o;
        return file == square.file && rank == square.rank;
    }

    @Override
    public int hashCode() {
        return Objects.hash(file, rank);
    }

    @Override
    public String toString() {
        return "Square{" +
                "file=" + file +
                ", rank=" + rank +
                '}';
    }
}
