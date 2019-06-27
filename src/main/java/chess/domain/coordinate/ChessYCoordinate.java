package chess.domain.coordinate;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public enum ChessYCoordinate {
    RANK_1("1", 7),
    RANK_2("2", 6),
    RANK_3("3", 5),
    RANK_4("4", 4),
    RANK_5("5", 3),
    RANK_6("6", 2),
    RANK_7("7", 1),
    RANK_8("8", 0);

    private String symbol;
    private int index;

    ChessYCoordinate(String symbol, int index) {
        this.symbol = symbol;
        this.index = index;
    }

    public Optional<ChessYCoordinate> move(int n) {
        try {
            return Optional.of(valueOf(index - n));
        } catch (IllegalArgumentException e) {
            return Optional.empty();
        }
    }

    public static ChessYCoordinate valueOf(int index) {
        return Arrays.stream(values())
                .filter(coord -> coord.index == index)
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("지원하지 않는 죄표입니다."));
    }

    public static Optional<ChessYCoordinate> of(String symbol) {
        return Arrays.stream(values())
                .filter(coord -> coord.symbol.equals(symbol))
                .findFirst();
    }

    public String getSymbol() {
        return symbol;
    }

    public int getIndex() {
        return index;
    }

    public static List<ChessYCoordinate> getAscendingCoordinates(ChessYCoordinate from) {
        return Arrays.stream(values())
                .filter(coord -> coord.index > from.index)
                .sorted(Comparator.comparingInt(c -> c.index))
                .collect(Collectors.toList());
    }

    public static List<ChessYCoordinate> allAscendingCoordinates() {
        return Arrays.stream(values())
                .sorted(Comparator.comparingInt(ChessYCoordinate::getIndex))
                .collect(Collectors.toList());
    }

    public static List<ChessYCoordinate> getDescendingCoordinates(ChessYCoordinate from) {
        return Arrays.stream(values())
                .filter(coord -> coord.index < from.index)
                .sorted(Comparator.comparingInt(ChessYCoordinate::getIndex).reversed())
                .collect(Collectors.toList());
    }
}
