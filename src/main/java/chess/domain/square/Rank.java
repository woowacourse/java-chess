package chess.domain.square;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Pattern;

public enum Rank {

    ONE(1),
    TWO(2),
    THREE(3),
    FOUR(4),
    FIVE(5),
    SIX(6),
    SEVEN(7),
    EIGHT(8),
    ;

    private static final String ERROR_NOT_NUMERIC = "랭크는 숫자여야 합니다.";
    private static final String ERROR_NOT_EXIST_RANK = " 은(는) 존재하지 않는 랭크입니다.";
    private static final Pattern NUMBER_PATTERN = Pattern.compile("-?\\d+");

    private final int index;

    Rank(int index) {
        this.index = index;
    }

    public static Rank findByValue(String value) {
        validateNumeric(value);
        int parsedRank = Integer.parseInt(value);
        return Arrays.stream(values())
                .filter(rank -> rank.index == parsedRank)
                .findAny()
                .orElseThrow(() -> new IllegalArgumentException(value + ERROR_NOT_EXIST_RANK));
    }

    private static void validateNumeric(String rank) {
        if (!NUMBER_PATTERN.matcher(rank).matches()) {
            throw new IllegalArgumentException(ERROR_NOT_NUMERIC);
        }
    }

    public boolean isSameRank(Rank other) {
        return index == other.index;
    }

    public int calculateDistance(Rank other) {
        return Math.abs(index - other.index);
    }

    public int calculateDirection(Rank other) {
        return (int) Math.signum(index - other.index);
    }

    public List<Rank> findRankPath(final Rank other) {
        int start = Math.min(index, other.index) + 1;
        int end = Math.max(index, other.index);

        List<Rank> rankPath = new ArrayList<>();
        for (int i = start; i < end; i++) {
            rankPath.add(valueOfIndex(i));
        }

        return rankPath;
    }

    private Rank valueOfIndex(final int value) {
        return Arrays.stream(values())
                .filter(rank -> rank.index == value)
                .findAny()
                .orElseThrow(() -> new IllegalArgumentException(value + ERROR_NOT_EXIST_RANK));
    }

    public int getIndex() {
        return index;
    }
}
