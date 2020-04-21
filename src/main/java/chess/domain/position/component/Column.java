package chess.domain.position.component;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public enum Column {
    ONE(1),
    TWO(2),
    THREE(3),
    FOUR(4),
    FIVE(5),
    SIX(6),
    SEVEN(7),
    EIGHT(8);

    private final int value;

    Column(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public static int getDiff(Column column1, Column column2) {
        Objects.requireNonNull(column1);
        Objects.requireNonNull(column2);
        return column1.getValue() - column2.getValue();
    }

    public static Column getSmaller(Column column1, Column column2) {
        if (column1.compareTo(column2) < 0) {
            return column1;
        }
        return column2;
    }

    public static Column getBigger(Column column1, Column column2) {
        if (column1.compareTo(column2) > 0) {
            return column1;
        }
        return column2;
    }

    public List<Column> between(Column biggerColumn) {
        return Arrays.asList(values())
                .subList(ordinal() + 1, biggerColumn.ordinal());
    }
}
