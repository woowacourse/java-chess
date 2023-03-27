package chess.domain;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import static chess.view.ErrorMessage.NO_COLUMN_ERROR_GUIDE_MESSAGE;

public enum Column {
    A(1, "a"),
    B(2, "b"),
    C(3, "c"),
    D(4, "d"),
    E(5, "e"),
    F(6, "f"),
    G(7, "g"),
    H(8, "h");

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

    public static Column findColumnByValue(String value) {
        return Arrays.stream(Column.values())
                .filter(column -> column.value.equals(value))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException(NO_COLUMN_ERROR_GUIDE_MESSAGE.getErrorMessage()));
    }


    public static Column findColumnByIndex(int index) {
        return Arrays.stream(values())
                .filter(column -> column.index == index)
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException(NO_COLUMN_ERROR_GUIDE_MESSAGE.getErrorMessage()));

    }

    public int getIndex() {
        return index;
    }

    public boolean isSameColumn(Column column){
        return this.equals(column);
    }
}
