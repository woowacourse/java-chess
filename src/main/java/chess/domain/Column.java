package chess.domain;

import java.util.Arrays;
import java.util.NoSuchElementException;

public enum Column {
    A("A"),
    B("B"),
    C("C"),
    D("D"),
    E("E"),
    F("F"),
    G("G"),
    H("H"),
    ;

    private static final String INVALID_RANGE = "유효하지 않은 범위입니다.";

    private final String value;

    Column(String value) {
        this.value = value;
    }

    public static Column of(String value){
        return Arrays.stream(values())
            .filter(column -> column.value.equalsIgnoreCase(value))
            .findAny()
            .orElseThrow(() -> new NoSuchElementException(INVALID_RANGE));
    }
}
