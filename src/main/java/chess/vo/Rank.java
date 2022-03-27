package chess.vo;

import static java.util.stream.Collectors.*;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public enum Rank {

    ONE(1),
    TWO(2),
    THREE(3),
    FOUR(4),
    FIVE(5),
    SIX(6),
    SEVEN(7),
    EIGHT(8);

    static final String ERROR_NOT_EXIST_RANK = "[ERROR] 존재 하지 않는 랭크입니다.";

    private final int value;

    Rank(int value) {
        this.value = value;
    }

    public static Rank of(String input) {
        return Arrays.stream(values())
            .filter(rank -> rank.value == Integer.parseInt(input))
            .findAny()
            .orElseThrow(() -> new IllegalArgumentException(ERROR_NOT_EXIST_RANK));
    }

    public static List<Rank> reverseValues() {
        return Arrays.stream(values())
            .sorted(Comparator.reverseOrder())
            .collect(toUnmodifiableList());
    }

    public int displacement(Rank other) {
        return other.value - this.value;
    }

    public boolean isBetween(Rank source, Rank target) {
        if (source.isBiggerThan(target)) {
            return this.isBiggerThan(target) && source.isBiggerThan(this) || source == target;
        }
        return this.isBiggerThan(source) && target.isBiggerThan(this) || source == target;
    }

    private boolean isBiggerThan(Rank other) {
        return this.compareTo(other) > 0;
    }
}
