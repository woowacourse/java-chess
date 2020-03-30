package chess.domain;

import java.util.Arrays;

public enum XPosition {
    A("a", 1),
    B("b", 2),
    C("c", 3),
    D("d", 4),
    E("e", 5),
    F("f", 6),
    G("g", 7),
    H("h", 8);

    private final String name;
    private final int number;

    XPosition(String name, int number) {
        this.name = name;
        this.number = number;
    }

    public int getNumber() {
        return number;
    }

    public static XPosition of(String fileValue) {
        return Arrays.stream(XPosition.values())
                .filter(x -> x.name.equals(fileValue))
                .findAny()
                .orElseThrow(IllegalArgumentException::new);
    }

    public static XPosition of(int number) {
        return Arrays.stream(XPosition.values())
                .filter(x -> x.number == number)
                .findAny()
                .orElseThrow(IllegalArgumentException::new);
    }
}
