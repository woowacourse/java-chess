package model.position;

import constant.ErrorCode;
import exception.InvalidPositionException;
import java.util.Arrays;

public enum Rank {

    ONE("1", 7),
    TWO("2", 6),
    THREE("3", 5),
    FOUR("4", 4),
    FIVE("5", 3),
    SIX("6", 2),
    SEVEN("7", 1),
    EIGHT("8", 0);

    private final String value;
    private final int index;

    Rank(final String value, final int index) {
        this.value = value;
        this.index = index;
    }

    public static Rank from(final char input) {
        return Arrays.stream(values())
                .filter(rank -> rank.value.equals(String.valueOf(input)))
                .findFirst()
                .orElseThrow(() -> new InvalidPositionException(ErrorCode.INVALID_POSITION));
    }

    public static Rank from(final int targetIndex) {
        return Arrays.stream(values())
                .filter(rank -> rank.index == targetIndex)
                .findFirst()
                .orElseThrow(() -> new IndexOutOfBoundsException("인덱스 범위 초과"));
    }

    public int getIndex() {
        return index;
    }

    public String getValue() {
        return value;
    }
}
