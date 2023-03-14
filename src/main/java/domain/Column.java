package domain;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public enum Column {
    A(0, "a"),
    B(1, "b"),
    C(2, "c"),
    D(3, "d"),
    E(4, "e"),
    F(5, "f"),
    G(6, "g"),
    H(7, "h");

    private final int index;
    private final String value;

    Column(int index, String value) {
        this.index = index;
        this.value = value;
    }

    public static List<Column> getOrderedColumns() {
        return Arrays.stream(Column.values())
                .sorted(Comparator.comparingInt(column -> column.index))
                .collect(Collectors.toList());
    }

    public static void validateValue(String value) {
        boolean isValidRank = Arrays.stream(Column.values())
                .anyMatch(column -> column.value.equals(value));

        if (!isValidRank) {
            throw new IllegalArgumentException("존재하지 않는 Column입니다");
        }
    }
}
