package view;

import java.util.Arrays;

public enum ColumnView {
    FIRST(0, 'a'),
    SECOND(1, 'b'),
    THIRD(2, 'c'),
    FOURTH(3, 'd'),
    FIFTH(4, 'e'),
    SIXTH(5, 'f'),
    SEVENTH(6, 'g'),
    EIGHTH(7, 'h'),
    ;

    private final int column;
    private final char sign;

    ColumnView(final int column, final char sign) {
        this.column = column;
        this.sign = sign;
    }

    public static int findColumn(final char sign) {
        final ColumnView columnView = Arrays.stream(ColumnView.values())
            .filter(view -> view.sign == sign)
            .findAny()
            .orElseThrow(IllegalAccessError::new);
        return columnView.column;
    }
}
