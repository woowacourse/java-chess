package chess.domain;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public enum ChessXCoordinate {
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

    ChessXCoordinate(String symbol, int index) {
        this.symbol = symbol;
        this.index = index;
    }

    public Optional<ChessXCoordinate> move(int n) {
        return valueOf(index + n);
    }

    public static Optional<ChessXCoordinate> of(String symbol) {
       return Arrays.stream(values())
               .filter( coord -> coord.symbol.equals(symbol))
               .findFirst();
    }

    private static Optional<ChessXCoordinate> valueOf(int index) {
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

    public static List<ChessXCoordinate> getAscendingCoordinates(ChessXCoordinate from) {
        return Arrays.stream(values())
                .filter(coord -> coord.index > from.index)
                .sorted(Comparator.comparingInt(c -> c.index))
                .collect(Collectors.toList());
    }

    public static List<ChessXCoordinate> getDescendingCoordinates(ChessXCoordinate from) {
        return Arrays.stream(values())
                .filter(coord -> coord.index < from.index)
                .sorted(Comparator.comparingInt(ChessXCoordinate::getIndex).reversed())
                .collect(Collectors.toList());
    }
}
