package chess.domain;

import java.util.Arrays;

public enum XAxis {
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

    XAxis(String name, int number) {
        this.name = name;
        this.number = number;
    }

    public int getNumber() {
        return number;
    }

    public static XAxis of(String fileValue) {
        return Arrays.stream(XAxis.values())
                .filter(x -> x.name.equals(fileValue))
                .findAny()
                .orElseThrow(IllegalArgumentException::new);
    }

    public static XAxis of(int number) {
        return Arrays.stream(XAxis.values())
                .filter(x -> x.number == number)
                .findAny()
                .orElseThrow(IllegalArgumentException::new);
    }
}
