package chess.domain.square;

import chess.domain.piece.Direction;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public final class Square {

    private static final int TOTAL_SQUARE_COUNT = 8 * 8;

    private static final Map<String, Square> cache = new HashMap<>(TOTAL_SQUARE_COUNT);
    private final File file;
    private final Rank rank;

    private Square(File file, Rank rank) {
        this.file = file;
        this.rank = rank;
    }

    public static Square of(File file, Rank rank) {
        // TODO: 나중에 key 방식 변경해야함
        final String cardKey = String.valueOf(List.of(file.getValue(), rank.getValue()));
        if (cache.containsKey(cardKey)) {
            return cache.get(cardKey);
        }
        Square square = new Square(file, rank);
        cache.put(cardKey, square);
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
        File newFile = file.move(direction.getFileDifference());
        Rank newRank = rank.move(direction.getRankDifference());
        return Square.of(newFile, newRank);
    }

    public boolean isRankTwo() {
        return false;
    }

    public boolean isRankSeven() {
        return false;
    }
}
