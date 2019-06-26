package chess.domain;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public enum CoordinateX {
    A("a", 0),
    B("b", 1),
    C("c", 2),
    D("d", 3),
    E("e", 4),
    F("f", 5),
    G("g", 6),
    H("h", 7);

    private String symbol;
    private int index;

    CoordinateX(String symbol, int index) {
        this.symbol = symbol;
        this.index = index;
    }

    public Optional<CoordinateX> move(int n) {
        return valueOf(index + n);
    }

    public static Optional<CoordinateX> valueOf(int index) {
        return Arrays.stream(values())
            .filter(coord -> coord.index == index)
            .findFirst();
    }

    public int getIndex() {
        return index;
    }

    public String getSymbol() {
        return symbol;
    }

    public static List<CoordinateX> allAscendingCoordinates() {
        return Arrays.stream(values())
            .sorted(Comparator.comparingInt(CoordinateX::getIndex))
            .collect(Collectors.toList());
    }

    @Override
    public String toString() {
        return String.format("CoordinateX { symbol: '%s', index: %d }", symbol, index);
    }
}
