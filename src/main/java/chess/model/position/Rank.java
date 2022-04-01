package chess.model.position;

import java.util.Arrays;
import java.util.List;

public enum Rank {
    EIGHT(8, "8"),
    SEVEN(7, "7"),
    SIX(6, "6"),
    FIVE(5, "5"),
    FOUR(4, "4"),
    THREE(3, "3"),
    TWO(2, "2"),
    ONE(1, "1");

    private final int index;
    private final String value;

    Rank(int index, String value) {
        this.index = index;
        this.value = value;
    }

    public static Rank indexOf(int index) {
        return Arrays.stream(Rank.values())
                .filter(rank -> rank.index == index)
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 값입니다 "));
    }

    public static Rank of(String value) {
        return Arrays.stream(Rank.values())
                .filter(rank -> rank.value.equals(value))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 값입니다."));
    }

    public static List<String> getEmptyRank() {
        return List.of("6", "5", "4", "3");
    }

    public int absMinus(Rank rank) {
        return Math.abs(index - rank.index);
    }

    public int minus(Rank rank) {
        return this.index - rank.index;
    }

    public boolean isLessThan(Rank rank) {
        return this.index < rank.index;
    }

    public Rank getNext(int distance) {
        return Rank.indexOf(index + distance);
    }

    public String getValue() {
        return value;
    }
}
