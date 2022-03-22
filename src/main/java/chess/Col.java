package chess;

import java.util.*;
import java.util.stream.Collectors;

public enum Col {

    ONE(1),
    TWO(2),
    THREE(3),
    FOUR(4),
    FIVE(5),
    SIX(6),
    SEVEN(7),
    EIGHT(8);

    private final int value;

    Col(int value) {
        this.value = value;
    }

    public static List<Col> orderedValues() {
        return Arrays.stream(values())
            .sorted(Comparator.<Col>comparingInt(col -> col.value).reversed())
            .collect(Collectors.toList());
    }
}
