package chess.domain;

import java.util.Arrays;

public enum Abscissa {
    a(1),
    b(2),
    c(3),
    d(4),
    e(5),
    f(6),
    g(7),
    h(8);

    private final int coordinate;

    Abscissa(int coordinate) {
        this.coordinate = coordinate;
    }

    public int getCoordinate() {
        return coordinate;
    }

    public static Abscissa findAbscissa(int value) {
        return Arrays.stream(Abscissa.values())
            .filter(abscissa -> abscissa.coordinate == value)
            .findAny()
            .orElseThrow();
    }
}
