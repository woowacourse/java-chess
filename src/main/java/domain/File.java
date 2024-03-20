package domain;

import java.util.Arrays;

public enum File {

    EIGHTH(7),
    SEVENTH(6),
    SIXTH(5),
    FIFTH(4),
    FORTH(3),
    THIRD(2),
    SECOND(1),
    FIRST(0),
    ;

    private final int order;

    File(int order) {
        this.order = order;
    }

    public static int max() {
        return Arrays.stream(values())
                .mapToInt(it -> it.order)
                .max()
                .orElseThrow();
    }

    public static File find(int order) {
        return Arrays.stream(values())
                .filter(it -> it.order == order)
                .findFirst()
                .orElseThrow();
    }
}
