package domain;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public enum Rank {
    EIGHT(0, "8"),
    SEVEN(1, "7"),
    SIX(2, "6"),
    FIVE(3, "5"),
    FOUR(4, "4"),
    THREE(5, "3"),
    TWO(6, "2"),
    ONE(7, "1");

    private final int index;
    private final String value;

    Rank(int index, String value) {
        this.index = index;
        this.value = value;
    }

    public static List<Rank> getOrderedRanks() {
        return Arrays.stream(Rank.values())
                .sorted(Comparator.comparingInt(rank -> rank.index))
                .collect(Collectors.toList());
    }

    public static void validateValue(String value) {
        boolean isValidRank = Arrays.stream(Rank.values())
                .anyMatch(rank -> rank.value.equals(value));

        if (!isValidRank) {
            throw new IllegalArgumentException("존재하지 않는 Rank입니다");
        }
    }
}
