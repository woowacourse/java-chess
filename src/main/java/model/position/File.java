package model.position;

import constant.ErrorCode;
import exception.InvalidPositionException;
import java.util.Arrays;

public enum File {

    A("a", 0),
    B("b", 1),
    C("c", 2),
    D("d", 3),
    E("e", 4),
    F("f", 5),
    G("g", 6),
    H("h", 7);

    private final String value;
    private final int index;

    File(final String value, final int index) {
        this.value = value;
        this.index = index;
    }

    public static File from(final char input) {
        return Arrays.stream(values())
                .filter(file -> file.value.equals(String.valueOf(input)))
                .findFirst()
                .orElseThrow(() -> new InvalidPositionException(ErrorCode.INVALID_POSITION));
    }

    public static File from(final int targetIndex) {
        return Arrays.stream(values())
                .filter(file -> file.index == targetIndex)
                .findFirst()
                .orElseThrow(() -> new IndexOutOfBoundsException("인덱스 범위 초과"));
    }

    public boolean isLast() {
        return this == H;
    }

    public String getValue() {
        return value;
    }

    public int getIndex() {
        return index;
    }
}
