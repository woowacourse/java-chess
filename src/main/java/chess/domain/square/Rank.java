package chess.domain.square;

import java.util.Arrays;
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

    static final String ERROR_NOT_NUMERIC = "랭크는 숫자여야 합니다.";
    static final String ERROR_NOT_EXIST_RANK = "존재하지 않는 랭크입니다.";
    private static final Pattern NUMBER_PATTERN = Pattern.compile("-?\\d+");

    private final int rank;

    Rank(int rank) {
        this.rank = rank;
    }

    public static Rank from(String input) {
        validateNumeric(input);
        int parsedRank = Integer.parseInt(input);
        return Arrays.stream(values())
                .filter(rank -> rank.rank == parsedRank)
                .findAny()
                .orElseThrow(() -> new IllegalArgumentException(ERROR_NOT_EXIST_RANK));
    }

    private static void validateNumeric(String rank) {
        if (!NUMBER_PATTERN.matcher(rank).matches()) {
            throw new IllegalArgumentException(ERROR_NOT_NUMERIC);
        }
    }

    public Rank add(int value) {
        return Arrays.stream(values())
                .filter(rank -> rank.rank == this.rank + value)
                .findAny()
                .orElseThrow(() -> new IllegalStateException("[" + (this.rank + value) + "] (은)는" + ERROR_NOT_EXIST_RANK));
    }

    public int get() {
        return rank;
    }

    public int diff(Rank other) {
        return rank - other.rank;
    }
}
